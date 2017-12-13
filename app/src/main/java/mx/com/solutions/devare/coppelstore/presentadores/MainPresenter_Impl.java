package mx.com.solutions.devare.coppelstore.presentadores;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import mx.com.solutions.devare.coppelstore.data.db.modelo.Producto;
import mx.com.solutions.devare.coppelstore.interactores.MainInteractor_Impl;
import mx.com.solutions.devare.coppelstore.views.base.BasePresenter;
import mx.com.solutions.devare.coppelstore.views.main.IMainInteractor;
import mx.com.solutions.devare.coppelstore.views.main.IMainPresenter;
import mx.com.solutions.devare.coppelstore.views.main.IMainView;


public class MainPresenter_Impl<V extends IMainView, I extends IMainInteractor> extends BasePresenter<V,I> implements IMainPresenter<V,I> {

    private List<Producto> mListaProductos;

    @Inject
    public MainPresenter_Impl(I mInteractor) {
        super(mInteractor);
        mListaProductos =  new ArrayList<>();
    }

    @Override
    public void onView(V mView) {
        super.onView(mView);
        getInteractor().setPresenter(this);
    }

    @Override
    public void retrieveGetAllProductosPresenter(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mListaProductos = savedInstanceState.getParcelableArrayList("KEY_PRODUCTOS");
            showUpdate();
            return;
        }
        getInteractor().getAllPromocionesIteractor();
    }

    @Override
    public void showProductosRV(Producto[] mProductoList) {

        if (mListaProductos !=null && mProductoList !=null){
            Collections.addAll(mListaProductos, mProductoList);
            showUpdate();
        }
    }

    private void showUpdate() {
        if (getView() != null){
            getView().generarLinearLayoutVertical();
            getView().initAdaptadorRV();
        }
    }

    @Override
    public List<Producto> getProductos() {
        return mListaProductos;
    }

}
