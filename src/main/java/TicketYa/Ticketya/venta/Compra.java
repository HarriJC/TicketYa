package TicketYa.Ticketya.venta;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Compra {
    private LocalDate fechaCompra;
    private double valorTotal;
    private String estadoCompra; // Ej: "PENDIENTE", "PAGADA", "CANCELADA"

    // Relación de composición con Comprador
    private Comprador comprador;


    @Override
    public String toString() {
        return "Compra{fecha=" + fechaCompra +
                ", valorTotal=" + valorTotal +
                ", estado='" + estadoCompra + "'" +
                ", comprador=" + comprador + "}";
    }
}