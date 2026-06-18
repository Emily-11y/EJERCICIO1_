package Modelo;

public class Arquero extends Personaje {

    public Arquero(String nombre, int vida) {
        super(nombre, vida);
    }

    @Override
    public int atacar() {
        return 15;
    }
}
