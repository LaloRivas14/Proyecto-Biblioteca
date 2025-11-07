
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
    
    String resp = "si"; 
    while(resp.equals("si")){
        menu();
        switch(teclado.nextInt()){
            case 1:
                System.out.print("Ingrese el titulo del libro");
                String tituloLibro = teclado.nextLine();
                System.out.print("Ingrese el num de edicion del libro");
                int edicionLibro = teclado.nextInt();
                teclado.nextLine();
                System.out.print("Ingrese la editorial del libro");
                String editorialLibro = teclado.nextLine();
                System.out.print("ingrese el año del libro");
                int anioLibro = teclado.nextInt();
                teclado.nextLine();
                biblioteca.nuevoLibro(tituloLibro,edicionLibro,editorialLibro,anioLibro);
                System.out.println("**** Ingreso de libro exitoso ****");
                break;
            case 4:
                Calendar fechaAct = Calendar.getInstance();
                
                System.out.print("ingrese el dni del socio");
                Socio socioPrestar = biblioteca.buscarSocio(teclado.nextInt());
                System.out.print("Ingrese el titulo del libro");
                Libro libroPrestar = buscarLibro(teclado.nextLine(),biblioteca.getLibros());
                biblioteca.prestarLibro(fechaAct,socioPrestar,libroPrestar);
                System.out.println("*** Libro Prestado Con Exito ***");
                break;
            case 5:
                System.out.print("Ingrese el titulo del libro");
                Libro libroDevolver = buscarLibro(teclado.nextLine(),biblioteca.getLibros());
                biblioteca.devolverLibro(libroDevolver);
                break;
            case 6:
                System.out.print("ingrese el tipo de socio que desea 
                
                
        }
    }
    
}



public static void menu(){
    System.out.println("Menu de acciones: "+"\n 1)_Nuevo Libro"+"\n 2)_Nuevo socio estudiante"+"\n 3)_Nuevo socio Docente"+
                    "\n 4)_Prestar Libro"+"\n 5)_Devolver Libro"+"\n 6)_Cantidad de socios"+"\n 7)_Listas y datos"+"\n 8)_Que socio tiene un libro especifico");
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