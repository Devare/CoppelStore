package mx.com.solutions.devare.coppelstore.di.modules;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import mx.com.solutions.devare.coppelstore.custom_views.CustomToast;
import mx.com.solutions.devare.coppelstore.data.db.servicios.ServicioProductos;
import mx.com.solutions.devare.coppelstore.data.network.RetrofitClient;
import mx.com.solutions.devare.coppelstore.data.prefs.AppPrefs;
import mx.com.solutions.devare.coppelstore.data.prefs.IAppPrefs;
import mx.com.solutions.devare.coppelstore.data.prefs.ISessionPrefs;
import mx.com.solutions.devare.coppelstore.data.prefs.SessionPrefs;
import mx.com.solutions.devare.coppelstore.di.scopes.ApplicationContext;
import mx.com.solutions.devare.coppelstore.di.scopes.PreferenceInfo;
import mx.com.solutions.devare.coppelstore.di.scopes.PreferenceTypeEnum;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private final Application application;
    private String baseUrl;

    public ApplicationModule(Application application, String baseUrl) {
        this.application = application;
        this.baseUrl = baseUrl;
    }

    @Singleton
    @Provides
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    Retrofit provideClienteRetrofit(RxJava2CallAdapterFactory adapterFactory, GsonConverterFactory converterFactory, OkHttpClient client) {
        return RetrofitClient.getClient(baseUrl, adapterFactory, converterFactory, client);
    }

    @Singleton
    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @PreferenceInfo(PreferenceTypeEnum.PREF_APP)
    String providePreferenceName() {
        return AppPrefs.PREFS_NAME_APP;
    }

    @Provides
    @PreferenceInfo(PreferenceTypeEnum.PREF_SESSION)
    String providePreferenceNameSession(){
        return SessionPrefs.PREFS_NAME;
    }

    @Singleton
    @Provides
    IAppPrefs provideAppPreference(AppPrefs prefences){
        return prefences;
    }

    @Singleton
    @Provides
    ISessionPrefs providesSessionPreference(SessionPrefs preference){
        return preference;
    }

    @Provides
    Realm provideRealm(){
        return Realm.getDefaultInstance();
    }

    @Singleton
    @Provides
    ServicioProductos provideServicioProductos(Realm realm){
        return new ServicioProductos(realm);
    }

}
