package org.superpichu.infoscreen_android;

import android.os.AsyncTask;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by chris on 4/13/15.
 */
public class dismissAlert extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... params) {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://theloganwalker.com/info/setInfo.php?cmd=dismiss");
        try{
            client.execute(get);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
