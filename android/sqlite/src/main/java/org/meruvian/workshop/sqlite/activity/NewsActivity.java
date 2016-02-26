package org.meruvian.workshop.sqlite.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.meruvian.workshop.sqlite.R;
import org.meruvian.workshop.sqlite.adapter.NewsAdapter;
import org.meruvian.workshop.sqlite.content.database.adapter.NewsDatabaseAdapter;
import org.meruvian.workshop.sqlite.entity.News;

import java.util.Date;

/**
 * Created by meruvian on 04/02/16.
 */
public class NewsActivity extends AppCompatActivity {

    private ListView listNews;
    private EditText title, content;
    private NewsAdapter newsAdapter;
    private NewsDatabaseAdapter newsDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        listNews = (ListView) findViewById(R.id.list_news);
        title = (EditText) findViewById(R.id.edit_title);
        content = (EditText) findViewById(R.id.edit_content);

        newsDatabaseAdapter = new NewsDatabaseAdapter(this);

        newsAdapter = new NewsAdapter(this, newsDatabaseAdapter.findNewsAll());
        listNews.setAdapter(newsAdapter);

        listNews.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialogAction(position);
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));

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

    public void dialogAction(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.action));
        builder.setItems(new String[] {getString(R.string.edit), getString(R.string.delete)}, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int location){
                News news = (News) newsAdapter.getItem(position);
                if(location == 0){
                    Toast.makeText(NewsActivity.this, "Edit News : " +news.getTitle(), Toast.LENGTH_LONG).show();
                }else if(location == 1){
                    confirmDelete(position);
                }
            }
        });
        builder.create().show();
    }


    private void confirmDelete(int position){
        final News news = (News)newsAdapter.getItem(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.delete));
        builder.setMessage(getString(R.string.confirm_delete));
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(NewsActivity.this, "Delete News : " + news.getTitle(), Toast.LENGTH_LONG).show();
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
