package org.superpichu.infoscreen_android;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import junit.framework.TestCase;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class WeatherFragment extends Fragment {
    View view;
    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_weather, container, false);
        return view;
    }
    public void updateWeather(Weather weather){
        TextView highTemp = (TextView)view.findViewById(R.id.highTemp);
        TextView lowTemp = (TextView)view.findViewById(R.id.lowTemp);
        TextView temp = (TextView)view.findViewById(R.id.temp);
        TextView description = (TextView)view.findViewById(R.id.description);
        TextView day = (TextView)view.findViewById(R.id.day);
        highTemp.setText(weather.highTemp);
        lowTemp.setText(weather.lowTemp);
        temp.setText(weather.currentTemp);
        description.setText(weather.description);
        day.setText(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(Calendar.getInstance().getTime().getTime()));
        int i =0;
        while(i<weather.forecasts.size()){
            TextView high;
            TextView low;
            TextView desc;
            switch (i){
                case 0:
                    high = (TextView)view.findViewById(R.id.high1);
                    low = (TextView)view.findViewById(R.id.low1);
                    day = (TextView)view.findViewById(R.id.day1);
                    desc = (TextView)view.findViewById(R.id.desc1);
                    high.setText(weather.forecasts.get(i).highTemp);
                    low.setText(weather.forecasts.get(i).lowTemp);
                    day.setText(parseDate(weather.forecasts.get(i).day));
                    desc.setText(weather.forecasts.get(i).description);
                    break;
                case 1:
                    high = (TextView)view.findViewById(R.id.high2);
                    low = (TextView)view.findViewById(R.id.low2);
                    day = (TextView)view.findViewById(R.id.day2);
                    desc = (TextView)view.findViewById(R.id.desc2);
                    high.setText(weather.forecasts.get(i).highTemp);
                    low.setText(weather.forecasts.get(i).lowTemp);
                    day.setText(parseDate(weather.forecasts.get(i).day));
                    desc.setText(weather.forecasts.get(i).description);
                    break;
                case 2:
                    high = (TextView)view.findViewById(R.id.high3);
                    low = (TextView)view.findViewById(R.id.low3);
                    day = (TextView)view.findViewById(R.id.day3);
                    desc = (TextView)view.findViewById(R.id.desc3);
                    high.setText(weather.forecasts.get(i).highTemp);
                    low.setText(weather.forecasts.get(i).lowTemp);
                    day.setText(parseDate(weather.forecasts.get(i).day));
                    desc.setText(weather.forecasts.get(i).description);
                    break;
                case 3:
                    high = (TextView)view.findViewById(R.id.high4);
                    low = (TextView)view.findViewById(R.id.low4);
                    day = (TextView)view.findViewById(R.id.day4);
                    desc = (TextView)view.findViewById(R.id.desc4);
                    high.setText(weather.forecasts.get(i).highTemp);
                    low.setText(weather.forecasts.get(i).lowTemp);
                    day.setText(parseDate(weather.forecasts.get(i).day));
                    desc.setText(weather.forecasts.get(i).description);
                    break;
            }
            i++;
        }
    }

    private String parseDate(String day) {
        SimpleDateFormat format = new SimpleDateFormat("EEE");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date date = format.parse(day);
            format = new SimpleDateFormat("EEEE");
            day = format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }
}
