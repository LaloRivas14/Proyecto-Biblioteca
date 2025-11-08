package vista;


/**
 * Write a description of class DialogNuevoSocio here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import p_biblioteca.*;

public class DialogNuevoSocio extends JDialog {
    private JComboBox<String> comboTipo;
    private JTextField campoDni, campoNombre, campoCarreraArea;
    private JButton btnAceptar;

    public DialogNuevoSocio(JFrame parent, Biblioteca biblioteca, JTextArea areaDatos) {
        super(parent, "Nuevo Socio", true);
        setSize(350, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 2, 5, 5));

        comboTipo = new JComboBox<>(new String[]{"Estudiante", "Docente"});
        campoDni = new JTextField();
        campoNombre = new JTextField();
        campoCarreraArea = new JTextField();
        btnAceptar = new JButton("Agregar");

        add(new JLabel("Tipo de Socio:"));
        add(comboTipo);
        add(new JLabel("DNI:"));
        add(campoDni);
        add(new JLabel("Nombre y Apellido:"));
        add(campoNombre);
        add(new JLabel("Carrera / Área:"));
        add(campoCarreraArea);
        add(new JLabel(""));
        add(btnAceptar);

        btnAceptar.addActionListener(e -> {
            try {
                int dni = Integer.parseInt(campoDni.getText());
                String nombre = campoNombre.getText();
                String extra = campoCarreraArea.getText();

                if (comboTipo.getSelectedItem().equals("Estudiante")) {
                    biblioteca.nuevoSocioEstudiante(dni, nombre, extra);
                    areaDatos.setText("🎓 Estudiante agregado:\n" + nombre);
                } else {
                    biblioteca.nuevoSocioDocente(dni, nombre, extra);
                    areaDatos.setText("👨‍🏫 Docente agregado:\n" + nombre);
                }

                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
