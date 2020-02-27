package com.example.hmplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean playing = false;
    int ft,st;
    ImageButton btnPlay;
    ImageButton btnPause;
    ImageButton btnStop,btnNext;
    SeekBar progress;
    MediaPlayer mp;
    private Handler myh=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (ImageButton) findViewById(R.id. imageButton);
        btnPause = (ImageButton) findViewById(R.id. imageButton2);
        btnStop = (ImageButton) findViewById(R.id. imageButton3);
        btnNext= (ImageButton) findViewById(R.id. imageButton4);
        progress= (SeekBar)findViewById(R.id. seekBar);

        TextView marque = (TextView) this.findViewById(R.id.textView);
        marque.setSelected(true);

        mp=MediaPlayer.create(getBaseContext(),R.raw.song1);

        btnPlay.setOnClickListener(new View.OnClickListener(){
            public Runnable UpdateSongTime;

            @Override
            public void onClick(View v){
                if(playing){
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
                    mp.pause();
                    Toast.makeText(getApplicationContext(),"Pause",Toast.LENGTH_SHORT).show();
                    playing = false;
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (playing) {
                    mp.stop();
                    playing = false;
                    Toast.makeText(getApplicationContext(),"Stop",Toast.LENGTH_SHORT).show();
                    progress.setProgress(0);
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Next",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
               startActivity(intent);

                mp.stop();
                finish();
                playing = false;

            }
        });






    }
}
