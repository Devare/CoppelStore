package mx.com.solutions.devare.coppelstore.utils;


public interface IValidate {
    boolean esVacio(String cadena);
    boolean esEmailValido(String email);
    boolean esNombreValido(String nombre);
    boolean esTelefonoValido(String telefono);
}
