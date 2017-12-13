package mx.com.solutions.devare.coppelstore.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Named;

import mx.com.solutions.devare.coppelstore.di.scopes.ApplicationContext;
import mx.com.solutions.devare.coppelstore.di.scopes.PreferenceInfo;
import mx.com.solutions.devare.coppelstore.di.scopes.PreferenceTypeEnum;

public class AppPrefs implements IAppPrefs{

    public static final String PREFS_NAME_APP = "coppelstore.preferencias";
    public static final String PREFERENCE_ESTADO_TYC = "estado.terminosycondiciones";
    public static final String PREFERENCE_ESTADO_NO_CERRAR_SESION = "estado.button.sesion";
    public static final String PREFERENCE_USUARIO_LOGIN = "usuario.login";
    public static final String PREFERENCE_BUTTON_LOGIN_FACEBOOK = "estado.login.facebook";
    public static final String PREFERENCE_BUTTON_LOGIN_COPPEL_STORE = "estado.login.coppelstore";
    public static final String PREFERENCE_MODO_LOGIN = "modo.tipologin";
    public static final String PREFERENCE_ESTADO_CARGA_DATOS = "estado.carga.datos";

    private final SharedPreferences mPrefs;

    @Inject
    public  AppPrefs(@ApplicationContext Context context, @PreferenceInfo(PreferenceTypeEnum.PREF_APP) String prefApp) {
        mPrefs = context.getSharedPreferences(prefApp, Context.MODE_PRIVATE);
    }

    @Override
    public void savePreferenceBoolean(boolean b, String key) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(key, b).apply();
    }

    @Override
    public void savePreferenceString(String b, String key) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(key, b).apply();
    }

    @Override
    public boolean getPreferenceBoolean(String key) {
        return mPrefs.getBoolean(key, false);// si es que nunca se ha guardado nada en esta key pues retornara false
    }

    @Override
    public String getPreferenceString(String key) {
        return mPrefs.getString(key, "");// si es que nunca se ha guardado nada en esta key pues retornara una cadena vacia
    }


}
