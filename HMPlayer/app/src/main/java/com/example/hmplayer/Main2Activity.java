package com.example.hmplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    boolean playing = false;
    int ft,st;
    ImageButton btnPlay;
    ImageButton btnPause;
    ImageButton btnStop, btnPrev;
    SeekBar progress;
    MediaPlayer mp;
    private Handler myh=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnPlay = (ImageButton) findViewById(R.id. imageButton);
        btnPause = (ImageButton) findViewById(R.id. imageButton2);
        btnStop = (ImageButton) findViewById(R.id. imageButton3);
        btnPrev = (ImageButton) findViewById(R.id. imageButton5);
        progress= (SeekBar)findViewById(R.id. seekBar);

        mp=MediaPlayer.create(getBaseContext(),R.raw.song1);

        btnPlay.setOnClickListener(new View.OnClickListener(){
            public Runnable UpdateSongTime;

            @Override
            public void onClick(View v){
                if(!playing){

                    mp.start();
                    playing=true;
                    Toast.makeText(getApplicationContext(),"Play",Toast.LENGTH_SHORT).show();
                    ft=mp.getDuration();
                    st=mp.getCurrentPosition();
                    progress.setMax(ft);
                    progress.setProgress(st);
                    myh.postDelayed(UpdateSongTime,100);
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playing) {
                    Toast.makeText(getApplicationContext(),"Pause",Toast.LENGTH_SHORT).show();
                    mp.pause();
                    playing = false;
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (playing) {
                    mp.stop();
                    Toast.makeText(getApplicationContext(),"Stop",Toast.LENGTH_SHORT).show();
                    playing = false;
                    progress.setProgress(0);
                }

            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Prev",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                mp.stop();
                finish();
                playing = false;

            }
        });





    }
}
