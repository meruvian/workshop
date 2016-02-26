package org.meruvian.workshop.rest.service;

import org.meruvian.workshop.rest.entity.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by meruvian on 28/01/16.
 */
public interface NewsClient {
    @GET("api/news")
    Call<List<News>> getNews();

    @GET("api/news")
    Call<List<News>> getNews(@Query("title") String title);

    @POST("api/news")
    Call<News> postNews(@Body News news);

    @PUT("api/news/{id}")
    Call<News> putNews(@Path("id") int id, @Body News news);

    @DELETE("api/news/{id}")
    Call<Void> deleteNews(@Path("id") int id);

}