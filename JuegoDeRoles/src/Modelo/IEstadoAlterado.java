package Modelo;

/**
 * Interfaz que define el contrato de cualquier estado alterado
 * (buff o debuff) que puede afectar a un personaje.
 * Funcionalidad 2: Sistema de Estados Alterados.
 */
public interface IEstadoAlterado {

    /** Nombre descriptivo del estado (p.ej. "Envenenado", "Congelado"). */
    String getNombreEstado();

    /**
     * Aplica el efecto del estado sobre el personaje objetivo.
     * Se llama una vez por turno al inicio del turno del personaje.
     */
    void aplicar(Personaje objetivo);

    /** retorna true si el estado ya agotó su duración. */
    boolean haExpirado();

    /** retorna true si este estado impide atacar durante el turno. */
    boolean bloqueaAtaque();

    /**
     * Bonus (o penalización, valor negativo) de ataque que este estado otorga.
     * Usado para calcular el ataque final del turno.
     */
    int getBonusAtaque();
}

