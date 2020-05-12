package edu.luc.etl.cs313.android.simplestopwatch.android;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import edu.luc.etl.cs313.android.simplestopwatch.R;
import edu.luc.etl.cs313.android.simplestopwatch.common.Constants;
import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchUIUpdateListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.ConcreteStopwatchModelFacade;
import edu.luc.etl.cs313.android.simplestopwatch.model.StopwatchModelFacade;

/**
 * A thin adapter component for the stopwatch.
 *
 * @author laufer
 */
public class StopwatchAdapter extends Activity implements StopwatchUIUpdateListener {

    private static String TAG = "stopwatch-android-activity";

    /**
     * The state-based dynamic model.
     */
    private StopwatchModelFacade model;

    protected void setModel(final StopwatchModelFacade model) {
        this.model = model;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // inject dependency on view so this adapter receives UI events
        setContentView(R.layout.activity_main);
        // inject dependency on model into this so model receives UI events
        this.setModel(new ConcreteStopwatchModelFacade());
        // inject dependency on this into model to register for UI updates
        model.setUIUpdateListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        model.onStart();
    }

    // TODO remaining lifecycle methods

    public void playDefaultNotification() {
        final Uri defaultringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final Context context = getApplicationContext();
        final MediaPlayer mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(context, defaultringtoneUri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
            mediaPlayer.start();
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Updates the seconds and minutes in the UI.
     * @param time
     */
    public void updateTime(final int time) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final TextView tvS = (TextView) findViewById(R.id.seconds);
                final int seconds = time;
                tvS.setText(Integer.toString(seconds / 10) + Integer.toString(seconds % 10));
            }
        });
            //final TextView tvS = (TextView) findViewById(R.id.seconds);
            //No longer needed since we only need seconds... final TextView tvM = (TextView) findViewById(R.id.minutes);
            //final int seconds = time % Constants.SEC_PER_MIN;
            //final int minutes = time / Constants.SEC_PER_MIN;
            //tvS.setText(Integer.toString(seconds / 10) + Integer.toString(seconds % 10));
            //tvM.setText(Integer.toString(minutes / 10) + Integer.toString(minutes % 10));

    }
    public void alarmStart(){
        playDefaultNotification();
    }

    /**
     * Updates the state name in the UI.
     * @param stateId
     */
    public void updateState(final int stateId) {
        // UI adapter responsibility to schedule incoming events on UI thread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final TextView stateName = (TextView) findViewById(R.id.stateName);
                stateName.setText(getString(stateId));
            }
        });
    }

    // forward event listener methods to the model
    public void onStartStop(final View view) {
        model.onSetReset();
    }

    //public void onLapReset(final View view)  {
        //model.onLapReset(); don't need this method}

}