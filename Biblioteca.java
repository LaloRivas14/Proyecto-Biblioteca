
/**
 * Write a description of class Biblioteca here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class Biblioteca{
    private String temp;
    private String nombre;
    private ArrayList<Libro> libros;
    private ArrayList<Socio> socios;
    
    public Biblioteca(String p_nombre){
        this.setNombre(p_nombre);
        this.setLibros(new ArrayList<Libro>());
        this.setSocios(new ArrayList<Socio>());
    }
    
    private void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }
    public String getNombre(){
        return this.nombre;
    }
    
    private void setLibros(ArrayList<Libro> p_libros){
        this.libros = p_libros;
    }
    private ArrayList<Libro> getLibros(){
        return this.libros;
    }
    
    private void setSocios(ArrayList<Socio> p_socios){
        this.socios = p_socios;
    }
    public ArrayList<Socio> getSocios(){
        return this.socios;
    }
    
    public boolean agregarLibro(Libro p_libro){
        return this.getLibros().add(p_libro);
    }
    public boolean removerLibro(Libro p_libro){
        return this.getLibros().remove(p_libro);
    }
    
    public void nuevoLibro(String p_titulo,int p_edicion,String p_editorial,int p_anio){
        this.getLibros().add(new Libro(p_titulo,p_edicion,p_editorial,p_anio));
    }
    
    public void nuevoSocioEstudiante(int p_dniSocio,String p_nombre,String p_carrera){
        this.getSocios().add(new Estudiante(p_dniSocio,p_nombre,p_carrera));
    }
    
    public void nuevoSocioDocente(int p_dniSocio,String p_nombre,String p_area){
        this.getSocios().add(new Docente(p_dniSocio,p_nombre,p_area));
    }
}