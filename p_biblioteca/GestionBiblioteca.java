package p_biblioteca;

/**
 * La clase GestionBiblioteca representa el punto de entrada del sistema.
 * Permite administrar una biblioteca mediante menu interactivo, ofreciendo
 * funcionalidades como carga de libros y socios, préstamos, devoluciones,
 * consultas y persistencia de datos en archivo.
 * 
 * - Alta y baja de socios y libros
 * - Gestión de préstamos y devoluciones
 * - Consultas sobre socios, libros y préstamos vencidos
 * - Guardado y lectura del estado de la biblioteca en disco
 * 
 * Maneja excepciones por entrada incorrecta y errores de logica.
 * 
 * @author Quiñonez Zoel, Rivas Lautaro, Lopez Victor, Pawlizki Micaela,  Ristovich Mauro, Toledo Pablo
 * @version 1.0
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.*;

public class GestionBiblioteca implements Serializable{
    
    public static void main(String [] args) throws LibroNoPrestadoException {
        Scanner teclado = new Scanner(System.in);
        Biblioteca biblioteca = cargarSiONo();
        boolean resp = true;
        int opcion = 0;
        while(resp){
            menu();
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch(opcion){
                case 1:
                    try{
                        cargarNuevoLibro(biblioteca);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(" ERROR DE INGRESO DE DATO INCORRECTO: " + e.getMessage());
                        System.out.println(" PRESIONE ENTER PARA CONTINUAR");
                        teclado.nextLine(); // Limpia el buffer
                        break;
                    } catch (Exception e) {
                        System.out.println(" ERROR GENERAL: " + e.getMessage());
                        break;
                    }

                case 2: 
                    try{
                        cargarNuevoSocio(biblioteca);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(" ERROR DE INGRESO DE DATO INCORRECTO: " + e.getMessage());
                        System.out.println(" PRESIONE ENTER PARA CONTINUAR");
                        teclado.nextLine(); // Limpia el buffer
                        break;
                    } catch (Exception e) { 
                        System.out.println(" ERROR GENERAL: " + e.getMessage());
                        break;
                    }

                case 3: 
                    try{
                        eliminacionDeElemento(biblioteca);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(" ERROR DE INGRESO DE DATO INCORRECTO: " + e.getMessage());
                        System.out.println(" PRESIONE ENTER PARA CONTINUAR");
                        teclado.nextLine(); // Limpia el buffer
                        break;
                    } catch (NullPointerException e) {
                        System.out.println(" ERROR: Se intentó acceder a un objeto nulo. El socio no se encontró.");
                        break;
                    } catch (Exception e) {
                        System.out.println(" ERROR GENERAL: " + e.getMessage());
                        break;
                    }

                case 4:
                    try{
                        prestamoLibro(biblioteca);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(" ERROR DE INGRESO DE DATO INCORRECTO: " + e.getMessage());
                        System.out.println(" PRESIONE ENTER PARA CONTINUAR");
                        teclado.nextLine(); // Limpia el buffer
                        break;
                    } catch (NullPointerException e) {
                        System.out.println(" ERROR: Se intentó acceder a un objeto nulo. El socio no se encontró.");
                        break;
                    } catch (Exception e) {
                        System.out.println(" ERROR GENERAL: " + e.getMessage());
                        break;
                    }
                case 5:
                    try{
                        System.out.print("Ingrese el titulo del libro: ");
                        Libro libroDevolver = buscarLibro(teclado.nextLine(),biblioteca.getLibros());
                        biblioteca.devolverLibro(libroDevolver);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(" ERROR DE INGRESO DE DATO INCORRECTO: " + e.getMessage());
                        System.out.println(" PRESIONE ENTER PARA CONTINUAR");
                        teclado.nextLine(); // Limpia el buffer
                        break;
                    } catch (NullPointerException e) {
                        System.out.println(" ERROR: libro no encontrado");
                        break;
                    } catch (Exception e) {
                        System.out.println(" ERROR GENERAL: " + e.getMessage());
                        break;
                    }
                case 6:
                    System.out.print("ingrese el tipo de socio que desea saber la cantidad: ");
                    String tipo = teclado.nextLine();
                    if(tipo.equalsIgnoreCase("estudiante")){
                        System.out.println("Cantidad de socios Estudiantes: "+biblioteca.cantidadDeSociosPorTipo(tipo));
                    }else if(tipo.equalsIgnoreCase("docente")){
                        System.out.println("Cantidad de socios Docentes: "+biblioteca.cantidadDeSociosPorTipo(tipo));
                    }else{
                        System.out.println("-----Tipo de socio ingresado Incorrecto------");
                    }
                    break;
                case 7:
                    try{
                        menuDeListas();
                        mostrarUnaLista(teclado.nextInt(),biblioteca);
                        teclado.nextLine();
                        break;
                    }catch(InputMismatchException e){
                        System.out.println(" ERROR DE INGRESO DE DATO INCORRECTO: " + e.getMessage());
                        System.out.println(" PRESIONE ENTER PARA CONTINUAR");
                        teclado.nextLine(); // Limpia el buffer
                        break;
                    }
                case 8:
                    try{
                        System.out.print("Ingrese el titulo del libro: ");
                        Libro libroEncontrar = buscarLibro(teclado.nextLine(),biblioteca.getLibros());
                        System.out.println("El o los socios que tienen el libro: "+biblioteca.quienTieneElLibro(libroEncontrar));
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(" ERROR DE INGRESO DE DATO INCORRECTO: " + e.getMessage());
                        System.out.println(" PRESIONE ENTER PARA CONTINUAR");
                        teclado.nextLine(); // Limpia el buffer
                        break;
                    } catch (NullPointerException e) {
                        System.out.println(" ERROR: Se intentó acceder a un objeto nulo. El libro no se encontró.");
                        break;
                    } catch (Exception e) {
                        System.out.println(" ERROR GENERAL: " + e.getMessage());
                        break;
                    }

                case 9:
                    System.out.println("**** Cerrando Programa ***");
                    resp = false;
                    break;
                case 10:
                    guardarBiblioteca(biblioteca);
                    break;
                default:
                    System.out.println("***** Opcion ingresada incorrecta ****");

            } 

        }

    }
    
    /** Muestra el menu principal del sistema */
    public static void menu(){
        System.out.println("Menu de acciones: "+"\n 1)Nuevo Libro"+"\n 2)Nuevo socio Estudiante / Docente \n 3)Eliminación \n 4)Prestar Libro"+"\n 5)Devolver Libro"+"\n 6)Cantidad de socios"+
            "\n 7)Listas y datos"+"\n 8)Que socio tiene un libro especifico \n 9)Cerrar programa \n 10)Guardar!");
    }
    
    /** Muestra el menu de consultas disponibles */
    public static void menuDeListas(){
        System.out.println("Menu de listas: \n 1)Docentes responsables \n 2)Prestamos Vencidos \n 3)Lista de socios \n 4)Lista de titulos \n 5)Lista de libros"); 
    }

    /**
     * Solicita y registra un nuevo libro en la biblioteca.
     * @param p_b biblioteca donde se agregara el libro
     */
    public static void cargarNuevoLibro(Biblioteca p_b){
        Scanner teclado = new Scanner(System.in);
        System.out.print("Ingrese el titulo del libro: ");
        String tituloLibro = teclado.nextLine();
        System.out.print("Ingrese el num de edicion del libro: ");
        int edicionLibro = teclado.nextInt();
        teclado.nextLine();
        System.out.print("Ingrese la editorial del libro: ");
        String editorialLibro = teclado.nextLine();
        System.out.print("ingrese el año del libro: ");
        int anioLibro = teclado.nextInt();
        teclado.nextLine();
        p_b.nuevoLibro(tituloLibro,edicionLibro,editorialLibro,anioLibro);
        System.out.println("**** Ingreso de libro exitoso ****");
    }

    /**
     * Solicita datos y registra un nuevo socio (estudiante o docente)
     * @param p_b biblioteca
     */
    public static void cargarNuevoSocio( Biblioteca p_b){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Que tipo de Socio desea agregar: ");
        System.out.println("Estudiante - 1");
        System.out.println("Docente - 2");
        System.out.println("Selecciona una opcion: ");
        int tipoS = teclado.nextInt();
        teclado.nextLine();

        if(tipoS == 1){

            System.out.println("Ingrese el dni del estudiante: ");
            int dniEstudiante = teclado.nextInt();
            teclado.nextLine();
            System.out.println("Ingrese el nombre y apellido del estudiante: ");
            String nombreEstudiante = teclado.nextLine();
            System.out.println("Ingrese la carrera del estudiante: ");
            String carreraEstudiante = teclado.nextLine();

            p_b.nuevoSocioEstudiante(dniEstudiante,nombreEstudiante, carreraEstudiante);
            System.out.println("Ingreso del estudiante exitoso");

        }else if(tipoS == 2){
            System.out.println("Ingrese el dni del docente: ");
            int dniDocente = teclado.nextInt();
            teclado.nextLine();
            System.out.println("Ingrese el nombre y apellido del docente: ");
            String nombreDocente = teclado.nextLine();
            System.out.println("Ingrese el area del docente: ");
            String areaDocente = teclado.nextLine();

            p_b.nuevoSocioDocente(dniDocente,nombreDocente, areaDocente);
            System.out.println("Ingreso del docente exitoso");
        }else{
            System.out.println("Seleccione una opcion correcta: ");
        }
    }
    
    /**
     * Realiza el flujo necesario para prestar un libro:
     * - solicita DNI del socio
     * - solicita titulo del libro
     * - busca socio y libro en la biblioteca
     * - intenta registrar el prestamo a traves de Biblioteca.prestarLibro(...)
     *
     * Muestra mensajes en consola segun el resultado.
     *
     * @param p_b biblioteca donde se realizara el prestamo
     * @throws NullPointerException si no se encuentra el socio o el libro (manejada en el main)
     */
    public static void prestamoLibro(Biblioteca p_b){
        Scanner teclado = new Scanner(System.in);
        Calendar fechaAct = Calendar.getInstance();

        System.out.print("ingrese el dni del socio: ");
        Socio socioPrestar = p_b.buscarSocio(teclado.nextInt());
        teclado.nextLine();

        System.out.print("Ingrese el titulo del libro: ");
        Libro libroPrestar = buscarLibro(teclado.nextLine(),p_b.getLibros());

        if(p_b.prestarLibro(fechaAct, socioPrestar, libroPrestar)){
            System.out.println("*** Libro Prestado Con Exito ***");
        }else{
            System.out.println("*** Libro ya Prestado O el socio no cumple los requerimientos ***");
        }

    }
    
    /**
     * Elimina un socio (por DNI) o un libro (por título) segun la opcion elegida
     * por el usuario. Si el elemento no existe, se lanza NullPointerException cuando
     * se intenta operar sobre null (manejada en main).
     *
     * @param p_b biblioteca donde se realizara la eliminacion
     * @throws InputMismatchException si el usuario ingresa un valor no numerico cuando se espera
     */
    public static void eliminacionDeElemento(Biblioteca p_b){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Seleccione una categoria para eliminar");
        System.out.println("Socio por DNI - 1");
        System.out.println("Libro - 2");
        System.out.print("Seleccionar una opcion: ");
        int tipoEliminar = teclado.nextInt();
        teclado.nextLine();

        if(tipoEliminar == 1){

            System.out.println("Ingrese el dni del socio a eliminar");
            int dniSocioEliminar = teclado.nextInt();
            teclado.nextLine();
            Socio quitarSocio = p_b.buscarSocio(dniSocioEliminar);
            p_b.eliminarSocio(quitarSocio);
            System.out.println("El socio se ha eliminado correctamente");

        }else if(tipoEliminar == 2){
            System.out.println("Ingrese el titulo del libro a eliminar: ");
            String tituloLibro = teclado.nextLine();
            Libro eliminarLibro = buscarLibro(tituloLibro, p_b.getLibros());
            p_b.removerLibro(eliminarLibro);
            System.out.println("El libro se ha eliminado correctamente");

        } else {
            System.out.println("Seleccione una opcion correcta");
        }

    }
    

    /**
     * Muestra, segun la opcion, distintas listas informativas de la biblioteca:
     * 1) Docentes responsables
     * 2) Prestamos vencidos
     * 3) Lista de socios
     * 4) Lista de títulos
     * 5) Lista de libros
     *
     * @param opcion numero de la lista a mostrar
     * @param p_b biblioteca sobre la que se consultan los datos
     */
    public static void mostrarUnaLista(int opcion,Biblioteca p_b){
        switch(opcion){
            case 1:
                System.out.println(p_b.listaDeDocentesResponsables());
                break;
            case 2:
                for(Prestamo pres : p_b.prestamosVencidos()){
                    System.out.println(pres.toString());
                }
                break;
            case 3:
                System.out.println(p_b.listaDeSocios());
                break;
            case 4:
                System.out.println(p_b.listaDeTitulos());
                break;
            case 5:
                System.out.println(p_b.listaDeLibros());
                break;
            default:
                System.out.println("Opcion Incorrecta......");
                break;
        }
    }

    /**
     * Busca un libro por titulo dentro de una lista de libros. 
     *
     * @param tituloLibro titulo a buscar
     * @param p_libros lista de libros donde buscar
     * @return libro encontrado o null si no existe
     */
    public static Libro buscarLibro(String tituloLibro, ArrayList<Libro> p_libros){
        Libro libroEncontrado = null;
        for(Libro libro : p_libros){
            if(libro.getTitulo().equalsIgnoreCase(tituloLibro)){
                libroEncontrado = libro;
            }
        }
        return libroEncontrado;
    }

    /**
     * Metodo auxiliar utilizado para cargar datos de prueba: dos libros, un estudiante
     * y un docente, y marcarles prestamos con una fecha pasada para simular vencidos.
     *
     * @param b biblioteca donde se insertan los datos de prueba
     */
    public static void cargarPrestamosVencidos(Biblioteca b){
        Calendar fecha = Calendar.getInstance();
        fecha.set(Calendar.YEAR, 2025);
        fecha.set(Calendar.MONTH, Calendar.AUGUST); // o 10 (noviembre es el mes 10, empieza en 0)
        fecha.set(Calendar.DAY_OF_MONTH, 15);

        Libro lb1 = new Libro("Programando con java",5,"Curuzu",2023);
        Libro lb2 = new Libro("Programando con C++",2,"Curuzu",2002);

        b.nuevoSocioEstudiante(33091399,"Estaban Quito","Systemas");
        b.nuevoSocioDocente(22091399,"Aldo Metini","Objetos");

        b.agregarLibro(lb1);
        b.agregarLibro(lb2);

        b.prestarLibro(fecha,b.buscarSocio(33091399),lb1);
        b.prestarLibro(fecha,b.buscarSocio(22091399),lb2);
    }

    /**
     * Persiste la biblioteca pasada como parametro en el archivo "Biblioteca.dat".
     * Si ocurre cualquier excepcion, se imprime la traza para diagnostico.
     *
     * @param p_biblioteca biblioteca a guardar
     */
    public static void guardarBiblioteca(Biblioteca p_biblioteca){
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
     * Intenta leer el objeto Biblioteca desde el archivo "Biblioteca.dat".
     * - Si el archivo existe, devuelve la biblioteca leída.
     * - Si no existe el archivo, solicita al usuario un nombre y crea una nueva biblioteca,
     *   ademas carga datos de prueba mediante cargarPrestamosVencidos(...).
     *
     * @return Biblioteca leida o nueva, nunca null
     */
    public static Biblioteca leerBiblioteca() {

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
            cargarPrestamosVencidos(bibliotecaGuardada);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el objeto:");
            e.printStackTrace();
        }
        return bibliotecaGuardada;
    }

     /**
     * Pregunta al usuario si desea cargar la biblioteca desde archivo; si la respuesta es
     * afirmativa llama a leerBiblioteca(), sino crea una nueva Biblioteca y carga datos
     * de prueba mediante cargarPrestamosVencidos(...).
     *
     * @return Biblioteca (cargada o nueva)
     */
    public static Biblioteca cargarSiONo(){
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
                cargarPrestamosVencidos(biblioteca);
            }
        } catch (InputMismatchException e) {
            System.out.println(" ERROR DE INGRESO DE DATO INCORRECTO: " + e.getMessage());
            System.out.println(" PRESIONE ENTER PARA CONTINUAR");
            teclado.nextLine(); // Limpia el buffer
        }
        return biblioteca;
    }
}
