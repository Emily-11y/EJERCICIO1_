package Modelo;

/**
 * Representa un personaje de tipo Mago.
 * Habilidad especial: "Ventisca Gélida" — congela al rival durante 1 turno.
 * Costo energía: 40.  Cooldown: 2 turnos.
 */
public class Mago1 extends Personaje {

    private static final int BONUS_ATAQUE  = 6;
    private static final int BONUS_DEFENSA = 3;

    private static final int COSTO_ENERGIA_HABILIDAD   = 40;
    private static final int COOLDOWN_HABILIDAD_TURNOS  = 2;

    private int poderMagico;
    private int mana;

    public Mago1(String nombre, int puntosVida, int nivelExperiencia,
                int poderMagico, int mana) {
        super(nombre, puntosVida, nivelExperiencia);
        this.poderMagico = poderMagico;
        this.mana        = mana;
    }

    // ── Polimorfismo ──────────────────────────────────────────────────────────
    @Override
    public int atacar() {
        return poderMagico + (nivelExperiencia * BONUS_ATAQUE);
    }

    @Override
    public int defender() {
        return mana + (nivelExperiencia * BONUS_DEFENSA);
    }

    /**
     * Habilidad especial: Ventisca Gélida.
     * Congela al propio personaje (el ControladorCombate se encargará de
     * aplicarla al rival a través del parámetro devuelto).
     *
     * Nota: el controlador recibe el texto "CONGELAR_RIVAL" y aplica el estado
     * al oponente. Se usa una convención de retorno para mantener el polimorfismo.
     *
     * @throws SinEnergiaException si no hay energía suficiente.
     */
    @Override
    public String usarHabilidadEspecial() throws SinEnergiaException {
        if (!habilidadDisponible()) {
            return "[" + nombre + "] Ventisca Gélida en cooldown ("
                    + getCooldownHabilidad() + " turnos restantes).";
        }
        consumirEnergia(COSTO_ENERGIA_HABILIDAD);
        setCooldownHabilidad(COOLDOWN_HABILIDAD_TURNOS);
        // El controlador detecta "CONGELAR_RIVAL" y aplica el estado al oponente
        return "CONGELAR_RIVAL:[" + nombre + "] lanza ¡VENTISCA GÉLIDA! "
                + "El rival quedará congelado 1 turno. (Energía restante: " + energia + ")";
    }

    // ── Getters / Setters ─────────────────────────────────────────────────────
    public int getPoderMagico() { return poderMagico; }
    public int getMana()        { return mana; }

    public void setPoderMagico(int poderMagico) { if (poderMagico > 0)  this.poderMagico = poderMagico; }
    public void setMana(int mana)               { if (mana >= 0)        this.mana        = mana; }

    @Override
    public String toString() {
        return "----- MAGO -----\n"
                + super.toString()
                + "\nPoder mágico: " + poderMagico
                + "\nManá: " + mana;
    }
}
