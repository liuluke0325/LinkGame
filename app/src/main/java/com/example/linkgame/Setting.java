package com.example.linkgame;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;


public class Setting extends AppCompatActivity {
    private int backGroundMusic = 0 ;

public void goBack(View view){
    finish();
}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


//        //SeekBar
       final AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE); // create the AudioManager

      int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(maxVolume); //設定最大上限
        seekBar.setProgress(currentVolume); // 設定起始數值

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // create the seekbar
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress , 0); // progress == 目前bar的位置
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        }

        );//seekBar End










    }
}
