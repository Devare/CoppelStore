package mx.com.solutions.devare.coppelstore.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import javax.inject.Inject;
import javax.inject.Named;

import mx.com.solutions.devare.coppelstore.data.db.modelo.ResponseLogin;
import mx.com.solutions.devare.coppelstore.di.scopes.ApplicationContext;
import mx.com.solutions.devare.coppelstore.di.scopes.PreferenceInfo;
import mx.com.solutions.devare.coppelstore.di.scopes.PreferenceTypeEnum;

/**
 * Manejador de preferencias de la sesión del usuario
 */
public class SessionPrefs implements ISessionPrefs {

    public static final String PREFS_NAME = "COPPEL_STORE_SESSION_PREFS";
    public static final String PREF_USUARIO_EMAIL = "PREF_USER_EMAIL";//Identificador Unico de usuario
    public static final String PREF_USUARIO_NAME = "PREF_USUARIO_NAME";
    public static final String PREF_USUARIO_GENERO = "PREF_USUARIO_GENERO";
    public static final String PREF_USUARIO_TOKEN = "PREF_USUARIO_TOKEN";
    public static final String PREF_MODE_LOGIN = "PREF_MODE_LOGIN";

    private final SharedPreferences mPrefs;

    private boolean mIsLoggedIn = false;

    @Inject
    public SessionPrefs(@ApplicationContext Context context, @PreferenceInfo(PreferenceTypeEnum.PREF_SESSION) String prefSession) {
        mPrefs = context.getSharedPreferences(prefSession, Context.MODE_PRIVATE);
        mIsLoggedIn = !TextUtils.isEmpty(mPrefs.getString(PREF_USUARIO_TOKEN, null));
    }

    @Override
    public boolean isLoggedIn() {
        return mIsLoggedIn;
    }

    @Override
    public String getPreferenceString(String key) {
        return mPrefs.getString(key, "");// si es que nunca se ha guardado nada en esta key pues retornara una cadena vacia
    }

    @Override
    public void saveUsuarioCoppelStore(ResponseLogin mLogin) {
        if (mLogin != null) {
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString(PREF_USUARIO_EMAIL, mLogin.getUsuario());
            editor.putString(PREF_USUARIO_NAME, String.format("%s %s %s", mLogin.getNombre(), mLogin.getAPaterno(), mLogin.getAMaterno()));
            editor.putString(PREF_USUARIO_GENERO, mLogin.getGenero());
            editor.putString(PREF_USUARIO_TOKEN, mLogin.getToken());
            editor.apply();
            mIsLoggedIn = true;
        }
    }


    @Override
    public void logOutCoppelStore() {
        mIsLoggedIn = false;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(PREF_USUARIO_EMAIL, null);
        editor.putString(PREF_USUARIO_NAME, null);
        editor.putString(PREF_USUARIO_GENERO, null);
        editor.putString(PREF_USUARIO_TOKEN, null);
        editor.apply();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPrefs.getInt(PREF_MODE_LOGIN,LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType());
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPrefs.edit().putInt(PREF_MODE_LOGIN,mode.getType()).apply();
    }

    public enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_COPPEL(1),
        LOGGED_IN_MODE_GOOGLE(2),
        LOGGED_IN_MODE_FB(3),
        LOGGED_IN_MODE_TWITTER(4),
        LOGGED_IN_MODE_LINKENIN(5);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }

}
