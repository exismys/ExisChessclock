package com.example.exischessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int seconds1 = 0;
    private int seconds2 = 0;
    private boolean running1, running2;
    private boolean wasHandlerRunning, wasClockRunning1, wasClock1Running2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            seconds1 = savedInstanceState.getInt("seconds1");
            seconds2 = savedInstanceState.getInt("seconds2");
            running1 = savedInstanceState.getBoolean("running1");
            running2 = savedInstanceState.getBoolean("running2");
            wasHandlerRunning = true;
            runTimer();
        }
    }

    public void onClickTime1(View view) {
        running1 = false;
        running2 = true;
    }

    public void onClickTime2(View view) {
        running1 = true;
        running2 = false;
    }

    public void onClickStart(View view) {
        Spinner spinner = (Spinner) findViewById(R.id.duration);
        String selectedItem = String.valueOf(spinner.getSelectedItem());
        String[] gameArray = getResources().getStringArray(R.array.durations);
        if (selectedItem.equals(gameArray[0])) {
            CharSequence text = "You haven't chosen a game type";
            int duration = Toast.LENGTH_LONG;
            Toast.makeText(this, text, duration).show();
        } else {
            // Setting up time buttons
            seconds1 = seconds2 = 60 * Integer.parseInt(selectedItem.replaceAll("[^0-9]", ""));
            String time = String.format(Locale.getDefault(), "%02d\n%02d", seconds1/60, 0);
            ((Button) findViewById(R.id.time_button1)).setText(time);
            ((Button) findViewById(R.id.time_button2)).setText(time);
            spinner.setSelection(0);
        }
        if (!wasHandlerRunning) {
            wasHandlerRunning = true;
            runTimer();
        }
    }

    public void onClickPause(View view) {
        running1 = running2 = false;
    }

    public void onClickReset(View view) {
        running1 = running2 = false;
        seconds1 = seconds2 = 0;
    }

    private void runTimer() {
        final Button timeButton1 = (Button) findViewById(R.id.time_button1);
        final Button timeButton2 = (Button) findViewById(R.id.time_button2);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int minutes1 = seconds1 / 60;
                int secs1 = seconds1 % 60;
                int minutes2 = seconds2 / 60;
                int secs2 = seconds2 % 60;

                String time1 = String.format(Locale.getDefault(), "%02d\n%02d", minutes1, secs1);
                timeButton1.setText(time1);
                String time2 = String.format(Locale.getDefault(), "%02d\n%02d", minutes2, secs2);
                timeButton2.setText(time2);
                if (seconds1 == 0 || seconds2 == 0) {
                    running1 = running2 = false;
                }
                if (running1) {
                    seconds1--;
                }
                if (running2) {
                    seconds2--;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds1", seconds1);
        savedInstanceState.putInt("seconds2", seconds2);
        savedInstanceState.putBoolean("running1", running1);
        savedInstanceState.putBoolean("wasClockRunning2", running2);
    }
}