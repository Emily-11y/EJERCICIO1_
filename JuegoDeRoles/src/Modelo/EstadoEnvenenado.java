package Modelo;
/**
 * Estado de VENENO: el personaje pierde vida cada turno durante N turnos.
 * Funcionalidad 2: Sistema de Estados Alterados.
 */
public class EstadoEnvenenado implements IEstadoAlterado {
    private int danioPorTurno;
    private int turnosRestantes;

    public EstadoEnvenenado(int danioPorTurno, int duracionTurnos) {
        this.danioPorTurno  = danioPorTurno;
        this.turnosRestantes = duracionTurnos;
    }

    @Override
    public String getNombreEstado() { 
        return "Envenenado"; 
    }

    @Override
    public void aplicar(Personaje objetivo) {
        if (turnosRestantes > 0) {
            objetivo.recibirDanio(danioPorTurno);
            turnosRestantes--;
            System.out.println("  [Veneno] " + objetivo.getNombre()
                    + " pierde " + danioPorTurno + " PV por veneno. "
                    + "(Turnos restantes: " + turnosRestantes + ")");
        }
    }

    @Override public boolean haExpirado()    { 
        return turnosRestantes <= 0; 
    }
    @Override public boolean bloqueaAtaque() { 
        return false; 
    }
    @Override public int getBonusAtaque(){ 
        return 0; 
    }
}