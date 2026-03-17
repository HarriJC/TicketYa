package TicketYa.Ticketya.gestion;

public class Zona {
    private String nombreZona;
    private Double precio;
    private int capacidad;
    private int boletasDisponibles;

    public Zona() {
    }


    public Zona(String nombreZona, Double precio, int capacidad, int boletasDisponibles) {
        this.nombreZona = nombreZona;
        this.precio = precio;
        this.capacidad = capacidad;
        this.boletasDisponibles = boletasDisponibles;
    }

    // Getters
    public String getNombreZona() { return nombreZona; }
    public Double getPrecio() { return precio; }
    public int getCapacidad() { return capacidad; }
    public int getBoletasDisponibles() { return boletasDisponibles; }

    // Setters
    public void setNombreZona(String nombreZona) { this.nombreZona = nombreZona; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public void setBoletasDisponibles(int boletasDisponibles) { this.boletasDisponibles = boletasDisponibles; }

    public void add(Zona zona) {
    }

    @Override
    public String toString() {
        return "Zona{nombreZona='" + nombreZona + "', precio=" + precio +
                ", capacidad=" + capacidad + ", boletasDisponibles=" + boletasDisponibles + "}";
    }
}