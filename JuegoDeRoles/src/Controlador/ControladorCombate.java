package Controlador;

import Modelo.EstadoCongelado;
import Modelo.EstadoEnvenenado;
import Modelo.Personaje;
import Modelo.SinEnergiaException;

/**
 * Controlador de combate expandido.
 * Integra las tres nuevas funcionalidades:
 *   1. calcularAtaque() / calcularDefensa() para incluir el equipamiento.
 *   2. procesarEstados() al inicio de cada turno (veneno, congelación, buffs).
 *   3. Intento de habilidad especial antes del ataque normal (cooldown + energía).
 */
public class ControladorCombate {

    private static final int TURNOS_HABILIDAD = 3; // intentar habilidad cada N turnos

    /**
     * Realiza una batalla completa entre dos personajes.
     *
     * @return el personaje ganador.
     */
    public Personaje combatir(Personaje p1, Personaje p2) {
        int turno = 1;

        while (p1.estaVivo() && p2.estaVivo()) {
            System.out.println("\n============ TURNO " + turno + " ===============");
            mostrarEstado(p1, p2);

            // ── Turno de p1 ──────────────────────────────────────────────────
            ejecutarTurno(p1, p2, turno);
            if (!p2.estaVivo()) break;

            // ── Turno de p2 ──────────────────────────────────────────────────
            ejecutarTurno(p2, p1, turno);

            turno++;
        }

        // Determinar ganador
        Personaje ganador = p1.estaVivo() ? p1 : p2;
        ganador.subirNivel();
        System.out.println("\n " + ganador.getNombre() + " ha ganado el combate y sube de nivel!");
        return ganador;
    }

    /**
     * Ejecuta un turno completo de un personaje:
     *  1. Procesa estados alterados activos.
     *  2. Intenta usar habilidad especial (cada TURNOS_HABILIDAD turnos).
     *  3. Si no está bloqueado, realiza ataque normal + bonus de estados.
     *  4. Reduce cooldown al final.
     *  5. Regenera energía.
     */
    private void ejecutarTurno(Personaje atacante, Personaje defensor, int turno) {
        System.out.println("\n Turno de " + atacante.getNombre() + ":");

        // 1. Procesar estados alterados (veneno, congelación, etc.)
        atacante.procesarEstados();

        if (!atacante.estaVivo()) {
            System.out.println("  " + atacante.getNombre() + " murio por los efectos de estado.");
            return;
        }

        // 2. Intentar habilidad especial
        if (turno % TURNOS_HABILIDAD == 0) {
            intentarHabilidadEspecial(atacante, defensor);
        }

        // 3. Ataque normal (si no está bloqueado)
        if (atacante.estaBloqueado()) {
            System.out.println("  " + atacante.getNombre() + " no puede atacar este turno.");
        } else {
            realizarAtaque(atacante, defensor);
        }

        // 4. Reducir cooldown
        atacante.reducirCooldown();

        // 5. Regenerar energía
        atacante.regenerarEnergia();
    }

    /**
     * Intenta usar la habilidad especial del atacante.
     * Procesa la respuesta para aplicar efectos al rival si corresponde.
     */
    private void intentarHabilidadEspecial(Personaje atacante, Personaje defensor) {
        try {
            String resultado = atacante.usarHabilidadEspecial();

            if (resultado.startsWith("CONGELAR_RIVAL:")) {
                String msg = resultado.substring("CONGELAR_RIVAL:".length());
                System.out.println("  " + msg);
                defensor.agregarEstado(new EstadoCongelado(1));

            } else if (resultado.startsWith("ENVENENAR_RIVAL:")) {
                String msg = resultado.substring("ENVENENAR_RIVAL:".length());
                System.out.println("  " + msg);
                defensor.agregarEstado(new EstadoEnvenenado(8, 3));

            } else {
                System.out.println("  " + resultado);
            }

        } catch (SinEnergiaException e) {
            System.out.println("error " + e.getMessage());
        }
    }

    /**
     * Calcula y aplica el daño del atacante al defensor.
     * Usa calcularAtaque() y calcularDefensa() para incluir equipamiento y estados.
     */
    private void realizarAtaque(Personaje atacante, Personaje defensor) {
        int ataque  = atacante.calcularAtaque()  + atacante.getBonusAtaqueEstados();
        int defensa = defensor.calcularDefensa();
        int danio   = Math.max(0, ataque - defensa);

        defensor.recibirDanio(danio);
        System.out.printf("%s ataca con %d (ATQ:%d - DEF:%d) → %s pierde %d PV. Vida restante: %d%n",
                atacante.getNombre(), danio,
                ataque, defensa,
                defensor.getNombre(), danio,
                defensor.getPuntosVida());
    }

    /** Muestra el estado actual de ambos personajes. */
    private void mostrarEstado(Personaje p1, Personaje p2) {
        System.out.printf("  %-20s PV:%-5d E:%-4d | %-20s PV:%-5d E:%-4d%n",
                p1.getNombre(), p1.getPuntosVida(), p1.getEnergia(),
                p2.getNombre(), p2.getPuntosVida(), p2.getEnergia());
    }
}
