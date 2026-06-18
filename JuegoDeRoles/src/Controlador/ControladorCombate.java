
package Controlador;
import Modelo.Personaje;

/**
 * Clase encargada de administrar el combate entre dos personajes.
 * Aquí se concentra toda la lógica del juego, respetando el patrón MVC.

 */
public class ControladorCombate {
    /**
     * Realiza una batalla entre dos personajes utilizando polimorfismo.
     */
    public Personaje combatir(Personaje personaje1,Personaje personaje2) {

        while (personaje1.estaVivo() && personaje2.estaVivo()) {
            realizarAtaque(personaje1, personaje2);
            if (!personaje2.estaVivo()) {
                break;
            }
            realizarAtaque(personaje2, personaje1);

        }

        if (personaje1.estaVivo()) {
            personaje1.subirNivel();
            return personaje1;
        }

        personaje2.subirNivel();
        return personaje2;

    }
    /**
     * Calcula el daño que un personaje ocasiona a otro.
     */
    private void realizarAtaque(Personaje atacante,Personaje defensor) {

        int ataque = atacante.atacar();
        int defensa = defensor.defender();

        int danio = ataque - defensa;

        // Evita daños negativos
        if (danio < 0) {
            danio = 0;
        }
        defensor.recibirDanio(danio);

    }

}
