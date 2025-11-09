package vista;

/**
 * Write a description of class controlador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import vista.*;
import p_biblioteca.*;
import java.util.*;
import javax.swing.*;

public class Controlador {
    private VentanaPrincipal vista;
    private Biblioteca biblioteca;

    public Controlador(VentanaPrincipal vista, boolean cargarDesdeArchivo) {
        this.vista = vista;

        if (cargarDesdeArchivo) {
            this.biblioteca = PersistenciaBiblioteca.leerBiblioteca();
            vista.getAreaDatos().setText("📂 Biblioteca cargada desde archivo.");
        } else {
            this.biblioteca = new Biblioteca("Biblioteca Visual");
            cargarDatosIniciales();
            vista.getAreaDatos().setText("📘 Biblioteca iniciada con datos precargados.");
        }

        inicializarEventos();
    }

    private void cargarDatosIniciales() {
        Calendar fecha = Calendar.getInstance();
        fecha.set(2025, Calendar.AUGUST, 15);

        // Libros
        Libro lb1 = new Libro("Programando con Java", 5, "Curuzu", 2023);
        Libro lb2 = new Libro("Programando con C++", 2, "Curuzu", 2002);
        Libro lb3 = new Libro("Estructuras de Datos", 3, "AlfaOmega", 2019);
        biblioteca.agregarLibro(lb1);
        biblioteca.agregarLibro(lb2);
        biblioteca.agregarLibro(lb3);

        // Socios
        biblioteca.nuevoSocioEstudiante(33091399, "Esteban Quito", "Sistemas");
        biblioteca.nuevoSocioDocente(22091399, "Alfredo Guzman", "Objetos");

        // Préstamos
        biblioteca.prestarLibro(fecha, biblioteca.buscarSocio(33091399), lb1);
        biblioteca.prestarLibro(fecha, biblioteca.buscarSocio(22091399), lb2);
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
                    JOptionPane.showMessageDialog(vista,"Cerrando programa...");
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
        
        vista.getItemGuardar().addActionListener(e -> {
                    PersistenciaBiblioteca.guardarBiblioteca(biblioteca);
                    vista.getAreaDatos().setText("✅ Biblioteca guardada correctamente.");
            });

        vista.getItemCargar().addActionListener(e -> {
                    biblioteca = PersistenciaBiblioteca.leerBiblioteca();
                    vista.getAreaDatos().setText("📂 Biblioteca cargada desde archivo.");
            });
    }

}
