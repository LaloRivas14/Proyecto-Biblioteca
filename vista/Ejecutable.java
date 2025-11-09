package vista;


/**
 * Write a description of class Ejecutable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.SwingUtilities;
import vista.*;


public class Ejecutable {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            new Controlador(ventana);
        });
    }
}
