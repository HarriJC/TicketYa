package TicketYa.Ticketya.gestion;

import java.util.ArrayList;
import java.util.List;

public class Boleta {

    public static final int MAX_BOLETAS = 10000;

    private List<Zona> zonas;
    private int estado;
    private int totalBoletas;
    private double precio;

    // Constructor vacío
    public Boleta() {
        this.zonas = new ArrayList<>();
        this.totalBoletas = 0;
    }

    // Constructor completo
    public Boleta(List<Zona> zonas, int estado, int totalBoletas, double precio) {
        this.zonas = zonas;
        this.estado = estado;
        this.totalBoletas = totalBoletas;
        this.precio = precio;
    }

    // Getters
    public List<Zona> getZonas()    { return zonas; }
    public int getEstado()          { return estado; }
    public int getTotalBoletas()    { return totalBoletas; }
    public double getPrecio()       { return precio; }

    // Setters
    public void setZonas(List<Zona> zonas)       { this.zonas = zonas; }
    public void setEstado(int estado)             { this.estado = estado; }
    public void setTotalBoletas(int totalBoletas) { this.totalBoletas = totalBoletas; }
    public void setPrecio(double precio)          { this.precio = precio; }


    public boolean agregarLocalidad(Zona zona) {
        if (totalBoletas + zona.getCapacidad() <= MAX_BOLETAS) {
            zonas.add(zona);
            totalBoletas += zona.getCapacidad();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Boleta{zonas=" + zonas +
                ", estado=" + estado +
                ", totalBoletas=" + totalBoletas +
                ", precio=" + precio + "}";
    }
}