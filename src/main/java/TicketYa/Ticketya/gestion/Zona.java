package TicketYa.Ticketya.gestion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Zona {
    private String nombreZona;
    private Double precio;
    private int capacidad;
    private int boletasDisponibles;

    public Zona() {
    }


    @Override
    public String toString() {
        return "Zona{nombreZona='" + nombreZona + "', precio=" + precio +
                ", capacidad=" + capacidad + ", boletasDisponibles=" + boletasDisponibles + "}";
    }
}