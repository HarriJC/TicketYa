package TicketYa.Ticketya.venta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Comprador {
    private String nombre;
    private int identificacion;


    public Comprador(String nombre, int identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;

    }
    @Override
    public String toString() {
        return "Comprador{nombre='" + nombre + "', identificacion=" + identificacion + "}";
    }
}

