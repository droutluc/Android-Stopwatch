package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;
/*
    This is the state we created to serve as the alarm state for our application.
*/

class AlarmState implements StopwatchState {

    public AlarmState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onSetReset() {
        sm.toStoppedState();
        //sm.toLapStoppedState(); dont need changed to stopped state
    }


    @Override
    public void onTick() {
        sm.actionAlarm();
        //sm.toLapRunningState(); dont need changed to calling action of the alarm to the UI over and over again
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.ALARMING;
    }

    @Override
    public int getTime(){
        return sm.actionGetRuntime();
    }
}
