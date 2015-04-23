package org.superpichu.infoscreen_android;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

/**
 * Created by chris on 4/23/15.
 */
public class CalendarAdapter extends ArrayAdapter<Event> {

    public CalendarAdapter(Context context, ArrayList<Event> events){
        super(context, R.layout.calendar_item, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.calendar_item,parent,false);
        TextView firstLine = (TextView)convertView.findViewById(R.id.firstLine);
        TextView secondLine = (TextView)convertView.findViewById(R.id.secondLine);
        Event event = getItem(position);
        firstLine.setText(event.owner + " - " + event.name);
        SimpleDateFormat format = new SimpleDateFormat("EEE dd, hh:mm a");
        format.setTimeZone(TimeZone.getDefault());
        String start = format.format(event.start);
        String end = format.format(event.end);
        secondLine.setText(start + " - " + end);
        ColorDrawable background = new ColorDrawable(Color.parseColor(event.color));
        background.setAlpha(100);
        convertView.findViewById(R.id.calendarItem).setBackgroundColor(background.getColor());
        return convertView;
    }
}
