package mx.com.solutions.devare.coppelstore.views.terminos_condiciones;

import android.content.Context;

import mx.com.solutions.devare.coppelstore.di.scopes.PerActivity;
import mx.com.solutions.devare.coppelstore.views.base.IBasePresenter;

@PerActivity
public interface ITYCPresenter<V extends ITYCView, I extends ITYCInteractor> extends IBasePresenter<V, I> {

    void escucharEventoScrollPresenter();
    void mostrarBotonesPresenter();
    void guardarPrefsTYCPresenter();
    void initLogin();
    void finalizarActividadPresenter();
}
