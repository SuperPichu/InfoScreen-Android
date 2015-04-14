package org.superpichu.infoscreen_android;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class BusFragment extends ListFragment {
    View view;

    public BusFragment() {
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
        view = inflater.inflate(R.layout.list, container, false);

        return view;
    }

    public void updateBuses(ArrayList<Bus> buses){
        BusAdapter adapter = new BusAdapter(getActivity(),buses);
        adapter.notifyDataSetChanged();
        setListAdapter(adapter);
    }
}
