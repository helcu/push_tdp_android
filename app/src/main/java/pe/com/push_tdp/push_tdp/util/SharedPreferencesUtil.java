package pe.com.push_tdp.push_tdp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by kenkina on 29/10/2017.
 */

public class SharedPreferencesUtil {

    public static void saveTokenToPrefs(Context context, String token) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.SP_FIREBASE_CLIENT_TOKEN, token);
        editor.apply();
    }

    public static String getTokenFromPrefs(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(Constants.SP_FIREBASE_CLIENT_TOKEN, null);
    }

    public static void saveUserIdToPrefs(Context context, String token) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.SP_USER_ID, token);
        editor.apply();
    }

    public static String getUserIdFromPrefs(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(Constants.SP_USER_ID, null);
    }

    public static void setKeepLogged(Context context, boolean keepLogged) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Constants.SP_KEEP_LOGGED, keepLogged);
        editor.apply();
    }

    public static boolean getKeepLogged(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(Constants.SP_KEEP_LOGGED, false);
    }
}
