package Controlador;
import Modelo.Personaje;
import Vista.VistaCombate;
public class ControladorCombate {
    private VistaCombate vista;

    public ControladorCombate(VistaCombate vista) {
        this.vista = vista;
    }

    public void iniciarCombate(Personaje p1, Personaje p2) {

        vista.mostrarMensaje("Combate entre "
                + p1.getNombre() + " y "
                + p2.getNombre());

        while (p1.getVida() > 0 && p2.getVida() > 0) {

            p2.recibirDanio(p1.atacar());

            vista.mostrarMensaje(
                p1.getNombre() + " ataca. Vida de "
                + p2.getNombre() + ": "
                + p2.getVida()
            );

            if (p2.getVida() <= 0)
                break;

            p1.recibirDanio(p2.atacar());

            vista.mostrarMensaje(
                p2.getNombre() + " ataca. Vida de "
                + p1.getNombre() + ": "
                + p1.getVida()
            );
        }

        if (p1.getVida() > 0) {
            vista.mostrarMensaje("Ganador: " + p1.getNombre());
        } else {
            vista.mostrarMensaje("Ganador: " + p2.getNombre());
        }
    }
}
