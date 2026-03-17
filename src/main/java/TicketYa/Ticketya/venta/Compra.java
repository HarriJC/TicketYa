package TicketYa.Ticketya.venta;

import TicketYa.Ticketya.venta.Comprador;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@Setter
@ToString

public class Compra {
    private LocalDate fechaCompra;
    private double valorTotal;
    private String estadoCompra; // Ej: "PENDIENTE", "PAGADA", "CANCELADA"

    // Relación de composición con Comprador
    private Comprador comprador;

    // Constructor
    public Compra(LocalDate fechaCompra, double valorTotal, String estadoCompra, Comprador comprador) {
        this.fechaCompra = fechaCompra;
        this.valorTotal = valorTotal;
        this.estadoCompra = estadoCompra;
        this.comprador = comprador;
    }


    @Override
    public String toString() {
        return "Compra{fecha=" + fechaCompra +
                ", valorTotal=" + valorTotal +
                ", estado='" + estadoCompra + "'" +
                ", comprador=" + comprador + "}";
    }
}