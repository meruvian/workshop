package org.meruvian.workshop.form.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.meruvian.workshop.form.R;
import org.meruvian.workshop.form.adapter.NewsAdapter;
import org.meruvian.workshop.form.entity.News;

/**
 * Created by meruvian on 04/02/16.
 */
public class NewsActivity extends AppCompatActivity {

    private ListView listNews;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listNews = (ListView)findViewById(R.id.list_news);
        newsAdapter = new NewsAdapter(this, News.data());
        listNews.setAdapter(newsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(NewsActivity.this, "Searching : " + s, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_save){
            Toast.makeText(this, getString(R.string.save), Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.action_refresh){
            Toast.makeText(this, getString(R.string.refresh), Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.action_search){
            Toast.makeText(this, getString(R.string.search), Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
