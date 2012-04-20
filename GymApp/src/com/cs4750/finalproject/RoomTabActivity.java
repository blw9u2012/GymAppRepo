package com.cs4750.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RoomTabActivity extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is the Rooms tab");
        setContentView(textview);
    }
}
