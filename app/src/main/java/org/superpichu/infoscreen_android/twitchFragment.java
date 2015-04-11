package org.superpichu.infoscreen_android;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;


public class twitchFragment extends Fragment {
    View view;
    VideoView videoView;
    String playlist;
    public twitchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_twitch, container, false);
        videoView = (VideoView)view.findViewById(R.id.videoView);
        try {
            Uri m3u = Uri.parse(new getTwitch().execute("saltybet").get());
            videoView.setVideoURI(m3u);
            videoView.requestFocus();
            videoView.setVisibility(View.VISIBLE);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mp) {
                    mp.setVolume(0,0);
                    mp.start();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }


}
