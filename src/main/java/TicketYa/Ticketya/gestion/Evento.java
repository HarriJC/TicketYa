package TicketYa.Ticketya.gestion;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter


public class Evento {
    private String nombreEvento;
    private Date fecha;
    private Date hora;
    private String lugar;
    private String patrocinador;

    public Evento(String nombreEvento, Date fecha, Date hora, String patrocinador) {
        this.nombreEvento = nombreEvento;
        this.fecha=fecha;
        this.hora=hora;
        this.patrocinador=patrocinador;


    }

}
