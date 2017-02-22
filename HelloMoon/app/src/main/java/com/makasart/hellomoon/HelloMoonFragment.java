package com.makasart.hellomoon;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

/**
 * Created by Maxim on 06.10.2016.
 */

public class HelloMoonFragment extends Fragment {
    private Button mPlayButon;
    private Button mStopButton;
    private Button mVideoButton;
    private AudioPlayer mPlayer = new AudioPlayer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.hello_moon_fragment, parent, false);
        final VideoView MV = (VideoView)v.findViewById(R.id.videoView);
        mPlayButon = (Button) v.findViewById(R.id.helloMoonPlayButton);
        mPlayButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.play(getActivity());
            }
        });
        mStopButton = (Button) v.findViewById(R.id.helloMoonStopButton);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayer.stop();
            }
        });
        mVideoButton = (Button) v.findViewById(R.id.helloMoonVideoButton);

        mVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MV.setEnabled(true);
                String uriPath = "android.resource://com.makasart.hellomoon/"+R.raw.apollo_17_stroll;
                Uri uri = Uri.parse(uriPath);
                MV.setVideoURI(uri);
                MV.requestFocus();
                MV.start();
            }
        });

        return v;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mPlayer.stop();
    }
}
