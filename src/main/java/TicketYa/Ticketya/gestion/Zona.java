package TicketYa.Ticketya.gestion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Zona {
    private String nombreZona;
    private Double precio;
    private int capacidad;
    private int boletasDisponibles;

    public int getCapacidad() {
        return capacidad;
    }

    @Override
    public String toString() {
        return "Zona{nombreZona='" + nombreZona + "', precio=" + precio +
                ", capacidad=" + capacidad + ", boletasDisponibles=" + boletasDisponibles + "}";
    }
}