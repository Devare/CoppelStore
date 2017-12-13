package mx.com.solutions.devare.coppelstore.views.login;

import android.content.Context;

import mx.com.solutions.devare.coppelstore.views.base.IBasePresenter;

public interface ILoginPresenter<V extends ILoginView, I extends ILoginInteractor> extends IBasePresenter<V,I> {

    void onLoginCoppelPresenter(String usuario, String password);
    void onLoginFacebookPresenter();
    void initRegistroPresenter();
    void setErrorUsuarioPresenter(String msj);
    void setErrorPasswordPresenter(String msj);
    void validaUsuarioPresenter(CharSequence charSequence);
    void validaPasswordPresenter(CharSequence charSequence);
    void navigateToHomePresenter();
    void mostrarErrorServidorPresenter(String msj);

}
