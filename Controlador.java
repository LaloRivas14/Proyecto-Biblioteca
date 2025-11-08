
/**
 * Write a description of class controlador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import vista.*;
import p_biblioteca.*;

import javax.swing.*;

public class Controlador {
    private VentanaPrincipal vista;
    private Biblioteca biblioteca;

    public Controlador(VentanaPrincipal vista) {
        this.vista = vista;
        this.biblioteca = new Biblioteca("Biblioteca Visual");
        inicializarEventos();
    }

    private void inicializarEventos() {
        vista.getBtnNuevoLibro().addActionListener(e -> {
                    new DialogNuevoLibro(vista, biblioteca, vista.getAreaDatos());
            });

        vista.getBtnNuevoSocio().addActionListener(e -> {
                    new DialogNuevoSocio(vista, biblioteca, vista.getAreaDatos());
            });

        vista.getBtnEliminar().addActionListener(e -> {
                    new DialogEliminar(vista, biblioteca, vista.getAreaDatos());
            });

        vista.getBtnPrestar().addActionListener(e -> {
                    new DialogPrestarLibro(vista, biblioteca, vista.getAreaDatos());
            });

        vista.getBtnDevolver().addActionListener(e -> {
                    new DialogDevolverLibro(vista, biblioteca, vista.getAreaDatos());
            });

        vista.getBtnCantidadSocios().addActionListener(e -> {
                    new DialogCantidadSocios(vista, biblioteca, vista.getAreaDatos());
            });

        vista.getBtnSocioPorLibro().addActionListener(e -> {
                    new DialogSocioPorLibro(vista, biblioteca, vista.getAreaDatos());
            });

        vista.getBtnCerrar().addActionListener(e -> {
                    JOptionPane.showMessageDialog(vista, "Cerrando programa...");
                    System.exit(0);
            });

        vista.getItemDocentes().addActionListener(e -> {
                    vista.getAreaDatos().setText("👨‍🏫 Docentes responsables:\n" + biblioteca.listaDeDocentesResponsables());
            });

        vista.getItemVencidos().addActionListener(e -> {
                    StringBuilder sb = new StringBuilder("📅 Préstamos vencidos:\n");
                    for (Object p : biblioteca.prestamosVencidos()) {
                        Prestamo pr = (Prestamo) p;
                        sb.append(p.toString()).append("\n");
                    }
                    vista.getAreaDatos().setText(sb.toString());
            });

        vista.getItemSocios().addActionListener(e -> {
                    vista.getAreaDatos().setText("👥 Lista de socios:\n" + biblioteca.listaDeSocios());
            });

        vista.getItemTitulos().addActionListener(e -> {
                    vista.getAreaDatos().setText("📚 Lista de títulos:\n" + biblioteca.listaDeTitulos());
            });

        vista.getItemLibros().addActionListener(e -> {
                    vista.getAreaDatos().setText("📖 Lista de libros:\n" + biblioteca.listaDeLibros());
            });
    }
}