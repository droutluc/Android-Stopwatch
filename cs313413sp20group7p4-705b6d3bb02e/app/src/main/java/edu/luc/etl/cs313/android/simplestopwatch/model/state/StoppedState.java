package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;
/*
 * This is the state we transition to when the user chooses to stop their app (or if the app
 * has reached a time of zero). When the user taps the button in this state,
 * the onSetReset method is calld and we start the clock once again to continue operating as before.
 * We are also allowing the user to increment time if they're starting from this state.
 */

class StoppedState implements StopwatchState {

    public StoppedState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onSetReset() {
        sm.actionStart();//starts clock
        sm.toIncrementingState();//starts incrementing with each click
    }

    //@Override
    //public void onLapReset() {
        //sm.actionReset();
        //sm.toStoppedState();
    //} dont need any of this

    @Override
    public void onTick() {
        sm.actionReset();
    }

    //need to finish editing and adding methods here add getTime method
    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.STOPPED;
    }

    @Override
    public int getTime(){
        return sm.actionGetRuntime();
    }
}
