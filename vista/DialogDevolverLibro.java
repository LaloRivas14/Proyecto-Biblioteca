package vista;


/**
 * Write a description of class DialogDevolverLibro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import p_biblioteca.*;

public class DialogDevolverLibro extends JDialog {
    private JTextField campoTitulo;
    private JButton btnDevolver;

    public DialogDevolverLibro(JFrame parent, Biblioteca biblioteca, JTextArea areaDatos) {
        super(parent, "Devolver Libro", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(2, 2, 5, 5));

        campoTitulo = new JTextField();
        btnDevolver = new JButton("Devolver");

        add(new JLabel("Título del libro:"));
        add(campoTitulo);
        add(new JLabel(""));
        add(btnDevolver);

        btnDevolver.addActionListener(e -> {
            try {
                String titulo = campoTitulo.getText();
                Libro libro = buscarLibro(titulo, biblioteca.getLibros());

                if (libro != null) {
                    biblioteca.devolverLibro(libro);
                    areaDatos.setText("✅ Libro devuelto correctamente:\n" + libro.getTitulo());
                    dispose();
                } else {
                    areaDatos.setText("❌ Libro no encontrado.");
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
