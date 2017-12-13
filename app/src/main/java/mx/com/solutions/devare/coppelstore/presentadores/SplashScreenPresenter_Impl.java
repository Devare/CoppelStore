package mx.com.solutions.devare.coppelstore.presentadores;

import javax.inject.Inject;

import mx.com.solutions.devare.coppelstore.views.base.BasePresenter;
import mx.com.solutions.devare.coppelstore.views.splash.ISplashScreenInteractor;
import mx.com.solutions.devare.coppelstore.views.splash.ISplashScreenPresenter;
import mx.com.solutions.devare.coppelstore.views.splash.ISplashScreenView;

public class SplashScreenPresenter_Impl<V extends ISplashScreenView, I extends ISplashScreenInteractor>
        extends BasePresenter<V, I> implements ISplashScreenPresenter<V, I>{

    @Inject
    public SplashScreenPresenter_Impl(I mInteractor) {
        super(mInteractor);
    }

    @Override
    public void onView(V mView) {
        super.onView(mView);
        getInteractor().setPresenter(this);
        initAnimacionPresenter();
    }

    @Override
    public void initAnimacionPresenter() {
        getInteractor().initAnimacionIteractor();
    }

    @Override
    public void initIntroAnimacionPresenter() {
        assert getView() !=null;
        getView().initIntroAnimacion();
    }

    @Override
    public void initTerminosYCondicionesPresenter() {
        assert getView() !=null;
        getView().initTerminosYCondiciones();
    }

    @Override
    public void initLoginPresenter() {
        assert getView() !=null;
        getView().initLogin();
    }

    @Override
    public void initMainPresenter() {
        assert getView() !=null;
        getView().initMain();
    }

    @Override
    public void finalizarActividadPresenter() {
        assert getView() !=null;
        getView().finalizarActividad();
    }

}
