package org.superpichu.infoscreen_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.github.curioustechizen.ago.RelativeTimeTextView;

import java.util.ArrayList;

import me.grantland.widget.AutofitTextView;

/**
 * Created by chris on 4/14/15.
 */
public class BusAdapter extends ArrayAdapter<Bus>{

    public BusAdapter(Context context,ArrayList<Bus> buses){
        super(context, R.layout.bus_row,buses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bus_row,parent,false);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);
            viewHolder.route = (AutofitTextView)convertView.findViewById(R.id.route);
            viewHolder.time = (RelativeTimeTextView)convertView.findViewById(R.id.time);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Bus bus = getItem(position);
        viewHolder.route.setText(bus.route);
        viewHolder.time.setReferenceTime(bus.time.getTime());
        notifyDataSetChanged();
        return convertView;
    }

    private static class ViewHolder{
        AutofitTextView route;
        RelativeTimeTextView time;
    }
}
