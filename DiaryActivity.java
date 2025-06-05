package com.example.miniproject;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DiaryActivity extends AppCompatActivity {

    DiaryDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        EditText entryEditText,entryEditText1,entryEditText2;
        dataSource = new DiaryDataSource(this);
        dataSource.open();

        entryEditText = findViewById(R.id.entryEditText);
        entryEditText1 = findViewById(R.id.titleEditText);
        entryEditText2 = findViewById(R.id.dateEditText);
        Button submitButton = findViewById(R.id.saveButton);
        Button viewButton = findViewById(R.id.viewButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = entryEditText.getText().toString();
                String title = entryEditText1.getText().toString();
                String date = entryEditText2.getText().toString();
                long id = dataSource.insertEntry(title,content,date);
                if (id != -1) {
                    showAlert("Entry added successfully!");
                } else {
                    showAlert("Error adding entry.");
                }
            }
        });
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dataSource.getAllEntries();
                StringBuilder entries = new StringBuilder();
                while (cursor.moveToNext()) {
                    entries.append("Title: ").append(cursor.getString(1)).append("\n")
                            .append("Date: ").append(cursor.getString(3)).append("\n")
                            .append("Content: ").append(cursor.getString(2)).append("\n\n");
                }
                cursor.close();
                showAlert(entries.toString());

            }
        });
    }

    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataSource.close();
    }
}
