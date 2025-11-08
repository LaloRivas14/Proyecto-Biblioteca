package p_biblioteca;


/**
 * Write a description of class GestioBiblioteca here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
public class GestioBiblioteca{
    public static void main(String [] args) throws LibroNoPrestadoException {
        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingrese el nombre que tendra la biblioteca: ");
        String nomBiblioteca = teclado.nextLine();

        Biblioteca biblioteca = new Biblioteca(nomBiblioteca);
        cargarPrestamosVencidos(biblioteca);
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
                        System.out.println(" ERROR: Se intentó acceder a un objeto nulo. El socio no se encontró.");
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
                    menuDeListas();
                    mostrarUnaLista(teclado.nextInt(),biblioteca);
                    teclado.nextLine();
                    break;
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
                default:
                    System.out.println("***** Opcion ingresada incorrecta ****");

            } 

        }

    }

    public static void menu(){
        System.out.println("Menu de acciones: "+"\n 1)Nuevo Libro"+"\n 2)Nuevo socio Estudiante / Docente \n 3)Eliminación \n 4)Prestar Libro"+"\n 5)Devolver Libro"+"\n 6)Cantidad de socios"+
            "\n 7)Listas y datos"+"\n 8)Que socio tiene un libro especifico \n 9)Cerrar programa");
    }

    public static void menuDeListas(){
        System.out.println("Menu de listas: \n 1)Docentes responsables \n 2)Prestamos Vencidos \n 3)Lista de socios \n 4)Lista de titulos \n 5)Lista de libros"); 
    }

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

    public static void prestamoLibro(Biblioteca p_b){
        Scanner teclado = new Scanner(System.in);
        Calendar fechaAct = Calendar.getInstance();

        System.out.print("ingrese el dni del socio: ");
        Socio socioPrestar = p_b.buscarSocio(teclado.nextInt());
        teclado.nextLine();
        System.out.print("Ingrese el titulo del libro: ");
        Libro libroPrestar = buscarLibro(teclado.nextLine(),p_b.getLibros());
        p_b.prestarLibro(fechaAct,socioPrestar,libroPrestar);
        System.out.println("*** Libro Prestado Con Exito ***");
    }

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
            System.out.println(p_b.listaDeLibros());
            System.out.print("Elija el indice del libro que desea eliminar: ");
            int libroEliminar = teclado.nextInt();
            teclado.nextLine();
            if (libroEliminar > 0 && libroEliminar <= p_b.getLibros().size()) {
                Libro eliminarLibro = p_b.getLibros().get(libroEliminar - 1);
                if (eliminarLibro.prestado() == false) { 
                    p_b.removerLibro(eliminarLibro); 
                    System.out.println("El libro se ha eliminado correctamente");
                }else {
                    System.out.println("El libro esta prestado");
                }
            } else {
                System.out.println("Ingrese un numero correcto");
            }

        } else {
            System.out.println("Seleccione una opcion correcta");
        }

    }

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

    public static Libro buscarLibro(String tituloLibro, ArrayList<Libro> p_libros){
        Libro libroEncontrado = null;
        for(Libro libro : p_libros){
            if(libro.getTitulo().equalsIgnoreCase(tituloLibro)){
                libroEncontrado = libro;
            }
        }
        return libroEncontrado;
    }

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

}
