package com.example.player.ui.home;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.player.MainActivity;
import com.example.player.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class HomeFragment extends Fragment {
    boolean playing = false;
    int ft, st;
    ImageButton btnPlay;
    ImageButton btnPause,btnNext;
    ImageButton btnStop, btnPrev;
    SeekBar progress;
    MediaPlayer mp;
    private Handler myh = new Handler();
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        btnPlay=(ImageButton) view.findViewById(R.id.imageButton);
        btnPause=(ImageButton) view.findViewById(R.id.imageButton2);
        btnStop=(ImageButton) view.findViewById(R.id.imageButton3);
        btnPrev=(ImageButton) view.findViewById(R.id.imageButton5);
        btnNext=(ImageButton) view.findViewById(R.id.imageButton4);
        progress= (SeekBar)view.findViewById(R.id. seekBar);

        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
       mp = MediaPlayer.create(getContext(),R.raw.song1);
        btnPlay.setOnClickListener(new View.OnClickListener(){
            public Runnable UpdateSongTime;
            @Override
            public void onClick(View v){
                if(!playing){
                    mp.start();
                    playing=true;
                    Toast.makeText(getActivity(),"Play",Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getActivity(),"Pause",Toast.LENGTH_SHORT).show();
                    mp.pause();
                    ft=mp.getDuration();
                    st=mp.getCurrentPosition();
                    playing = false;
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (playing) {
                    mp.stop();
                    Toast.makeText(getActivity(),"Stop",Toast.LENGTH_SHORT).show();
                    playing = false;
                    progress.setProgress(0);
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Next",Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(getActivity(), MainActivity.class);
               startActivity(intent);
                mp.stop();
                getActivity().finish();
                playing = false;

            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"Prev",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                mp.stop();
                getActivity().finish();
                playing = false;

            }
        });

        return view;
        }
    }

