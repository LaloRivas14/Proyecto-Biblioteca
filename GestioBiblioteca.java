
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
    
    boolean resp = true; 
    int opcion = 0;
    while(resp){
        menu();
        opcion = teclado.nextInt();
        teclado.nextLine();
        switch(opcion){
            case 1:
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
                biblioteca.nuevoLibro(tituloLibro,edicionLibro,editorialLibro,anioLibro);
                System.out.println("**** Ingreso de libro exitoso ****");
                break;

            case 2: 
                System.out.println("Socio que desea agregar");
                System.out.println("Estudiante - 1");
                System.out.println("Docente - 2");
                System.out.print("Seleccionar una opcion: ");
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

                biblioteca.nuevoSocioEstudiante(dniEstudiante,nombreEstudiante, carreraEstudiante);
                System.out.println("Ingreso del estudiante exitoso");

                }else if(tipoS == 2){
                System.out.println("Ingrese el dni del docente: ");
                int dniDocente = teclado.nextInt();
                teclado.nextLine();
                System.out.println("Ingrese el nombre y apellido del docente: ");
                String nombreDocente = teclado.nextLine();
                System.out.println("Ingrese el area del docente: ");
                String areaDocente = teclado.nextLine();

                biblioteca.nuevoSocioDocente(dniDocente,nombreDocente, areaDocente);
                System.out.println("Ingreso del docente exitoso");
                }else{
                System.out.println("Seleccione una opcion correcta: ");
                }
                break;
                
            case 4:
                Calendar fechaAct = Calendar.getInstance();
                
                System.out.print("ingrese el dni del socio: ");
                Socio socioPrestar = biblioteca.buscarSocio(teclado.nextInt());
                teclado.nextLine();
                System.out.print("Ingrese el titulo del libro: ");
                Libro libroPrestar = buscarLibro(teclado.nextLine(),biblioteca.getLibros());
                if(libroPrestar == null){
                    System.out.println("Libro prestado o no encontrado");
                    break;
                }
                biblioteca.prestarLibro(fechaAct,socioPrestar,libroPrestar);
                System.out.println("*** Libro Prestado Con Exito ***");
                break;
            case 5:
                System.out.print("Ingrese el titulo del libro: ");
                Libro libroDevolver = buscarLibro(teclado.nextLine(),biblioteca.getLibros());
                biblioteca.devolverLibro(libroDevolver);
                break;
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
                break;
            case 8:
                System.out.print("Ingrese el titulo del libro: ");
                Libro libroEncontrar = buscarLibro(teclado.nextLine(),biblioteca.getLibros());
                if(libroEncontrar == null){
                    System.out.println("Libro no encontrado..... ");
                    break;
                }
                System.out.println("El o los socios que tienen el libro: "+biblioteca.quienTieneElLibro(libroEncontrar));
                break;
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
    System.out.println("Menu de acciones: "+"\n 1)Nuevo Libro"+"\n 2)Nuevo socio Estudiando / Docente "+"\n 4)Prestar Libro"+"\n 5)Devolver Libro"+"\n 6)Cantidad de socios"+
                        "\n 7)Listas y datos"+"\n 8)Que socio tiene un libro especifico \n 9)Cerrar programa");
}
public static void menuDeListas(){
    System.out.println("Menu de listas: \n 1)Docentes responsables \n 2)Prestamos Vencidos \n 3)Lista de socios \n 4)Lista de titulos \n 5)Lista de libros"); 
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
        if(libro.getTitulo().equalsIgnoreCase(tituloLibro)&&!libro.prestado()){
            libroEncontrado = libro;
        }
    }
    return libroEncontrado;
}




}
