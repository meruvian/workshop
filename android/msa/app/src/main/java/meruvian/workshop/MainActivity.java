package meruvian.workshop;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


import meruvian.workshop.adapter.NewsAdapter;
import meruvian.workshop.entity.News;
import meruvian.workshop.UpdateActivity;
import meruvian.workshop.service.NewsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.jackson.JacksonConverterFactory;

import static meruvian.workshop.R.id.edtContent;
import static meruvian.workshop.R.id.edtTitle;

public class MainActivity extends AppCompatActivity  {

    private Retrofit retrofit;
    private EditText title, content;
    private List<News> news;
    public static ListView listView;
    private NewsAdapter newsAdapter;
    private FloatingActionButton btnAdd;
    private News newss;
    private NewsService service;
    private String TAG = getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.list_news);
        title = (EditText) findViewById(R.id.edtTitle);
        content = (EditText) findViewById(R.id.edtContent);
        btnAdd =(FloatingActionButton) findViewById(R.id.fab_add);
        newsAdapter = new NewsAdapter(this);
        listView.setAdapter(newsAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialogAction(i);




                return true;
            }
        });

        getData();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(intent);
            }
        });

    }


    public void getData() {
        //ketika aplikasi sedang mengambil data kita akan melihat progress dialog muncul
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait..", false, false);


        Log.d(getClass().getSimpleName(), "cal service");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.106:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        NewsService service = retrofit.create(NewsService.class);
        Call<List<News>> call = service.getAllNews();
        call.enqueue(new Callback<List<News>>() {
                         @Override
                         public void onResponse(Call<List<News>> call, Response<List<News>> response) {

                             Log.d(getClass().getSimpleName(), "success");
                             newsAdapter.addNewses(response.body());
                             loading.dismiss();

                         }

                         @Override
                         public void onFailure(Call<List<News>> call, Throwable t) {
                             Log.d("onFailure", t.toString());
                         }
                     }
        );

    }

    private void   dialogAction(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.action));
        builder.setItems(new String[]{getString(R.string.edit), getString(R.string.delete)}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int location) {
                newss = (News) newsAdapter.getItem(position);

                if (newss != null) {
                    if (location == 0) {

                        Log.d(TAG, "NEWS ID : " + newss.getTitle() + "");
                        title.setText(newss.getTitle());
                        content.setText(newss.getContent());

                        title.requestFocus();
                    } else if (location == 1) {
                        confirmDelete();
                    }
                }
            }
        });

        builder.create().show();
    }

    private void confirmDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.delete));
        builder.setMessage(getString(R.string.confirm_delete) + " '" + newss.getId() + "' ?");
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "NEWS ID : " + newss.getId() + "");

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.2.106:8080/")
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();
                NewsService service = retrofit.create(NewsService.class);
                Call<List<News>> call = service.deleteNews(newss.getId());

                call.enqueue(new Callback<List<News>>() {
                    @Override
                    public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                        newsAdapter.clear();

                       getData();

                    }

                    @Override
                    public void onFailure(Call<List<News>> call, Throwable t) {
                        Log.d("onFailure", t.toString());
                    }
                });


            }
        });
        builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.create().show();
    }

}
