package edu.luc.etl.cs313.android.simplestopwatch.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */
interface StopwatchSMStateView {

    // transitions
    void toAlarmState();
    void toStoppedState();
    //void toLapRunningState();
    //void toLapStoppedState(); not using laps states
    void toIncrementingState();
    void toDecrementingState();//states for incrementing and decrementing the timer.

    // actions
    void actionInit();
    void actionReset();
    void actionStart();
    void actionStop();
    //void actionLap();
    void actionInc();
    void actionUpdateView();
    int  actionGetRuntime();

    // state-dependent UI updates
    void updateUIRuntime();
    //void updateUILaptime(); Don't need this

    void actionAlarm();
    void actionDec();

}
