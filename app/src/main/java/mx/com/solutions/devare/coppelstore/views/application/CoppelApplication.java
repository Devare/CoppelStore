package mx.com.solutions.devare.coppelstore.views.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import mx.com.solutions.devare.coppelstore.di.components.ApplicationComponent;
import mx.com.solutions.devare.coppelstore.di.components.DaggerApplicationComponent;
import mx.com.solutions.devare.coppelstore.di.modules.ApplicationModule;
import mx.com.solutions.devare.coppelstore.utils.Constantes;

public class CoppelApplication extends Application {
    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealmConfiguration();
        initApplicationComponet();
    }

    private void initApplicationComponet() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, Constantes.ROOT_URL))
                .build();
        applicationComponent.inyect(this);
    }

    private void initRealmConfiguration() {
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("CoppelStore")
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public ApplicationComponent getComponente(){
        return applicationComponent;
    }


}
