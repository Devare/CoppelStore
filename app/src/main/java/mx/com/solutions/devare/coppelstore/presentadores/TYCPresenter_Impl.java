package mx.com.solutions.devare.coppelstore.presentadores;

import android.view.View;

import javax.inject.Inject;
import mx.com.solutions.devare.coppelstore.views.base.BasePresenter;
import mx.com.solutions.devare.coppelstore.views.terminos_condiciones.ITYCInteractor;
import mx.com.solutions.devare.coppelstore.views.terminos_condiciones.ITYCPresenter;
import mx.com.solutions.devare.coppelstore.views.terminos_condiciones.ITYCView;

public class TYCPresenter_Impl<V extends ITYCView, I extends ITYCInteractor>
        extends BasePresenter<V, I> implements ITYCPresenter<V, I> {

    @Inject
    public TYCPresenter_Impl(I mInteractor) {
        super(mInteractor);
    }

    @Override
    public void onView(V mView) {
        super.onView(mView);
        getInteractor().setPresenter(this);
    }

    @Override
    public void escucharEventoScrollPresenter() {
        assert getView() !=null;
        getView().changeListenerScroll();
    }

    @Override
    public void mostrarBotonesPresenter() {
        assert getView() !=null;
        getView().habilitarBotones(View.VISIBLE);
    }

    @Override
    public void guardarPrefsTYCPresenter() {
        getInteractor().guardarPrefsTYCInteractor();
    }

    @Override
    public void initLogin() {
        assert getView() !=null;
        getView().initLogin();
        getView().finalizarActividad();
    }

    @Override
    public void finalizarActividadPresenter() {
        assert getView() !=null;
        getView().finalizarActividad();
    }
}
