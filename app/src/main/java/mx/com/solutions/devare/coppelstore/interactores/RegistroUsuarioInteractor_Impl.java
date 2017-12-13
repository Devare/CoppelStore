package mx.com.solutions.devare.coppelstore.interactores;

import android.content.Context;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mx.com.solutions.devare.coppelstore.R;
import mx.com.solutions.devare.coppelstore.data.db.modelo.RequestUsuario;
import mx.com.solutions.devare.coppelstore.data.db.modelo.ResponseSucess;
import mx.com.solutions.devare.coppelstore.data.network.ApiError;
import mx.com.solutions.devare.coppelstore.data.network.ApiService;
import mx.com.solutions.devare.coppelstore.di.scopes.ApplicationContext;
import mx.com.solutions.devare.coppelstore.utils.IValidate;
import mx.com.solutions.devare.coppelstore.utils.Validaciones;
import mx.com.solutions.devare.coppelstore.views.base.BaseInteractor;
import mx.com.solutions.devare.coppelstore.views.registro_usuario.IRegistroUsuarioInteractor;
import mx.com.solutions.devare.coppelstore.views.registro_usuario.IRegistroUsuarioPresenter;
import retrofit2.HttpException;
import retrofit2.Response;

public class RegistroUsuarioInteractor_Impl extends BaseInteractor implements IRegistroUsuarioInteractor {

    private IRegistroUsuarioPresenter mPresenter;
    private IValidate validate;
    private Context context;

    @Inject
    public ApiService service;

    @Inject
    public RegistroUsuarioInteractor_Impl(@ApplicationContext Context context) {
        validate = new Validaciones();
        this.context = context;
    }

