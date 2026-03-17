package TicketYa.Ticketya.gestion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Zona {
    private String nombreZona;
    private Double precio;
    private int capacidad;
    private int boletasDisponibles;

    public Zona( String nombreZona, Double precio, int capacidad, int boletasDisponibles){
        this.nombreZona= nombreZona;
        this.precio=precio;
        this.capacidad=capacidad;
        this.boletasDisponibles=boletasDisponibles;

    }


    public void add(Zona zona) {
    }
}
