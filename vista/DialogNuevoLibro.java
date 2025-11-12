package vista;


/**
 * Tipo Dialog que se presenta al presionar el boton NuevoLibro
 * 
 */
import javax.swing.*;
import java.awt.*;
import p_biblioteca.*;

public class DialogNuevoLibro extends JDialog {
    private JTextField campoTitulo, campoEdicion, campoEditorial, campoAnio;
    private JButton btnAceptar;

    public DialogNuevoLibro(JFrame parent, Biblioteca biblioteca, JTextArea areaDatos) {
        super(parent, "Nuevo Libro", true);
        setSize(300, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 2, 5, 5));

        campoTitulo = new JTextField();
        campoEdicion = new JTextField();
        campoEditorial = new JTextField();
        campoAnio = new JTextField();
        btnAceptar = new JButton("Agregar");

        add(new JLabel("Título:"));
        add(campoTitulo);
        add(new JLabel("Edición:"));
        add(campoEdicion);
        add(new JLabel("Editorial:"));
        add(campoEditorial);
        add(new JLabel("Año:"));
        add(campoAnio);
        add(new JLabel(""));
        add(btnAceptar);

        btnAceptar.addActionListener(e -> {
            try {
                String titulo = campoTitulo.getText();
                int edicion = Integer.parseInt(campoEdicion.getText());
                String editorial = campoEditorial.getText();
                int anio = Integer.parseInt(campoAnio.getText());

                biblioteca.nuevoLibro(titulo, edicion, editorial, anio);
                areaDatos.setText(" Libro agregado correctamente:\n" + titulo);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
