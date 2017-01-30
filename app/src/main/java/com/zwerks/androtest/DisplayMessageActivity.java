package com.zwerks.androtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        //Get the intent that started the activity, and retrieve the string "extra"
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //Programmatically create a TextView and set its size and message
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        //Get the layout where you want to put the TextView and cast it into a ViewGroup (superclass of all Layouts)
        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
    }
}
