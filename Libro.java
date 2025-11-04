import java.util.ArrayList;

/**
 * Representa un libro dentro del sistema de gestion de biblioteca.
 * Guarda informacion basica del libro y los prestamos que tuvo.
 * Permite saber si el libro esta prestado actualmente y obtener su ultimo prestamo.
 * 
 * @author Pawlizki Micaela
 * @version 1.0
 */
public class Libro{
    /** Titulo del libro */
    private String titulo;
    /** Numero de edicion del libro */
    private int edicion;
    /** Editorial del libro */
    private String editorial;
    /** Anio de publicacion del libro */
    private int anio;
    /** Lista de prestamos asociados al libro */
    private ArrayList<Prestamo> prestamos;
    
    /**
     * Constructor que crea un libro sin prestamos previos.
     * @param p_titulo titulo del libro
     * @param p_edicion numero de edicion
     * @param p_editorial editorial del libro
     * @param p_anio anio de publicacion
     */
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio){
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamos(new ArrayList<Prestamo>());
    }
    
    /**
     * Constructor que crea un libro con prestamos ya cargados.
     * @param p_titulo titulo del libro
     * @param p_edicion numero de edicion
     * @param p_editorial editorial del libro
     * @param p_anio anio de publicacion
     * @param p_prestamos lista de prestamos
     */
    public Libro(String p_titulo, int p_edicion, String p_editorial, int p_anio, ArrayList<Prestamo> p_prestamos){
        this.setTitulo(p_titulo);
        this.setEdicion(p_edicion);
        this.setEditorial(p_editorial);
        this.setAnio(p_anio);
        this.setPrestamos(p_prestamos);
    }
    
    private void setTitulo(String p_titulo){
        this.titulo = p_titulo;
    }
    
    private void setEdicion(int p_edicion){
        this.edicion = p_edicion;
    }
    
    private void setEditorial(String p_editorial){
        this.editorial = p_editorial;
    }
    
    private void setAnio(int p_anio){
        this.anio = p_anio;
    }
    
    private void setPrestamos(ArrayList<Prestamo> p_prestamo){
        this.prestamos = p_prestamo;
    }
    
    /** @return titulo del libro */
    public String getTitulo(){
        return this.titulo;
    }
    
    /** @return numero de edicion */
    public int getEdicion(){
        return this.edicion;
    }
    
    /** @return editorial del libro */
    public String getEditorial(){
        return this.editorial;
    }
    
    /** @return anio de publicacion */
    public int getAnio(){
        return this.anio;
    }
    
    /** @return lista de prestamos */
    public ArrayList<Prestamo> getPrestamos(){
        return this.prestamos;
    }
    
    /**
     * Agrega un prestamo al libro.
     * @param p_prestamo prestamo a registrar
     * @return true si se agrego correctamente
     */
    public boolean agregarPrestamo(Prestamo p_prestamo){
        return this.getPrestamos().add(p_prestamo);
    }
    
    /**
     * Quita un prestamo del libro.
     * @param p_prestamo prestamo a eliminar
     * @return true si se removio correctamente
     */
    public boolean quitarPrestamo(Prestamo p_prestamo){
        return this.getPrestamos().remove(p_prestamo);
    }
    
    /**
     * Obtiene el ultimo prestamo realizado sobre el libro.
     * @return ultimo prestamo o null si nunca fue prestado
     */
    public Prestamo ultimoPrestamo(){
        if(this.getPrestamos().isEmpty()){
            return null;
        }
        
        return this.getPrestamos().get(this.getPrestamos().size() - 1);
    }
    
    /**
     * Indica si el libro esta actualmente prestado.
     * @return true si esta prestado, false si esta disponible
     */
    public boolean prestado(){
        Prestamo ultimo = this.ultimoPrestamo();
        
        return (ultimo != null && ultimo.getFechaDevolucion() == null);
    }
    
    /**
     * Representacion en texto del libro.
     * @return cadena con el titulo del libro
     */
    public String toString(){
        return "titulo: " + this.getTitulo();
    }
}
