package org.meruvian.workshop.sample.crud.activity;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import org.meruvian.workshop.sample.crud.R;
import org.meruvian.workshop.sample.crud.adapter.NewsAdapter;
import org.meruvian.workshop.sample.crud.content.database.adapter.NewsDatabaseAdapter;
import org.meruvian.workshop.sample.crud.entity.News;

import java.util.Date;

/**
 * Created by ludviantoovandi on 25/02/15.
 */
public class NewsActivity extends ActionBarActivity {
    private ListView listCategory;
    private EditText name, description;

    private NewsDatabaseAdapter newsDatabaseAdapter;
    private NewsAdapter newsAdapter;

    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listCategory = (ListView) findViewById(R.id.list_category);
        name = (EditText) findViewById(R.id.edit_name);
        description = (EditText) findViewById(R.id.edit_description);

        newsDatabaseAdapter = new NewsDatabaseAdapter(this);
        newsAdapter = new NewsAdapter(this, newsDatabaseAdapter.findCategoryAll());
        listCategory.setAdapter(newsAdapter);

        listCategory.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialogList(i);

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                newsAdapter.clear();
                newsAdapter.addNews(newsDatabaseAdapter.findCategoryByName(s));

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
            if (news == null) {
                news = new News();
            }

            news.setStatus(1);
            news.setDescription(description.getText().toString());
            news.setName(name.getText().toString());
            news.setCreateDate(new Date().getTime());

            newsDatabaseAdapter.save(news);
            newsAdapter.clear();
            newsAdapter.addNews(newsDatabaseAdapter.findCategoryAll());

            name.setText("");
            description.setText("");
            news = new News();
        } else if (item.getItemId() == R.id.action_refresh) {
            newsAdapter.clear();
            newsAdapter.addNews(newsDatabaseAdapter.findCategoryAll());
        }

        return true;
    }

    private void dialogList(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.action));
        builder.setItems(new String[] {getString(R.string.edit), getString(R.string.delete)}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int location) {
                news = newsDatabaseAdapter.findCategoryById(((News) newsAdapter.getItem(position)).getId());

                if (location == 0) {
                    if (news != null) {
                        name.setText(news.getName());
                        description.setText(news.getDescription());

                        name.requestFocus();
                    }
                } else if (location == 1) {
                    if (news != null) {
                        confirmDelete(news);
                    }
                }
            }
        });

        builder.create().show();
    }

    private void confirmDelete(final News news) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.delete));
        builder.setMessage(getString(R.string.confirm_delete) + news.getName() + "?");
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                newsDatabaseAdapter.delete(news);
                newsAdapter.clear();
                newsAdapter.addNews(newsDatabaseAdapter.findCategoryAll());
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
