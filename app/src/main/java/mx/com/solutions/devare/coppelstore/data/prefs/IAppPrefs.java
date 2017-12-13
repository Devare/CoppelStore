package mx.com.solutions.devare.coppelstore.data.prefs;

import javax.inject.Singleton;

@Singleton
public interface IAppPrefs {

    void savePreferenceBoolean(boolean b, String key);
    void savePreferenceString(String b, String key);
    boolean getPreferenceBoolean(String key);
    String getPreferenceString(String key);
}
