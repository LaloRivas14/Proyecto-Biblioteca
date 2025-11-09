package vista;


/**
 * Write a description of class DialogPrestarLibro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import p_biblioteca.*;

public class DialogPrestarLibro extends JDialog {
    private JTextField campoDni, campoTitulo;
    private JButton btnPrestar;

    public DialogPrestarLibro(JFrame parent, Biblioteca biblioteca, JTextArea areaDatos) {
        super(parent, "Prestar Libro", true);
        setSize(350, 180);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 2, 5, 5));

        campoDni = new JTextField();
        campoTitulo = new JTextField();
        btnPrestar = new JButton("Prestar");

        add(new JLabel("DNI del socio:"));
        add(campoDni);
        add(new JLabel("Título del libro:"));
        add(campoTitulo);
        add(new JLabel(""));
        add(btnPrestar);

        btnPrestar.addActionListener(e -> {
            try {
                int dni = Integer.parseInt(campoDni.getText());
                String titulo = campoTitulo.getText();

                Socio socio = biblioteca.buscarSocio(dni);
                Libro libro = buscarLibro(titulo, biblioteca.getLibros());

                if (libro != null && socio != null) {
                    Calendar fecha = Calendar.getInstance();
                    if(biblioteca.prestarLibro(fecha, socio, libro)){
                        areaDatos.setText("📚 Libro prestado con éxito:\n" + libro.getTitulo() + " a " + socio.getNombre());
                        dispose();
                    }else {
                        areaDatos.setText("📚 Libro ya prestado o Socio no cumple los requerimentos para un prestamo");
                    }
                    
                } else {
                    areaDatos.setText("❌ Socio o libro no encontrado.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }

    private Libro buscarLibro(String titulo, java.util.ArrayList<Libro> libros) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }
}
