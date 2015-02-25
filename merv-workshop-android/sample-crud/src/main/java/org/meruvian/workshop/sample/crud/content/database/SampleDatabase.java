package org.meruvian.workshop.sample.crud.content.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.meruvian.workshop.sample.crud.content.database.model.CategoryDatabaseModel;

/**
 * Created by meruvian on 29/01/15.
 */

public class SampleDatabase extends SQLiteOpenHelper {
    public static final String DATABASE = "sample_crud";
    private static final int VERSION = 1;

    public static final String CATEGORY_TABLE = "tbl_category";

    private Context context;

    public SampleDatabase(Context context) {
        super(context, DATABASE, null, VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + CATEGORY_TABLE + "("
                + CategoryDatabaseModel.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + CategoryDatabaseModel.NAME + " TEXT, "
                + CategoryDatabaseModel.DESCRIPTION+ " TEXT, "
                + CategoryDatabaseModel.CREATE_DATE + " INTEGER, "
                + CategoryDatabaseModel.STATUS + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {

        }
    }
}
