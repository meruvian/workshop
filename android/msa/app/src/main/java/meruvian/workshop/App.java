package meruvian.workshop;

import meruvian.workshop.service.NewsService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by AKM on 11/21/2016.
 */

public class App {

    public Retrofit rest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.3.108:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        NewsService service = retrofit.create(NewsService.class);
        return retrofit;
    }

    public void load(){


    }
}
