package p_biblioteca;

/**
 * La clase Biblioteca representa una biblioteca que administra una colección de libros
 * y una lista de socios (estudiantes y docentes). Permite registrar nuevos libros y socios,
 * gestionar préstamos y devoluciones, consultar información y persistir los datos en archivo.
 * 
 * Provee funcionalidades como:
 * - Alta de libros y socios
 * - Registro y devolución de préstamos
 * - Listados de libros y socios
 * - Identificación de préstamos vencidos y docentes responsables
 * - Persistencia de la biblioteca en archivo
 * 
 * @author Quiñonez Zoel, Rivas Lautaro, Lopez Victor
 * @version 1.0
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.*;

public class Biblioteca implements Serializable{
    /** Nombre identificatorio de la biblioteca */
    private String nombre;
    /** Lista que almacena todos los socios registrados */
    private ArrayList<Libro> libros;
    /** Lista que almacena todos los libros disponibles en la biblioteca */
    private ArrayList<Socio> socios;

    /**
     * Constructor para crear un objeto Biblioteca.
     * Inicializa las colecciones vacias y asigna un nombre identificatorio.
     *
     * @param p_nombre Nombre de la biblioteca.
     */
    public Biblioteca(String p_nombre){
        this.setNombre(p_nombre);
        this.setLibros(new ArrayList<Libro>());
        this.setSocios(new ArrayList<Socio>());
    }

    /**
     * Constructor con ingreso de nombre, coleccion de libros y coleccion de socios
     * @param nombre de la biblioteca, coleccion de libros y coleccion de socios
     */
    public Biblioteca(String p_nombre,ArrayList<Libro> p_libros,ArrayList<Socio> p_socios){
        this.setNombre(p_nombre);
        this.setLibros(p_libros);
        this.setSocios(p_socios);
    }

    /**
     * Asigna un nombre a la biblioteca.
     *
     * @param p_nombre Nombre que tendra la biblioteca.
     */
    private void setNombre(String p_nombre){
        this.nombre = p_nombre;
    }

    /**
     * Retorna el nombre de la biblioteca
     * @return nombre de la biblioteca
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Asignacion de la coleccion de libros
     * @param coleccion de objetos Libro
     */
    private void setLibros(ArrayList<Libro> p_libros){
        this.libros = p_libros;
    }

    /**
     * Retorna la coleccion de libros
     * @return coleccion de objetos Libro
     */
    public ArrayList<Libro> getLibros(){
        return this.libros;
    }

    /**
     * Asignacion de la coleccion de socios
     * @param coleccion de objetos Socio
     */
    private void setSocios(ArrayList<Socio> p_socios){
        this.socios = p_socios;
    }

    /**
     * Retorna la coleccion de socios
     * @return coleccion de objetos Socio
     */
    public ArrayList<Socio> getSocios(){
        return this.socios;
    }

    public void eliminarSocio(Socio p_socios) {
        this.getSocios().remove(p_socios);
    }

    /**
     * agrega un libro a la coleccion
     * @param Un objeto libro
     * @return true si se agrego correctamente, false de lo contrario 
     */
    public boolean agregarLibro(Libro p_libro){
        return this.getLibros().add(p_libro);
    }

    /**
     * remueve un libro de la coleccion
     * @param Un objeto libro
     * @return true si se removio correctamente, false de lo contrario 
     */
    public boolean removerLibro(Libro p_libro){
        return this.getLibros().remove(p_libro);
    }

    /**
     * Ingreso de un nuevo libro
     * @param  p_titulo titulo del libro
     * @param p_edicion numero de edicion del libro
     * @param p_editorial editorial del libro 
     * @param p_anio anio de publicacion del libro
     */
    public void nuevoLibro(String p_titulo,int p_edicion,String p_editorial,int p_anio){
        this.getLibros().add(new Libro(p_titulo,p_edicion,p_editorial,p_anio));
    }

    /**
     * Ingreso de un nuevo socio del tipo estudiante
     * @param p_dni dni del socio
     * @param p_nombre nombre del socio 
     * @param p_carrera la carrera que cursa el estudiante
     */
    public void nuevoSocioEstudiante(int p_dniSocio,String p_nombre,String p_carrera){
        this.getSocios().add(new Estudiante(p_dniSocio,p_nombre,p_carrera));
    }

    /**
     * Ingreso de un nuevo socio del tipo docente
     * @param p_dni dni del socio
     * @param p_nombre nombre del socio 
     * @param p_area el area del docente
     */
    public void nuevoSocioDocente(int p_dniSocio,String p_nombre,String p_area){
        this.getSocios().add(new Docente(p_dniSocio,p_nombre,p_area));
    }

    /**
     * Intenta registrar un prestamo para un libro
     * @param p_fechaRetiro fecha de retiro del libro
     * @param p_socio socio que solicita el prestamo
     * @param p_libro libro que se desea prestar
     * @return (true)se realizo el prestamo con exito 
     * (false) el socio no púede pedir mas libros o el libro ya fue prestado
     */
    public boolean prestarLibro(Calendar p_fechaRetiro,Socio p_socio,Libro p_libro ){
        if(p_socio.puedePedir() && !p_libro.prestado()){
            Prestamo prestamo = new Prestamo(p_fechaRetiro,p_socio,p_libro);
            p_socio.agregarPrestamo(prestamo);
            p_libro.agregarPrestamo(prestamo);
            return true;
        }else{
            return false;
        }
    }

    /**
     * devolucion de un libro 
     * @param p_libro libro que se desea devolver
     * @throw libroNoPrestadoExeption el libro ya se encuentra en la biblioteca por lo que no se puede devolver
     */
    public void devolverLibro(Libro p_libro) throws LibroNoPrestadoException{
        if(!p_libro.prestado()){
            throw new LibroNoPrestadoException("El libro "+ p_libro.getTitulo()+" no se puede devolver ya que se encuentra en la bilbioteca"); 
        }
        Calendar fecha = Calendar.getInstance();
        p_libro.ultimoPrestamo().registrarFechaDevolucion(fecha);
        System.out.println("Libro devuelto el: " +
            fecha.get(Calendar.DAY_OF_MONTH) + "/" +
            (fecha.get(Calendar.MONTH) + 1) + "/" +
            fecha.get(Calendar.YEAR));

    }  

    /**
     * Cuenta la cantidad de socios que coinciden con un tipo especifico.
     *  @param p_objeto El tipo de socio a contar.
     */
    public int cantidadDeSociosPorTipo(String p_tipoSocio) {
        int cantidadTipo = 0;
        for (Socio unSocio : this.getSocios()) {
            if (unSocio.soyDeLaClase().equalsIgnoreCase(p_tipoSocio)) {
                cantidadTipo += 1;
            }
        }
        return cantidadTipo;
    }

    /**
     * devuelve un colección con los prestamos vencidos.
     * @return un arraylist con los prestamos vencidos.
     */
    public ArrayList<Prestamo> prestamosVencidos() {
        ArrayList<Prestamo> prestamosVencidos = new ArrayList();
        Calendar fechaHoy = Calendar.getInstance();
        for (Socio unSocio : this.getSocios()) {
            for (Prestamo unPrestamo : unSocio.getPrestamos()) {
                if (unPrestamo.vencido(fechaHoy)) {
                    prestamosVencidos.add(unPrestamo);
                }
            }
        }
        return prestamosVencidos;
    }

    /**
     * devuelve un colección con los docentes responsables.
     * @return array list
     */
    public ArrayList<Socio> docentesResponsables(){
        ArrayList<Socio> docentesResponsables = new ArrayList();
        for(Socio unSocio : this.getSocios()){
            if(unSocio.soyDeLaClase().equalsIgnoreCase("Docente")){
                Docente socioDoc = (Docente) unSocio;
                if(socioDoc.esResponsable()){
                    docentesResponsables.add(unSocio);
                }
            }
        }
        return docentesResponsables;
    }

    /**
     * Dependiendo si el libro esta prestado o no, devuelve un mensaje, si lo esta devuleve el socio que lo posee, sino un mensaje que esta en la biblioteca.
     * @param p_libro libro que se quiere saber si esta prestado o disponible.
     */
    public String quienTieneElLibro(Libro p_libro) throws LibroNoPrestadoException{
        //si el libro no esta prestado tira la excepcion
        if(!p_libro.prestado()){
            throw new LibroNoPrestadoException("el libro se encuentra en la biblioteca");
        }
        
        //buscar en todos los socios 
        for (Socio unSocio : this.getSocios()) {
            for (Prestamo unPrestamo : unSocio.getPrestamos()) {
                //si encontramos un prestamo del libro y no fue devuelto 
                if (unPrestamo.getLibro() == p_libro && unPrestamo.getFechaDevolucion() == null){
                    //lo tiene este socio 
                   return unSocio.getNombre();
            }
        }
        } 
        //si no se encontro tira la excepcion
        throw new LibroNoPrestadoException("El libro se encuentra en la biblioteca");
    }

    /**
     * Devuelve el Socio que tiene el dni pasado como parámetro, o null si no lo encuentra.

     * @param entero
     * @return Socio
     */
    public Socio buscarSocio(int p_dniSocio){
        for(Socio unSocio : this.getSocios()){
            if(unSocio.getDniSocio() == p_dniSocio){
                return unSocio;
            }
        }
        return null;
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
            listaSocios += indice+")"+unSocio.toString()+"\n";
        }
        listaSocios += "**************************************\nCantidad de Socios del tipo estudiante: "+
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
            listaLibros += indice+")"+unLibro.toString()+" || Prestado: "+prestado+"\n";
        }
        return listaLibros;
    }

    /**
     * Devuelve un String con la lista de los títulos con los que cuenta la Biblioteca.
     * @return cadena de caracteres
     */
    public String listaDeTitulos(){
        //Aca no s si se refiere a todos los que le pertenecen o a los que puede prestar
        String listaLibros = "";
        for(Libro unLibro : this.getLibros()){
            listaLibros += unLibro.getTitulo()+"\n";
        }
        return listaLibros;
    }

    /**
     * listaDeDocentesResponsables(): devuelve un String según formato3.
     * @return cadena de caracteres
     */
    public String listaDeDocentesResponsables(){
        String listaDocentesResponsables = "      Lista de Docentes Responsables:\n\n";
        int indice = 0;
        for(Socio unSocio : this.docentesResponsables()){
            indice ++;
            listaDocentesResponsables += indice + ")" + unSocio.toString() + "\n";
        }
        return listaDocentesResponsables;
    }
    
    
    // --- Persistencia y carga de datos --- //

    /**
     * Pregunta al usuario si desea cargar una biblioteca desde archivo.
     * Si ingresa 'S', se intenta cargar Biblioteca.dat; si no, crea una nueva.
     * @return biblioteca cargada o nueva
     */

    public  Biblioteca cargarSiONo(){
        Scanner teclado = new Scanner(System.in);
        Biblioteca biblioteca = null;
        System.out.print("Ingrese S si desea cargar una biblioteca, caso contrario\ningrese cualquier otro caracter: ");
        String cargarSiONo = teclado.nextLine();
        try{
            if(cargarSiONo.equalsIgnoreCase("s")){
                biblioteca = leerBiblioteca();
            }else{
                System.out.print("Ingrese el nombre que tendra la biblioteca: ");
                String nomBiblioteca = teclado.nextLine();

                biblioteca = new Biblioteca(nomBiblioteca);
            }
        } catch (InputMismatchException e) {
            System.out.println(" ERROR DE INGRESO DE DATO INCORRECTO: " + e.getMessage());
            System.out.println(" PRESIONE ENTER PARA CONTINUAR");
            teclado.nextLine(); // Limpia el buffer
        }
        return biblioteca;
    }
    
    /**
     * Guarda la biblioteca en un archivo para persistencia.
     * @param p_biblioteca biblioteca a guardar
     */
    public  void guardarBiblioteca(Biblioteca p_biblioteca){
        try {
            FileOutputStream archivoOutput = new FileOutputStream("Biblioteca.dat");
            ObjectOutputStream objetoOutput = new ObjectOutputStream(archivoOutput);

            objetoOutput.writeObject(p_biblioteca);

            objetoOutput.close();
            System.out.println("Objeto guardado correctamente!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Lee la biblioteca desde el archivo Biblioteca.dat.
     * Si no existe, pide al usuario crear una nueva.
     * @return biblioteca cargada o nueva
     */

    public  Biblioteca leerBiblioteca() {

        Biblioteca bibliotecaGuardada = null;
        try (ObjectInputStream objetoInput = new ObjectInputStream(
                new FileInputStream("Biblioteca.dat"))) {

            bibliotecaGuardada = (Biblioteca) objetoInput.readObject();
            System.out.println("Objeto leído desde disco!");

        }catch(FileNotFoundException f){
            Scanner teclado = new Scanner(System.in);
            System.out.println("Archivo no encontrado");
            System.out.print("Ingrese el nombre que tendra la biblioteca: ");
            String nomBiblioteca = teclado.nextLine();

            bibliotecaGuardada = new Biblioteca(nomBiblioteca);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el objeto:");
            e.printStackTrace();
        }
        return bibliotecaGuardada;
    }
    
    
}




