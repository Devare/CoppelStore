package mx.com.solutions.devare.coppelstore.utils;

import java.util.regex.Pattern;

public class Validaciones implements IValidate{

    public Validaciones() {
    }

    @Override
    public boolean esVacio(String cadena) {
        return cadena.isEmpty();
    }

    @Override
    public boolean esEmailValido(String email){
        Pattern patron = Pattern.compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$");
        return patron.matcher(email).matches();
    }

    @Override
    public boolean esNombreValido(String nombre) {
        //Se valida un nombre completo que solo acepte acentos,a-z,A-Z y espacios entre nombres.
        Pattern patron = Pattern.compile("^([a-zA-ZÁÉÍÓÚñáéíóú]{3,30})+([a-zA-ZÁÉÍÓÚñáéíóú ]{0,30}+)$");
        return patron.matcher(nombre).matches();
    }

    @Override
    public boolean esTelefonoValido(String telefono){
       /* $var = '011-4959-0200';
        $pattern = '/^([0-9]{4})(-)([0-9]{7})$/';
        $pattern_2 = '/^([0-9]{3})(-)([0-9]{4})(-)([0-9]{4})$/';*/
        Pattern patron = Pattern.compile("^([0-9]{10})$");
        return patron.matcher(telefono).matches();
    }
}
