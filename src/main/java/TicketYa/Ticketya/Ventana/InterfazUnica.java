package TicketYa.Ticketya.Ventana;

import TicketYa.Ticketya.gestion.Evento;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class InterfazUnica extends JFrame {

    // Colores y Estilo
    private final Color BG_SIDEBAR = Color.BLACK;
    private final Color BG_CONTENT = new Color(20, 20, 20);
    private final Color ACCENT_GREEN = new Color(40, 160, 80);
    private final Color ACCENT_RED = new Color(180, 50, 50);

    // Navegación y Datos
    private CardLayout cardLayout = new CardLayout();
    private JPanel panelCentral = new JPanel(cardLayout);
    private DefaultListModel<Evento> modeloEventos = new DefaultListModel<>();

    // Variables de Estado
    private Evento eventoSeleccionado;
    private String zonaSeleccionada;
    private JPanel pnlZonasDinamico;

    // Campos de Pago
    private JTextField txtNomComp, txtCedComp, txtCantComp;
    private JComboBox<String> cbMetodo;

    public InterfazUnica() {
        setTitle("TicketYa - Admin Suite");
        setSize(1100, 850);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- SIDEBAR ---
        JPanel sidebar = new JPanel(new GridLayout(10, 1, 0, 5));
        sidebar.setBackground(BG_SIDEBAR);
        sidebar.setPreferredSize(new Dimension(180, 0));
        sidebar.add(new JLabel(" TICKETYA") {{ setForeground(Color.WHITE); setFont(new Font("SansSerif", Font.BOLD, 20)); }});
        sidebar.add(crearBotonMenu("Eventos", "VISTA_EVENTOS"));
        sidebar.add(crearBotonMenu("Zonas", "VISTA_ZONAS"));

        panelCentral.add(panelRegistroEventos(), "VISTA_EVENTOS");
        panelCentral.add(panelZonasDetalle(), "VISTA_ZONAS");
        panelCentral.add(panelFormularioPago(), "VISTA_PAGO");

        add(sidebar, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);
    }

    // --- MÓDULO 1: REGISTRO Y GESTIÓN ---
    private JPanel panelRegistroEventos() {
        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBackground(BG_CONTENT);
        main.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // FORMULARIO SUPERIOR
        JPanel formContainer = new JPanel();
        formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
        formContainer.setOpaque(false);

        JPanel pnlBasico = crearSeccionForm("Información Básica");
        JTextField txtNom = new JTextField(); JTextField txtLug = new JTextField();
        JTextField txtFec = new JTextField(); JTextField txtHor = new JTextField();
        JTextField txtPat = new JTextField();

        pnlBasico.add(crearLabel("Nombre del Evento *")); pnlBasico.add(txtNom);
        pnlBasico.add(crearLabel("Ciudad/Lugar *")); pnlBasico.add(txtLug);
        pnlBasico.add(crearLabel("Fecha (dd/mm/aaaa) *")); pnlBasico.add(txtFec);
        pnlBasico.add(crearLabel("Hora (hh:mm) *")); pnlBasico.add(txtHor);
        pnlBasico.add(crearLabel("Patrocinador *")); pnlBasico.add(txtPat);

        JPanel pnlSecciones = new JPanel(new GridLayout(3, 1, 5, 5));
        pnlSecciones.setOpaque(false);
        JTextField vP = new JTextField("0"); JTextField vC = new JTextField("0");
        JTextField pP = new JTextField("0"); JTextField pC = new JTextField("0");
        JTextField gP = new JTextField("0"); JTextField gC = new JTextField("0");

        pnlSecciones.add(crearFilaSeccion("Sección 1: VIP", vP, vC));
        pnlSecciones.add(crearFilaSeccion("Sección 2: Platea", pP, pC));
        pnlSecciones.add(crearFilaSeccion("Sección 3: General", gP, gC));

        JButton btnCrear = new JButton("CREAR EVENTO");
        estilizarBoton(btnCrear, ACCENT_GREEN);

        formContainer.add(pnlBasico);
        formContainer.add(Box.createVerticalStrut(10));
        formContainer.add(pnlSecciones);
        formContainer.add(Box.createVerticalStrut(10));
        formContainer.add(btnCrear);

        // LISTA CENTRAL
        JList<Evento> lista = new JList<>(modeloEventos);
        lista.setBackground(new Color(30, 30, 30));
        lista.setForeground(Color.WHITE);
        lista.setCellRenderer(new EventoRenderer());

        btnCrear.addActionListener(e -> {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
                sdf.setLenient(false);

                for (int i = 0; i < modeloEventos.size(); i++) {
                    Evento ex = modeloEventos.get(i);
                    if (sdf.format(ex.getFecha()).equals(txtFec.getText()) && stf.format(ex.getHora()).equals(txtHor.getText())) {
                        JOptionPane.showMessageDialog(this, "¡ERROR! Ya existe un evento en esa fecha y hora.", "Cruce de Horario", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                Evento ev = new Evento(txtNom.getText(), sdf.parse(txtFec.getText()), stf.parse(txtHor.getText()),
                        txtLug.getText(), txtPat.getText(),
                        Integer.parseInt(vC.getText()), Integer.parseInt(pC.getText()), Integer.parseInt(gC.getText()),
                        Double.parseDouble(vP.getText()), Double.parseDouble(pP.getText()), Double.parseDouble(gP.getText()),
                        "ACTIVO");

                modeloEventos.addElement(ev);
                mostrarMensajeRegistro(ev);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error de formato.");
            }
        });

        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Evento sel = lista.getSelectedValue();
                    if (sel != null && !sel.getEstado().equals("CANCELADO")) {
                        eventoSeleccionado = sel;
                        actualizarVistaZonas();
                        cardLayout.show(panelCentral, "VISTA_ZONAS");
                    }
                }
            }
        });

        // --- AQUÍ REGRESA TU BOTÓN DE ELIMINAR/CANCELAR ---
        JButton btnGestionar = new JButton("CANCELAR O ELIMINAR EVENTO SELECCIONADO");
        estilizarBoton(btnGestionar, ACCENT_RED);
        btnGestionar.addActionListener(e -> {
            Evento sel = lista.getSelectedValue();
            if(sel != null) {
                Object[] opciones = {"CANCELAR EVENTO", "ELIMINAR DE LISTA", "VOLVER"};
                int r = JOptionPane.showOptionDialog(this, "¿Qué acción desea realizar?", "Gestión de Eventos",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);

                if(r == 0) {
                    sel.setEstado("CANCELADO");
                    lista.repaint();
                    JOptionPane.showMessageDialog(this, "Evento cancelado.");
                } else if(r == 1) {
                    modeloEventos.removeElement(sel);
                    JOptionPane.showMessageDialog(this, "Evento eliminado permanentemente.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un evento de la lista.");
            }
        });

        main.add(formContainer, BorderLayout.NORTH);
        main.add(new JScrollPane(lista), BorderLayout.CENTER);
        main.add(btnGestionar, BorderLayout.SOUTH);

        return main;
    }

    // --- MÓDULO 2: ZONAS ---
    private JPanel panelZonasDetalle() {
        pnlZonasDinamico = new JPanel(new BorderLayout(20, 20));
        pnlZonasDinamico.setBackground(BG_CONTENT);
        pnlZonasDinamico.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        actualizarVistaZonas();
        return pnlZonasDinamico;
    }

    private void actualizarVistaZonas() {
        pnlZonasDinamico.removeAll();
        if (eventoSeleccionado != null) {
            JPanel header = new JPanel(new GridLayout(2, 1));
            header.setOpaque(false);
            header.add(crearLabel("ZONAS PARA:"));
            header.add(new JLabel(eventoSeleccionado.getNombreEvento().toUpperCase()) {{
                setForeground(ACCENT_GREEN); setFont(new Font("SansSerif", Font.BOLD, 26));
            }});

            JPanel grid = new JPanel(new GridLayout(1, 3, 25, 0));
            grid.setOpaque(false);
            DecimalFormat df = new DecimalFormat("$#,###");

            grid.add(crearCardZona("VIP", eventoSeleccionado.getCantVip(), df.format(eventoSeleccionado.getPrecioVip())));
            grid.add(crearCardZona("PLATEA", eventoSeleccionado.getCantPlatea(), df.format(eventoSeleccionado.getPrecioPlatea())));
            grid.add(crearCardZona("GENERAL", eventoSeleccionado.getCantGeneral(), df.format(eventoSeleccionado.getPrecioGeneral())));

            JButton btnVolver = new JButton("<- VOLVER A EVENTOS");
            estilizarBoton(btnVolver, Color.DARK_GRAY);
            btnVolver.addActionListener(e -> cardLayout.show(panelCentral, "VISTA_EVENTOS"));

            pnlZonasDinamico.add(header, BorderLayout.NORTH);
            pnlZonasDinamico.add(grid, BorderLayout.CENTER);
            pnlZonasDinamico.add(btnVolver, BorderLayout.SOUTH);
        }
        pnlZonasDinamico.revalidate(); pnlZonasDinamico.repaint();
    }

    private JPanel crearCardZona(String zona, int cupo, String precio) {
        JPanel card = new JPanel(new GridLayout(4, 1, 10, 10));
        card.setBackground(new Color(35, 35, 35));
        card.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        card.add(new JLabel(zona, SwingConstants.CENTER) {{ setForeground(Color.WHITE); setFont(new Font("SansSerif", Font.BOLD, 18)); }});
        card.add(new JLabel(precio, SwingConstants.CENTER) {{ setForeground(ACCENT_GREEN); setFont(new Font("SansSerif", Font.BOLD, 22)); }});
        card.add(new JLabel("Cupos: " + cupo, SwingConstants.CENTER) {{ setForeground(Color.GRAY); }});

        JButton btn = new JButton("COMPRAR");
        estilizarBoton(btn, ACCENT_GREEN);
        btn.addActionListener(e -> {
            this.zonaSeleccionada = zona;
            cardLayout.show(panelCentral, "VISTA_PAGO");
        });
        card.add(btn);
        return card;
    }

    // --- MÓDULO 3: PAGO ---
    private JPanel panelFormularioPago() {
        JPanel main = new JPanel(new BorderLayout(20, 20));
        main.setBackground(BG_CONTENT);
        main.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 150));

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 20));
        form.setOpaque(false);

        txtNomComp = new JTextField(); txtCedComp = new JTextField();
        txtCantComp = new JTextField("1");
        cbMetodo = new JComboBox<>(new String[]{"PSE", "Tarjeta Crédito", "Tarjeta Débito"});

        form.add(crearLabel("Nombre Comprador:")); form.add(txtNomComp);
        form.add(crearLabel("Cédula:")); form.add(txtCedComp);
        form.add(crearLabel("Cantidad (Máx 10):")); form.add(txtCantComp);
        form.add(crearLabel("Método de Pago:")); form.add(cbMetodo);

        JButton btnConfirmar = new JButton("PAGAR AHORA");
        estilizarBoton(btnConfirmar, ACCENT_GREEN);
        btnConfirmar.addActionListener(e -> procesarVentaFinal());

        main.add(crearLabel("DETALLES DE PAGO FINAL"), BorderLayout.NORTH);
        main.add(form, BorderLayout.CENTER);
        main.add(btnConfirmar, BorderLayout.SOUTH);
        return main;
    }

    private void procesarVentaFinal() {
        try {
            int cant = Integer.parseInt(txtCantComp.getText());
            if (cant > 10 || cant <= 0) {
                JOptionPane.showMessageDialog(this, "Error: Debe comprar entre 1 y 10 boletas.");
                return;
            }

            boolean exito = false;
            if (zonaSeleccionada.equals("VIP") && eventoSeleccionado.getCantVip() >= cant) {
                eventoSeleccionado.setCantVip(eventoSeleccionado.getCantVip() - cant); exito = true;
            } else if (zonaSeleccionada.equals("PLATEA") && eventoSeleccionado.getCantPlatea() >= cant) {
                eventoSeleccionado.setCantPlatea(eventoSeleccionado.getCantPlatea() - cant); exito = true;
            } else if (zonaSeleccionada.equals("GENERAL") && eventoSeleccionado.getCantGeneral() >= cant) {
                eventoSeleccionado.setCantGeneral(eventoSeleccionado.getCantGeneral() - cant); exito = true;
            }

            if (exito) {
                JOptionPane.showMessageDialog(this, "¡TRANSACCIÓN EXITOSA!\nBoletas: " + cant + "\nZona: " + zonaSeleccionada);
                actualizarVistaZonas();
                cardLayout.show(panelCentral, "VISTA_EVENTOS");
            } else {
                JOptionPane.showMessageDialog(this, "Lo sentimos, no hay suficientes cupos.");
            }
        } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Datos inválidos."); }
    }

    // --- AUXILIARES ---
    private void mostrarMensajeRegistro(Evento ev) {
        DecimalFormat df = new DecimalFormat("$#,###");
        String t = "EVENTO REGISTRADO\nPatrocinador: " + ev.getPatrocinador() + "\nEstado: " + ev.getEstado() + "\n\nVIP: " + df.format(ev.getPrecioVip()) + "\nPLATEA: " + df.format(ev.getPrecioPlatea()) + "\nGENERAL: " + df.format(ev.getPrecioGeneral());
        JOptionPane.showMessageDialog(this, new JTextArea(t));
    }

    private JPanel crearSeccionForm(String titulo) {
        JPanel p = new JPanel(new GridLayout(0, 2, 10, 10)); p.setOpaque(false);
        p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), titulo, 0, 0, null, Color.WHITE));
        return p;
    }

    private JPanel crearFilaSeccion(String n, JTextField p, JTextField c) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT)); panel.setOpaque(false);
        panel.add(crearLabel(n)); panel.add(new JLabel(" $:") {{ setForeground(Color.GRAY); }});
        panel.add(p); p.setPreferredSize(new Dimension(60, 25));
        panel.add(new JLabel(" Cant:") {{ setForeground(Color.GRAY); }});
        panel.add(c); c.setPreferredSize(new Dimension(50, 25));
        return panel;
    }

    private JLabel crearLabel(String t) { return new JLabel(t) {{ setForeground(Color.WHITE); }}; }
    private void estilizarBoton(JButton b, Color bg) { b.setBackground(bg); b.setForeground(Color.WHITE); b.setFocusPainted(false); b.setCursor(new Cursor(Cursor.HAND_CURSOR)); }
    private JButton crearBotonMenu(String txt, String vista) {
        JButton b = new JButton(txt); b.setBackground(BG_SIDEBAR); b.setForeground(Color.LIGHT_GRAY);
        b.addActionListener(e -> cardLayout.show(panelCentral, vista));
        return b;
    }

    class EventoRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object val, int idx, boolean sel, boolean foc) {
            JLabel l = (JLabel) super.getListCellRendererComponent(list, val, idx, sel, foc);
            Evento e = (Evento) val;
            l.setForeground(e.getEstado().equals("CANCELADO") ? Color.RED : new Color(100, 255, 100));
            l.setText(String.format("%s | %s | %s | %s", e.getNombreEvento().toUpperCase(), e.getLugar().toUpperCase(), e.getPatrocinador().toUpperCase(), e.getEstado()));
            return l;
        }
    }

    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new InterfazUnica().setVisible(true)); }
}