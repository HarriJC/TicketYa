package TicketYa.Ticketya.GestionPago;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class Pago {
    private String numeroComprobante;
    private double valorPagado;
    private LocalDate fechaPago;

    // Constructor vacío
    public Pago() {}

    // Constructor completo
    public Pago(String numeroComprobante, double valorPagado, LocalDate fechaPago) {
        this.numeroComprobante = numeroComprobante;
        this.valorPagado = valorPagado;
        this.fechaPago = fechaPago;
    }



    public boolean PagoRealizado() {
        return fechaPago != null &&
                (fechaPago.isBefore(LocalDate.now()) || fechaPago.isEqual(LocalDate.now()));
    }

    public String mostrarResumen() {
        return "Comprobante: " + numeroComprobante +
                " | Valor: $" + valorPagado +
                " | Fecha: " + fechaPago;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "numeroComprobante='" + numeroComprobante + '\'' +
                ", valorPagado=" + valorPagado +
                ", fechaPago=" + fechaPago +
                '}';
    }
}
