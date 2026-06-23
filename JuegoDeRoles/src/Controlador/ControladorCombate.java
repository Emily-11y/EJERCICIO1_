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
            System.out.println("\n══════════════ TURNO " + turno + " ══════════════");
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
        System.out.println("\n🏆 ¡" + ganador.getNombre() + " ha ganado el combate y sube de nivel!");
        return ganador;
    }
