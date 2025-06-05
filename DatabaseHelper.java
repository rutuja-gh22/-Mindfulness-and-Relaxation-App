package com.example.miniproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jetbrains.annotations.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "combined_Database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE entries (" +
                    "_id INTEGER PRIMARY KEY," +
                    "title TEXT," +
                    "content TEXT," +
                    "date TEXT)";

    private static final String SQL_CREATE_USERS =
            "CREATE TABLE Users (name TEXT PRIMARY KEY, password TEXT)";

    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS entries");
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }

    public boolean insertData(String name, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("password", password);
        long result = myDB.insert("Users", null, cv);

        return result != -1;
    }

    public boolean checkName(String name) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cur = myDB.rawQuery("SELECT * FROM Users WHERE name = ?", new String[]{name});
        return cur.getCount() > 0;
    }

    public boolean checkNamePassword(String name, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cur = myDB.rawQuery("SELECT * FROM Users WHERE name = ? AND password = ?", new String[]{name, password});
        return cur.getCount() > 0;
    }
}
