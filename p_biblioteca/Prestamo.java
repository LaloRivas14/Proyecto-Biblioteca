package p_biblioteca;

import java.io.Serializable;
import java.util.Calendar;

public class Prestamo implements Serializable{
    private Calendar fechaRetiro;
    private Calendar fechaDevolucion;
    private Socio socio;
    private Libro libro;
    /**
     * Constructor que inicializa un préstamo con una fecha de retiro, fecha de devolución, 
     * el socio que realiza el préstamo y el libro prestado.
     * 
     * @param p_fechaRetiro la fecha en que el socio retira el libro
     * @param p_fechaDevolucion la fecha en que el libro debe ser devuelto
     * @param p_socio el socio que realiza el préstamo
     * @param p_libro el libro que es prestado
     */
    public Prestamo(Calendar p_fechaRetiro, Calendar p_fechaDevolucion, Socio p_socio, Libro p_libro) {
        this.setFechaRetiro(p_fechaRetiro);
        this.setSocio(p_socio);
        this.setLibro(p_libro);
    }
    /**
     * Constructor que inicializa un préstamo con una fecha de retiro, sin una fecha de devolución 
     * inicializada (por ejemplo, si el libro aún no ha sido devuelto), 
     * junto con el socio y el libro prestado.
     * 
     * @param p_fechaRetiro la fecha en que el socio retira el libro
     * @param p_socio el socio que realiza el préstamo
     * @param p_libro el libro que es prestado
     */
    public Prestamo(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro) {
        this.setFechaRetiro(p_fechaRetiro);
        this.setSocio(p_socio);
        this.setLibro(p_libro);
    }
    /**
     * Establece la fecha de retiro del préstamo.
     * 
     * @param p_fechaRetiro la fecha en que el socio retira el libro
     */
    public void setFechaRetiro(Calendar p_fechaRetiro) {
        this.fechaRetiro = p_fechaRetiro;
    }

    /**
     * Establece la fecha de devolución del préstamo.
     * 
     * @param p_fechaDevolucion la fecha en que el libro debe ser devuelto
     */
    public void setFechaDevolucion(Calendar p_fechaDevolucion) {
        this.fechaDevolucion = p_fechaDevolucion;
    }

    /**
     * Establece el socio que realiza el préstamo.
     * 
     * @param p_socio el socio que realiza el préstamo
     */
    public void setSocio(Socio p_socio) {
        this.socio = p_socio;
    }

    /**
     * Establece el libro que es prestado.
     * 
     * @param p_libro el libro que es prestado
     */
    public void setLibro(Libro p_libro) {
        this.libro = p_libro;
    }

    /**
     * Obtiene la fecha de retiro del préstamo.
     * 
     * @return la fecha de retiro del préstamo
     */
    public Calendar getFechaRetiro() {
        return this.fechaRetiro;
    }

    /**
     * Obtiene la fecha de devolución del préstamo.
     * 
     * @return la fecha de devolución del préstamo
     */
    public Calendar getFechaDevolucion() {
        return this.fechaDevolucion;
    }

    /**
     * Obtiene el socio que realizó el préstamo.
     * 
     * @return el socio que realizó el préstamo
     */
    public Socio getSocio() {
        return this.socio;
    }

    /**
     * Obtiene el libro que ha sido prestado.
     * 
     * @return el libro que ha sido prestado
     */
    public Libro getLibro() {
        return this.libro;
    }

    /**
     * Registra la fecha de devolución del préstamo. Si la fecha de devolución es nula, 
     * el libro aún no ha sido devuelto.
     * 
     * @param p_fecha la fecha en que el libro debe ser devuelto
     */
    public void registrarFechaDevolucion(Calendar p_fecha) {
        this.setFechaDevolucion(p_fecha);
    }
   
    /**
     * Verifica si el préstamo ha vencido comparando la fecha actual con la fecha límite 
     * de devolución calculada a partir de la fecha de retiro y los días de préstamo del socio.
     * 
     * @param p_fecha la fecha actual que se va a comparar con la fecha límite de devolución
     * @return verdadero si el préstamo está vencido, falso en caso contrario
     */
   public boolean vencido(Calendar p_fecha) {
    if (p_fecha == null){
        return false;
    }

    Calendar fechaVencimiento = (Calendar) this.getFechaRetiro().clone();
    fechaVencimiento.add(Calendar.DAY_OF_YEAR, this.getSocio().getDiasPrestamo());

    return p_fecha.after(fechaVencimiento);
    }

    /**
     * Devuelve una representación en cadena del préstamo, incluyendo las fechas de retiro y devolución,
     * el título del libro y el nombre del socio que realizó el préstamo.
     * 
     * @return una cadena con la información del préstamo
     */
    public String toString() {
    String retiroStr = "\n\nRetiro: " + this.getFechaRetiro().get(Calendar.YEAR) + "/" +
        (this.getFechaRetiro().get(Calendar.MONTH) + 1) + "/" +
        this.getFechaRetiro().get(Calendar.DAY_OF_MONTH);

    String devolucionStr = " - Devolucion: ";
    if (this.getFechaDevolucion() != null) {
        devolucionStr += this.getFechaDevolucion().get(Calendar.YEAR) + "/" +
            (this.getFechaDevolucion().get(Calendar.MONTH) + 1) + "/" +
            this.getFechaDevolucion().get(Calendar.DAY_OF_MONTH) + "\n";
    } else {
        devolucionStr += "N/A \n";
    }

    String libroStr = "Libro: " + this.getLibro().getTitulo() + "\n";
    String socioStr = "Socio: " + this.getSocio().getNombre();

    return retiroStr + devolucionStr + libroStr + socioStr;
    }

}   

