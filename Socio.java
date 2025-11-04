
/**
 *Clase Socio TP7
 */
import java.util.ArrayList;
import java.util.Calendar;
public abstract class Socio 
{
    private int dniSocio;
    private String nombre;
    private int diasPrestamo;
    private ArrayList<Prestamo> prestamos;
    
    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo, ArrayList<Prestamo> p_prestamos){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(p_prestamos);
    }
    
    public Socio(int p_dniSocio, String p_nombre, int p_diasPrestamo){
        this.setDniSocio(p_dniSocio);
        this.setNombre(p_nombre);
        this.setDiasPrestamo(p_diasPrestamo);
        this.setPrestamos(new ArrayList<Prestamo>());
    }
    
     private void setDniSocio(int p_dniSocio){
         this.dniSocio = p_dniSocio;
        }
    private void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }
    private void setDiasPrestamo(int p_diasPrestamo){
        this.diasPrestamo = p_diasPrestamo;
    }
    private void setPrestamos(ArrayList<Prestamo> p_prestamos){
        this.prestamos = p_prestamos;
    }
    public int getDniSocio(){
        return this.dniSocio;
    }
    public String getNombre(){
        return this.nombre;
    }
    public int getDiasPrestamo(){
        return this.diasPrestamo;
    }
    public ArrayList<Prestamo> getPrestamos(){
        return this.prestamos;
    }
    
    public boolean agregarPrestamo(Prestamo p_prestamo){
        return this.getPrestamos().add(p_prestamo);
    }
    
    public boolean quitarPrestamo(Prestamo p_prestamo){
        if(this.getPrestamos().size() > 0){
         return this.getPrestamos().remove(p_prestamo);
        }
    }
    
    public int cantLibrosPrestados(){
        return this.getPrestamos().size();
    }
    
    public String toString(){
        return ("DNI:" + this.getDniSocio() + " || " + this.getNombre() + 
        " (" + this.soyDeLaClase() + ") || Libros Prestados: " + this.cantLibrosPrestados());
    }
    
    public boolean puedePedir(){
        Calendar fechaHoy = Calendar.getInstance();
        for(Prestamo prestamo: this.getPrestamos()){
            if(prestamo.vencido(fechaHoy)){
                return false;
            }
        }
        return true;
    }
    public abstract String soyDeLaClase();
}