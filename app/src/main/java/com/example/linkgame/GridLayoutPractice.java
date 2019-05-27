package com.example.linkgame;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class GridLayoutPractice extends AppCompatActivity {
    MediaPlayer mediaPlayer;


    public void pressButton(View view){

        Button button = (Button) view;

        Log.i("Button", button.getTag().toString());


        if (button.getTag().toString().equals("Stop")){
            mediaPlayer.pause();
            Toast.makeText(this,"Stop Playing",Toast.LENGTH_LONG).show();
        }else {

            mediaPlayer.pause();
            mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier(button.getTag().toString(), "raw", getPackageName()));
            // MediaPlayer mediaplayer2 = MediaPlayer.create(this,R.raw.howitbegan);
            // 運用tag來呼叫檔案名稱 , getResources().getIdentifier(button.getTag().toString(),"mp3",getPackageName())
            mediaPlayer.start();
        }

    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout_practice);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.pause();
        mediaPlayer.stop();


        ListView listView = findViewById(R.id.listView);

        final ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("stop");
        arrayList.add("howitbegan");
        arrayList.add("splashing");
        arrayList.add("click");
        arrayList.add("penclick");



        //建立連接
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        //把adapter 放入listview
        listView.setAdapter(arrayAdapter);
        //當按下時候發生~
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           //使全部消失
                // parent.setVisibility(View.GONE);
                Log.i("listview", arrayList.get(position) );


                Toast.makeText(getApplicationContext(),  arrayList.get(position) , Toast.LENGTH_SHORT).show();


                mediaPlayer.stop();
                if (position != 0) {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(arrayList.get(position), "raw", getPackageName()));
                    mediaPlayer.start();
                }



            }
        });

    }
}
