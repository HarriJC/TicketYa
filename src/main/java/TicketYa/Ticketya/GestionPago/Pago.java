package TicketYa.Ticketya.GestionPago;

import java.time.LocalDate;

public class Pago {
    private String numeroComprobante;
    private double valorPagado;
    private LocalDate fechaPago;

    // Constructor vacío
    public Pago() {
    }

    // Constructor completo
    public Pago(String numeroComprobante, double valorPagado, LocalDate fechaPago) {
        this.numeroComprobante = numeroComprobante;
        this.valorPagado = valorPagado;
        this.fechaPago = fechaPago;
    }

    // Getters
    public String getNumeroComprobante() { return numeroComprobante; }
    public double getValorPagado() { return valorPagado; }
    public LocalDate getFechaPago() { return fechaPago; }

    // Setters
    public void setNumeroComprobante(String numeroComprobante) { this.numeroComprobante = numeroComprobante; }
    public void setValorPagado(double valorPagado) { this.valorPagado = valorPagado; }
    public void setFechaPago(LocalDate fechaPago) { this.fechaPago = fechaPago; }

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
