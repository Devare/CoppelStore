package mx.com.solutions.devare.coppelstore.data.prefs;

import javax.inject.Singleton;

import mx.com.solutions.devare.coppelstore.data.db.modelo.ResponseLogin;

@Singleton
public interface ISessionPrefs {

    boolean isLoggedIn();
    String getPreferenceString(String key);
    void saveUsuarioCoppelStore(ResponseLogin mLogin);
    void logOutCoppelStore();
    int getCurrentUserLoggedInMode();
    void setCurrentUserLoggedInMode(SessionPrefs.LoggedInMode mode);
}
