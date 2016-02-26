package org.meruvian.workshop.fragment.fragment;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.meruvian.workshop.fragment.R;
import org.meruvian.workshop.fragment.adapter.NewsAdapter;
import org.meruvian.workshop.fragment.entity.News;

/**
 * Created by meruvian on 04/02/16.
 */
public class NewsFragment extends Fragment {

    private ListView listNews;
    private NewsAdapter newsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listNews = (ListView) view.findViewById(R.id.list_news);

        newsAdapter = new NewsAdapter(getActivity(), News.data());
        listNews.setAdapter(newsAdapter);

        listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                News news = (News) adapterView.getAdapter().getItem(position);

                Bundle bundle = new Bundle();
                bundle.putString("title", news.getTitle());
                bundle.putString("content", news.getContent());
                bundle.putLong("date", news.getCreateDate());

                DetailNewsFragment detailNewsFragment = new DetailNewsFragment();
                detailNewsFragment.setArguments(bundle);

                if (getArguments() != null && getArguments().getString("screen").equals("large")) {
                    getFragmentManager().beginTransaction().replace(R.id.container_inner, detailNewsFragment).commit();
                } else {
                    getFragmentManager().beginTransaction().replace(R.id.container, detailNewsFragment).addToBackStack(null).commit();
                }
            }

        });

        listNews.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialogActions(position);
                return true;
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.actions, menu);

        SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getActivity(), "Searching : " + query, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_save){
            Toast.makeText(getActivity(), getString(R.string.save), Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.action_refresh){
            Toast.makeText(getActivity(), getString(R.string.refresh), Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.action_search){
            Toast.makeText(getActivity(), getString(R.string.search), Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void dialogActions(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.action));
        builder.setItems(new String[]{getString(R.string.edit), getString(R.string.delete)}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int location) {
                News news = (News) newsAdapter.getItem(position);
                if (location == 0) {
                    Toast.makeText(getActivity(), "Edit :" + news.getTitle(), Toast.LENGTH_LONG).show();
                } else if (location == 1) {
                    confirmDelete(position);
                }
            }
        });
        builder.create().show();
    }

    private void confirmDelete(int position) {
        final News news = (News) newsAdapter.getItem(position);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.delete));
        builder.setMessage(getString(R.string.confirm_delete) + " " + news.getTitle() + " ?");
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Delete News : " + news.getTitle(), Toast.LENGTH_LONG).show();
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
