package com.example.student3.timecounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    public long score; public long count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
        updateInfo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    public void load(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        long defaultScore = System.currentTimeMillis();
        score = sharedPref.getLong("score", defaultScore);
        count = defaultScore - score;
    }

    public void save(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong("score", count);
        editor.commit();
    }

    public void updateInfo(){
        Chronometer clock = (Chronometer) findViewById(R.id.chronometer);
        clock.setBase(count);
    }

    public void onClick(View v) {
        count = 0;
        updateInfo();
    }
}
