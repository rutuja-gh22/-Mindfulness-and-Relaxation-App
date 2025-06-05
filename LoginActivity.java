package com.example.miniproject;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    MediaPlayer mediaPlayer;
    static int activityCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize MediaPlayer and start playing
        mediaPlayer = MediaPlayer.create(this, R.raw.bgmusic); // Replace with your audio file name
        mediaPlayer.start();
        dbHelper = new DatabaseHelper(this);


        EditText n = (EditText)findViewById(R.id.name);
        EditText p = (EditText)findViewById(R.id.password);
        Button b = (Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = n.getText().toString();
                String password = p.getText().toString();

                if(name.equals("") || password.equals("") ){
                    Toast.makeText(LoginActivity.this,"All fields are mandatory",Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkCredentials = dbHelper.checkNamePassword(name,password);

                    if(checkCredentials == true){
                        Toast.makeText(LoginActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name", name); // Assuming 'username' is the variable holding the username
                        editor.apply();

                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        i.putExtra("name",name.toString());
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        TextView t = (TextView) findViewById(R.id.textView6);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i2);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        activityCount++;
        if (activityCount >= 3) {
            stopMediaPlayer();
        }
    }
    private void stopMediaPlayer() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }
}
