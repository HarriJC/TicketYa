package TicketYa.Ticketya.gestion;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Boleta {

    public static final int MAX_BOLETAS = 10000;

    private List<Zona> zonas;
    private int estado;
    private int totalBoletas;
    private double precio;


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