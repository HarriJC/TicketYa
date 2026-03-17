package TicketYa.Ticketya.gestion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter

public class Boleta {

    private List<String> localidad;
    private int estado;
    private int totalBoletas;
    private int botelasDisponibles;
    private double precio;

    public Boleta() {
    }

    public Boleta(List<String> localidad, int estado, int totalBoletas, int botelasDisponibles, double precio) {
        this.localidad = localidad;
        this.estado = estado;
        this.totalBoletas = totalBoletas;
        this.botelasDisponibles = botelasDisponibles;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Boleta{" +
                "localidad=" + localidad +
                ", estado=" + estado +
                ", totalBoletas=" + totalBoletas +
                ", botelasDisponibles=" + botelasDisponibles +
                ", precio=" + precio +
                '}';
    }

}
