package mx.com.solutions.devare.coppelstore.di.components;

import dagger.Component;
import mx.com.solutions.devare.coppelstore.di.modules.ActivityModule;
import mx.com.solutions.devare.coppelstore.di.scopes.ApplicationContext;
import mx.com.solutions.devare.coppelstore.di.scopes.PerActivity;
import mx.com.solutions.devare.coppelstore.views.login.LoginActivity;
import mx.com.solutions.devare.coppelstore.views.main.MainActivity;
import mx.com.solutions.devare.coppelstore.views.registro_usuario.RegistroUsuarioActivity;
import mx.com.solutions.devare.coppelstore.views.splash.SplashScreen;
import mx.com.solutions.devare.coppelstore.views.terminos_condiciones.TerminosCondicionesActivity;

@PerActivity
@Component(modules = ActivityModule.class,dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(SplashScreen splashScreen);
    void inject(TerminosCondicionesActivity tyCondiciones);
    void inject(LoginActivity loginActivity);
    void inject(RegistroUsuarioActivity registroUsuario);
    void inject(MainActivity mainActivity);
}
