package p_biblioteca;

 /**
 *La clase Docente es una subclase de Socio.
 * Representa a un socio de la biblioteca que es docente,
 * incorporando el atributo especifico "area" y reglas particulares
 * sobre responsabilidad y modificacion de dias de prestamo.
 * 
 * @author Toledo Pablo
 * @version 1.1
 */
import java.util.Calendar;

public class Docente extends Socio {
    /** area del docente */
    private String area;
    
    /**
     * Constructor para crear un docente como socio de biblioteca.
     * Inicializa dni, nombre y asigna 5 dias iniciales de prestamo.
     * Luego establece el area del docente.
     * 
     * @param p_dniSocio DNI del docente
     * @param p_nombre nombre del docente
     * @param p_area area academica del docente
     */
    public Docente(int p_dniSocio, String p_nombre, String p_area) {
        super(p_dniSocio, p_nombre, 5);
        this.setArea(p_area);
    }
    
    /**Guarda el área del docente. */
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
     * Si el docente es responsable, incrementa sus dias de prestamo.
     * Se suman los dias recibidos como parametro.
     * @param p_dias dias que se van a agregar
     */
    public void cambiarDiasDePrestamos(int p_dias) {
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
