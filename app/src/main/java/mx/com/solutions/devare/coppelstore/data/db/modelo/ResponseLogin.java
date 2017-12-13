package mx.com.solutions.devare.coppelstore.data.db.modelo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin {

    @SerializedName("estado")
    @Expose
    private Integer estado;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("ResponseLogin")
    @Expose
    private String usuario;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("APaterno")
    @Expose
    private String aPaterno;
    @SerializedName("AMaterno")
    @Expose
    private String aMaterno;
    @SerializedName("Genero")
    @Expose
    private String genero;
    @SerializedName("Token")
    @Expose
    private String token;


    public ResponseLogin() {
    }

    public ResponseLogin(Integer estado, String mensaje) {
        super();
        this.estado=estado;
        this.mensaje=mensaje;
    }

    /**
     * @param aPaterno
     * @param nombre
     * @param genero
     * @param estado
     * @param token
     * @param usuario
     * @param aMaterno
     */
    public ResponseLogin(Integer estado, String usuario, String nombre, String aPaterno, String aMaterno, String genero, String token) {
        super();
        this.estado = estado;
        this.usuario = usuario;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.genero = genero;
        this.token = token;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAPaterno() {
        return aPaterno;
    }

    public void setAPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getAMaterno() {
        return aMaterno;
    }

    public void setAMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}