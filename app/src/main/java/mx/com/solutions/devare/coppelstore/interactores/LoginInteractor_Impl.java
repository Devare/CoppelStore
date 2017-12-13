package mx.com.solutions.devare.coppelstore.interactores;

import android.content.Context;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import mx.com.solutions.devare.coppelstore.R;
import mx.com.solutions.devare.coppelstore.data.network.ApiError;
import mx.com.solutions.devare.coppelstore.data.db.modelo.RequestLogin;
import mx.com.solutions.devare.coppelstore.data.db.modelo.ResponseLogin;
import mx.com.solutions.devare.coppelstore.data.db.modelo.ResponseProductos;
import mx.com.solutions.devare.coppelstore.data.network.ApiService;
import mx.com.solutions.devare.coppelstore.data.prefs.ISessionPrefs;
import mx.com.solutions.devare.coppelstore.data.prefs.SessionPrefs;
import mx.com.solutions.devare.coppelstore.di.scopes.ApplicationContext;
import mx.com.solutions.devare.coppelstore.utils.IValidate;
import mx.com.solutions.devare.coppelstore.utils.Validaciones;
import mx.com.solutions.devare.coppelstore.views.base.BaseInteractor;
import mx.com.solutions.devare.coppelstore.views.login.ILoginInteractor;
import mx.com.solutions.devare.coppelstore.views.login.ILoginPresenter;
import retrofit2.HttpException;
import retrofit2.Response;

public class LoginInteractor_Impl extends BaseInteractor implements ILoginInteractor {

    private ILoginPresenter mPresenter;
    private IValidate validate;
    private Context context;

    @Inject
    ApiService service;
    @Inject
    ISessionPrefs sessionPrefs;


    @Inject
    public LoginInteractor_Impl(@ApplicationContext Context context) {
        this.context = context;
        validate = new Validaciones();
    }

    @Override
    public void setPresenter(ILoginPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void validaCamposIteractor(String mUsuario, String mPassword) {
        boolean statusUsuario = validate.esVacio(mUsuario);
        boolean statusPassword = validate.esVacio(mPassword);

        if (!statusUsuario && !statusPassword) {
            validaCredenciales(mUsuario, mPassword);
        }

        verificaUsuario(statusUsuario);
        verificaPassword(statusPassword);
    }

    private void validaCredenciales(String mUsuario, String mPassword) {

        service.login(new RequestLogin(mUsuario, mPassword))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseLogin>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseLogin responseLogin) {
                        sessionPrefs.saveUsuarioCoppelStore(responseLogin);
                        sessionPrefs.setCurrentUserLoggedInMode(SessionPrefs.LoggedInMode.LOGGED_IN_MODE_COPPEL);
                    }

                    @Override
                    public void onError(Throwable error) {

                        if (error instanceof HttpException) {
                            mPresenter.mostrarErrorServidorPresenter(getErrorMensaje(error));
                        } else if (error instanceof IOException) {
                            mPresenter.mostrarErrorServidorPresenter(error.getMessage());
                        } else {
                            mPresenter.mostrarErrorServidorPresenter(error.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        getAndSaveProductos();
                    }
                });

    }

    private void getAndSaveProductos() {
        Realm realm = Realm.getDefaultInstance();
        service.getProductos(getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseProductos>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseProductos responseProductos) {

                        realm.beginTransaction();
                        realm.insert(responseProductos.getProductos());
                        realm.commitTransaction();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mPresenter.navigateToHomePresenter();
                        realm.close();
                    }
                });
    }

    /*********************************
     * Obtener el Token del usuario  *
     *********************************/
    private String getToken() {
        return sessionPrefs.getPreferenceString(SessionPrefs.PREF_USUARIO_TOKEN);
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
    public void validaUsuarioIteractor(CharSequence mUsuario) {
        verificaUsuario(validate.esVacio(mUsuario.toString().trim()));
    }

    @Override
    public void validaPasswordIteractor(CharSequence mPassword) {
        verificaPassword(validate.esVacio(mPassword.toString().trim()));
    }

    /******************************************************
     *  Administrador de las validaciones                 *
     ******************************************************/
    private void verificaPassword(boolean status) {
        if (status) {
            mPresenter.setErrorPasswordPresenter(getMsjVacio());
        } else {
            mPresenter.setErrorPasswordPresenter(null);
        }
    }

    private void verificaUsuario(boolean status) {
        if (status) {
            mPresenter.setErrorUsuarioPresenter(getMsjVacio());
        } else {
            mPresenter.setErrorUsuarioPresenter(null);
        }
    }

    private String getMsjVacio() {
        return context.getString(R.string.msj_error_campo_vacio);
    }

}

