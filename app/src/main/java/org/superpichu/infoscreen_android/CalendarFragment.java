package org.superpichu.infoscreen_android;

import android.app.ListFragment;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class CalendarFragment extends ListFragment {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CalendarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void updateCalendar(ArrayList<Event> events){
        CalendarAdapter adapter = new CalendarAdapter(getActivity(),events);
        adapter.notifyDataSetChanged();
        setListAdapter(adapter);
    }

}
