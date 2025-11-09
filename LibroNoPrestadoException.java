/**
 * La clase LibroNoPrestadoException representa una excepcion personalizada
 * que se lanza cuando se intenta devolver un libro que no esta prestado.
 * 
 * Esta excepcion extiende la clase base Exception y permite mostrar un mensaje
 * específico para informar el error.
 *
 * @author Rivas Lautaro
 * @version 1.0
 */

public class LibroNoPrestadoException extends Exception{
    /**
     * Constructor que recibe un mensaje descriptivo del error.
     * @param mensaje detalle del motivo por el cual se lanza la excepcion.
     */
    public LibroNoPrestadoException(String mensaje){
        super(mensaje);
    }
}
