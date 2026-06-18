package Main;
import Controlador.ControladorCombate;
import Modelo.Guerrero;
import Modelo.Arquero;
import Vista.VistaCombate;
public class PersonajeEjecutor {

    public static void main(String[] args) {

        Guerrero guerrero = new Guerrero("Kratos", 100);
        Arquero arquero = new Arquero("Legolas", 100);

        VistaCombate vista = new VistaCombate();

        ControladorCombate controlador =
                new ControladorCombate(vista);

        controlador.iniciarCombate(guerrero, arquero);
    }
}
