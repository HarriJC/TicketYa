package TicketYa.Ticketya.gestion;

public class Boleta {
    public static final int MaximoBoletas = 10000;

    private int codigo;
    private String estado;
    private int totalDeBoletas;

    public Boleta(int totalDeBoletas){
        this.totalDeBoletas = 0;

    }
}
