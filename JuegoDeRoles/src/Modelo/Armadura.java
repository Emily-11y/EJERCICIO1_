package Modelo;
/**
 * Representa una armadura equipable que incrementa la defensa del personaje.
 * Funcionalidad 1: Sistema de Inventario y Equipamiento.
 */
public class Armadura extends Objeto {
    private int bonusDefensa;

    public Armadura(String nombre, String descripcion, int bonusDefensa) {
        super(nombre, descripcion);
        this.bonusDefensa = bonusDefensa;
    }

    public int getBonusDefensa() { return bonusDefensa; }

    @Override
    public String toString() {
        return super.toString() + " [+DEF: " + bonusDefensa + "]";
    }
}
