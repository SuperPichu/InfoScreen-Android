package org.superpichu.infoscreen_android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {
    Server server;
    boolean isUpdated;
    boolean isShowing = false;
    boolean firstRun = true;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        server = new Server();
        server.twitchChannel = "twitchplayspokemon";
        Timer updateTimer = new Timer();
        updateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateServer();
            }
        },0,10000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void updateServer(){
        new Thread(serverUpdater).start();
    }

    final Runnable serverUpdater = new Runnable() {
        @Override
        public void run() {
            try {
                String old = server.twitchChannel;
                String url = getResources().getString(R.string.url);
                String auth = getResources().getString(R.string.auth);
                String buses = getResources().getString(R.string.buses);
                url = url + "?auth=" + auth + "&stpid="+buses;
                server = new getServer().execute(url).get();
                isUpdated = (!old.equals(server.twitchChannel));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        WeatherFragment weatherFragment = (WeatherFragment)getFragmentManager().findFragmentById(R.id.weather);
                        weatherFragment.updateWeather(server.weather);
                        BusFragment busFragment = (BusFragment)getFragmentManager().findFragmentById(R.id.bus);
                        busFragment.updateBuses(server.buses);
                        CalendarFragment calendarFragment = (CalendarFragment)getFragmentManager().findFragmentById(R.id.calendar);
                        calendarFragment.updateCalendar(server.events);
                        if(server.alertIsActive){
                            showDialog(server);
                        }
                        if (isUpdated || firstRun) {
                            TwitchFragment twitchFragment = (TwitchFragment) getFragmentManager().findFragmentById(R.id.twitch);
                            twitchFragment.updateTwitch(server.twitchChannel);
                            firstRun = false;
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void showDialog(Server server) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(server.alertSender+":");
        builder.setMessage(server.alertBody);
        builder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                new dismissAlert().execute();
                isShowing = false;
            }
        });
        dialog = builder.create();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        if(!isShowing) {
            isShowing = true;
            dialog.show();
            dialog.getWindow().setAttributes(lp);

        }
    }
}
