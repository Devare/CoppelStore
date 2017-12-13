package mx.com.solutions.devare.coppelstore.views.login;

import mx.com.solutions.devare.coppelstore.views.base.IBaseView;

public interface ILoginView extends IBaseView {

    void setErrorUsuario(String msj);
    void setErrorPassword(String msj);
    void navigateToHome();
    void navigateToRegistro();
    void finishActivity();
    void mostrarMsjError(String msj);
}
