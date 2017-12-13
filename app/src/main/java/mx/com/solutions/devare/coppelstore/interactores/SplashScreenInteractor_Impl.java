package mx.com.solutions.devare.coppelstore.interactores;

import android.content.Context;
import android.os.Handler;

import javax.inject.Inject;

import mx.com.solutions.devare.coppelstore.data.prefs.AppPrefs;
import mx.com.solutions.devare.coppelstore.data.prefs.IAppPrefs;
import mx.com.solutions.devare.coppelstore.data.prefs.ISessionPrefs;
import mx.com.solutions.devare.coppelstore.data.prefs.SessionPrefs;
import mx.com.solutions.devare.coppelstore.di.scopes.ApplicationContext;
import mx.com.solutions.devare.coppelstore.views.base.BaseInteractor;
import mx.com.solutions.devare.coppelstore.views.splash.ISplashScreenInteractor;
import mx.com.solutions.devare.coppelstore.views.splash.ISplashScreenPresenter;

public class SplashScreenInteractor_Impl extends BaseInteractor
        implements ISplashScreenInteractor {

    private ISplashScreenPresenter mPresenter;
    private static final int POST_DERLAY = 5000;
    private boolean ESTADO_LOGIN = false;
    private Context context;
    @Inject
    IAppPrefs appPrefs;
    @Inject
    ISessionPrefs sessionPrefs;

    @Inject
    public SplashScreenInteractor_Impl(@ApplicationContext Context context) {
        this.context = context;
    }

    @Override
    public void setPresenter(ISplashScreenPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void initAnimacionIteractor() {
        mPresenter.initIntroAnimacionPresenter();
        new Handler().postDelayed(() -> {
            if (verificarEstadoTYC()) {
                if (sessionPrefs.isLoggedIn()) mPresenter.initMainPresenter();
                else mPresenter.initLoginPresenter();
            } else {
               mPresenter.initTerminosYCondicionesPresenter();
            }
            mPresenter.finalizarActividadPresenter();
        }, POST_DERLAY);

    }

    private boolean verificarEstadoTYC() {
       return  appPrefs.getPreferenceBoolean(AppPrefs.PREFERENCE_ESTADO_TYC);
    }

}
