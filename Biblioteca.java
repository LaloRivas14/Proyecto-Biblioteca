
/**
 * Write a description of class Biblioteca here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
public class Biblioteca{
    private String nombre;
    private ArrayList<Libro> libros;
    private ArrayList<Socio> socios;

    public Biblioteca(String p_nombre){
        this.setNombre(p_nombre);
        this.setLibros(new ArrayList<Libro>());
        this.setSocios(new ArrayList<Socio>());
    }

    private void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }

    public String getNombre(){
        return this.nombre;
    }

    private void setLibros(ArrayList<Libro> p_libros){
        this.libros = p_libros;
    }

    private ArrayList<Libro> getLibros(){
        return this.libros;
    }

    private void setSocios(ArrayList<Socio> p_socios){
        this.socios = p_socios;
    }

    public ArrayList<Socio> getSocios(){
        return this.socios;
    }

    public boolean agregarLibro(Libro p_libro){
        return this.getLibros().add(p_libro);
    }

    public boolean removerLibro(Libro p_libro){
        return this.getLibros().remove(p_libro);
    }

    public void nuevoLibro(String p_titulo,int p_edicion,String p_editorial,int p_anio){
        this.getLibros().add(new Libro(p_titulo,p_edicion,p_editorial,p_anio));
    }

    public void nuevoSocioEstudiante(int p_dniSocio,String p_nombre,String p_carrera){
        this.getSocios().add(new Estudiante(p_dniSocio,p_nombre,p_carrera));
    }

    public void nuevoSocioDocente(int p_dniSocio,String p_nombre,String p_area){
        this.getSocios().add(new Docente(p_dniSocio,p_nombre,p_area));
    }

    /**
     * Devuelve el Socio que tiene el dni pasado como parámetro, o null si no lo encuentra.

     * @param entero
     * @return Socio
     */
    public Socio buscarSocio(int p_dniSocio){
        for(Socio unSocio : this.getSocios()){
            if(unSocio.getDniSocio().equals(p_dniSocio)){
                return unSocio;
            }else{
                return null;
            }
        }
    }

    /**
     * Devuelve un String con la lista de todos los socios según formato1.
     * D.N.I.: <<dni>> || <<nombre y apellido>> (<<tipo>>) || Libros Prestados: <<cant. prést. actuales >>
     * @return cadena de caracteres
     */
    public String listaDeSocios(){
        String listaSocios = "      Lista de Socios:\n\n";
        int indice = 0;
        for(Socio unSocio : this.getSocios()){
            indice ++;
            listaSocios = indice+")"+unSocio.toString()+"\n";
        }
        listaSocios =+ "**************************************\nCantidad de Socios del tipo estudiante: "+
        this.cantidadDeSociosPorTipo("estudiante")+"\nCantidad de Socios del tipo Docente: "+
        this.cantidadDeSociosPorTipo("docente")+"\n**************************************";

        return listaSocios;
    }

    /**
     * Devuelve un String con la lista de todos los libros según formato2.
     * Titulo: <<titulo>> || Prestado: (<<Si|No>>)
     * @return cadena de caracteres
     */
    public String listaDeLibros(){
        String listaLibros = "      Lista de Libros:\n\n";
        String prestado;
        int indice = 0;
        for(Libro unLibro : this.getLibros()){
            indice ++;
            if(unLibro.prestado()){
                prestado = "Si";
            }else{
                prestado = "No";
            }
            listaLibros =+ indice+")"+unLibro.toString()+" || Prestado: "+prestado+"\n";
        }
        return listaLibros;
    }

    /**
     * Devuelve un String con la lista de los títulos con los que cuenta la Biblioteca.
     * @return cadena de caracteres
     */
    public String listaDeTitulos(){
        //Aca no s si se refiere a todos los que le pertenecen o a los que puede prestar
        String listaLibros;
        for(Libro unLibro : this.getLibros()){
            listaLibros =+ unLibro.getTitulo()+"\n";
        }
        return listaLibros;
    }

    /**
     * listaDeDocentesResponsables(): devuelve un String según formato3.
     * @return cadena de caracteres
     */
    public String listaDeDocentesResponsables(){
        String listaSocios = "      Lista de Docentes Responsables:\n\n";
        int indice = 0;
        for(Socio unSocio : this.getSocios()){
            if(unSocio.soyDeLaClase().equalsIgnoreCase("Docente")){
                if(unSocio.esResponsable()){
                    indice ++;
                    listaSocios = indice+")"+unSocio.toString()+"\n";
                }
            }
        }
        return listaSocios;
    }
}