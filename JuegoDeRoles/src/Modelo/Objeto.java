package Modelo;

/**
 * Clase base para todos los objetos del inventario.
 * Funcionalidad 1: Sistema de Inventario y Equipamiento.
 */
public abstract class Objeto {
    protected String nombre;
    protected String descripcion;

    public Objeto(String nombre, String descripcion) {
        this.nombre      = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre()      { 
        return nombre; 
    }
    public String getDescripcion() { 
        return descripcion; 
    }

    @Override
    public String toString() {
        return nombre + " - " + descripcion;
    }
}