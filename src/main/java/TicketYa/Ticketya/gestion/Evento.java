package TicketYa.Ticketya.gestion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor

public class Evento {
    private String nombreEvento;
    private Date fecha;
    private Date hora;
    private String lugar;
    private String patrocinador;

    public void crearEvento() {
        System.out.println("Evento creado: " + nombreEvento);
    }

    @Override
    public String toString() {
        return "Evento{nombreEvento='" + nombreEvento +
                "', fecha=" + fecha +
                ", hora=" + hora +
                ", lugar='" + lugar +
                "', patrocinador='" + patrocinador + "'}";
    }
}