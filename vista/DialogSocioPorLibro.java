package vista;


/**
 * Write a description of class DialogSocioPorLibro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import p_biblioteca.*;

public class DialogSocioPorLibro extends JDialog {
    private JTextField campoTitulo;
    private JButton btnConsultar;

    public DialogSocioPorLibro(JFrame parent, Biblioteca biblioteca, JTextArea areaDatos) {
        super(parent, "¿Quién tiene el libro?", true);
        setSize(350, 150);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(2, 2, 5, 5));

        campoTitulo = new JTextField();
        btnConsultar = new JButton("Consultar");

        add(new JLabel("Título del libro:"));
        add(campoTitulo);
        add(new JLabel(""));
        add(btnConsultar);

        btnConsultar.addActionListener(e -> {
            try {
                String titulo = campoTitulo.getText();
                Libro libro = buscarLibro(titulo, biblioteca.getLibros());

                if (libro != null) {
                    String resultado = biblioteca.quienTieneElLibro(libro);
                    areaDatos.setText("📖 El libro \"" + titulo + "\" lo tiene:\n" + resultado);
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
