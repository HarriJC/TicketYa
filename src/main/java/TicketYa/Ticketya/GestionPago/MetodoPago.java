package TicketYa.Ticketya.GestionPago;

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

    // Getters
    public String getTipo()        { return tipo; }
    public String getDescripcion() { return descripcion; }

    // Setters
    public void setTipo(String tipo)               { this.tipo = tipo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }


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