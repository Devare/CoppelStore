package mx.com.solutions.devare.coppelstore.views.splash;


import mx.com.solutions.devare.coppelstore.di.scopes.PerActivity;
import mx.com.solutions.devare.coppelstore.views.base.IBasePresenter;

@PerActivity
public interface ISplashScreenPresenter<V extends ISplashScreenView,
        I extends ISplashScreenInteractor> extends IBasePresenter<V, I> {

    void initAnimacionPresenter();
    void initIntroAnimacionPresenter();
    void initTerminosYCondicionesPresenter();
    void initLoginPresenter();
    void initMainPresenter();
    void finalizarActividadPresenter();
}
