package org.superpichu.infoscreen_android;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by chris on 4/13/15.
 */
public class getServer extends AsyncTask<String, Void, Server> {
    @Override
    protected Server doInBackground(String... params) {
        Server server = new Server();
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://theloganwalker.com/info/getInfo.php");
        try {
            HttpResponse response = client.execute(get);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"UTF-8"));
            String result = reader.readLine();
            JSONObject json = new JSONObject(result).getJSONObject("infoScreen");
            JSONObject  serverVars = json.getJSONObject("serverVars");
            server.twitchChannel = serverVars.getString("twitchChannel");
            server.alertBody = serverVars.getString("alertText");
            server.alertSender = serverVars.getString("alertSender");
            server.alertIsActive = serverVars.getBoolean("alertIsActive");
            server.weather = getWeather(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return server;
    }

    private Weather getWeather(JSONObject json) {
        Weather weather = new Weather();
        try {
            JSONObject jsonWeather = json.getJSONObject("weather");
            weather.currentTemp = jsonWeather.getString("currentTemp");
            weather.lowTemp = jsonWeather.getString("lowTemp");
            weather.highTemp = jsonWeather.getString("highTemp");
            weather.description = jsonWeather.getString("description");
            JSONArray forecastArray = jsonWeather.getJSONObject("forecast").getJSONArray("forecastDay");
            ArrayList<Forecast> forecasts = new ArrayList<>();
            for(int i = 0;i<forecastArray.length();i++){
                JSONObject jsonObject = forecastArray.getJSONObject(i);
                Forecast forecast = new Forecast();
                forecast.day = jsonObject.getString("day");
                forecast.highTemp = jsonObject.getString("highTemp");
                forecast.lowTemp = jsonObject.getString("lowTemp");
                forecast.description = jsonObject.getString("description");
                forecasts.add(forecast);
            }
            weather.forecasts = forecasts;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weather;
    }
}
