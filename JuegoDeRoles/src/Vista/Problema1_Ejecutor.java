
package Vista;
import Modelo.Arma;
import Controlador.ControladorCombate;
import Modelo.*;
import java.util.Scanner;

/**
 * Clase principal del juego.
 * Demuestra las tres funcionalidades nuevas:
 *   1. Inventario y Equipamiento (Arma / Armadura).
 *   2. Estados Alterados (veneno, congelación, fuerza aumentada).
 *   3. Habilidades especiales con Energía y Cooldown.
 *
 * Puede ejecutarse en modo DEMO (automático) o modo INTERACTIVO.
 */
public class Problema1_Ejecutor {

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════");
        System.out.println("       JUEGO DE ROLES - COMBATE RPG        ");
        System.out.println("   (Sistema Expandido - Versión 2.0)       ");
        System.out.println("═══════════════════════════════════════════\n");

        System.out.println("Seleccione modo:");
        System.out.println("  1. Demo automático (recomendado para ver todas las funcionalidades)");
        System.out.println("  2. Modo interactivo");
        System.out.print("Opción: ");

        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        try { opcion = Integer.parseInt(sc.nextLine().trim()); } catch (Exception ignored) {}

        if (opcion == 2) {
            modoInteractivo(sc);
        } else {
            modoDemo();
        }

