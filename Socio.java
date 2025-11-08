 


/**
 *Clase abstracta Socio TP7.
 */
import java.util.ArrayList;
import java.util.Calendar;
public abstract class Socio 
{
    private int dniSocio;
    private String nombre;
    private int diasPrestamo;
    private ArrayList<Prestamo> prestamos;
    /**
     * Constructor que inicializa un socio con DNI, nombre, días de préstamo y una lista de préstamos 
     * La lista de préstamos se establece a partir del parámetro.
     *
     * @param p_dniSocio el DNI del socio
     * @param p_nombre el nombre del socio
     * @param p_diasPrestamos los días permitidos para los préstamos
     * @param p_prestamos la lista de préstamos del socio
     */
    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo, ArrayList<Prestamo> p_prestamos){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(p_prestamos);
    }
    /**
     * Constructor que inicializa un socio con DNI, nombre, días de préstamo y una lista de préstamos vacía.
     * @param p_dniSocio el DNI del socio
     * @param p_nombre el nombre del socio
     * @param p_diasPrestamos los días permitidos para los préstamos
     */
    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(new ArrayList<Prestamo>());
    }
    /**
     * Establece el DNI del socio.
     * 
     * @param p_dniSocio el DNI del socio
     */
     private void setDniSocio(int p_dniSocio){
         this.dniSocio = p_dniSocio;
        }
        /**
     * Establece el nombre del socio.
     * 
     * @param p_nombre el nombre del socio
     */
    private void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }
    /**
     * Establece los días de préstamo permitidos para el socio.
     * 
     * @param p_diasPrestamos los días de préstamo
     */
    protected void setDiasPrestamo(int p_diasPrestamo){
        this.diasPrestamo = p_diasPrestamo;
    }
    /**
     * Establece la lista de préstamos del socio.
     * 
     * @param p_prestamos la lista de préstamos
     */
    private void setPrestamos(ArrayList<Prestamo> p_prestamos){
        this.prestamos = p_prestamos;
    }
    /**
     * Obtiene el DNI del socio.
     * 
     * @return el DNI del socio
     */
    public int getDniSocio(){
        return this.dniSocio;
    }
    /**
     * Obtiene el nombre del socio.
     * 
     * @return el nombre del socio
     */
    public String getNombre(){
        return this.nombre;
    }
    /**
     * Obtiene los días de préstamo permitidos para el socio.
     * 
     * @return los días de préstamo del socio
     */
    public int getDiasPrestamo(){
        return this.diasPrestamo;
    }
    /**
     * Obtiene la lista de préstamos activos del socio.
     * 
     * @return la lista de préstamos
     */
    public ArrayList<Prestamo> getPrestamos(){
        return this.prestamos;
    }
    /**
     * Agrega un préstamo a la lista de préstamos del socio.
     * 
     * @param p_prestamo el préstamo a agregar
     * @return verdadero si el préstamo se agregó correctamente, falso en caso contrario
     */
    public boolean agregarPrestamo(Prestamo p_prestamo){
        return this.getPrestamos().add(p_prestamo);
    }
    /**
     * Elimina un préstamo de la lista de préstamos del socio.
     * 
     * @param p_prestamo el préstamo a eliminar
     * @return verdadero si el préstamo se eliminó correctamente, falso en caso contrario
     */
    public boolean quitarPrestamo(Prestamo p_prestamo){ 
         return this.getPrestamos().remove(p_prestamo);
    }
    /**
     * Devuelve la cantidad de libros actualmente prestados al socio.
     * 
     * @return el número de libros prestados
     */
    public int cantLibrosPrestados(){
        int prestados = 0;
        for(Prestamo prestamo : this.getPrestamos()){
            if(prestamo.getFechaDevolucion() == null){
                prestados++;
            }
     }
        return prestados;
    }
    /**
     * Devuelve una representación en cadena del socio, incluyendo su DNI, nombre, tipo de socio y cantidad de libros prestados.
     * 
     * @return una cadena con la información del socio
     */
    public String toString(){
        return ("DNI:" + this.getDniSocio() + " || " + this.getNombre() + 
        " (" + this.soyDeLaClase() + ") || Libros Prestados: " + this.cantLibrosPrestados());
    }
    /**
     * Verifica si el socio puede solicitar más préstamos, basado en si tiene préstamos vencidos.
     * 
     * @return verdadero si el socio puede pedir un nuevo préstamo, falso si tiene algún préstamo vencido
     */
    public boolean puedePedir(){
        Calendar fechaHoy = Calendar.getInstance();
        for(Prestamo prestamo: this.getPrestamos()){
            if(prestamo.vencido(fechaHoy)){
                return false;
            }
        }
        return true;
    }
    /**
     * Método abstracto que debe ser implementado por las clases hijas para devolver el tipo de socio.
     * 
     * @return el tipo de socio como una cadena
     */
    public abstract String soyDeLaClase();
}
