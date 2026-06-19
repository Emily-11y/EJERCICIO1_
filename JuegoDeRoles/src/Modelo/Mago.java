
package Modelo;
/**
 * Representa un personaje de tipo Mago.
 * El mago utiliza su poder mágico para atacar  y el maná para fortalecer su defensa.
 */
public class Mago extends Personaje {

    private static final int BONUS_ATAQUE = 6;
    private static final int BONUS_DEFENSA = 3;

    private int poderMagico;
    private int mana;

    public Mago(String nombre,int puntosVida,int nivelExperiencia, int poderMagico, int mana) {
        super(nombre, puntosVida, nivelExperiencia);
        this.poderMagico = poderMagico;
        this.mana = mana;//fortalece defensa
    }

    @Override
    public int atacar() {//Calcula el daño producido por el mago.
        return poderMagico + (nivelExperiencia * BONUS_ATAQUE);
    }

    @Override
    public int defender() {//Calcula la defensa del mago.
        return mana + (nivelExperiencia * BONUS_DEFENSA);

    }

    public int getPoderMagico() {
        return poderMagico;
    }

    public int getMana() {
        return mana;
    }

    public void setPoderMagico(int poderMagico) {
        if (poderMagico > 0) {
            this.poderMagico = poderMagico;
        }
    }

    public void setMana(int mana) {
        if (mana >= 0) {
            this.mana = mana;
        }
    }

    @Override
    public String toString() {

        return "----- MAGO -----"
                + "\n"
                + super.toString()
                + "\nPoder mágico: " + poderMagico
                + "\nManá: " + mana;

    }

}
