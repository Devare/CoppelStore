package mx.com.solutions.devare.coppelstore.data.db.modelo;


public class RequestLogin {
    private String Usuario;
    private String Password;

    public RequestLogin(String usuario, String password) {
        Usuario = usuario;
        Password = password;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

