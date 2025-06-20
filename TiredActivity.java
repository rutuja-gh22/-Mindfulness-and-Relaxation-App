package com.example.miniproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class TiredActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    int currentSong = 0;
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tired);
        Button video = findViewById(R.id.playPauseButton);
        Button previousButton = findViewById(R.id.previousButton);
        Button playButton = findViewById(R.id.playButton);
        Button nextButton = findViewById(R.id.nextButton);

        VideoView videoView = findViewById(R.id.idVideoView);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.tiredvdo;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(null);
        mediaController.setAnchorView(videoView);

        int[] songs = {R.raw.tired1, R.raw.tired2, R.raw.tired3};
        mediaPlayer = MediaPlayer.create(this, songs[currentSong]);
        ImageButton sugg=findViewById(R.id.calmvideo);
        sugg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.healthline.com/health/how-to-stop-feeling-tired";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSong > 0) {
                    currentSong--;
                    mediaPlayer.release();
                    mediaPlayer = MediaPlayer.create(TiredActivity.this, songs[currentSong]);
                    mediaPlayer.start();
                }
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentSong < songs.length - 1) {
                    currentSong++;
                    mediaPlayer.release();
                    mediaPlayer = MediaPlayer.create(TiredActivity.this, songs[currentSong]);
                    mediaPlayer.start();
                }
            }
        });


        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                } else {
                    videoView.start();
                }
            }
        });



    }
}