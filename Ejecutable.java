 


/**
 * Write a description of class Ejecutable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.*;
import vista.*;
public class Ejecutable {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Mostrar diálogo de confirmación
            int respuesta = JOptionPane.showOptionDialog(
                null,
                "¿Desea cargar la biblioteca?",
                "Cargar datos",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Sí", "No"},
                "Sí"
            );

            // Crear ventana principal
            VentanaPrincipal ventana = new VentanaPrincipal();

            // Crear controlador con decisión del usuario
            Controlador controlador = new Controlador(ventana, respuesta == JOptionPane.YES_OPTION);
        });
    }
}
