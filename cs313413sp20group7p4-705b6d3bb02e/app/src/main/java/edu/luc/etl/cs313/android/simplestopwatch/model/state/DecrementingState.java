package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import android.widget.TextView;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class DecrementingState implements StopwatchState {

    public DecrementingState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;
    int b = 0; //setting this var to 0 so that it can be called to check if the alarm is at 0 to play the ring.


    @Override
    public void onSetReset() {
        sm.actionReset();
        sm.toStoppedState();
        sm.actionStop();
    }

    //@Override
    //public void onLapReset() {
    //sm.toStoppedState();
    //sm.actionUpdateView();

    @Override
    public void onTick() {
        int time = this.getTime();
        time--;
        sm.actionDec();
        sm.actionUpdateView();
        if (time == b) {
            //We want the app to sound the alarm when we hit zero. in addition it needs to stop cou
            //nting down.
            sm.toAlarmState();
        }
    }

        @Override
        public int getTime () {
            return sm.actionGetRuntime();
        }


        @Override
        public void updateView () {
            sm.updateUIRuntime();
        }

        @Override
        public int getId () {
            return R.string.DECREMENTING;
        }
    }

