package mx.com.solutions.devare.coppelstore.views.registro_usuario;

import android.content.Context;

import mx.com.solutions.devare.coppelstore.data.db.modelo.RequestUsuario;
import mx.com.solutions.devare.coppelstore.views.base.IBasePresenter;

public interface IRegistroUsuarioPresenter<V extends IRegistroUsuarioView, I extends IRegistroUsuarioInteractor> extends IBasePresenter<V,I> {

    void initAdatadoresAutoCompletePresenter();
    void registrarUsuarioPresenter(String correo, String password, String ConfPassword,String nombre, String apaterno, String amaterno, String fecha_nacimiento, String telefono, String genero);
    void seleccionarFechaPresenter();
    void enviarCredencialesALoginPresenter(String usuario, String password);
    void setErrorEmailPresenter(String msj);
    void setErrorPasswordPresenter(String msj);
    void setErrorConfirmarPasswordPresenter(String msj);
    void setErrorNombrePresenter(String msj);
    void setErrorApaternoPresenter(String msj);
    void setErrorAmaternoPresenter(String msj);
    void setErrorFechaNacimientoPresenter(String msj);
    void setErrorTelefonoPresenter(String msj);
    void setErrorSexoPresenter(String msj);
    void validaEmailPresenter(CharSequence charSequence);
    void validaPasswordPresenter(CharSequence charSequence);
    void validaConfirmarPresenter(CharSequence charSequence);
    void validaNombrePresenter(CharSequence charSequence);
    void validaApaternoPresenter(CharSequence charSequence);
    void validaAmaternoPresenter(CharSequence charSequence);
    void validaFechaNacimientoPresenter(CharSequence charSequence);
    void validaTelefonoPresenter(CharSequence charSequence);
    void mostrarMensajePresenter(String msj);
}
