package es.kingcreek.swiftycompanion.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

    public static final String PREF_NAME = "MyAppPreferences";
    public static final String ACCESS_TOKEN_KEY = "access_token";
    public static final String AUTORIZATION_CODE_KEY = "autorization_code";

    private static PreferencesManager instance;
    private final SharedPreferences preferences;

    private PreferencesManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized PreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new PreferencesManager(context.getApplicationContext());
        }
        return instance;
    }

    /*
    public void setAutorizationCode(String accessToken) {
        preferences.edit().putString(AUTORIZATION_CODE_KEY, accessToken).apply();
    }

    public String getAutorizationCode() {
        return preferences.getString(AUTORIZATION_CODE_KEY, null);
    }*/

    public void setAccessToken(String accessToken) {
        preferences.edit().putString(ACCESS_TOKEN_KEY, accessToken).apply();
    }

    public String getAccessToken() {
        return preferences.getString(ACCESS_TOKEN_KEY, null);
    }

}