package p_biblioteca;


/**
 *Esta clase Estudiante es un subclase de la superclase(Socio).
 *Esta clase modela a un Estudiante que a su vez es un Socio de la Biblioteca.
 * @author (pablo) 
 * @version (1.1)
 */
import java.util.Calendar;
public class Estudiante extends Socio{
  /** nombre de la carrera del estudiante */
  private String carrera;
  
  /**
     * Constructor para instanciar un objeto estudiante.
     * @param p_dniSocio dni del estudiante socio(heredado)
     * @param p_nombre nombre del estudiante socio(heredado)
     * @param p_carrera carrera del estudiante
     */
   public Estudiante(int p_dniSocio,String p_nombre,String p_carrera){
      super(p_dniSocio,p_nombre,20);
      this.setCarrera(p_carrera);
  }
  
  /**setArea guarda el valor ingresado como parametro en la variable carrera*/
  private void setCarrera(String p_carrera){
      this.carrera = p_carrera;
  }
  
  /**@return carrera*/
  public String getEstudiante(){
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
