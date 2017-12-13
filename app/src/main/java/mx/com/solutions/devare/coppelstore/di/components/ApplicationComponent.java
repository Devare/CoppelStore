package mx.com.solutions.devare.coppelstore.di.components;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import mx.com.solutions.devare.coppelstore.custom_views.CustomToast;
import mx.com.solutions.devare.coppelstore.data.db.servicios.ServicioProductos;
import mx.com.solutions.devare.coppelstore.data.prefs.IAppPrefs;
import mx.com.solutions.devare.coppelstore.data.prefs.ISessionPrefs;
import mx.com.solutions.devare.coppelstore.di.modules.ApplicationModule;
import mx.com.solutions.devare.coppelstore.di.scopes.ApplicationContext;
import retrofit2.Retrofit;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inyect(Application application);

    Retrofit exposeClienteRetrofit();

    @ApplicationContext
    Context exposeContext();

    IAppPrefs exposeAppPreference();
    ISessionPrefs exposeSessionPreference();

    ServicioProductos exposeServicioProductos();
}
