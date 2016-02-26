package org.meruvian.workshop.rest.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.meruvian.workshop.rest.R;
import org.meruvian.workshop.rest.adapter.NewsAdapter;
import org.meruvian.workshop.rest.entity.News;
import org.meruvian.workshop.rest.service.NewsClient;
import org.meruvian.workshop.rest.service.ServiceGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by meruvian on 04/02/16.
 */
public class NewsActivity extends AppCompatActivity {

    private ListView listNews;
    private NewsAdapter newsAdapter;
    private NewsClient client;
    private String TAG = getClass().getSimpleName();
    private News news;
    private EditText title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listNews = (ListView) findViewById(R.id.list_news);
        title = (EditText) findViewById(R.id.edit_title);
        content = (EditText) findViewById(R.id.edit_content);

        newsAdapter = new NewsAdapter(this, new ArrayList<News>());
        client = ServiceGenerator.createService(NewsClient.class);

        listNews.setAdapter(newsAdapter);

        listNews.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialogAction(position);
                return true;
            }
        });

        newsGetTask("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                newsGetTask(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    private void newsGetTask(String title) {
        Call<List<News>> call = client.getNews(title);
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Response<List<News>> response) {
                newsAdapter.clear();
                newsAdapter.addNews(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(NewsActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getLocalizedMessage() + " |\n| " + t.getMessage());
            }
        });
    }

    private void newsPostTask(News body) {
        Call<News> call = client.postNews(news);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Response<News> response) {
                title.setText("");
                content.setText("");

                news = new News();
                newsGetTask("");
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(NewsActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getLocalizedMessage() + " |\n| " + t.getMessage());
            }
        });
    }

    private void newsPutTask(News body) {
        Call<News> call = client.putNews(body.getId(), body);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Response<News> response) {
                title.setText("");
                content.setText("");

                news = new News();
                newsGetTask("");
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(NewsActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getLocalizedMessage() + " |\n| " + t.getMessage());
            }
        });
    }

    private void newsDeleteTask(int id) {
        Call<Void> call = client.deleteNews(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response) {
                newsGetTask("");
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(NewsActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getLocalizedMessage() + " |\n| " + t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            if (news == null) {
                news = new News();
            }

            news.setStatus(1);
            news.setContent(content.getText().toString());
            news.setTitle(title.getText().toString());
            news.setCreateDate(new Date().getTime());

            if (news.getId() == -1) {
                newsPostTask(news);
            } else {
                newsPutTask(news);
            }

            return true;
        } else if (id == R.id.action_refresh) {
            newsGetTask("");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void dialogAction(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.action));
        builder.setItems(new String[]{getString(R.string.edit), getString(R.string.delete)}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int location) {
                news = (News) newsAdapter.getItem(position);
                if (location == 0) {
                    if (news != null) {
                        title.setText(news.getTitle());
                        content.setText(news.getContent());

                        title.requestFocus();
                    }
                } else if (location == 1) {
                    confirmDelete(news.getId());
                }
            }
        });
        builder.create().show();
    }

    private void confirmDelete(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.delete));
        builder.setMessage(getString(R.string.confirm_delete));
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                newsDeleteTask(id);
                news = new News();
            }
        });
        builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    
}
