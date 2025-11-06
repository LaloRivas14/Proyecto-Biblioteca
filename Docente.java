
/**
 * Write a description of class Docente here.
 * 
 * @author (pablo) 
 * @version (1)
 */
import java.util.Calendar;

public class Docente extends Socio {
    /** nombre de la carrera del estudiante */
    private String area;
    
    /**
     * Constructor que crea un docente
     * @param p_dniSocio dni del socio docente
     * @param p_nombre nombre del docente
     * @param p_area area del docente
     */
    public Docente(int p_dniSocio, String p_nombre, String p_area) {
        super(p_dniSocio, p_nombre, 5);
        this.setArea(p_area);
    }
    
    private void setArea(String p_area) {
        this.area = p_area;
    }

    public String getArea() {
        return this.area;
    }
    
     /**@return true si el docente es responsable */
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
     * modifica setDiasPrestamo si el docente es responsable
     * @param p_dias dias que se van a agregar
     */
    public void agregarDiasDePrestamo(int p_dias) {
        if (this.esResponsable()) {
            this.setDiasPrestamo(this.getDiasPrestamo() + p_dias);
        }
    }
    
     /** @return "Docente"*/
    public String soyDeLaClase() {
        return "Docente";
    }
}