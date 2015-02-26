package org.meruvian.workshop.android.form.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import org.meruvian.workshop.android.form.R;
import org.meruvian.workshop.android.form.adapter.NewsAdapter;
import org.meruvian.workshop.android.form.entity.News;

import java.util.ArrayList;

/**
 * Created by ludviantoovandi on 25/02/15.
 */
public class NewsActivity extends ActionBarActivity {
    private ListView listNews;

    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listNews = (ListView) findViewById(R.id.list_news);

        newsAdapter = new NewsAdapter(this, News.data());
        listNews.setAdapter(newsAdapter);
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
        if (item.getItemId() == R.id.action_save) {
            Toast.makeText(this, getString(R.string.save), Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.action_refresh) {
            Toast.makeText(this, getString(R.string.refresh), Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.action_search) {
            Toast.makeText(this, getString(R.string.search), Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}
