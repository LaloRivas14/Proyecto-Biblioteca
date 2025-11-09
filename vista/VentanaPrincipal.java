package vista;

/**
 * Write a description of class VentanaPrincipal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JTextArea areaDatos;
    private JButton btnNuevoLibro, btnNuevoSocio, btnEliminar, btnPrestar, btnDevolver, btnCantidadSocios, btnSocioPorLibro, btnCerrar;
    private JMenuItem itemDocentes, itemVencidos, itemSocios, itemTitulos, itemLibros,itemGuardar,itemCargar;
    
    public VentanaPrincipal() {
        setTitle("Sistema de Biblioteca");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inicializarComponentes();
        setVisible(true);
    }

    private void inicializarComponentes() {
        areaDatos = new JTextArea();
        areaDatos.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaDatos);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(8, 1, 5, 5));
        btnNuevoLibro = new JButton("1) Nuevo Libro");
        btnNuevoSocio = new JButton("2) Nuevo Socio");
        btnEliminar = new JButton("3) Eliminación");
        btnPrestar = new JButton("4) Prestar Libro");
        btnDevolver = new JButton("5) Devolver Libro");
        btnCantidadSocios = new JButton("6) Cantidad de Socios");
        btnSocioPorLibro = new JButton("7) ¿Quién tiene un libro?");
        btnCerrar = new JButton("8) Cerrar Programa");

        panelBotones.add(btnNuevoLibro);
        panelBotones.add(btnNuevoSocio);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnPrestar);
        panelBotones.add(btnDevolver);
        panelBotones.add(btnCantidadSocios);
        panelBotones.add(btnSocioPorLibro);
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.EAST);

        // Menú superior
        JMenuBar barraMenu = new JMenuBar();
        JMenu menuListas = new JMenu("Listas y Datos");
        JMenu menuGuardados = new JMenu("Guardados y Carga");

        itemDocentes = new JMenuItem("Docentes responsables");
        itemVencidos = new JMenuItem("Préstamos vencidos");
        itemSocios = new JMenuItem("Lista de socios");
        itemTitulos = new JMenuItem("Lista de títulos");
        itemLibros = new JMenuItem("Lista de libros");
        
        itemGuardar = new JMenuItem("Guardar biblioteca");
        itemCargar = new JMenuItem("Cargar biblioteca");

        menuListas.add(itemDocentes);
        menuListas.add(itemVencidos);
        menuListas.add(itemSocios);
        menuListas.add(itemTitulos);
        menuListas.add(itemLibros);
        
        menuGuardados.add(itemGuardar);
        menuGuardados.add(itemCargar);
        
        barraMenu.add(menuListas);
        barraMenu.add(menuGuardados);
        setJMenuBar(barraMenu);
    }

    public JTextArea getAreaDatos() {
        return areaDatos;
    }

    public JButton getBtnNuevoLibro() { return btnNuevoLibro; }

    public JButton getBtnNuevoSocio() { return btnNuevoSocio; }

    public JButton getBtnEliminar() { return btnEliminar; }

    public JButton getBtnPrestar() { return btnPrestar; }

    public JButton getBtnDevolver() { return btnDevolver; }

    public JButton getBtnCantidadSocios() { return btnCantidadSocios; }

    public JButton getBtnSocioPorLibro() { return btnSocioPorLibro; }

    public JButton getBtnCerrar() { return btnCerrar; }

    public JMenuItem getItemDocentes() { return itemDocentes; }

    public JMenuItem getItemVencidos() { return itemVencidos; }

    public JMenuItem getItemSocios() { return itemSocios; }

    public JMenuItem getItemTitulos() { return itemTitulos; }

    public JMenuItem getItemLibros() { return itemLibros; }
    
    public JMenuItem getItemGuardar() { return itemGuardar; }
    
    public JMenuItem getItemCargar() { return itemCargar; }
}
