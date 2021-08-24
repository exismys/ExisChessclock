package com.example.exischessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int seconds1 = 0;
    private int seconds2 = 0;
    private boolean running1, running2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickTime1(View view) {

    }

    public void onClickTime2(View view) {

    }

    public void onClickStart(View view) {
        String selectedItem = String.valueOf(((Spinner) findViewById(R.id.duration)).getSelectedItem());
        String[] gameArray = getResources().getStringArray(R.array.durations);
        if (selectedItem.equals(gameArray[0])) {
            CharSequence text = "You haven't chosen a game type";
            int duration = Toast.LENGTH_LONG;
            Toast.makeText(this, text, duration).show();
        } else {
            // Setting up time buttons
            seconds1 = seconds2 = 60 * Integer.parseInt(selectedItem.replaceAll("[^0-9]", ""));
            String time = String.format(Locale.getDefault(), "%02d\n%02d", seconds1/60, 0);
            /*String name1 = String.valueOf(((EditText) findViewById(R.id.white_name)).getText());
            String name2 = String.valueOf(((EditText) findViewById(R.id.black_name)).getText());
            String text1 = name1 + "\n" + time;
            String text2 = name2 + "\n" + time;
            // Formatting strings using SpannableString
            SpannableString string1 = new SpannableString(text1);
            string1.setSpan(new RelativeSizeSpan(0.2f), 0, text1.length() - 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            SpannableString string2 = new SpannableString(text2);
            string2.setSpan(new RelativeSizeSpan(0.2f), 0, text2.length() - 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
             */
            ((Button) findViewById(R.id.time_button1)).setText(time);
            ((Button) findViewById(R.id.time_button2)).setText(time);
        }
    }

    public void onClickPause(View view) {

    }

    public void onClickReset(View view) {

    }
}