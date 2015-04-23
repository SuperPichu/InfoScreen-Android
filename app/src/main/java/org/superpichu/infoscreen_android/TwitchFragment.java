package org.superpichu.infoscreen_android;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;


public class TwitchFragment extends Fragment {
    View view;
    VideoView videoView;
    public TwitchFragment() {
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

        return view;
    }


    public void updateTwitch(String playlist){
        videoView = (VideoView)view.findViewById(R.id.videoView);
        try {
            Uri m3u = Uri.parse(new getTwitch().execute(playlist).get());
            videoView.setVideoURI(m3u);
            videoView.requestFocus();
            videoView.setVisibility(View.VISIBLE);
            videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    return true;
                }
            });
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    mp.setVolume(0,0);
                    mp.start();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
