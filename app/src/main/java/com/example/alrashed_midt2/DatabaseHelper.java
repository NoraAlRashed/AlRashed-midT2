package com.example.alrashed_midt2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.SparseArray;

import androidx.annotation.Nullable;
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Mid2.db";
    private static final String TABLE_NAME = "PERSON";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NID = "NID";
    private static final String COLUMN_NAME = "Name";

    public DatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_NAME + "(" +COLUMN_ID
                + " INTEGER PRIMARY KEY," + COLUMN_NID + " INTEGER NOT NULL,"
                + COLUMN_NAME + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Add(int id, int nid,  String name){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NID, nid);
        values.put(COLUMN_NAME, name);

        db.insert(TABLE_NAME, null, values);

    }
    public Cursor View(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor x = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return x;
    }

    public Integer Delete(String inp){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "NAME = ?", new String[] {inp});
    }

}

