package com.example.miniproject;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TimerFragment extends Fragment {
    private CountDownTimer timer;
    private TextView timerTextView;
    private Button startStopButton;
    private Button resetButton;
    private NumberPicker numberPicker;
    private boolean isTimerRunning = false;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timer, container, false);

        timerTextView = rootView.findViewById(R.id.timerTextView);
        startStopButton = rootView.findViewById(R.id.startStopButton);
        resetButton = rootView.findViewById(R.id.resetButton);
        numberPicker = rootView.findViewById(R.id.numberPicker);

        // Programmatically set the min and max values for the NumberPicker
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(60);

        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.medsong);

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimerRunning) {
                    stopTimer();
                    stopAudio();
                } else {
                    startTimer();
                    playAudio();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
                stopAudio();
            }
        });

        return rootView;
    }

    private void startTimer() {
        int selectedMinutes = numberPicker.getValue();
        long durationInMillis = selectedMinutes * 60 * 1000;

        timer = new CountDownTimer(durationInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long minutes = millisUntilFinished / 60000;
                long seconds = (millisUntilFinished % 60000) / 1000;
                String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
                timerTextView.setText(timeLeftFormatted);
            }

            @Override
            public void onFinish() {
                timerTextView.setText("00:00");
            }
        };

        timer.start();
        isTimerRunning = true;
        startStopButton.setText("Pause");
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
        isTimerRunning = false;
        startStopButton.setText("Start");
    }

    private void resetTimer() {
        if (timer != null) {
            timer.cancel();
            timerTextView.setText("00:00");
        }
        isTimerRunning = false;
        startStopButton.setText("Start");
    }

    private void playAudio() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    private void stopAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}