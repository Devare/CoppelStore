package mx.com.solutions.devare.coppelstore.views.main;

import mx.com.solutions.devare.coppelstore.views.base.IBaseInteractor;

public interface IMainInteractor  extends IBaseInteractor{
    void setPresenter(IMainPresenter mPresenter);
    void getAllPromocionesIteractor();
}
