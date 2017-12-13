package mx.com.solutions.devare.coppelstore.di.modules;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mx.com.solutions.devare.coppelstore.custom_views.CustomToast;
import mx.com.solutions.devare.coppelstore.data.network.ApiService;
import mx.com.solutions.devare.coppelstore.di.scopes.ActivityContext;
import mx.com.solutions.devare.coppelstore.di.scopes.PerActivity;
import mx.com.solutions.devare.coppelstore.interactores.LoginInteractor_Impl;
import mx.com.solutions.devare.coppelstore.interactores.MainInteractor_Impl;
import mx.com.solutions.devare.coppelstore.interactores.RegistroUsuarioInteractor_Impl;
import mx.com.solutions.devare.coppelstore.interactores.SplashScreenInteractor_Impl;
import mx.com.solutions.devare.coppelstore.interactores.TYCInteractor_Impl;
import mx.com.solutions.devare.coppelstore.presentadores.LoginPresenter_Impl;
import mx.com.solutions.devare.coppelstore.presentadores.MainPresenter_Impl;
import mx.com.solutions.devare.coppelstore.presentadores.RegistroUsuarioPresenter_Impl;
import mx.com.solutions.devare.coppelstore.presentadores.SplashScreenPresenter_Impl;
import mx.com.solutions.devare.coppelstore.presentadores.TYCPresenter_Impl;
import mx.com.solutions.devare.coppelstore.views.login.ILoginInteractor;
import mx.com.solutions.devare.coppelstore.views.login.ILoginPresenter;
import mx.com.solutions.devare.coppelstore.views.login.ILoginView;
import mx.com.solutions.devare.coppelstore.views.main.IMainInteractor;
import mx.com.solutions.devare.coppelstore.views.main.IMainPresenter;
import mx.com.solutions.devare.coppelstore.views.main.IMainView;
import mx.com.solutions.devare.coppelstore.views.registro_usuario.IRegistroUsuarioInteractor;
import mx.com.solutions.devare.coppelstore.views.registro_usuario.IRegistroUsuarioPresenter;
import mx.com.solutions.devare.coppelstore.views.registro_usuario.IRegistroUsuarioView;
import mx.com.solutions.devare.coppelstore.views.splash.ISplashScreenInteractor;
import mx.com.solutions.devare.coppelstore.views.splash.ISplashScreenPresenter;
import mx.com.solutions.devare.coppelstore.views.splash.ISplashScreenView;
import mx.com.solutions.devare.coppelstore.views.terminos_condiciones.ITYCInteractor;
import mx.com.solutions.devare.coppelstore.views.terminos_condiciones.ITYCPresenter;
import mx.com.solutions.devare.coppelstore.views.terminos_condiciones.ITYCView;
import retrofit2.Retrofit;

@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }

    @PerActivity
    @Provides
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }


    @Provides
    @PerActivity
    ISplashScreenPresenter<ISplashScreenView, ISplashScreenInteractor> provideSplashPresenter(SplashScreenPresenter_Impl<ISplashScreenView, ISplashScreenInteractor> mPresenter) {
        return mPresenter;
    }

    @Provides
    @PerActivity
    ISplashScreenInteractor provideSplashInteractor(SplashScreenInteractor_Impl mInteractor) {
        return mInteractor;
    }

    @Provides
    @PerActivity
    ITYCPresenter<ITYCView, ITYCInteractor> providesTYCPresenter(TYCPresenter_Impl<ITYCView, ITYCInteractor> mPresenter) {
        return mPresenter;
    }

    @Provides
    @PerActivity
    ITYCInteractor providesTYCInteractor(TYCInteractor_Impl mInteractor) {
        return mInteractor;
    }

    @Provides
    @PerActivity
    ILoginPresenter<ILoginView, ILoginInteractor> providesLoginPresenter(LoginPresenter_Impl<ILoginView, ILoginInteractor> mPresenter) {
        return mPresenter;
    }

    @Provides
    @PerActivity
    ILoginInteractor providesLoginInteractor(LoginInteractor_Impl mInteractor) {
        return mInteractor;
    }

    @Provides
    @PerActivity
    IRegistroUsuarioPresenter<IRegistroUsuarioView, IRegistroUsuarioInteractor> provideRegistroUsuarioPresenter(RegistroUsuarioPresenter_Impl<IRegistroUsuarioView, IRegistroUsuarioInteractor> mPresenter) {
        return mPresenter;
    }

    @Provides
    @PerActivity
    IRegistroUsuarioInteractor providesRegistroUsuarioInteractor(RegistroUsuarioInteractor_Impl mInteractor) {
        return mInteractor;
    }

    @Provides
    @PerActivity
    IMainPresenter<IMainView, IMainInteractor> provideMainPresenter(MainPresenter_Impl<IMainView, IMainInteractor> mPresenter) {
        return mPresenter;
    }

    @Provides
    @PerActivity
    IMainInteractor provideMainInteractor(MainInteractor_Impl mInteractor) {
        return mInteractor;
    }

    @Provides
    @PerActivity
    CustomToast provideCustomToast(@ActivityContext Context context){
        return new CustomToast(context);
    }
}
