package org.meruvian.workshop.oauth2login;

import android.util.Base64;

/**
 * Created by Yusak DK on 15/03/17.
 */

public class GlobalVariables {
    public static final String APP_ID = "419c6697-14b7-4853-880e-b68e3731e316";
    public static final String APP_SECRET = "s3cr3t";
    public static final String APP_AUTHORIZATION = new String(Base64.encode((APP_ID + ":" + APP_SECRET).getBytes(), Base64.DEFAULT));
    public static final String API_BASE_URL = "http://yama-showcase.yama2.meruvian.org/";
    public static final String PREFS_SERVER = "APP_SERVER";
    public static final String KEY_URL = "server_url";
    public static final String HAS_URL = "has_url";
    public static final String URL_CHANGE = "url_change";
    public static final String GRANT_TYPE_PASSWORD = "password";
    public static final String BASIC = "Basic " ;
    public static final String BEARER = "Bearer " ;

}
