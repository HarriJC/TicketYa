package TicketYa.Ticketya.GestionPago;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetodoPago {

    private String tipo;
    private String descripcion;

    // Constructor vacío
    public MetodoPago() {
    }

    // Constructor completo
    public MetodoPago(String tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
    }


    public void procesarPago(Pago pago) {
        if (pago == null) {
            System.out.println("Error: no hay pago para procesar.");
            return;
        }
        System.out.println("=== Procesando Pago ===");
        System.out.println("Metodo:      " + tipo);
        System.out.println("Descripcion: " + descripcion);
        System.out.println("Comprobante: " + pago.getNumeroComprobante());
        System.out.println("Valor:       $" + pago.getValorPagado());
        System.out.println("Fecha:       " + pago.getFechaPago());
        System.out.println("======================");
        System.out.println("Pago procesado exitosamente.");
    }

    @Override
    public String toString() {
        return "MetodoPago{tipo='" + tipo + "', descripcion='" + descripcion + "'}";
    }
}