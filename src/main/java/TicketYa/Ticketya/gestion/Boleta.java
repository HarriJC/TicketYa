package TicketYa.Ticketya.gestion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Boleta {

    public static final int MAX_BOLETAS = 10000;

    private List<Zona> Zonas;
    private int estado;
    private int totalBoletas;
    private double precio;

    public Boleta() {
        this.totalBoletas=0;
    }

    public boolean agregarLocalidad(Zona zona)
    {
        if(totalBoletas + zona.getCapacidad() <= MAX_BOLETAS){
            zona.add(zona);
            totalBoletas += zona.getCapacidad();
            return true;
        }
        return false;
    }


    public Boleta(List<Zona> Zonas, int estado, int totalBoletas, int botelasDisponibles, double precio) {
        this.Zonas = Zonas;
        this.estado = estado;
        this.totalBoletas = totalBoletas;
        this.precio = precio;


    }


    @Override
    public String toString() {
        return "Boleta{" +
                "localidad=" + Zonas +
                ", estado=" + estado +
                ", totalBoletas=" + totalBoletas +
                ", precio=" + precio +
                '}';
    }

}
