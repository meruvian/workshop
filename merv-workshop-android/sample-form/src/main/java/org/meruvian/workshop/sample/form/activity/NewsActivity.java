package org.meruvian.workshop.sample.form.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.meruvian.workshop.sample.form.adapter.NewsAdapter;
import org.meruvian.workshop.sample.form.entity.News;
import org.meruvian.workshop.sample.form.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by ludviantoovandi on 25/02/15.
 */
public class NewsActivity extends ActionBarActivity {
    private ListView listCategory;
    private EditText title, content;
    private ProgressDialog progressDialog;

    private News news = new News();
    private int editPosition = -1;

    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(org.meruvian.workshop.sample.form.R.layout.activity_news);

        listCategory = (ListView) findViewById(R.id.list_category);
        title = (EditText) findViewById(R.id.edit_name);
        content = (EditText) findViewById(R.id.edit_description);

        newsAdapter = new NewsAdapter(this, News.data());
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
            news.setStatus(newsAdapter.getCount() + 1);
            news.setContent(content.getText().toString());
            news.setTitle(title.getText().toString());
            news.setCreateDate(new Date().getTime());

            if (news.getId() == -1) {
                newsAdapter.addNews(news);

                title.setText("");
                content.setText("");

                news = new News();
            } else {
                ((News) newsAdapter.getItem(editPosition)).setCreateDate(new Date().getTime());
                ((News) newsAdapter.getItem(editPosition)).setContent(content.getText().toString());
                ((News) newsAdapter.getItem(editPosition)).setTitle(title.getText().toString());

                newsAdapter.notifyDataSetChanged();

                title.setText("");
                content.setText("");
                editPosition = -1;
                news = new News();
            }
        } else if (item.getItemId() == R.id.action_refresh) {
            newsAdapter.clear();
            newsAdapter.addNews(News.data());
        }

        return true;
    }

    private void dialogList(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(org.meruvian.workshop.sample.form.R.string.action));
        builder.setItems(new String[] {getString(org.meruvian.workshop.sample.form.R.string.edit), getString(org.meruvian.workshop.sample.form.R.string.delete)}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int location) {
                news = (News) newsAdapter.getItem(position);

                if (location == 0) {
                    if (news != null) {
                        title.setText(news.getTitle());
                        content.setText(news.getContent());

                        title.requestFocus();
                        editPosition = position;
                    }
                } else if (location == 1) {
                    if (news != null) {
                        confirmDelete(position);
                    }
                }
            }
        });

        builder.create().show();
    }
    //
    private void confirmDelete(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.delete));
        builder.setMessage(getString(R.string.confirm_delete) + news.getTitle() + " ?");
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                newsAdapter.remove(position);
            }
        });
        builder.setNegativeButton(getString(org.meruvian.workshop.sample.form.R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.create().show();
    }

}
