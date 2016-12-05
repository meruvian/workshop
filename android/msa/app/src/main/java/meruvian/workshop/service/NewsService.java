package meruvian.workshop.service;

import java.util.List;


import meruvian.workshop.entity.News;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by AKM on 11/21/2016.
 */

public interface NewsService {
    @GET("news")
    Call<List<News>> getAllNews();

    @POST("news")
    Call<List<News>> postNews(@Body News news);

    @PUT("news/{id}")
    Call<List<News>> putNews(@Path("id") long id);

    @GET("news/{id}")
    Call<List<News>> getNews(@Path("id") long id, @Body News news);

    @DELETE("news/{id}")
    Call<List<News>> deleteNews(@Path("id") long id);


}
