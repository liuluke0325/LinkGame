package com.example.linkgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.GridLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int countTurn; // 0 = red, 1 - yellow
    private ArrayList<Integer> alreadySelectedList = new ArrayList<>();
    private ArrayList<Integer> redSelectedList = new ArrayList<>();
    private ArrayList<Integer> yellowSelectedList = new ArrayList<>();

    public void setDisplayWord(String string ){

        TextView textView = findViewById(R.id.textDisplay);
        textView.setText(string);
    }



public void restartGame(View view){

    countTurn = 0; // switch back to yellow
    alreadySelectedList.clear();
    redSelectedList.clear();
    yellowSelectedList.clear();

    GridLayout gridLayout = findViewById(R.id.gridLayout);

    for(int i = 0;  i< gridLayout.getChildCount();i++){

        ImageView eachChild = (ImageView) gridLayout.getChildAt(i);
        eachChild.setImageDrawable(null);

        Log.i("Reset","Press button");
    }

    Button newGameButton = findViewById(R.id.NewGameButton);
    newGameButton.setVisibility(View.INVISIBLE);
    //Toast.makeText(this,"Start a New Game", Toast.LENGTH_SHORT).show();
    setDisplayWord("New Game Start!");
}


    public boolean alreadySelect(int index){

        for(int i = 0; i < alreadySelectedList.size(); i ++){

            if(index == alreadySelectedList.get(i).intValue()) {
                System.out.println("Has the same value");
                Toast.makeText(this,"Can Not Select This One!", Toast.LENGTH_SHORT).show();

                return true;
            }
        }
        return false;
    }



    public boolean isWin(List<Integer> playerList){

        if (playerList.contains(Integer.valueOf(0)) &&  playerList.contains(Integer.valueOf(1)) && playerList.contains(Integer.valueOf(2))) {
            return true;
        }
        else  if (playerList.contains(Integer.valueOf(3)) &&  playerList.contains(Integer.valueOf(4)) && playerList.contains(Integer.valueOf(5))) {
            return true;
        }else  if (playerList.contains(Integer.valueOf(6)) &&  playerList.contains(Integer.valueOf(7)) && playerList.contains(Integer.valueOf(8))) {
            return true;
        }else  if (playerList.contains(Integer.valueOf(0)) &&  playerList.contains(Integer.valueOf(4)) && playerList.contains(Integer.valueOf(8))) {
            return true;
        }else  if (playerList.contains(Integer.valueOf(2)) &&  playerList.contains(Integer.valueOf(4)) && playerList.contains(Integer.valueOf(6))) {
            return true;
        }else  if (playerList.contains(Integer.valueOf(0)) &&  playerList.contains(Integer.valueOf(3)) && playerList.contains(Integer.valueOf(6))) {
            return true;
        }else  if (playerList.contains(Integer.valueOf(1)) &&  playerList.contains(Integer.valueOf(4)) && playerList.contains(Integer.valueOf(7))) {
            return true;
        }else  if (playerList.contains(Integer.valueOf(2)) &&  playerList.contains(Integer.valueOf(5)) && playerList.contains(Integer.valueOf(8))) {
            return true;
        }

        return false;
    }


    public void dropImagine(View view){

        ImageView imageView = (ImageView) view;
        Button newGameButton = findViewById(R.id.NewGameButton);
        int index = Integer.parseInt(imageView.getTag().toString());
        Log.i("ImagineView","Hit "+index);


        if (!alreadySelect(index)) {// check already click or not
            imageView.setTranslationX(-2000);
            alreadySelectedList.add(Integer.valueOf(index));

            if (countTurn == 0) { // red select
                imageView.setImageResource(R.drawable.red);
                imageView.animate().translationXBy(2000).setDuration(300);
                redSelectedList.add(Integer.valueOf(index));
                if(isWin(redSelectedList)){
                   newGameButton.setVisibility(View.VISIBLE);
                    //Toast.makeText(this,"Winner is Red" , Toast.LENGTH_SHORT).show();
                    setDisplayWord("Winner is Red");
                }
                countTurn = 1;
            } else {
                imageView.setImageResource(R.drawable.yellow);  // yellow select
                imageView.animate().translationXBy(2000).setDuration(300);
                yellowSelectedList.add(Integer.valueOf(index));
                if(isWin(yellowSelectedList)){
                    newGameButton.setVisibility(View.VISIBLE);
                    //Toast.makeText(this,"Winner is Yellow" , Toast.LENGTH_SHORT).show();
                    setDisplayWord("Winner is Yellow");
                }
                countTurn = 0;
            }
        }

        if (alreadySelectedList.size() == 9 && !isWin(yellowSelectedList) && !isWin(redSelectedList)){
            setDisplayWord("End in a draw!");
            newGameButton.setVisibility(View.VISIBLE);
        }

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
