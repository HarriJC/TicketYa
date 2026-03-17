package TicketYa.Ticketya.GestionPago;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class metodoPago {

    private String tipo;        // "PSE", "EFECTIVO", "TARJETA"
    private String descripcion;


    public metodoPago(String tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
    }



    public void procesarPago(Pago pago) {
        if (pago == null) {
            System.out.println("Error: no hay pago para procesar.");
            return;
        }

        System.out.println("=== Procesando Pago ===");
        System.out.println("Método:      " + tipo);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Comprobante: " + pago.getNumeroComprobante());
        System.out.println("Valor:       $" + pago.getValorPagado());
        System.out.println("Fecha:       " + pago.getFechaPago());
        System.out.println("======================");
        System.out.println(" Pago procesado exitosamente.");
    }

    @Override
    public String toString() {
        return "MetodoPago tipo='" + tipo + "', descripcion='" + descripcion + "'}";
    }
}