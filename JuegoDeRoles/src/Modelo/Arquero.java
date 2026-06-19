
package Modelo;

/**
 * Representa un personaje de tipo Arquero.
 * El arquero destaca por su precisión en los ataques
 * a distancia y su agilidad para esquivar ataques.
 */
public class Arquero extends Personaje {
    // CONSTANTES
    private static final int BONUS_ATAQUE = 4;
    private static final int BONUS_DEFENSA = 4;

    private int precision;
    private int agilidad;

    public Arquero(String nombre,int puntosVida,int nivelExperiencia,int precision,int agilidad) {
        super(nombre, puntosVida, nivelExperiencia);
        this.precision = precision;
        this.agilidad = agilidad;
    }

    @Override
    public int atacar() {
        return precision + (nivelExperiencia * BONUS_ATAQUE);
    }

    @Override
    public int defender() {
        return agilidad + (nivelExperiencia * BONUS_DEFENSA);

    }

    public int getPrecision() {
        return precision;
    }

    public int getAgilidad() {
        return agilidad;
    }

    public void setPrecision(int precision) {
        if (precision > 0) {
            this.precision = precision;
        }

    }

    public void setAgilidad(int agilidad) {
        if (agilidad >= 0) {
            this.agilidad = agilidad;
        }

    }

    @Override
    public String toString() {
        return "----- ARQUERO -----"
                + "\n"
                + super.toString()
                + "\nPrecisión: " + precision
                + "\nAgilidad: " + agilidad;

    }

}
