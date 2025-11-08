package vista;


/**
 * Write a description of class DialogCantidadSocios here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import p_biblioteca.*;

public class DialogCantidadSocios extends JDialog {
    private JComboBox<String> comboTipo;
    private JButton btnConsultar;

    public DialogCantidadSocios(JFrame parent, Biblioteca biblioteca, JTextArea areaDatos) {
        super(parent, "Cantidad de Socios", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(2, 2, 5, 5));

        comboTipo = new JComboBox<>(new String[]{"Estudiante", "Docente"});
        btnConsultar = new JButton("Consultar");

        add(new JLabel("Tipo de socio:"));
        add(comboTipo);
        add(new JLabel(""));
        add(btnConsultar);

        btnConsultar.addActionListener(e -> {
            String tipo = comboTipo.getSelectedItem().toString().toLowerCase();
            int cantidad = biblioteca.cantidadDeSociosPorTipo(tipo);
            areaDatos.setText("👥 Cantidad de socios " + tipo + "s: " + cantidad);
            dispose();
        });

        setVisible(true);
    }
}
