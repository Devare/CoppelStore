package mx.com.solutions.devare.coppelstore.views.terminos_condiciones;

import mx.com.solutions.devare.coppelstore.views.base.IBaseInteractor;

public interface ITYCInteractor extends IBaseInteractor {
    void setPresenter(ITYCPresenter mPresenter);
    void guardarPrefsTYCInteractor();
}
