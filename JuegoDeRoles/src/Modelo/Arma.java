
package Modelo;
/**
 * Representa un arma equipable que incrementa el ataque del personaje
 * Funcionalidad 1: Sistema de Inventario y Equipamiento.
 */
public class Arma extends Objeto {
    private int bonusAtaque;

    public Arma(String nombre, String descripcion, int bonusAtaque) {
        super(nombre, descripcion);
        this.bonusAtaque = bonusAtaque;
    }

    public int getBonusAtaque() {
        return bonusAtaque;
    }

    @Override
    public String toString() {
        return super.toString() + " [+Ataque: " + bonusAtaque + "]";
    }
}


