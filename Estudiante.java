/**
 *Esta clase Estudiante es una subclase de la superclase Socio.
 * Modela a un socio de tipo Estudiante en la biblioteca, añadiendo el
 * atributo específico 'carrera' y la lógica de préstamo propia para
 * este tipo de socio.
 * 
 * @author Toledo Pablo
 * @version 1.1
 */
import java.util.Calendar;
public class Estudiante extends Socio{
  /** nombre de la carrera del estudiante */
  private String carrera;
  
  /**
     * Constructor para crear una instancia de Estudiante.
    * Inicializa los datos heredados de Socio (dni, nombre y dias de prestamo)
     * y asigna la carrera del estudiante.
     * @param p_dniSocio dni del estudiante 
     * @param p_nombre nombre del estudiante 
     * @param p_carrera carrera del estudiante
     */
   public Estudiante(int p_dniSocio,String p_nombre,String p_carrera){
      super(p_dniSocio,p_nombre,20);
      this.setCarrera(p_carrera);
  }
  
  /** establece la carrera del estudiante*/
  private void setCarrera(String p_carrera){
      this.carrera = p_carrera;
  }
  
  /**@return carrera*/
  public String getCarrera(){
      return this.carrera;
  }
  
   /**
     * redefine el metodo puedePedir() de la superclase para verificar
       si el estudiante puede pedir un prestamo pero añadiendole una nueva condicion
     * @return true si el estudiante puede pedir un prestamo
     */
  @Override
  public boolean puedePedir(){
      boolean puedePedir = super.puedePedir();
      return (puedePedir && this.cantLibrosPrestados() <= 3); 
  }
  
  /**
     * Implementa el método abstracto 'soyDeLaClase' de la superclase Socio.
     * @return la cadena "Estudiante" para identificar este tipo de socio.
     */
    @Override
    public String soyDeLaClase() {
        return "Estudiante";
    }
  }
