package com.clww.nine_pad;

import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //app will crash if widgets are initialized here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_main);

        final Button button1 = (Button) findViewById(R.id.button1);
        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button3 = (Button) findViewById(R.id.button3);
        final Button button4 = (Button) findViewById(R.id.button4);
        final Button button5 = (Button) findViewById(R.id.button5);
        final Button button6 = (Button) findViewById(R.id.button6);
        final Button button7 = (Button) findViewById(R.id.button7);
        final Button button8 = (Button) findViewById(R.id.button8);
        final Button button9 = (Button) findViewById(R.id.button9);
        final Button buttonSettings = (Button) findViewById(R.id.buttonSettings);
        final Switch switchRecord = (Switch) findViewById(R.id.switchRecord);
        final Switch switchVolume = (Switch) findViewById(R.id.switchVolume);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){ //from "implements View.OnClickListener"
        switch(v.getId()){
            case R.id.button1:
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
            case R.id.button4:
                break;
            case R.id.button5:
                break;
            case R.id.button6:
                break;
            case R.id.button7:
                break;
            case R.id.button8:
                break;
            case R.id.button9:
                break;
            case R.id.buttonSettings:
                Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
