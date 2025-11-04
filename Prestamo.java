
import java.util.Calendar;

public class Prestamo {
    private Calendar fechaRetiro;
    private Calendar fechaDevolucion;
    private Socio socio;
    private Libro libro;

    public Prestamo(Calendar p_fechaRetiro, Socio p_socio, Libro p_libro) {
        this.setFechaRetiro(p_fechaRetiro);
        this.setSocio(p_socio);
        this.setLibro(p_libro);
    }

    private void setFechaRetiro(Calendar p_fechaRetiro) {
        this.fechaRetiro = p_fechaRetiro;
    }

    public Calendar getFechaRetiro() {
        return this.fechaRetiro;
    }

    private void setFechaDevolucion(Calendar p_fechaDevolucion) {
        this.fechaDevolucion = p_fechaDevolucion;
    }

    public void registrarFechaDevolucion(Calendar p_fechaDevolucion) {
        this.setFechaDevolucion(p_fechaDevolucion);
    }

    public Calendar getFechaDevolucion() {
        return this.fechaDevolucion;
    }

    private void setSocio(Socio p_socio) {
        this.socio = p_socio;
    }

    public Socio getSocio() {
        return this.socio;
    }

    private void setLibro(Libro p_libro) {
        this.libro = p_libro;
    }

    public Libro getLibro() {
        return this.libro;
    }
    
   

   public boolean vencido(Calendar p_fecha) {
    if (p_fecha == null){
        return false;
    }

    Calendar fechaVencimiento = (Calendar) this.getFechaRetiro().clone();
    fechaVencimiento.add(Calendar.DAY_OF_YEAR, this.getSocio().getDiasPrestamo());

    return p_fecha.after(fechaVencimiento);
}


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

