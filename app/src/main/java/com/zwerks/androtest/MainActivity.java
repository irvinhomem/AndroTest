package com.zwerks.androtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.widget.EditText;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.google.firebase.crash.FirebaseCrash;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.zwerks.androtest.MESSAGE";
    /** Logging stuff */
    public static final String LOG_TAG = "[MY_LOGS]"+ MainActivity.class.getSimpleName();
    /*** Logging tag used for common UI lifecycle events*/
    public static final String LOG_UI = "UI";
    /*** Logging tag used for any kind of network I/O communication */
    public static final String LOG_NET = "NET";
    /*** Logging tag used for storage; local files, preferences and databases */
    public static final String LOG_DATA = "DATA";
    /*** Logging tag used for business logic and app related things not already covered by the other log tags  */
    public static final String LOG_APP = "APP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** Have Crashlytics run both on debug and in production (i.e all the time even with silly bugs while coding)
            Generally not a good idea */
        //Fabric.with(this, new Crashlytics());
        //Better [To run crashlytics when not debugging, i.e. only in the release version]:
        Fabric.with(
                this, new Crashlytics.Builder().core(
                        new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()
                ).build(), new Crashlytics()
        );


        // Set the "activity_main.xml" layout as the current View
        setContentView(R.layout.activity_main);

        //Find the view (item) with the id "toolbar"
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        // Set a "toolbar" as the ActionBar (from the Android Dev Docs)
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        // Snackbar is the small horizontal bar that pops-in at the bottom (like a toast but from the bottom)
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*
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
    */

    /**/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        /** You can choose to use either an "if...else" or a "switch ...case" */
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.action_about:
                //User chose the "About" action ... so show the "About" activity or pop up of some sort
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /* */

    /** Called when the user clicks the "Send" button */
    public void sendMessage(View view){
        //Do something in response to button click
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** Called when the user clicks the "LinearLayout button */
    public void openLinearLayout(View view){
        //Do something in response to button click
        Intent intent = new Intent(this, LinearLayoutActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        if(BuildConfig.DEBUG) {
            //Testing inbuilt logging
            Log.d(LOG_UI + LOG_TAG, "Opening Linear Layout: Inbuilt logging output");
            //Testing Crashlytics logging
            Crashlytics.log(Log.DEBUG, LOG_UI + LOG_TAG, "Opening Linear Layout: Crashlytics Log output");
            //Testing Firebase logging
            FirebaseCrash.log("Opening Linear Layout: FirebaseCrash Log Output");
            FirebaseCrash.logcat(Log.DEBUG, LOG_UI + LOG_TAG, "Opening Linear Layout: FirebaseCrash Log output");
        }
    }

    /** Called when the user clicks the "RelativeLayout button */
    public void openRelativeLayout(View view){
        //Do something in response to button click
        Intent intent = new Intent(this, RelativeLayoutActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
        if(BuildConfig.DEBUG) {
            //Testing inbuilt logging
            Log.d(LOG_UI + LOG_TAG, "Opening Relative Layout: Inbuilt logging output");
            //Testing Crashlytics logging
            Crashlytics.log(Log.DEBUG, LOG_UI + LOG_TAG, "Opening Relative Layout: Crashlytics Log output");
            //Testing Firebase logging
            FirebaseCrash.log("Opening Relative Layout: FirebaseCrash Log Output");
            FirebaseCrash.logcat(Log.DEBUG, LOG_UI + LOG_TAG, "Opening Relative Layout: FirebaseCrash Log output");
        }
    }

    /* Called when user clioks the "Force Crash" button*/
    public void forceCrash(View view) {
        //Create the exception
        RuntimeException ex =  new RuntimeException("This is the SIMULATED crash");
        Log.e(LOG_UI + LOG_TAG, "This is the SIMULATED crash: Inbuilt logging output");
        //Log the exception to Crashlytics logging and Firebase (it is automatically submitted to the online systens)
        Crashlytics.log(Log.ERROR, LOG_UI + LOG_TAG, "This is the SIMULATED crash: Crashlytics Log output");
        FirebaseCrash.logcat(Log.ERROR, LOG_UI + LOG_TAG, "This is the SIMULATED crash: FirebaseCrash Log Output");
        //FirebaseCrash.report(ex);
        // Fire the Exception
        throw ex;
    }

}
