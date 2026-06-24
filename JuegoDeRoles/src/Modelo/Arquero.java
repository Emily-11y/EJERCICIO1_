
import Modelo.Personaje;


/**
 * Representa un personaje de tipo Arquero.
 * Habilidad especial: "Flecha Envenenada" — envenena al rival durante 3 turnos.
 * Costo energía: 25.  Cooldown: 2 turnos.
 */
public class Arquero extends Personaje {

    private static final int BONUS_ATAQUE  = 4;
    private static final int BONUS_DEFENSA = 4;

    private static final int COSTO_ENERGIA_HABILIDAD   = 25;
    private static final int COOLDOWN_HABILIDAD_TURNOS  = 2;

    private int precision;
    private int agilidad;

    public Arquero(String nombre, int puntosVida, int nivelExperiencia,
                   int precision, int agilidad) {
        super(nombre, puntosVida, nivelExperiencia);
        this.precision = precision;
        this.agilidad  = agilidad;
    }

    // ── Polimorfismo ──────────────────────────────────────────────────────────
    @Override
    public int atacar() {
        return precision + (nivelExperiencia * BONUS_ATAQUE);
    }

    @Override
    public int defender() {
        return agilidad + (nivelExperiencia * BONUS_DEFENSA);
    }

    /**
     * Habilidad especial: Flecha Envenenada.
     * El controlador detecta "ENVENENAR_RIVAL" y aplica el estado al oponente.
     *
     * @throws SinEnergiaException si no hay energía suficiente.
     */
    @Override
    public String usarHabilidadEspecial() throws SinEnergiaException {
        if (!habilidadDisponible()) {
            return "[" + nombre + "] Flecha Envenenada en cooldown ("
                    + getCooldownHabilidad() + " turnos restantes).";
        }
        consumirEnergia(COSTO_ENERGIA_HABILIDAD);
        setCooldownHabilidad(COOLDOWN_HABILIDAD_TURNOS);
        return "ENVENENAR_RIVAL:[" + nombre + "] dispara ¡FLECHA ENVENENADA! "
                + "El rival recibirá 8 de daño por veneno durante 3 turnos. "
                + "(Energía restante: " + energia + ")";
    }

    // ── Getters / Setters ─────────────────────────────────────────────────────
    public int getPrecision() { return precision; }
    public int getAgilidad()  { return agilidad;  }

    public void setPrecision(int precision) { if (precision > 0)  this.precision = precision; }
    public void setAgilidad(int agilidad)   { if (agilidad >= 0)  this.agilidad  = agilidad;  }

    @Override
    public String toString() {
        return "----- ARQUERO -----\n"
                + super.toString()
                + "\nPrecisión: " + precision
                + "\nAgilidad: " + agilidad;
    }
}
