package com.example.linkgame;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.GridLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private int countTurn; // 0 = red, 1 - yellow
    private boolean gameEnd = false ; //判斷一局遊戲結束沒
    private ArrayList<Integer> alreadySelectedList = new ArrayList<>();
    private ArrayList<Integer> redSelectedList = new ArrayList<>();
    private ArrayList<Integer> yellowSelectedList = new ArrayList<>();
    private int redWinTurn = 0;
    private int yellowWinTurn = 0;
    private int drawTurn = 0;
    private MediaPlayer mediaPlayer;
    private int backGroundMusic = 0 ;


    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }

    public void goSetting(View view){
        Intent intent = new Intent(getApplicationContext(),Setting.class);
        startActivity(intent);
    }

    public void setDisplayWord(String string ){

        TextView textView = findViewById(R.id.textDisplay);
        textView.setText(string);
    }

    public void reSetCountPoint(View view){
        this.redWinTurn = 0;
        this.yellowWinTurn = 0;
        this.drawTurn = 0;
        countWinTurn(false,false,false);
    }

public void restartGame(View view){

    countTurn = 0; // switch back to yellow
    alreadySelectedList.clear();
    redSelectedList.clear();
    yellowSelectedList.clear();
    gameEnd = false;

    GridLayout gridLayout = findViewById(R.id.gridLayout);

    for(int i = 0;  i< gridLayout.getChildCount();i++){

        ImageView eachChild = (ImageView) gridLayout.getChildAt(i);
        eachChild.setImageDrawable(null);

        Log.i("Reset","Press button");
    }

    Button newGameButton = findViewById(R.id.NewGameButton);
    newGameButton.setVisibility(View.INVISIBLE);
    //Toast.makeText(this,"Start a New Game", Toast.LENGTH_SHORT).show();
    //setDisplayWord("New Game Start!");
    setDisplayWord("新遊戲開始!");
}


    public void countWinTurn( boolean redWin, boolean yellowWin ,boolean draw ){
        if (redWin){
            this.redWinTurn += 1;
        }

        if (yellowWin){
            this.yellowWinTurn += 1;
        }

        if (draw){
            this.drawTurn += 1;
        }




        TextView textView = findViewById(R.id.displayPoint);

        textView.setText("成績 : "+"  紅: " + redWinTurn + "  黃 : " + yellowWinTurn +  "  平手 : " + drawTurn);


    }

    public boolean alreadySelect(int index){

        for(int i = 0; i < alreadySelectedList.size(); i ++){

            if(index == alreadySelectedList.get(i).intValue()) {
                System.out.println("Has the same value");
               // Toast.makeText(this,"Can Not Select This One!", Toast.LENGTH_SHORT).show();
                 Toast.makeText(this,"已經被選過了", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return false;
    }



    public boolean isWin(List<Integer> playerList){

        if (playerList.contains(Integer.valueOf(0)) &&  playerList.contains(Integer.valueOf(1)) && playerList.contains(Integer.valueOf(2))) {
            gameEnd = true;
        }
        else  if (playerList.contains(Integer.valueOf(3)) &&  playerList.contains(Integer.valueOf(4)) && playerList.contains(Integer.valueOf(5))) {
            gameEnd = true;
        }else  if (playerList.contains(Integer.valueOf(6)) &&  playerList.contains(Integer.valueOf(7)) && playerList.contains(Integer.valueOf(8))) {
            gameEnd = true;
        }else  if (playerList.contains(Integer.valueOf(0)) &&  playerList.contains(Integer.valueOf(4)) && playerList.contains(Integer.valueOf(8))) {
            gameEnd = true;
        }else  if (playerList.contains(Integer.valueOf(2)) &&  playerList.contains(Integer.valueOf(4)) && playerList.contains(Integer.valueOf(6))) {
            gameEnd = true;
        }else  if (playerList.contains(Integer.valueOf(0)) &&  playerList.contains(Integer.valueOf(3)) && playerList.contains(Integer.valueOf(6))) {
            gameEnd = true;
        }else  if (playerList.contains(Integer.valueOf(1)) &&  playerList.contains(Integer.valueOf(4)) && playerList.contains(Integer.valueOf(7))) {
            gameEnd = true;
        }else  if (playerList.contains(Integer.valueOf(2)) &&  playerList.contains(Integer.valueOf(5)) && playerList.contains(Integer.valueOf(8))) {
            gameEnd = true;
        }

        if(gameEnd == true){
            MediaPlayer winSound = MediaPlayer.create(this, R.raw.penclick);
            winSound.start();
            return true;
        }
        return false;
    }

//    public void backGroundMusic(View view){
//
//        if (backGroundMusic == 0){
//            mediaPlayer.pause();
//            backGroundMusic = 1;
//        }else{
//            mediaPlayer.start();
//            backGroundMusic = 0;
//        }
//    }

    public void dropImagine(View view){

        ImageView imageView = (ImageView) view;
        Button newGameButton = findViewById(R.id.NewGameButton);
        int index = Integer.parseInt(imageView.getTag().toString());
        Log.i("ImagineView","Hit "+index);

        if (gameEnd == false){


            if (!alreadySelect(index)) {// check already click or not
                imageView.setTranslationX(-2000);
                alreadySelectedList.add(Integer.valueOf(index));

                if (countTurn == 0) { // red select
                    clickSound();
                    imageView.setImageResource(R.drawable.red);
                    imageView.animate().translationXBy(2000).setDuration(300);
                    redSelectedList.add(Integer.valueOf(index));
                    if(isWin(redSelectedList)){
                        newGameButton.setVisibility(View.VISIBLE);
                        //Toast.makeText(this,"Winner is Red" , Toast.LENGTH_SHORT).show();
                        //setDisplayWord("Winner is Red");
                        setDisplayWord("紅色贏了!");
                        countWinTurn(true,false,false);

                    }
                    countTurn = 1;
                } else {
                   clickSound();
                    imageView.setImageResource(R.drawable.yellow);  // yellow select
                    imageView.animate().translationXBy(2000).setDuration(300);
                    yellowSelectedList.add(Integer.valueOf(index));
                    if(isWin(yellowSelectedList)){
                        newGameButton.setVisibility(View.VISIBLE);
                        //Toast.makeText(this,"Winner is Yellow" , Toast.LENGTH_SHORT).show();
                        //setDisplayWord("Winner is Yellow");
                        setDisplayWord("黃色贏了!");
                        countWinTurn(false,true,false);

                    }
                    countTurn = 0;
                }
            }

            if (alreadySelectedList.size() == 9 && !isWin(yellowSelectedList) && !isWin(redSelectedList)){
                setDisplayWord("End in a draw!");
                setDisplayWord("平手!");
                newGameButton.setVisibility(View.VISIBLE);
                countWinTurn(false,false,true);
                this.gameEnd = true;

            }

        }

    }


    public void clickSound(){
        MediaPlayer button = MediaPlayer.create(this, R.raw.click);
        button.start();


    }









  //End of music Service










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///google ad
        MobileAds.initialize(this, "ca-app-pub-6224254485550727~8514446625");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        // google end
        countWinTurn(false,false,false);


        //play the music
        mediaPlayer = MediaPlayer.create(this, R.raw.splashing);
        //mediaPlayer.start();
        //mediaPlayer.setLooping(true);



    }
}
