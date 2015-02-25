package org.meruvian.workshop.sample.crud.content.database.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

import org.meruvian.workshop.sample.crud.content.SampleContentProvider;
import org.meruvian.workshop.sample.crud.content.database.model.CategoryDatabaseModel;
import org.meruvian.workshop.sample.crud.entity.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meruvian on 29/01/15.
 */
public class NewsDatabaseAdapter {
    private Uri dbUriCategory = Uri.parse(SampleContentProvider.CONTENT_PATH
            + SampleContentProvider.TABLES[0]);;

    private Context context;

    public NewsDatabaseAdapter(Context context) {
        this.context = context;
    }

    public void save(News news) {
        ContentValues values = new ContentValues();

        if (news.getId() == -1) {
            values.put(CategoryDatabaseModel.NAME, news.getName());
            values.put(CategoryDatabaseModel.DESCRIPTION, news.getDescription());
            values.put(CategoryDatabaseModel.STATUS, 1);
            values.put(CategoryDatabaseModel.CREATE_DATE, news.getCreateDate());

            context.getContentResolver().insert(dbUriCategory, values);
        } else {
            values.put(CategoryDatabaseModel.NAME, news.getName());
            values.put(CategoryDatabaseModel.DESCRIPTION, news.getDescription());
            values.put(CategoryDatabaseModel.STATUS, 1);
            values.put(CategoryDatabaseModel.CREATE_DATE, news.getCreateDate());

            context.getContentResolver().update(dbUriCategory, values, CategoryDatabaseModel.ID + " = ?", new String[] { news.getId() + "" });
        }
    }

    public List<News> findCategoryByName(String name) {
        String query = CategoryDatabaseModel.NAME + " like ? AND " + CategoryDatabaseModel.STATUS + " = ?";
        String[] parameter = { "%" + name + "%", "1" };

        Cursor cursor = context.getContentResolver().query(dbUriCategory, null, query, parameter, CategoryDatabaseModel.CREATE_DATE);

        List<News> categories = new ArrayList<News>();

        if(cursor != null) {
            while (cursor.moveToNext()) {
                News news = new News();
                news.setId(cursor.getLong(cursor.getColumnIndex(CategoryDatabaseModel.ID)));
                news.setName(cursor.getString(cursor.getColumnIndex(CategoryDatabaseModel.NAME)));
                news.setDescription(cursor.getString(cursor.getColumnIndex(CategoryDatabaseModel.DESCRIPTION)));
                news.setStatus(cursor.getInt(cursor.getColumnIndex(CategoryDatabaseModel.STATUS)));
                news.setCreateDate(cursor.getLong(cursor.getColumnIndex(CategoryDatabaseModel.CREATE_DATE)));

                categories.add(news);
            }
        }

        cursor.close();

        return categories;
    }

    public List<News> findCategoryAll() {
        String query = CategoryDatabaseModel.STATUS + " = ?";
        String[] parameter = { "1" };

        Cursor cursor = context.getContentResolver().query(dbUriCategory, null, query, parameter, CategoryDatabaseModel.CREATE_DATE);

        List<News> categories = new ArrayList<News>();

        if(cursor != null) {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    News news = new News();
                    news.setId(cursor.getLong(cursor.getColumnIndex(CategoryDatabaseModel.ID)));
                    news.setName(cursor.getString(cursor.getColumnIndex(CategoryDatabaseModel.NAME)));
                    news.setDescription(cursor.getString(cursor.getColumnIndex(CategoryDatabaseModel.DESCRIPTION)));
                    news.setStatus(cursor.getInt(cursor.getColumnIndex(CategoryDatabaseModel.STATUS)));
                    news.setCreateDate(cursor.getLong(cursor.getColumnIndex(CategoryDatabaseModel.CREATE_DATE)));

                    categories.add(news);
                }
            }
        }

        cursor.close();

        return categories;
    }

    public News findCategoryById(long id) {
        String query = CategoryDatabaseModel.ID + " = ?";
        String[] parameter = { id + "" };

        Cursor cursor = context.getContentResolver().query(dbUriCategory, null, query, parameter, null);

        News news = null;

        if(cursor != null) {
            if(cursor.getCount() > 0) {
                try {
                    cursor.moveToFirst();

                    news = new News();
                    news.setId(cursor.getLong(cursor.getColumnIndex(CategoryDatabaseModel.ID)));
                    news.setName(cursor.getString(cursor.getColumnIndex(CategoryDatabaseModel.NAME)));
                    news.setDescription(cursor.getString(cursor.getColumnIndex(CategoryDatabaseModel.DESCRIPTION)));
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

        context.getContentResolver().update(dbUriCategory, values, CategoryDatabaseModel.ID + " = ? ", new String[] { news.getId() + "" });
    }


}
