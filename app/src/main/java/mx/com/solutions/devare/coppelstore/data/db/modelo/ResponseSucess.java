package mx.com.solutions.devare.coppelstore.data.db.modelo;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;

public class ResponseSucess {

    private String estado;
    private String mensaje;

    public ResponseSucess(String estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
