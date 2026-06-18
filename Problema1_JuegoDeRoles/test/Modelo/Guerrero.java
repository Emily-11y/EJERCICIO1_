package Modelo;
public class Guerrero extends Personaje {

    public Guerrero(String nombre, int vida) {
        super(nombre, vida);
    }

    @Override
    public int atacar() {
        return 20;
    }
}


