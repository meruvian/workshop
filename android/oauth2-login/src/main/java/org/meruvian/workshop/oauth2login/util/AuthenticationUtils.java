package org.meruvian.workshop.oauth2login.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.meruvian.workshop.oauth2login.LoginApplication;
import org.meruvian.workshop.oauth2login.core.commons.User;
import org.meruvian.workshop.oauth2login.repository.rest.model.Authentication;

import java.io.IOException;

/**
 * Created by meruvian on 29/07/15.
 */
public class AuthenticationUtils {
    private static final String AUTHENTICATION = "AUTHENTICATION";

    public static void registerAuthentication(Authentication authentication) {
        ObjectMapper mapper = LoginApplication.getInstance().getJsonMapper();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginApplication.getInstance());
        SharedPreferences.Editor editor = preferences.edit();
        try {
            editor.putString(AUTHENTICATION, mapper.writeValueAsString(authentication));
        } catch (JsonProcessingException e) {
            Log.e(AuthenticationUtils.class.getSimpleName(), e.getMessage(), e);
        }
        editor.apply();
    }

    public static void registerUser(User user) {
        Authentication authentication = getCurrentAuthentication();
        authentication.setUser(user);
        registerAuthentication(authentication);
    }

    public static Authentication getCurrentAuthentication() {
        LoginApplication instance = LoginApplication.getInstance();
        ObjectMapper mapper = instance.getJsonMapper();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginApplication.getInstance());
        String jsonAuth = preferences.getString(AUTHENTICATION, "");
        Log.w("AUTHENTICATION-UTILS", "JSON AUTH:" + jsonAuth);

        if (!jsonAuth.equals("")) {
            try {
                return mapper.readValue(jsonAuth, Authentication.class);
            } catch (IOException e) {
                Log.e(AuthenticationUtils.class.getSimpleName(), e.getMessage(), e);
            }
        }

        return null;
    }

    public static boolean expiresToken(long date){
        long now = date - AuthenticationUtils.getCurrentAuthentication().getLoginDate();
        long nowSec = now / 1000;
        if (nowSec >= AuthenticationUtils.getCurrentAuthentication().getExpiresIn()) {
            return true;
        }
        return false;
    }

    public static void logout() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginApplication.getInstance());
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(AUTHENTICATION);
        editor.apply();
    }
}
