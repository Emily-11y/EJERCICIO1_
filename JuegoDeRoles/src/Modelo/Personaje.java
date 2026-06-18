package Modelo;
/**
 * Clase abstracta que representa un personaje del juego.
 * Contiene los atributos y comportamientos comunes para
 * Guerreros, Magos y Arqueros.
 */

public abstract class Personaje {
    protected String nombre;
    protected int puntosVida;
    protected int nivelExperiencia;

    public Personaje(String nombre, int puntosVida, int nivelExperiencia) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.nivelExperiencia = nivelExperiencia;
    }

    // MÉTODOS ABSTRACTOS
    // Cada personaje implementará su propia forma de atacar y defenderse (POLIMORFISMO).

    public abstract int atacar();//Calcula el daño que realiza el personaje al atacar.
    public abstract int defender();//Calcula la capacidad defensiva del personaje.


    public void recibirDanio(int danio) {//Reduce la vida del personaje según el daño recibido.
        if (danio <= 0) {
            return;
        }
        puntosVida -= danio;//La vida nunca podrá ser menor que cero.
        if (puntosVida < 0) {
            puntosVida = 0;
        }

    }

    public void subirNivel() {//Aumenta el nivel del personaje al ganar una batalla.
        nivelExperiencia++;//También recupera una pequeña cantidad de vida.
        puntosVida += 20;
    }

    public boolean estaVivo() {//Verifica si el personaje sigue con vida.
        return puntosVida > 0;//true si la vida es mayor que cero.
    }

    
    
    @Override
    public String toString() {
        return "Nombre: " + nombre
                + "\nPuntos de vida: " + puntosVida
                + "\nNivel de experiencia: " + nivelExperiencia;
    }

}
