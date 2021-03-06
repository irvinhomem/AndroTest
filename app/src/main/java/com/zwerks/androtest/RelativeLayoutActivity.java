package com.zwerks.androtest;

//import android.support.v4.app.DialogFragment;
import android.app.DialogFragment;
import android.content.Context;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Spinner;

import com.crashlytics.android.Crashlytics;

public class RelativeLayoutActivity extends AppCompatActivity {
    //private String PKG_NAME = "";
    //private String CLASS_NAME = "";
    /*LOGGING TAG WORKS*/
    public static final String LOG_TAG = "[MY_LOGS]"+ RelativeLayoutActivity.class.getSimpleName();
    /*** Logging tag used for common UI lifecycle events*/
    public static final String LOG_UI = "UI";
    /*** Logging tag used for any kind of network I/O communication */
    public static final String LOG_NET = "NET";
    /*** Logging tag used for storage; local files, preferences and databases */
    public static final String LOG_DATA = "DATA";
    /*** Logging tag used for business logic and app related things not already covered by the other log tags  */
    public static final String LOG_APP = "APP";

    /*
    public interface Constants {
        //String LOG_TAG = this.PKG_NAME + ":" + this.CLASS_NAME; //"com.zwerks.androtest.RelativeLayoutActivity";
        String LOG_TAG = RelativeLayoutActivity.class.getSimpleName();
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);

        //this.PKG_NAME = getApplicationContext().getPackageName();
        //this.CLASS_NAME = getLocalClassName();

        Spinner dateSpinner = (Spinner)findViewById(R.id.spinner_date);
        Spinner timeSpinner = (Spinner)findViewById(R.id.spinner_time);

        View.OnTouchListener Spinner_OnTouch = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if(BuildConfig.DEBUG) {
                        Log.d(LOG_UI+LOG_TAG, v.getTag().toString()); //Can't get v.getId() into a String for logging so using v.getTag
                        Crashlytics.log(Log.DEBUG,LOG_UI+LOG_TAG, v.getTag().toString());
                    }

                    if (v.getId() == R.id.spinner_date) {
                        showDatePickerDialog(v);
                    }
                    else if(v.getId() == R.id.spinner_time){
                        showTimePickerDialog(v);
                    }
                }
                return true;
            }
        };

        View.OnKeyListener Spinner_OnKey = new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
                    if (v.getId() == R.id.spinner_date) {
                        showDatePickerDialog(v);
                    }
                    else if(v.getId() == R.id.spinner_date){
                        showDatePickerDialog(v);
                    }

                    return true;
                }
                else {
                    return false;
                }
            }
        };

        dateSpinner.setOnTouchListener(Spinner_OnTouch);
        dateSpinner.setOnKeyListener(Spinner_OnKey);

        timeSpinner.setOnTouchListener(Spinner_OnTouch);
        timeSpinner.setOnKeyListener(Spinner_OnKey);


    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "Date");
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        //newFragment.show(getSupportFragmentManager(), "timePicker");
        newFragment.show(getFragmentManager(), "timePicker");
    }
}