        sc.close();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // MODO DEMO
    // ─────────────────────────────────────────────────────────────────────────
    private static void modoDemo() {
        System.out.println("\n════════════════════════════════════════════");
        System.out.println("              MODO DEMO");
        System.out.println("════════════════════════════════════════════");

        // ── Crear personajes ──────────────────────────────────────────────────
        Guerrero thor  = new Guerrero("Thor",  150, 2, 40, 25);
        Mago     merlin = new Mago("Merlín",  110, 2, 45, 20);
        Arquero  legolas = new Arquero("Légolas", 120, 2, 38, 30);

        // ─────────────────────────────────────────────────────────────────────
        // FUNCIONALIDAD 1: Inventario y Equipamiento
        // ─────────────────────────────────────────────────────────────────────
        System.out.println("\n════ FUNCIONALIDAD 1: INVENTARIO Y EQUIPAMIENTO ════");
        Arma    espadaLegendaria = new Arma("Espada Legendaria",    "Forjada en el volcán", 15);
        Arma    arcoElfico       = new Arma("Arco Élfico",          "Madera de roble mágico", 12);
        Arma    bastionArcano    = new Arma("Bastión Arcano",       "Amplifica la magia",   18);
        Armadura pielDragon      = new Armadura("Piel de Dragón",   "Escamas irrompibles",  10);
        Armadura capaMago        = new Armadura("Capa del Archimago","Tejida con runas",      8);
        Armadura armaduraArquero = new Armadura("Chaleco de Cuero Reforzado","Ligero y resistente", 6);

        thor.equiparArma(espadaLegendaria);
        thor.equiparArmadura(pielDragon);

        merlin.equiparArma(bastionArcano);
        merlin.equiparArmadura(capaMago);

        legolas.equiparArma(arcoElfico);
        legolas.equiparArmadura(armaduraArquero);

        // Agregar objetos extra al inventario de Thor
        Objeto pociones = new Arma("Espada Oxidada", "Espada de repuesto", 2);
        thor.agregarAlInventario(pociones);

        mostrarPersonaje(thor);
        mostrarPersonaje(merlin);
        mostrarPersonaje(legolas);

        // ─────────────────────────────────────────────────────────────────────
        // FUNCIONALIDAD 2: Estados Alterados
        // ─────────────────────────────────────────────────────────────────────
        System.out.println("\n════ FUNCIONALIDAD 2: ESTADOS ALTERADOS ════");
        System.out.println("(Los estados se aplicarán durante el combate via habilidades especiales)");
        System.out.println("  → Merlín puede CONGELAR al rival (1 turno sin atacar).");
        System.out.println("  → Légolas puede ENVENENAR al rival (8 daño × 3 turnos).");
        System.out.println("  → Thor puede AUMENTAR SU FUERZA (+20 ATQ × 3 turnos).");

        // Demostración aislada de estado antes del combate
        System.out.println("\nDemo de veneno fuera de combate:");
        Guerrero dummy = new Guerrero("Maniquí", 50, 1, 10, 5);
        dummy.agregarEstado(new EstadoEnvenenado(5, 2));
        System.out.println("  Estado inicial: " + dummy.getPuntosVida() + " PV");
        dummy.procesarEstados();
        System.out.println("  Después de 1 procesamiento: " + dummy.getPuntosVida() + " PV");
        dummy.procesarEstados();
        System.out.println("  Después de 2 procesamientos: " + dummy.getPuntosVida() + " PV");

        // ─────────────────────────────────────────────────────────────────────
        // FUNCIONALIDAD 3: Habilidades especiales + Energía + Cooldown
        // ─────────────────────────────────────────────────────────────────────
        System.out.println("\n════ FUNCIONALIDAD 3: HABILIDADES Y COOLDOWN ════");
        System.out.println("  Thor    → Golpe Devastador  (costo: 30 energía, cooldown: 3 turnos)");
        System.out.println("  Merlín  → Ventisca Gélida   (costo: 40 energía, cooldown: 2 turnos)");
        System.out.println("  Légolas → Flecha Envenenada (costo: 25 energía, cooldown: 2 turnos)");

        // ─────────────────────────────────────────────────────────────────────
        // COMBATE 1: Thor vs Merlín
        // ─────────────────────────────────────────────────────────────────────
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║       COMBATE 1: THOR  vs  MERLÍN         ║");
        System.out.println("╚═══════════════════════════════════════════╝");

        ControladorCombate controlador = new ControladorCombate();
        Personaje ganador1 = controlador.combatir(thor, merlin);

        System.out.println("\n═══ GANADOR del Combate 1 ═══");
        System.out.println(ganador1);

        // ─────────────────────────────────────────────────────────────────────
        // COMBATE 2: Légolas vs Thor (si sobrevivió)
        // ─────────────────────────────────────────────────────────────────────
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║     COMBATE 2: LÉGOLAS  vs  GANADOR 1     ║");
        System.out.println("╚═══════════════════════════════════════════╝");

        Personaje ganadorFinal = controlador.combatir(legolas, ganador1);

        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║          🏆  CAMPEÓN FINAL                ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.println(ganadorFinal);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // MODO INTERACTIVO
    // ─────────────────────────────────────────────────────────────────────────
    private static void modoInteractivo(Scanner sc) {
        System.out.println("\n════════════════════════════════════════════");
        System.out.println("           MODO INTERACTIVO");
        System.out.println("════════════════════════════════════════════\n");

        System.out.println("==== PERSONAJE 1 ====");
        Personaje p1 = crearPersonaje(sc);
        equiparPersonaje(p1);

        System.out.println("\n==== PERSONAJE 2 ====");
        Personaje p2 = crearPersonaje(sc);
        equiparPersonaje(p2);

        System.out.println("\n═══ Personajes creados ═══");
        System.out.println(p1);
        System.out.println();
        System.out.println(p2);

        System.out.println("\n════════ INICIANDO COMBATE ════════\n");
        ControladorCombate controlador = new ControladorCombate();
        Personaje ganador = controlador.combatir(p1, p2);

        System.out.println("\n╔════════════════╗");
        System.out.println("║    GANADOR     ║");
        System.out.println("╚════════════════╝");
        System.out.println(ganador);
    }

    private static Personaje crearPersonaje(Scanner sc) {
        int opcion;
        do {
            System.out.println("\nTipo de personaje:");
            System.out.println("  1. Guerrero  (Habilidad: Golpe Devastador)");
            System.out.println("  2. Mago      (Habilidad: Ventisca Gélida)");
            System.out.println("  3. Arquero   (Habilidad: Flecha Envenenada)");
            System.out.print("Opción: ");
            try { opcion = Integer.parseInt(sc.nextLine().trim()); }
            catch (Exception e) { opcion = 0; }
            if (opcion < 1 || opcion > 3) System.out.println("  Opción inválida.");
        } while (opcion < 1 || opcion > 3);

        System.out.print("Nombre del personaje: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) nombre = "Héroe";

        return switch (opcion) {
            case 1 -> new Guerrero(nombre, 150, 2, 40, 25);
            case 2 -> new Mago(nombre,     110, 2, 45, 20);
            default-> new Arquero(nombre,  120, 2, 38, 30);
        };
    }

    private static void equiparPersonaje(Personaje p) {
        System.out.println("\n-- Equipamiento para " + p.getNombre() + " --");
        System.out.println("¿Desea equipar arma? (s/n): ");
        // Para simplificar el modo interactivo, asignamos armas predefinidas
        // según la clase del personaje.
        if (p instanceof Guerrero) {
            p.equiparArma(new Arma("Espada de Acero", "Espada estándar de guerrero", 10));
            p.equiparArmadura(new Armadura("Escudo de Hierro", "Protección básica", 8));
        } else if (p instanceof Mago) {
            p.equiparArma(new Arma("Vara Mágica", "Canaliza la magia", 12));
            p.equiparArmadura(new Armadura("Túnica Rúnica", "Repele hechizos", 6));
        } else {
            p.equiparArma(new Arma("Arco de Madera", "Arco confiable", 8));
            p.equiparArmadura(new Armadura("Chaleco de Cuero", "Ligero y ágil", 5));
        }
    }

    private static void mostrarPersonaje(Personaje p) {
        System.out.println("\n" + p);
        System.out.println("  ATQ total: " + p.calcularAtaque()
                + " | DEF total: " + p.calcularDefensa());
        System.out.println("  Inventario (" + p.getInventario().size() + " objetos):");
        for (Objeto o : p.getInventario()) {
            System.out.println("    - " + o);
        }
    }
}

