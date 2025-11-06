
/**
 *La clase Docente es un subclase de la clase superclase (Socio).
 * Esta clase modela a un Docente que a su vez es un Socio de la Biblioteca.
 * @author (pablo) 
 * @version (1.1)
 */
import java.util.Calendar;

public class Docente extends Socio {
    /** nombre de la carrera del estudiante */
    private String area;
    
    /**
     * Constructor para instanciar un objeto docente
     * @param p_dniSocio dni del socio docente(heredado)
     * @param p_nombre nombre del docente(heredado)
     * @param p_area area del docente
     */
    public Docente(int p_dniSocio, String p_nombre, String p_area) {
        super(p_dniSocio, p_nombre, 5);
        this.setArea(p_area);
    }
    
    /**setArea guarda el valor ingresado como parametro en la variable area*/
    private void setArea(String p_area) {
        this.area = p_area;
    }
    /**@return area*/
    public String getArea() {
        return this.area;
    }
    
    /**
     * Verifica si el docente es un socio responsable.
     * Un docente es responsable si no tiene ningún préstamo vencido.
     *
     * @return true si el docente está al día (no tiene préstamos vencidos), 
     * false si tiene al menos un préstamo vencido.
     */
    public boolean esResponsable() {
        Calendar p_fecha = Calendar.getInstance();
        for (Prestamo unPrestamo : this.getPrestamos()) {
            if (unPrestamo.vencido(p_fecha)) {
                return false;
            }
        }
        return true;
    }
    
     /**
     * modifica setDiasPrestamo y le agrega dias si el docente es responsable
     * @param p_dias dias que se van a agregar
     */
    public void agregarDiasDePrestamo(int p_dias) {
        if (this.esResponsable()) {
            this.setDiasPrestamo(this.getDiasPrestamo() + p_dias);
        }
    }
    
    /**
     * Implementa el método abstracto 'soyDeLaClase' de la superclase Socio.
     * @return la cadena "Docente" para identificar este tipo de socio.
     */
    @Override
    public String soyDeLaClase() {
        return "Docente";
    }
}
