package org.meruvian.workshop.oauth2login.util;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static org.meruvian.workshop.oauth2login.GlobalVariables.API_BASE_URL;

/**
 * Created by meruvian on 23/02/16.
 */
public class ServiceGenerator {
    private static String apiBaseUrl = API_BASE_URL;

    public ServiceGenerator() {
    }

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .addConverterFactory(JacksonConverterFactory.create())
                    .baseUrl(apiBaseUrl);

    public static void changeApiBaseUrl(String newApiBaseUrl) {
        builder = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(newApiBaseUrl);
    }

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

}
