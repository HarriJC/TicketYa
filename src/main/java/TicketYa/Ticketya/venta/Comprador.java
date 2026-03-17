package TicketYa.Ticketya.venta;

public class Comprador {
    private String nombre;
    private int identificacion;

    // Constructor vacío
    public Comprador() {
    }

    // Constructor completo
    public Comprador(String nombre, int identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    // Getters
    public String getNombre()       { return nombre; }
    public int getIdentificacion()  { return identificacion; }

    // Setters
    public void setNombre(String nombre)             { this.nombre = nombre; }
    public void setIdentificacion(int identificacion){ this.identificacion = identificacion; }

    @Override
    public String toString() {
        return "Comprador{nombre='" + nombre + "', identificacion=" + identificacion + "}";
    }
}