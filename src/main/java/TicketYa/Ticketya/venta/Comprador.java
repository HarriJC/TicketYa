package TicketYa.Ticketya.venta;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor

public class Comprador {
    private String nombre;
    private int identificacion;


  

    @Override
    public String toString() {
        return "Comprador{nombre='" + nombre + "', identificacion=" + identificacion + "}";
    }
}