package meruvian.workshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import meruvian.workshop.adapter.NewsAdapter;
import meruvian.workshop.entity.News;
import meruvian.workshop.MainActivity;
import meruvian.workshop.service.NewsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by AKM on 11/21/2016.
 */

public class InsertActivity extends AppCompatActivity {
    private EditText edtTitle, edtContent;
    private Button btnSave;
    private News news;
    private  NewsAdapter newsAdapter;
    private Toolbar toolbar;

    private String url = null;
    public static String KEY_ITEM = "item";
    public static String KEY_POSITION = "position";

    private int position;
    private boolean isUpdateForm = false;

    private MainActivity application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_news);
      newsAdapter = new NewsAdapter(this);
        MainActivity.listView.setAdapter(newsAdapter);


        edtTitle = (EditText)findViewById(R.id.edtTitle);
        edtContent = (EditText)findViewById(R.id.edtContent);
        btnSave = (Button)findViewById(R.id.btnSubmit);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            postRequest();
            }
        }); }

    private void postRequest() {
        final ProgressDialog dialog = new ProgressDialog(InsertActivity.this);
        dialog.setMessage("Please wait...");
        dialog.show(); Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.106:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        NewsService service = retrofit.create(NewsService.class);
        Log.d(getClass().getSimpleName(), "call service");

        news = new News();
        news.setTitle(edtTitle.getText().toString());
        news.setContent(edtContent.getText().toString());

        Call<List<News>> call = service.postNews(news);
        call.enqueue(new Callback<List<News>>() {

            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                newsAdapter.clear();


                Log.d(getClass().getSimpleName(), "success");
                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);






            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(InsertActivity.this, t != null ? t.getMessage() : "Failed to post data", Toast.LENGTH_LONG).show();

            }
        })

    ;}



}
