package vista;


/**
 * Write a description of class PersistenciaBiblioteca here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import p_biblioteca.*;
import javax.swing.JOptionPane;

public class PersistenciaBiblioteca {

    public static Biblioteca leerBiblioteca() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Biblioteca.dat"))) {
            return (Biblioteca) in.readObject();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Archivo no encontrado. Se creará una nueva biblioteca.");
            return new Biblioteca("Biblioteca Nueva");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la biblioteca: " + e.getMessage());
            return new Biblioteca("Biblioteca Nueva");
        }
    }

    public static void guardarBiblioteca(Biblioteca biblio) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Biblioteca.dat"))) {
            out.writeObject(biblio);
            JOptionPane.showMessageDialog(null, "Biblioteca guardada correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la biblioteca: " + e.getMessage());
        }
    }
}