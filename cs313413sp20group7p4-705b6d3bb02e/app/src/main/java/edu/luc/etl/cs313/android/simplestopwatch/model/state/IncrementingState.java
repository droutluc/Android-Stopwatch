package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;
/*
 * Here, we are making sure that the app is only able to get incremented up to the number 99. As long as the user
 * has chosen a number of seconds up until 99, then the app can be free to transition to a decrementing state. If the user hits the button
 * , then the onSetReset method is called and we make sure that the amount of time chosen is less than the max (99).
 */

class IncrementingState implements StopwatchState {

    public IncrementingState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;
    int ticks ;//# of ticks before decrementing begins
    int maximum = 99;//maximum number of times user can increase time.


    @Override
    public void onSetReset() {
        if (sm.actionGetRuntime()<maximum){
            ticks = 3;
            sm.actionInc();

        }
        else
            { //else statement to sound the alarm otherwise.
            sm.actionAlarm();
            sm.toDecrementingState();
        }
    }

    //@Override
    //public void onLapReset() {
        //sm.actionLap();
        //sm.toLapRunningState();


    @Override
    public void onTick() {
        ticks--;
        if(ticks==0) {
            sm.actionAlarm();
            sm.toDecrementingState();
        }
    }

    @Override
    public int getTime(){
      return sm.actionGetRuntime();
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.INCREMENTING;
    }
}
