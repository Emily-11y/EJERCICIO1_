package Modelo;

/**
 * Representa un personaje de tipo Guerrero.
 * Habilidad especial: "Golpe Devastador" — ataque masivo con cooldown de 3 turnos.
 * Costo energía: 30.
 */
public class Guerrero1 extends Personaje {

    private static final int BONUS_ATAQUE  = 5;
    private static final int BONUS_DEFENSA = 2;

    // Habilidad especial
    private static final int COSTO_ENERGIA_HABILIDAD  = 30;
    private static final int COOLDOWN_HABILIDAD_TURNOS = 3;

    private int fuerza;
    private int armadura;

    public Guerrero1(String nombre, int puntosVida, int nivelExperiencia,
                    int fuerza, int armadura) {
        super(nombre, puntosVida, nivelExperiencia);
        this.fuerza   = fuerza;
        this.armadura = armadura;
    }

    // ── Polimorfismo ──────────────────────────────────────────────────────────
    @Override
    public int atacar() {
        return fuerza + (nivelExperiencia * BONUS_ATAQUE);
    }

    @Override
    public int defender() {
        return armadura + (nivelExperiencia * BONUS_DEFENSA);
    }

    /**
     * Habilidad especial: Golpe Devastador.
     * Aplica el estado AumentarFuerza al propio Guerrero (buff 3 turnos).
     *
     * @throws SinEnergiaException si no hay energía suficiente.
     */
    @Override
    public String usarHabilidadEspecial() throws SinEnergiaException {
        if (!habilidadDisponible()) {
            return "[" + nombre + "] Golpe Devastador en cooldown ("
                    + getCooldownHabilidad() + " turnos restantes).";
        }
        consumirEnergia(COSTO_ENERGIA_HABILIDAD);
        setCooldownHabilidad(COOLDOWN_HABILIDAD_TURNOS);
        agregarEstado(new EstadoAumentarFuerza(20, 3));
        return "[" + nombre + "] usa ¡GOLPE DEVASTADOR! "
                + "Fuerza aumentada +20 por 3 turnos. (Energía restante: " + energia + ")";
    }

    // ── Getters / Setters ─────────────────────────────────────────────────────
    public int getFuerza()   { return fuerza; }
    public int getArmadura() { return armadura; }

    public void setFuerza(int fuerza)     { if (fuerza   > 0)  this.fuerza   = fuerza; }
    public void setArmadura(int armadura) { if (armadura >= 0) this.armadura = armadura; }

    @Override
    public String toString() {
        return "----- GUERRERO -----\n"
                + super.toString()
                + "\nFuerza: " + fuerza
                + "\nArmadura: " + armadura;
    }
}