    @Override
    public void setPresenter(IRegistroUsuarioPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void registrarUsuarioInteractor(String correo, String password, String ConfPassword, String nombre, String apaterno, String amaterno, String fecha_nacimiento, String telefono, String genero) {
        boolean STATUS_EMAIL = verificaEmail(correo);
        boolean STATUS_PASSWORD = verificaPassword(password, ConfPassword);
        boolean STATUS_NOMBRE = verificaNombre(nombre);
        boolean STATUS_APATERNO = verificaApaterno(apaterno);
        boolean STATUS_AMATERNO = verificaAmaterno(amaterno);
        boolean STATUS_FECHA_NACIMIENTO = verificaFechaNacimiento(fecha_nacimiento);
        boolean STATUS_TELEFONO = verificaTelefono(telefono);
        boolean STATUS_GENERO = verificaGenero(genero);

        if (STATUS_EMAIL && STATUS_PASSWORD && STATUS_NOMBRE && STATUS_APATERNO && STATUS_AMATERNO && STATUS_FECHA_NACIMIENTO && STATUS_TELEFONO && STATUS_GENERO) {

            service.registrarUsuario(new RequestUsuario(correo, password, nombre, apaterno, amaterno, fecha_nacimiento, telefono, genero))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseSucess>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ResponseSucess responseSucess) {
                            mPresenter.mostrarMensajePresenter(responseSucess.getMensaje());
                        }

                        @Override
                        public void onError(Throwable error) {

                            if (error instanceof HttpException) {
                                mPresenter.mostrarMensajePresenter(getErrorMensaje(error));
                            } else if (error instanceof IOException) {
                                mPresenter.mostrarMensajePresenter(error.getMessage());
                            } else {
                                mPresenter.mostrarMensajePresenter(error.getMessage());
                            }
                        }

                        @Override
                        public void onComplete() {
                            mPresenter.enviarCredencialesALoginPresenter(correo, password);
                        }
                    });
        }
    }

    /******************************************************
     *  Manejo de errores del servidor                    *
     ******************************************************/
    private String getErrorMensaje(Throwable error) {
        HttpException httpException = (HttpException) error;
        Response response = httpException.response();

        if (response.errorBody().contentType().subtype().equals("json")) {
            ApiError apiError = ApiError.fromResponseBody(response.errorBody());
            assert apiError != null;
            return apiError.getMensaje();
        } else {
            return response.message();
        }
    }

    /******************************************************
     *  Metodos de la Interfaz de comunicacion Interactor *
     ******************************************************/
    @Override
    public void validaEmailInteractor(CharSequence charSequence) {
        verificaEmail(charToString(charSequence));
    }

    @Override
    public void validaPasswordInteractor(CharSequence charSequence) {
        verificaPassword(charToString(charSequence), null);
    }

    @Override
    public void validaConfirmarInteractor(CharSequence charSequence) {
        verificaPassword(null, charToString(charSequence));
    }

    @Override
    public void validaNombreInteractor(CharSequence charSequence) {
        verificaNombre(charToString(charSequence));
    }

    @Override
    public void validaApaternoInteractor(CharSequence charSequence) {
        verificaApaterno(charToString(charSequence));
    }

    @Override
    public void validaAmaternoInteractor(CharSequence charSequence) {
        verificaAmaterno(charToString(charSequence));
    }

    @Override
    public void validaFechaNacimientoInteractor(CharSequence charSequence) {
        verificaFechaNacimiento(charToString(charSequence));
    }

    @Override
    public void validaTelefonoInteractor(CharSequence charSequence) {
        verificaTelefono(charToString(charSequence));
    }

    /******************************************************
     *  Administrador de las validaciones                 *
     ******************************************************/

    private boolean verificaEmail(String cadena) {
        if (validate.esVacio(cadena)) {
            setErrorEmail(getMsjVacio());
            return false;
        }
        if (!validate.esEmailValido(cadena)) {
            setErrorEmail(getMsjEmailInvalido());
            return false;
        }
        setErrorEmail(null);
        return true;
    }

    private void setErrorEmail(String msj) {
        mPresenter.setErrorEmailPresenter(msj);
    }

    private boolean verificaPassword(String password, String confirmarPassword) {

        if (password != null) {
            if (validate.esVacio(password)) {
                setErrorPassword(getMsjVacio());
                return false;
            }
        }

        if (confirmarPassword != null) {
            if (validate.esVacio(confirmarPassword)) {
                setErrorConfirmarPassword(getMsjVacio());
                return false;
            }
        }

        if (password != null && confirmarPassword != null) {
            if (!password.equals(confirmarPassword)) {
                setErrorConfirmarPassword(getMsjPasswordIncorrecto());
                return false;
            }
        }

        setErrorPassword(null);
        setErrorConfirmarPassword(null);
        return true;
    }

    private void setErrorPassword(String msj) {
        mPresenter.setErrorPasswordPresenter(msj);
    }

    private void setErrorConfirmarPassword(String msj) {
        mPresenter.setErrorConfirmarPasswordPresenter(msj);
    }


    private boolean verificaNombre(String cadena) {
        if (validate.esVacio(cadena)) {
            setErrorNombre(getMsjVacio());
            return false;
        }

        if (!validate.esNombreValido(cadena)) {
            setErrorNombre(getMsjNombreInvalido());
            return false;
        }

        setErrorNombre(null);
        return true;
    }

    private void setErrorNombre(String msj) {
        mPresenter.setErrorNombrePresenter(msj);
    }

    private boolean verificaApaterno(String cadena) {
        if (validate.esVacio(cadena)) {
            setErrorApaterno(getMsjVacio());
            return false;
        }

        if (!validate.esNombreValido(cadena)) {
            setErrorApaterno(getMsjNombreInvalido());
            return false;
        }

        setErrorApaterno(null);
        return true;
    }

    private void setErrorApaterno(String msj) {
        mPresenter.setErrorApaternoPresenter(msj);
    }

    private boolean verificaAmaterno(String cadena) {
        if (validate.esVacio(cadena)) {
            setErrorAmaterno(getMsjVacio());
            return false;
        }

        if (!validate.esNombreValido(cadena)) {
            setErrorAmaterno(getMsjNombreInvalido());
            return false;
        }

        setErrorAmaterno(null);
        return true;
    }

    private void setErrorAmaterno(String msj) {
        mPresenter.setErrorAmaternoPresenter(msj);
    }

    private boolean verificaFechaNacimiento(String cadena) {
        if (cadena.equals(getHintFecha())) {
            setErrorFechaNacimiento(getMsjFechaNoSeleccionada());
            return false;
        }
        setErrorFechaNacimiento(null);
        return true;
    }

    private void setErrorFechaNacimiento(String msj) {
        mPresenter.setErrorFechaNacimientoPresenter(msj);
    }

    private boolean verificaTelefono(String cadena) {
        if (validate.esVacio(cadena)) {
            setErrorTelefono(getMsjVacio());
            return false;
        }

        if (!validate.esTelefonoValido(cadena)) {
            setErrorTelefono(getMsjTelefono());
            return false;
        }

        setErrorTelefono(null);
        return true;
    }

    private void setErrorTelefono(String msj) {
        mPresenter.setErrorTelefonoPresenter(msj);
    }

    private boolean verificaGenero(String cadena) {
        if (cadena == null) {
            setErrorGenero(getMsjGenero());
            return false;
        }
        return true;
    }

    private void setErrorGenero(String msj) {
        mPresenter.setErrorSexoPresenter(msj);
    }

    private String getMsjVacio() {
        return context.getString(R.string.msj_error_campo_vacio);
    }

    private String getMsjEmailInvalido() {
        return context.getString(R.string.msj_error_email);
    }

    private String getMsjNombreInvalido() {
        return context.getString(R.string.msj_error_nombre);
    }

    private String getMsjGenero() {
        return context.getString(R.string.msj_error_sexo);
    }

    private String getMsjPasswordIncorrecto() {
        return context.getString(R.string.msj_error_contrasenas);
    }

    private String getMsjFechaNoSeleccionada() {
        return context.getString(R.string.msj_error_fecha_nacimiento);
    }

    private String getHintFecha() {
        return context.getString(R.string.hint_registro_fecha_de_nacimiento);
    }

    private String getMsjTelefono() {
        return context.getString(R.string.msj_error_telefono);
    }

    private String charToString(CharSequence charSequence) {
        return charSequence.toString().trim();
    }

}
