package mx.com.solutions.devare.coppelstore.interactores;

import android.content.Context;

import javax.inject.Inject;

import mx.com.solutions.devare.coppelstore.data.prefs.AppPrefs;
import mx.com.solutions.devare.coppelstore.data.prefs.IAppPrefs;
import mx.com.solutions.devare.coppelstore.di.scopes.ApplicationContext;
import mx.com.solutions.devare.coppelstore.views.base.BaseInteractor;
import mx.com.solutions.devare.coppelstore.views.base.IBaseInteractor;
import mx.com.solutions.devare.coppelstore.views.terminos_condiciones.ITYCInteractor;
import mx.com.solutions.devare.coppelstore.views.terminos_condiciones.ITYCPresenter;

public class TYCInteractor_Impl extends BaseInteractor implements ITYCInteractor {
    private ITYCPresenter mPresenter;

    @Inject
     IAppPrefs mAppPrefs;

    @Inject
    public TYCInteractor_Impl() {
    }

    @Override
    public void setPresenter(ITYCPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void guardarPrefsTYCInteractor() {
        mAppPrefs.savePreferenceBoolean(true, AppPrefs.PREFERENCE_ESTADO_TYC);
        mPresenter.initLogin();
    }
}
