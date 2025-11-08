package vista;


/**
 * Write a description of class DialogEliminar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import p_biblioteca.*;

public class DialogEliminar extends JDialog {
    private JComboBox<String> comboTipo;
    private JTextField campoDni, campoIndice;
    private JButton btnEliminar;

    public DialogEliminar(JFrame parent, Biblioteca biblioteca, JTextArea areaDatos) {
        super(parent, "Eliminar Elemento", true);
        setSize(350, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2, 5, 5));

        comboTipo = new JComboBox<>(new String[]{"Socio por DNI", "Libro por índice"});
        campoDni = new JTextField();
        campoIndice = new JTextField();
        btnEliminar = new JButton("Eliminar");

        add(new JLabel("Tipo de eliminación:"));
        add(comboTipo);
        add(new JLabel("DNI del socio:"));
        add(campoDni);
        add(new JLabel("Índice del libro:"));
        add(campoIndice);
        add(new JLabel(""));
        add(btnEliminar);

        btnEliminar.addActionListener(e -> {
            try {
                if (comboTipo.getSelectedItem().equals("Socio por DNI")) {
                    int dni = Integer.parseInt(campoDni.getText());
                    Socio socio = biblioteca.buscarSocio(dni);
                    biblioteca.eliminarSocio(socio);
                    areaDatos.setText("🧍 Socio eliminado correctamente: DNI " + dni);
                } else {
                    int indice = Integer.parseInt(campoIndice.getText()) - 1;
                    if (indice >= 0 && indice < biblioteca.getLibros().size()) {
                        Libro libro = biblioteca.getLibros().get(indice);
                        if (!libro.prestado()) {
                            biblioteca.removerLibro(libro);
                            areaDatos.setText("📕 Libro eliminado correctamente: " + libro.getTitulo());
                        } else {
                            areaDatos.setText("⚠️ El libro está prestado y no puede eliminarse.");
                        }
                    } else {
                        areaDatos.setText("❌ Índice fuera de rango.");
                    }
                }
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
