package org.superpichu.infoscreen_android;

import java.util.Date;

/**
 * Created by chris on 4/10/15.
 */
public class Event implements Comparable<Event> {
    String name;
    String owner;
    String location;
    Date start;
    Date end;
    String color;

    @Override
    public int compareTo(Event another) {
        int x = 0;
        if(this.start.before(another.start)){
            x = -1;
        }else if(this.start.after(another.start)){
            x = 1;
        }
        return x;
    }
}
