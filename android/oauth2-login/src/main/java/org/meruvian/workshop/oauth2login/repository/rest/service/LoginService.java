package org.meruvian.workshop.oauth2login.repository.rest.service;

import org.meruvian.workshop.oauth2login.core.commons.User;
import org.meruvian.workshop.oauth2login.repository.rest.model.Authentication;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by meruvian on 23/02/16.
 */
public interface LoginService {

    @POST("/oauth/token")
    Call<Authentication> login(@Header("Authorization") String basicAuth,
                               @QueryMap Map<String, String> queryParam);

    @GET("/api/users/me")
    Call<User> getUser(@Header("Authorization") String authorization);

}
