package mx.com.solutions.devare.coppelstore.views.registro_usuario;

import mx.com.solutions.devare.coppelstore.views.base.IBaseView;

public interface IRegistroUsuarioView  extends IBaseView{
    void initAdatadoresAutoComplete();
    void mostrarDatePickerFecha();
    void enviarCredencialesALogin(String usuario, String password);
    void setErrorEmail(String msj);
    void setErrorPassword(String msj);
    void setErrorConfirmarPassword(String msj);
    void setErrorNombre(String msj);
    void setErrorApaterno(String msj);
    void setErrorAmaterno(String msj);
    void setErrorFechaNacimiento(String msj);
    void setErrorTelefono(String msj);
    void setErrorSexo(String msj);
    void finalizarActividad();
    void mostrarMensajeServidor(String msj);
}
