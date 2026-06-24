
package Modelo;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que representa un personaje del juego.
 * Versión expandida con soporte para:
 *   - Inventario y Equipamiento (Funcionalidad 1)
 *   - Estados Alterados / Buffs y Debuffs (Funcionalidad 2)
 *   - Sistema de Energía y Cooldowns (Funcionalidad 3)
 */
public abstract class Personaje {

    // ── Atributos base ────────────────────────────────────────────────────────
    protected String nombre;
    protected int    puntosVida;
    protected int    nivelExperiencia;

    // ── Funcionalidad 1 · Inventario ─────────────────────────────────────────
    private List<Objeto> inventario   = new ArrayList<>();
    private Arma         armaEquipada = null;
    private Armadura     armaduraEquipada = null;

    // ── Funcionalidad 2 · Estados Alterados ──────────────────────────────────
    private List<IEstadoAlterado> estados = new ArrayList<>();

    // ── Funcionalidad 3 · Energía y Cooldowns ────────────────────────────────
    protected int energia;
    protected int energiaMaxima;
    private   int cooldownHabilidad = 0;   // 0 = disponible

    // ─────────────────────────────────────────────────────────────────────────

    public Personaje(String nombre, int puntosVida, int nivelExperiencia,
                     int energiaMaxima) {
        this.nombre           = nombre;
        this.puntosVida       = puntosVida;
        this.nivelExperiencia = nivelExperiencia;
        this.energiaMaxima    = energiaMaxima;
        this.energia          = energiaMaxima;
    }

    // ── Métodos abstractos (polimorfismo) ─────────────────────────────────────
    public abstract int atacar();
    public abstract int defender();
    public abstract String usarHabilidadEspecial() throws SinEnergiaException;

    // ── Ataque con bonificación de arma ──────────────────────────────────────
    /**
     * Retorna el ataque base del personaje más el bonus del arma equipada.
     * El ControladorCombate llama a este método, no a atacar() directamente,
     * para que siempre se incluya el equipamiento.
     */
    public int calcularAtaque() {
        int bonus = (armaEquipada != null) ? armaEquipada.getBonusAtaque() : 0;
        return atacar() + bonus;
    }

    /**
     * Retorna la defensa base del personaje más el bonus de la armadura equipada.
     */
    public int calcularDefensa() {
        int bonus = (armaduraEquipada != null) ? armaduraEquipada.getBonusDefensa() : 0;
        return defender() + bonus;
    }

    // ── Funcionalidad 1 · Inventario ─────────────────────────────────────────
    public void agregarAlInventario(Objeto objeto) {
        inventario.add(objeto);
        System.out.println("  [Inventario] " + nombre + " obtuvo: " + objeto.getNombre());
    }

    public void equiparArma(Arma arma) {
        if (!inventario.contains(arma)) {
            agregarAlInventario(arma);
        }
        this.armaEquipada = arma;
        System.out.println("  [Equipo] " + nombre + " equipó arma: "
                + arma.getNombre() + " (+" + arma.getBonusAtaque() + " ATQ)");
    }

    public void equiparArmadura(Armadura armadura) {
        if (!inventario.contains(armadura)) {
            agregarAlInventario(armadura);
        }
        this.armaduraEquipada = armadura;
        System.out.println("  [Equipo] " + nombre + " equipó armadura: "
                + armadura.getNombre() + " (+" + armadura.getBonusDefensa() + " DEF)");
    }

    public Arma    getArmaEquipada()     { return armaEquipada; }
    public Armadura getArmaduraEquipada() { return armaduraEquipada; }
    public List<Objeto> getInventario()  { return inventario; }

    // ── Funcionalidad 2 · Estados Alterados ──────────────────────────────────
    public void agregarEstado(IEstadoAlterado estado) {
        estados.add(estado);
        System.out.println("  [Estado] " + nombre + " recibe estado: "
                + estado.getNombreEstado());
    }

    /**
     * Aplica todos los estados activos al inicio del turno y elimina los expirados.
     */
    public void procesarEstados() {
        List<IEstadoAlterado> expirados = new ArrayList<>();
        for (IEstadoAlterado e : estados) {
            e.aplicar(this);
            if (e.haExpirado()) {
                expirados.add(e);
                System.out.println("  [Estado] El estado '" + e.getNombreEstado()
                        + "' en " + nombre + " ha expirado.");
            }
        }
        estados.removeAll(expirados);
    }

    /**
     * Indica si el personaje está bloqueado (no puede atacar este turno).
     */
    public boolean estaBloqueado() {
        for (IEstadoAlterado e : estados) {
            if (e.bloqueaAtaque()) return true;
        }
        return false;
    }

    /**
     * Bonus de ataque acumulado de todos los estados activos (buffs).
     */
    public int getBonusAtaqueEstados() {
        int bonus = 0;
        for (IEstadoAlterado e : estados) {
            bonus += e.getBonusAtaque();
        }
        return bonus;
    }

    public List<IEstadoAlterado> getEstados() { return estados; }

    // ── Funcionalidad 3 · Energía y Cooldowns ────────────────────────────────
    public int getEnergia()        { return energia; }
    public int getEnergiaMaxima()  { return energiaMaxima; }

    public int getCooldownHabilidad() { return cooldownHabilidad; }

    public void setCooldownHabilidad(int turnos) {
        this.cooldownHabilidad = turnos;
    }

    public boolean habilidadDisponible() {
        return cooldownHabilidad == 0;
    }

    /** Reduce el cooldown en 1 al final de cada turno. */
    public void reducirCooldown() {
        if (cooldownHabilidad > 0) cooldownHabilidad--;
    }

    /** Regenera un porcentaje de energía por turno (10 %). */
    public void regenerarEnergia() {
        int regen = Math.max(1, energiaMaxima / 10);
        energia = Math.min(energiaMaxima, energia + regen);
    }

    protected void consumirEnergia(int cantidad) throws SinEnergiaException {
        if (energia < cantidad) {
            throw new SinEnergiaException(nombre + " no tiene suficiente energía. "
                    + "Necesita " + cantidad + " pero solo tiene " + energia + ".");
        }
        energia -= cantidad;
    }

    // ── Métodos base ─────────────────────────────────────────────────────────
    public void recibirDanio(int danio) {
        if (danio <= 0) return;
        puntosVida -= danio;
        if (puntosVida < 0) puntosVida = 0;
    }

    public void subirNivel() {
        nivelExperiencia++;
        puntosVida  += 20;
        energia      = energiaMaxima;  // recupera energía al subir nivel
    }

    public boolean estaVivo() { return puntosVida > 0; }

    // ── Getters básicos ───────────────────────────────────────────────────────
    public String getNombre()          { return nombre; }
    public int    getPuntosVida()      { return puntosVida; }
    public int    getNivelExperiencia(){ return nivelExperiencia; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre)
          .append("\nPuntos de vida: ").append(puntosVida)
          .append("\nNivel de experiencia: ").append(nivelExperiencia)
          .append("\nEnergía: ").append(energia).append("/").append(energiaMaxima);

        if (armaEquipada    != null) sb.append("\nArma equipada: ").append(armaEquipada.getNombre());
        if (armaduraEquipada != null) sb.append("\nArmadura equipada: ").append(armaduraEquipada.getNombre());
        if (!estados.isEmpty()) {
            sb.append("\nEstados activos: ");
            for (IEstadoAlterado e : estados) sb.append(e.getNombreEstado()).append(" ");
        }
        return sb.toString();
    }
}
