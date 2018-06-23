package com.example.waheguru.eggtimer;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timeTextView;
    SeekBar timeSeekBar;
    Button startTimerButton;
    boolean counterIsActive=false;
    CountDownTimer countDownTimer;
    public void resetTimer()
    {
        countDownTimer.cancel();
        timeSeekBar.setEnabled(true);
        timeSeekBar.setProgress(30);
        timeTextView.setText("0:30");
        counterIsActive=false;
        startTimerButton.setText("Go");
    }
    public void updateTimer(long secondsLeft) {
        String secondString=Long.toString(secondsLeft%60);
        if ((secondsLeft%60) == 0)
        {
            secondString="00";
        }
        timeTextView.setText(Long.toString((secondsLeft/60))+":"+secondString);
    }
    public void startTimerButton(View view)
    {
        if(!counterIsActive)
        {
            counterIsActive=true;
            startTimerButton.setText("Stop");
            timeSeekBar.setEnabled(false);
            countDownTimer= new CountDownTimer((timeSeekBar.getProgress() * 1000), 1000) {
                @Override
                public void onTick(long milliSecondsUntilFinsihed) {

                    updateTimer(milliSecondsUntilFinsihed / 1000);
                }

                @Override
                public void onFinish() {
                    timeTextView.setText("0:00");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
        else
        {
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         timeSeekBar = (SeekBar) findViewById(R.id.seekBar);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        startTimerButton=(Button) findViewById(R.id.button);
        timeSeekBar.setMax(600);
        timeSeekBar.setProgress(30);
        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b)
            {
                    updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {


            }
        });
    }
}
