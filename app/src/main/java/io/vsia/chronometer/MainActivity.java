package io.vsia.chronometer;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    Button btn_start;
    Button btn_pause;
    Button btn_stop;

    long elapsedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.btn_start);
        btn_pause = findViewById(R.id.btn_pause);
        btn_stop = findViewById(R.id.btn_stop);
        chronometer = findViewById(R.id.chronometer);

        btnEnabled(true, false, false);
    }

    public void onStart(View view) {
        elapsedTime = SystemClock.elapsedRealtime() - elapsedTime;
        chronometer.setBase(elapsedTime);

        chronometer.start();

        btnEnabled(false, true, true);
    }

    public void onPause(View view) {
        elapsedTime = SystemClock.elapsedRealtime() - chronometer.getBase();

        chronometer.stop();

        btnEnabled(true, false, true);
    }

    public void onStop(View view) {
        elapsedTime = 0;

        chronometer.stop();

        chronometer.setBase(SystemClock.elapsedRealtime());

        btnEnabled(true, false, false);
    }

    public void btnEnabled(boolean startButton, boolean pauseButton, boolean stopButton) {
        btn_start.setEnabled(startButton);
        btn_pause.setEnabled(pauseButton);
        btn_stop.setEnabled(stopButton);
    }
}
