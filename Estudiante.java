
/**
 * Write a description of class Estudiante here.
 * 
 * @author (pablo) 
 * @version (1)
 */
import java.util.Calendar;
public class Estudiante extends Socio{
  /** nombre de la carrera del estudiante */
  private String carrera;
  
  /**
     * Constructor que crea un objeto estudiante.
     * @param p_dniSocio dni del estudiante socio
     * @param p_nombre nombre del estudiante socio
     * @param p_carrera carrera del estudiante
     */
   public Estudiante(int p_dniSocio,String p_nombre,String p_carrera){
      super(p_dniSocio,p_nombre,20);
      this.setCarrera(p_carrera);
  }
  
  private void setCarrera(String p_carrera){
      this.carrera = p_carrera;
  }
  
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
      return (puedePedir && super.cantLibrosPrestados() <= 3); 
  }
  
   /** @return "Estudiante"*/
  public String soyDeLaClase(){
      return "Estudiante";
  }
  }