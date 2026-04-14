package TicketYa.Ticketya.gestion;

import lombok.*;
import java.util.Date;
import java.text.SimpleDateFormat;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Evento {
    private String nombreEvento;
    private Date fecha;
    private Date hora;
    private String lugar;
    private String patrocinador;

    // Campos para las secciones (Imagen de referencia)
    private int cantVip;
    private int cantPlatea;
    private int cantGeneral;
    private double precioVip;
    private double precioPlatea;
    private double precioGeneral;

    private String estado = "ACTIVO";

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm");

        // Formato visual alineado para la lista
        return String.format("%-15s | %-10s | %s | %-10s | VIP: %-3d | PLT: %-3d | GEN: %-3d | %s",
                nombreEvento.toUpperCase(),
                lugar.toUpperCase(),
                sdf.format(fecha),
                patrocinador.toUpperCase(),
                cantVip, cantPlatea, cantGeneral,
                estado);
    }
}