package mx.com.solutions.devare.coppelstore.views.main;

import android.content.Context;
import android.os.Bundle;

import java.util.List;

import mx.com.solutions.devare.coppelstore.data.db.modelo.Producto;
import mx.com.solutions.devare.coppelstore.views.base.IBasePresenter;

public interface IMainPresenter<V extends IMainView, I extends IMainInteractor> extends IBasePresenter<V,I> {

    void retrieveGetAllProductosPresenter(Bundle savedInstanceState);
    void showProductosRV(Producto[]  mProductoList);
    List<Producto> getProductos();
}

