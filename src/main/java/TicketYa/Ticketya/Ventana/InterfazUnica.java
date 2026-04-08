package TicketYa.Ticketya.Ventana;

import TicketYa.Ticketya.gestion.Evento;
import TicketYa.Ticketya.gestion.Zona;
import TicketYa.Ticketya.gestion.Boleta;
import TicketYa.Ticketya.venta.Comprador;
import TicketYa.Ticketya.venta.Compra;
import TicketYa.Ticketya.GestionPago.Pago;
import TicketYa.Ticketya.GestionPago.MetodoPago;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InterfazUnica extends JFrame {

    // ── Campos Evento ──────────────────────────────────────────
    private JTextField txtEventoNombre       = new JTextField();
    private JTextField txtEventoFecha        = new JTextField();
    private JTextField txtEventoHora         = new JTextField();
    private JTextField txtEventoLugar        = new JTextField();
    private JTextField txtEventoPatrocinador = new JTextField();

    // ── Campos Zona ────────────────────────────────────────────
    private JTextField txtZonaNombre    = new JTextField();
    private JTextField txtZonaPrecio    = new JTextField();
    private JTextField txtZonaCapacidad = new JTextField();
    private JTextField txtZonaBoletas   = new JTextField();

    // ── Campos Boleta ──────────────────────────────────────────
    private JTextField txtBoletaEstado = new JTextField();
    private JTextField txtBoletaTotal  = new JTextField();

    // ── Campos Comprador ───────────────────────────────────────
    private JTextField txtCompradorNombre = new JTextField();
    private JTextField txtCompradorId     = new JTextField();

    // ── Campos Compra ──────────────────────────────────────────
    private JTextField txtCompraValor   = new JTextField();
    private JComboBox<String> cmbEstado = new JComboBox<>(
            new String[]{"PENDIENTE", "PAGADA", "CANCELADA"});

    // ── Campos Pago ────────────────────────────────────────────
    private JTextField txtPagoComprobante = new JTextField();
    private JTextField txtPagoValor       = new JTextField();
    private JComboBox<String> cmbMetodo   = new JComboBox<>(
            new String[]{"EFECTIVO", "TARJETA_CREDITO", "TARJETA_DEBITO", "TRANSFERENCIA", "PSE"});

    // ── Listas del dominio ─────────────────────────────────────
    private List<Evento>     listaEventos     = new ArrayList<>();
    private List<Zona>       listaZonas       = new ArrayList<>();
    private List<Boleta>     listaBoletas     = new ArrayList<>();
    private List<Comprador>  listaCompradores = new ArrayList<>();
    private List<Compra>     listaCompras     = new ArrayList<>();
    private List<Pago>       listaPagos       = new ArrayList<>();

    // ── Modelos visuales ───────────────────────────────────────
    private DefaultListModel<String> modeloEventos     = new DefaultListModel<>();
    private DefaultListModel<String> modeloZonas       = new DefaultListModel<>();
    private DefaultListModel<String> modeloBoletas     = new DefaultListModel<>();
    private DefaultListModel<String> modeloCompradores = new DefaultListModel<>();
    private DefaultListModel<String> modeloCompras     = new DefaultListModel<>();
    private DefaultListModel<String> modeloPagos       = new DefaultListModel<>();

    // ── Objetos actuales ───────────────────────────────────────
    private Zona      zona;
    private Comprador comprador;

    // ══════════════════════════════════════════════════════════
    // CONSTRUCTOR
    // ══════════════════════════════════════════════════════════
    public InterfazUnica() {
        setTitle("TicketYa - Sistema de Boletas");
        setSize(640, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Evento",    panelEvento());
        tabs.addTab("Zona",      panelZona());
        tabs.addTab("Boleta",    panelBoleta());
        tabs.addTab("Comprador", panelComprador());
        tabs.addTab("Compra",    panelCompra());
        tabs.addTab("Pago",      panelPago());

        add(tabs);
    }

    // ══════════════════════════════════════════════════════════
    // MÉTODO AUXILIAR — crearLabel (evita conflicto con awt.Label)
    // ══════════════════════════════════════════════════════════
    private JLabel crearLabel(String texto) {
        return new JLabel(texto);
    }

    private JPanel crearPanel(int filas) {
        JPanel p = new JPanel(new GridLayout(filas, 2, 8, 8));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return p;
    }

    private void mostrarOk(String msg) {
        JOptionPane.showMessageDialog(this, "OK: " + msg, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarError(String msg) {
        JOptionPane.showMessageDialog(this, "Error: " + msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // ══════════════════════════════════════════════════════════
    // PANEL EVENTO
    // ══════════════════════════════════════════════════════════
    private JPanel panelEvento() {
        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel form = crearPanel(6);
        form.add(crearLabel("Nombre:"));       form.add(txtEventoNombre);
        form.add(crearLabel("Fecha:"));        form.add(txtEventoFecha);
        form.add(crearLabel("Hora:"));         form.add(txtEventoHora);
        form.add(crearLabel("Lugar:"));        form.add(txtEventoLugar);
        form.add(crearLabel("Patrocinador:")); form.add(txtEventoPatrocinador);
        JButton btn = new JButton("Crear Evento");
        form.add(new JLabel()); form.add(btn);

        JList<String> lista = new JList<>(modeloEventos);
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createTitledBorder("Eventos Creados"));

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> { modeloEventos.clear(); listaEventos.clear(); });

        btn.addActionListener(e -> {
            if (txtEventoNombre.getText().isEmpty()) { mostrarError("El nombre es obligatorio"); return; }
            if (txtEventoFecha.getText().isEmpty())  { mostrarError("La fecha es obligatoria");  return; }
            if (txtEventoLugar.getText().isEmpty())  { mostrarError("El lugar es obligatorio");  return; }

            Evento evento = new Evento();
            evento.setNombreEvento(txtEventoNombre.getText());
            evento.setFecha(new Date());
            evento.setHora(new Date());
            evento.setLugar(txtEventoLugar.getText());
            evento.setPatrocinador(txtEventoPatrocinador.getText());

            listaEventos.add(evento);
            modeloEventos.addElement(
                    txtEventoNombre.getText()
                            + " | " + txtEventoLugar.getText()
                            + " | " + txtEventoFecha.getText()
                            + " | " + txtEventoHora.getText()
            );

            txtEventoNombre.setText(""); txtEventoFecha.setText("");
            txtEventoHora.setText("");   txtEventoLugar.setText("");
            txtEventoPatrocinador.setText("");

            mostrarOk("Evento creado. Total: " + listaEventos.size());
        });

        principal.add(form,       BorderLayout.NORTH);
        principal.add(scroll,     BorderLayout.CENTER);
        principal.add(btnLimpiar, BorderLayout.SOUTH);
        return principal;
    }

    // ══════════════════════════════════════════════════════════
    // PANEL ZONA
    // ══════════════════════════════════════════════════════════
    private JPanel panelZona() {
        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel form = crearPanel(5);
        form.add(crearLabel("Nombre Zona:"));         form.add(txtZonaNombre);
        form.add(crearLabel("Precio:"));              form.add(txtZonaPrecio);
        form.add(crearLabel("Capacidad:"));           form.add(txtZonaCapacidad);
        form.add(crearLabel("Boletas Disponibles:")); form.add(txtZonaBoletas);
        JButton btn = new JButton("Guardar Zona");
        form.add(new JLabel()); form.add(btn);

        JList<String> lista = new JList<>(modeloZonas);
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createTitledBorder("Zonas Creadas"));

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> { modeloZonas.clear(); listaZonas.clear(); });

        btn.addActionListener(e -> {
            try {
                zona = new Zona();
                zona.setNombreZona(txtZonaNombre.getText());
                zona.setPrecio(Double.parseDouble(txtZonaPrecio.getText()));
                zona.setCapacidad(Integer.parseInt(txtZonaCapacidad.getText()));
                zona.setBoletasDisponibles(Integer.parseInt(txtZonaBoletas.getText()));

                listaZonas.add(zona);
                modeloZonas.addElement(
                        txtZonaNombre.getText()
                                + " | $" + txtZonaPrecio.getText()
                                + " | Cap: " + txtZonaCapacidad.getText()
                                + " | Boletas: " + txtZonaBoletas.getText()
                );

                txtZonaNombre.setText(""); txtZonaPrecio.setText("");
                txtZonaCapacidad.setText(""); txtZonaBoletas.setText("");

                mostrarOk("Zona guardada. Total: " + listaZonas.size());
            } catch (NumberFormatException ex) {
                mostrarError("Precio, capacidad y boletas deben ser numericos");
            }
        });

        principal.add(form,       BorderLayout.NORTH);
        principal.add(scroll,     BorderLayout.CENTER);
        principal.add(btnLimpiar, BorderLayout.SOUTH);
        return principal;
    }

    // ══════════════════════════════════════════════════════════
    // PANEL BOLETA
    // ══════════════════════════════════════════════════════════
    private JPanel panelBoleta() {
        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel form = crearPanel(3);
        form.add(crearLabel("Estado:"));        form.add(txtBoletaEstado);
        form.add(crearLabel("Total Boletas:")); form.add(txtBoletaTotal);
        JButton btn = new JButton("Guardar Boleta");
        form.add(new JLabel()); form.add(btn);

        JList<String> lista = new JList<>(modeloBoletas);
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createTitledBorder("Boletas Creadas"));

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> { modeloBoletas.clear(); listaBoletas.clear(); });

        btn.addActionListener(e -> {
            if (zona == null) { mostrarError("Primero crea una Zona"); return; }
            try {
                Boleta boleta;
                boleta = new Boleta();
                boleta.setEstado(Integer.parseInt(txtBoletaEstado.getText()));
                boleta.setTotalBoletas(Integer.parseInt(txtBoletaTotal.getText()));
                boleta.setZonas(new ArrayList<>());
                boleta.getZonas().add(zona);

                listaBoletas.add(boleta);
                modeloBoletas.addElement(
                        "Estado: " + txtBoletaEstado.getText()
                                + " | Total: " + txtBoletaTotal.getText()
                                + " | Zona: " + zona.getNombreZona()
                );

                txtBoletaEstado.setText(""); txtBoletaTotal.setText("");
                mostrarOk("Boleta guardada. Total: " + listaBoletas.size());
            } catch (NumberFormatException ex) {
                mostrarError("Estado y total deben ser numericos");
            }
        });

        principal.add(form,       BorderLayout.NORTH);
        principal.add(scroll,     BorderLayout.CENTER);
        principal.add(btnLimpiar, BorderLayout.SOUTH);
        return principal;
    }

    // ══════════════════════════════════════════════════════════
    // PANEL COMPRADOR
    // ══════════════════════════════════════════════════════════
    private JPanel panelComprador() {
        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel form = crearPanel(3);
        form.add(crearLabel("Nombre:"));         form.add(txtCompradorNombre);
        form.add(crearLabel("Identificacion:")); form.add(txtCompradorId);
        JButton btn = new JButton("Registrar Comprador");
        form.add(new JLabel()); form.add(btn);

        JList<String> lista = new JList<>(modeloCompradores);
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createTitledBorder("Compradores Registrados"));

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> { modeloCompradores.clear(); listaCompradores.clear(); });

        btn.addActionListener(e -> {
            if (txtCompradorNombre.getText().isEmpty()) { mostrarError("El nombre es obligatorio"); return; }
            if (txtCompradorId.getText().isEmpty())     { mostrarError("La identificacion es obligatoria"); return; }
            try {
                comprador = new Comprador(
                        txtCompradorNombre.getText(),
                        Integer.parseInt(txtCompradorId.getText())
                );

                listaCompradores.add(comprador);
                modeloCompradores.addElement(
                        txtCompradorNombre.getText()
                                + " | ID: " + txtCompradorId.getText()
                );

                txtCompradorNombre.setText(""); txtCompradorId.setText("");
                mostrarOk("Comprador registrado. Total: " + listaCompradores.size());
            } catch (NumberFormatException ex) {
                mostrarError("La identificacion debe ser numerica");
            }
        });

        principal.add(form,       BorderLayout.NORTH);
        principal.add(scroll,     BorderLayout.CENTER);
        principal.add(btnLimpiar, BorderLayout.SOUTH);
        return principal;
    }

    // ══════════════════════════════════════════════════════════
    // PANEL COMPRA
    // ══════════════════════════════════════════════════════════
    private JPanel panelCompra() {
        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel form = crearPanel(3);
        form.add(crearLabel("Valor Total:")); form.add(txtCompraValor);
        form.add(crearLabel("Estado:"));      form.add(cmbEstado);
        JButton btn = new JButton("Confirmar Compra");
        form.add(new JLabel()); form.add(btn);

        JList<String> lista = new JList<>(modeloCompras);
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createTitledBorder("Compras Realizadas"));

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> { modeloCompras.clear(); listaCompras.clear(); });

        btn.addActionListener(e -> {
            if (comprador == null)              { mostrarError("Primero registra un Comprador"); return; }
            if (txtCompraValor.getText().isEmpty()) { mostrarError("El valor es obligatorio"); return; }
            try {
                Compra compra = new Compra(
                        LocalDate.now(),
                        Double.parseDouble(txtCompraValor.getText()),
                        (String) cmbEstado.getSelectedItem(),
                        comprador
                );

                listaCompras.add(compra);
                modeloCompras.addElement(
                        "$" + txtCompraValor.getText()
                                + " | " + cmbEstado.getSelectedItem()
                                + " | " + comprador.getNombre()
                                + " | " + LocalDate.now()
                );

                txtCompraValor.setText("");
                mostrarOk("Compra registrada. Total: " + listaCompras.size());
            } catch (NumberFormatException ex) {
                mostrarError("El valor debe ser numerico");
            }
        });

        principal.add(form,       BorderLayout.NORTH);
        principal.add(scroll,     BorderLayout.CENTER);
        principal.add(btnLimpiar, BorderLayout.SOUTH);
        return principal;
    }

    // ══════════════════════════════════════════════════════════
    // PANEL PAGO
    // ══════════════════════════════════════════════════════════
    private JPanel panelPago() {
        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel form = crearPanel(4);
        form.add(crearLabel("Comprobante:"));    form.add(txtPagoComprobante);
        form.add(crearLabel("Valor:"));          form.add(txtPagoValor);
        form.add(crearLabel("Metodo de Pago:")); form.add(cmbMetodo);
        JButton btn = new JButton("Registrar Pago");
        form.add(new JLabel()); form.add(btn);

        JList<String> lista = new JList<>(modeloPagos);
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(BorderFactory.createTitledBorder("Pagos Registrados"));

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> { modeloPagos.clear(); listaPagos.clear(); });

        btn.addActionListener(e -> {
            if (txtPagoComprobante.getText().isEmpty()) { mostrarError("El comprobante es obligatorio"); return; }
            if (txtPagoValor.getText().isEmpty())       { mostrarError("El valor es obligatorio"); return; }
            try {
                Pago pago = new Pago(
                        txtPagoComprobante.getText(),
                        Double.parseDouble(txtPagoValor.getText()),
                        LocalDate.now()
                );
                MetodoPago metodo = new MetodoPago(
                        (String) cmbMetodo.getSelectedItem(), ""
                );
                metodo.procesarPago(pago);

                listaPagos.add(pago);
                modeloPagos.addElement(
                        txtPagoComprobante.getText()
                                + " | $" + txtPagoValor.getText()
                                + " | " + cmbMetodo.getSelectedItem()
                                + " | " + LocalDate.now()
                );

                txtPagoComprobante.setText(""); txtPagoValor.setText("");
                mostrarOk(pago.mostrarResumen());
            } catch (NumberFormatException ex) {
                mostrarError("El valor debe ser numerico");
            }
        });

        principal.add(form,       BorderLayout.NORTH);
        principal.add(scroll,     BorderLayout.CENTER);
        principal.add(btnLimpiar, BorderLayout.SOUTH);
        return principal;
    }

    // ══════════════════════════════════════════════════════════
    // MAIN
    // ══════════════════════════════════════════════════════════
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazUnica().setVisible(true));
    }
}