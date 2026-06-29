package Modelo;
/**
 * Estado de CONGELACIÓN: el personaje no puede atacar durante 1 turno.
 * Funcionalidad 2: Sistema de Estados Alterados.
 */
public class EstadoCongelado implements IEstadoAlterado {
    private int turnosRestantes;

    public EstadoCongelado(int duracionTurnos) {
        this.turnosRestantes = duracionTurnos;
    }

    @Override
    public String getNombreEstado() { return "Congelado"; }

    @Override
    public void aplicar(Personaje objetivo) {
        if (turnosRestantes > 0) {
            turnosRestantes--;
            System.out.println("  [Hielo] " + objetivo.getNombre()
                    + " esta congelado y no puede atacar. "
                    + "(Turnos restantes: " + turnosRestantes + ")");
        }
    }

    @Override public boolean haExpirado()    { 
        return turnosRestantes <= 0; 
    }
    @Override public boolean bloqueaAtaque() { 
        return turnosRestantes > 0; 
    }
    @Override public int  getBonusAtaque(){ 
        return 0; 
    }
}
