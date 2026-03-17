package TicketYa.Ticketya.gestion;

import java.util.Date;

public class Evento {
    private String nombreEvento;
    private Date fecha;
    private Date hora;
    private String lugar;
    private String patrocinador;

    // Constructor vacío
    public Evento() {
    }

    // Constructor completo
    public Evento(String nombreEvento, Date fecha, Date hora, String lugar, String patrocinador) {
        this.nombreEvento = nombreEvento;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.patrocinador = patrocinador;
    }

    // Getters
    public String getNombreEvento() { return nombreEvento; }
    public Date getFecha()          { return fecha; }
    public Date getHora()           { return hora; }
    public String getLugar()        { return lugar; }
    public String getPatrocinador() { return patrocinador; }

    // Setters
    public void setNombreEvento(String nombreEvento) { this.nombreEvento = nombreEvento; }
    public void setFecha(Date fecha)                 { this.fecha = fecha; }
    public void setHora(Date hora)                   { this.hora = hora; }
    public void setLugar(String lugar)               { this.lugar = lugar; }
    public void setPatrocinador(String patrocinador) { this.patrocinador = patrocinador; }


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