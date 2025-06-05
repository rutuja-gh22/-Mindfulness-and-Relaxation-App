package com.example.miniproject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DiaryDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public DiaryDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertEntry(String title, String content, String date) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);
        values.put("date", date);

        return database.insert("entries", null, values);
    }

    public Cursor getAllEntries() {

        String[] allColumns = {"_id", "title", "content", "date"};
        return database.query("entries", allColumns, null, null, null, null, null);

    }
}
