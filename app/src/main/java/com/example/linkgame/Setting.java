package com.example.linkgame;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;


public class Setting extends AppCompatActivity {
    private int backGroundMusic = 0 ;

public void goBack(View view){
    finish();
}


public void emailToMe(View view){
    Intent i = new Intent(Intent.ACTION_SEND);
    i.setType("message/rfc822");
    i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"liuluke0325@gmail.com"});
    i.putExtra(Intent.EXTRA_SUBJECT, "Tic Tac Toe 問題與建議");
    //i.putExtra(Intent.EXTRA_TEXT   , "Send to Developer");
    try {
        startActivity(Intent.createChooser(i, "Send mail..."));
    } catch (android.content.ActivityNotFoundException ex) {
        Toast.makeText(this, "找不到email應用程式.", Toast.LENGTH_SHORT).show();
    }

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
