package TicketYa.Ticketya.GestionPago;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class Pago {
    private String numeroComprobante;
    private double valorPagado;
    private LocalDate fechaPago;

    // Constructor vacío
    public Pago() {
    }


    // Métodos útiles
    public boolean esPagoRealizado() {
        return fechaPago != null &&
                (fechaPago.isBefore(LocalDate.now()) || fechaPago.isEqual(LocalDate.now()));
    }

    public double aplicarDescuento(double porcentaje) {
        return valorPagado - (valorPagado * porcentaje / 100);
    }

    public String mostrarResumen() {
        return "Comprobante: " + numeroComprobante +
                " | Valor: $" + valorPagado +
                " | Fecha: " + fechaPago;
    }

    @Override
    public String toString() {
        return "Pago{numeroComprobante='" + numeroComprobante +
                "', valorPagado=" + valorPagado +
                ", fechaPago=" + fechaPago + "}";
    }
}
