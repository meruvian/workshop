package org.meruvian.workshop.sqlite.content.database.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import org.meruvian.workshop.sqlite.content.NewsContentProvider;
import org.meruvian.workshop.sqlite.content.database.model.NewsDatabaseModel;
import org.meruvian.workshop.sqlite.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by merv on 6/5/15.
 */
public class NewsDatabaseAdapter {
    private Uri dbUriNews = Uri.parse(NewsContentProvider.CONTENT_PATH + NewsContentProvider.TABLES[0]);

    private Context context;

    public NewsDatabaseAdapter(Context context){
        this.context = context;
    }

    public void save(News news){
        ContentValues values = new ContentValues();
        if(news.getId() == -1){
            values.put(NewsDatabaseModel.TITLE, news.getTitle());
            values.put(NewsDatabaseModel.CONTENT, news.getContent());
            values.put(NewsDatabaseModel.STATUS, 1);
            values.put(NewsDatabaseModel.CREATE_DATE, news.getCreateDate());

            context.getContentResolver().insert(dbUriNews, values);
        }else{
            values.put(NewsDatabaseModel.TITLE, news.getTitle());
            values.put(NewsDatabaseModel.CONTENT, news.getContent());
            values.put(NewsDatabaseModel.STATUS, 1);
            values.put(NewsDatabaseModel.CREATE_DATE, news.getCreateDate());

            context.getContentResolver().update(dbUriNews, values, NewsDatabaseModel.ID + "=?",new String[]{news.getId()+""});
        }
    }

    public List<News> findNewsByTitle(String title){
        String query = NewsDatabaseModel.TITLE + " like ? AND "+NewsDatabaseModel.STATUS + " = ?";
        String[] parameter = {"%"+title+"%","1"};

        Cursor cursor = context.getContentResolver().query(dbUriNews, null, query, parameter, NewsDatabaseModel.CREATE_DATE);

        List<News> categories = new ArrayList<News>();

        if(cursor != null){
            while (cursor.moveToNext()){
                News news = new News();
                news.setId(cursor.getInt(cursor.getColumnIndex(NewsDatabaseModel.ID)));
                news.setTitle(cursor.getString(cursor.getColumnIndex(NewsDatabaseModel.TITLE)));
                news.setContent(cursor.getString(cursor.getColumnIndex(NewsDatabaseModel.CONTENT)));
                news.setStatus(cursor.getInt(cursor.getColumnIndex(NewsDatabaseModel.STATUS)));
                news.setCreateDate(cursor.getLong(cursor.getColumnIndex(NewsDatabaseModel.CREATE_DATE)));

                categories.add(news);
            }
        }
        cursor.close();

        return categories;
    }

    public List<News> findNewsAll(){
        String query = NewsDatabaseModel.STATUS + "= ?";
        String[] parameter = {"1"};

        Cursor cursor = context.getContentResolver().query(dbUriNews, null, query, parameter, NewsDatabaseModel.CREATE_DATE);

        List<News> categories = new ArrayList<News>();

        if(cursor != null){
            if (cursor.getCount() > 0){
                while (cursor.moveToNext()){
                    News news = new News();
                    news.setId(cursor.getInt(cursor.getColumnIndex(NewsDatabaseModel.ID)));
                    news.setTitle(cursor.getString(cursor.getColumnIndex(NewsDatabaseModel.TITLE)));
                    news.setContent(cursor.getString(cursor.getColumnIndex(NewsDatabaseModel.CONTENT)));
                    news.setStatus(cursor.getInt(cursor.getColumnIndex(NewsDatabaseModel.STATUS)));
                    news.setCreateDate(cursor.getLong(cursor.getColumnIndex(NewsDatabaseModel.CREATE_DATE)));

                    categories.add(news);
                }
            }
        }
        cursor.close();

        return categories;
    }

    public News findNewsById(long id){
        String query = NewsDatabaseModel.ID + "= ?";
        String[] parameter = {id+""};

        Cursor cursor = context.getContentResolver().query(dbUriNews, null, query, parameter, null);

        News news = null;

        if(cursor != null){
            if(cursor.getCount() > 0){
                cursor.moveToFirst();

                news = new News();
                news.setId(cursor.getInt(cursor.getColumnIndex(NewsDatabaseModel.ID)));
                news.setTitle(cursor.getString(cursor.getColumnIndex(NewsDatabaseModel.TITLE)));
                news.setContent(cursor.getString(cursor.getColumnIndex(NewsDatabaseModel.CONTENT)));
                news.setStatus(cursor.getInt(cursor.getColumnIndex(NewsDatabaseModel.STATUS)));
                news.setCreateDate(cursor.getLong(cursor.getColumnIndex(NewsDatabaseModel.CREATE_DATE)));

            }
        }
        cursor.close();

        return news;
    }

    public  void delete(News news){
        ContentValues values = new ContentValues();
        values.put(NewsDatabaseModel.STATUS, 0);

        context.getContentResolver().update(dbUriNews, values, NewsDatabaseModel.ID + " = ?", new String[] {news.getId()+""});
    }
}
