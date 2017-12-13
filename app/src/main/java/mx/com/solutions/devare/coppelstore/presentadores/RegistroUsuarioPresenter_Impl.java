package mx.com.solutions.devare.coppelstore.presentadores;

import javax.inject.Inject;

import mx.com.solutions.devare.coppelstore.views.base.BasePresenter;
import mx.com.solutions.devare.coppelstore.views.registro_usuario.IRegistroUsuarioInteractor;
import mx.com.solutions.devare.coppelstore.views.registro_usuario.IRegistroUsuarioPresenter;
import mx.com.solutions.devare.coppelstore.views.registro_usuario.IRegistroUsuarioView;

public class RegistroUsuarioPresenter_Impl<V extends IRegistroUsuarioView, I extends IRegistroUsuarioInteractor> extends BasePresenter<V,I> implements IRegistroUsuarioPresenter<V,I>{

    @Inject
    public RegistroUsuarioPresenter_Impl(I mInteractor) {
        super(mInteractor);
    }

    @Override
    public void onView(V mView) {
        super.onView(mView);
        getInteractor().setPresenter(this);
    }

    @Override
    public void initAdatadoresAutoCompletePresenter() {
        assert getView() != null;
        getView().initAdatadoresAutoComplete();
    }

    @Override
    public void registrarUsuarioPresenter(String correo, String password, String conf_Password, String nombre, String apaterno, String amaterno, String fecha_nacimiento, String telefono, String genero) {
        getInteractor().registrarUsuarioInteractor(correo,password,conf_Password,nombre,apaterno,amaterno,fecha_nacimiento,telefono,genero);
    }

    @Override
    public void seleccionarFechaPresenter() {
        assert getView() != null;
        getView().mostrarDatePickerFecha();
    }

    @Override
    public void enviarCredencialesALoginPresenter(String usuario, String password) {
        assert getView() != null;
        getView().enviarCredencialesALogin(usuario, password);
        getView().finalizarActividad();
    }

    @Override
    public void setErrorEmailPresenter(String msj) {
        assert getView() != null;
        getView().setErrorEmail(msj);
    }

    @Override
    public void setErrorPasswordPresenter(String msj) {
        assert getView() != null;
        getView().setErrorPassword(msj);
    }

    @Override
    public void setErrorConfirmarPasswordPresenter(String msj) {
        assert getView() != null;
        getView().setErrorConfirmarPassword(msj);
    }

    @Override
    public void setErrorNombrePresenter(String msj) {
        assert getView() != null;
        getView().setErrorNombre(msj);
    }

    @Override
    public void setErrorApaternoPresenter(String msj) {
        assert getView() != null;
        getView().setErrorApaterno(msj);
    }

    @Override
    public void setErrorAmaternoPresenter(String msj) {
        assert getView() != null;
        getView().setErrorAmaterno(msj);
    }

    @Override
    public void setErrorFechaNacimientoPresenter(String msj) {
        assert getView() != null;
        getView().setErrorFechaNacimiento(msj);
    }

    @Override
    public void setErrorTelefonoPresenter(String msj) {
        assert getView() != null;
        getView().setErrorTelefono(msj);
    }

    @Override
    public void setErrorSexoPresenter(String msj) {
        assert getView() != null;
        getView().setErrorSexo(msj);
    }

    @Override
    public void validaEmailPresenter(CharSequence charSequence) {
        getInteractor().validaEmailInteractor(charSequence);
    }

    @Override
    public void validaPasswordPresenter(CharSequence charSequence) {
        getInteractor().validaPasswordInteractor(charSequence);
    }

    @Override
    public void validaConfirmarPresenter(CharSequence charSequence) {
        getInteractor().validaConfirmarInteractor(charSequence);
    }

    @Override
    public void validaNombrePresenter(CharSequence charSequence) {
        getInteractor().validaNombreInteractor(charSequence);
    }

    @Override
    public void validaApaternoPresenter(CharSequence charSequence) {
        getInteractor().validaApaternoInteractor(charSequence);
    }

    @Override
    public void validaAmaternoPresenter(CharSequence charSequence) {
        getInteractor().validaAmaternoInteractor(charSequence);
    }

    @Override
    public void validaFechaNacimientoPresenter(CharSequence charSequence) {
        getInteractor().validaFechaNacimientoInteractor(charSequence);
    }

    @Override
    public void validaTelefonoPresenter(CharSequence charSequence) {
        getInteractor().validaTelefonoInteractor(charSequence);
    }

    @Override
    public void mostrarMensajePresenter(String msj) {
        assert getView() != null;
        getView().mostrarMensajeServidor(msj);
    }

}
