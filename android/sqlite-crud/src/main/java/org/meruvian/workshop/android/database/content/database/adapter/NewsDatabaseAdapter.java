package org.meruvian.workshop.android.database.content.database.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

import org.meruvian.workshop.android.database.content.SampleContentProvider;
import org.meruvian.workshop.android.database.content.database.model.CategoryDatabaseModel;
import org.meruvian.workshop.android.database.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meruvian on 29/01/15.
 */
public class NewsDatabaseAdapter {
    private Uri dbUriNews = Uri.parse(SampleContentProvider.CONTENT_PATH
            + SampleContentProvider.TABLES[0]);;

    private Context context;

    public NewsDatabaseAdapter(Context context) {
        this.context = context;
    }

    public void save(News news) {
        ContentValues values = new ContentValues();

        if (news.getId() == -1) {
            values.put(CategoryDatabaseModel.TITLE, news.getTitle());
            values.put(CategoryDatabaseModel.CONTENT, news.getContent());
            values.put(CategoryDatabaseModel.STATUS, 1);
            values.put(CategoryDatabaseModel.CREATE_DATE, news.getCreateDate());

            context.getContentResolver().insert(dbUriNews, values);
        } else {
            values.put(CategoryDatabaseModel.TITLE, news.getTitle());
            values.put(CategoryDatabaseModel.CONTENT, news.getContent());
            values.put(CategoryDatabaseModel.STATUS, 1);
            values.put(CategoryDatabaseModel.CREATE_DATE, news.getCreateDate());

            context.getContentResolver().update(dbUriNews, values, CategoryDatabaseModel.ID + " = ?", new String[] { news.getId() + "" });
        }
    }

    public List<News> findNewsByTitle(String title) {
        String query = CategoryDatabaseModel.TITLE + " like ? AND " + CategoryDatabaseModel.STATUS + " = ?";
        String[] parameter = { "%" + title + "%", "1" };

        Cursor cursor = context.getContentResolver().query(dbUriNews, null, query, parameter, CategoryDatabaseModel.CREATE_DATE);

        List<News> categories = new ArrayList<News>();

        if(cursor != null) {
            while (cursor.moveToNext()) {
                News news = new News();
                news.setId(cursor.getInt(cursor.getColumnIndex(CategoryDatabaseModel.ID)));
                news.setTitle(cursor.getString(cursor.getColumnIndex(CategoryDatabaseModel.TITLE)));
                news.setContent(cursor.getString(cursor.getColumnIndex(CategoryDatabaseModel.CONTENT)));
                news.setStatus(cursor.getInt(cursor.getColumnIndex(CategoryDatabaseModel.STATUS)));
                news.setCreateDate(cursor.getLong(cursor.getColumnIndex(CategoryDatabaseModel.CREATE_DATE)));

                categories.add(news);
            }
        }

        cursor.close();

        return categories;
    }

    public List<News> findNewsAll() {
        String query = CategoryDatabaseModel.STATUS + " = ?";
        String[] parameter = { "1" };

        Cursor cursor = context.getContentResolver().query(dbUriNews, null, query, parameter, CategoryDatabaseModel.CREATE_DATE);

        List<News> categories = new ArrayList<News>();

        if(cursor != null) {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    News news = new News();
                    news.setId(cursor.getInt(cursor.getColumnIndex(CategoryDatabaseModel.ID)));
                    news.setTitle(cursor.getString(cursor.getColumnIndex(CategoryDatabaseModel.TITLE)));
                    news.setContent(cursor.getString(cursor.getColumnIndex(CategoryDatabaseModel.CONTENT)));
                    news.setStatus(cursor.getInt(cursor.getColumnIndex(CategoryDatabaseModel.STATUS)));
                    news.setCreateDate(cursor.getLong(cursor.getColumnIndex(CategoryDatabaseModel.CREATE_DATE)));

                    categories.add(news);
                }
            }
        }

        cursor.close();

        return categories;
    }

    public News findNewsById(long id) {
        String query = CategoryDatabaseModel.ID + " = ?";
        String[] parameter = { id + "" };

        Cursor cursor = context.getContentResolver().query(dbUriNews, null, query, parameter, null);

        News news = null;

        if(cursor != null) {
            if(cursor.getCount() > 0) {
                try {
                    cursor.moveToFirst();

                    news = new News();
                    news.setId(cursor.getInt(cursor.getColumnIndex(CategoryDatabaseModel.ID)));
                    news.setTitle(cursor.getString(cursor.getColumnIndex(CategoryDatabaseModel.TITLE)));
                    news.setContent(cursor.getString(cursor.getColumnIndex(CategoryDatabaseModel.CONTENT)));
                    news.setStatus(cursor.getInt(cursor.getColumnIndex(CategoryDatabaseModel.STATUS)));
                    news.setCreateDate(cursor.getLong(cursor.getColumnIndex(CategoryDatabaseModel.CREATE_DATE)));

                } catch (SQLException e) {
                    e.printStackTrace();

                    news = null;
                }
            }
        }

        cursor.close();

        return news;
    }

    public void delete(News news) {
        ContentValues values = new ContentValues();
        values.put(CategoryDatabaseModel.STATUS, 0);

        context.getContentResolver().update(dbUriNews, values, CategoryDatabaseModel.ID + " = ? ", new String[] { news.getId() + "" });
    }


}
