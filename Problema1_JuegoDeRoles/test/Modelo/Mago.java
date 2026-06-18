package Modelo;


import Modelo.Personaje;

public class Mago {
  public class mago extends Personaje {

    public mago(String nombre, int vida) {
        super(nombre, vida);
    }

    @Override
    public int atacar() {
        return 25;
    }
  }
}
