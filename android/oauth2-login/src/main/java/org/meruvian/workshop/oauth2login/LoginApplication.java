package org.meruvian.workshop.oauth2login;

import android.app.Application;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Yusak DK on 15/03/17.
 */

public class LoginApplication extends Application {
    private static LoginApplication instance;
    private static ObjectMapper objectMapper;
    private ObjectMapper jsonMapper;

    public LoginApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        jsonMapper = objectMapper;
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static LoginApplication getInstance() {
        return instance;
    }

    public ObjectMapper getJsonMapper() {
        return jsonMapper;
    }
}
