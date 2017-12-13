package mx.com.solutions.devare.coppelstore.data.db.modelo;


public class RequestUsuario {

    private String idUsuario;
    private String Password;
    private String Nombre;
    private String APaterno;
    private String AMaterno;
    private String Fecha_Nacimiento;
    private String Telefono;
    private String Genero;

    public RequestUsuario() {

    }

    public RequestUsuario(String idUsuario,String password,String nombre, String APaterno, String AMaterno, String fecha_Nacimiento, String telefono, String genero) {
        this.idUsuario = idUsuario;
        this.Password = password;
        Nombre = nombre;
        this.APaterno = APaterno;
        this.AMaterno = AMaterno;
        Fecha_Nacimiento = fecha_Nacimiento;
        Telefono = telefono;
        Genero = genero;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getAPaterno() {
        return APaterno;
    }

    public void setAPaterno(String APaterno) {
        this.APaterno = APaterno;
    }

    public String getAMaterno() {
        return AMaterno;
    }

    public void setAMaterno(String AMaterno) {
        this.AMaterno = AMaterno;
    }

    public String getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(String fecha_Nacimiento) {
        Fecha_Nacimiento = fecha_Nacimiento;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    @Override
    public String toString() {
        return "RequestUsuario{" +
                "idUsuario='" + idUsuario + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", APaterno='" + APaterno + '\'' +
                ", AMaterno='" + AMaterno + '\'' +
                ", Fecha_Nacimiento='" + Fecha_Nacimiento + '\'' +
                ", Telefono='" + Telefono + '\'' +
                ", Genero='" + Genero + '\'' +
                '}';
    }
}
