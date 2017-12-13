package mx.com.solutions.devare.coppelstore.interactores;

import javax.inject.Inject;

import io.realm.Realm;
import mx.com.solutions.devare.coppelstore.data.db.servicios.ServicioProductos;
import mx.com.solutions.devare.coppelstore.views.base.BaseInteractor;
import mx.com.solutions.devare.coppelstore.views.main.IMainInteractor;
import mx.com.solutions.devare.coppelstore.views.main.IMainPresenter;


public class MainInteractor_Impl extends BaseInteractor implements IMainInteractor {
    private IMainPresenter mPresenter;

    @Inject ServicioProductos servicioProductos;

    @Inject
    public MainInteractor_Impl() {
    }

    @Override
    public void setPresenter(IMainPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void getAllPromocionesIteractor() {
        Realm realm= Realm.getDefaultInstance();
        mPresenter.showProductosRV(servicioProductos.getAllProductos());
    }
}
