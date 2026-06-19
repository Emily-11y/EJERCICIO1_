
package Vista;

import java.util.Scanner;

import Controlador.ControladorCombate;

import Modelo.Arquero;
import Modelo.Guerrero;
import Modelo.Mago;
import Modelo.Personaje;

public class Problema1_Ejecutor {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        ControladorCombate controlador = new ControladorCombate();

        System.out.println("=========================================");
        System.out.println("        JUEGO DE ROLES - COMBATE");
        System.out.println("=========================================\n");

        System.out.println("======== PERSONAJE 1 ========");
        Personaje personaje1 = crearPersonaje(teclado);

        System.out.println("\n======== PERSONAJE 2 ========");
        Personaje personaje2 = crearPersonaje(teclado);

        System.out.println("\n=========================================");
        System.out.println("          PERSONAJES CREADOS");
        System.out.println("=========================================");

        System.out.println("\n" + personaje1);
        System.out.println();
        System.out.println(personaje2);

        System.out.println("\n=========================================");
        System.out.println("         INICIANDO COMBATE...");
        System.out.println("=========================================\n");

       Personaje ganador =controlador.combatir(personaje1, personaje2);

        System.out.println("=========================================");
        System.out.println("              GANADOR");
        System.out.println("=========================================\n");
        System.out.println(ganador);

    }

    /**
     * Permite crear cualquier tipo de personaje.
     * Gracias al polimorfismo, el método devuelve un Personaje aunque realmente construya un Guerrero, Mago o Arquero.
     */
    private static Personaje crearPersonaje(Scanner teclado) {

        int opcion;
        do {
            System.out.println("\nSeleccione el tipo de personaje:");
            System.out.println("1. Guerrero");
            System.out.println("2. Mago");
            System.out.println("3. Arquero");
            System.out.print("Opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine();
            if (opcion < 1 || opcion > 3) {
                System.out.println("Opción inválida.\n");
            }

        } while (opcion < 1 || opcion > 3);

        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();

        switch (opcion) {
            case 1:
                return new Guerrero(nombre,120,1,35,20);
            case 2:
                return new Mago( nombre,90,1,40,18);
            default:
                return new Arquero( nombre,100,1,32,25);

        }
   }
}
