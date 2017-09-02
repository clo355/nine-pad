package com.clww.nine_pad;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.media.SoundPool;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    //hmmmm
    private SoundPool sp;
    private int sp1Id, sp2Id, sp3Id, sp6Id;
    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 0;
    private final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 2;

    @TargetApi(21)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_main);

        //Permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
        }

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

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_GAME)
                .build();

        sp = new SoundPool.Builder()
                .setMaxStreams(9)
                .setAudioAttributes(audioAttributes)
                .build();

        sp1Id = sp.load(this, R.raw.soundbutton1, 1);
        sp2Id = sp.load(this, R.raw.soundbutton2, 1);
        sp3Id = sp.load(this, R.raw.soundbutton3, 1);
        sp6Id = sp.load(this, R.raw.soundbutton6, 1);

        button1.setOnTouchListener(this);
        button2.setOnTouchListener(this);
        button3.setOnTouchListener(this);
        button4.setOnTouchListener(this);
        button5.setOnTouchListener(this);
        button6.setOnTouchListener(this);
        button7.setOnTouchListener(this);
        button8.setOnTouchListener(this);
        button9.setOnTouchListener(this);
        buttonSettings.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event){ //for "implements View.OnTouchListener"
        switch(v.getId()){
            case R.id.button1:
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    sp.play(sp1Id, 1, 1, 1, 0, 1);
                    return true;
                } else{
                    return false;
                }
            case R.id.button2:
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    sp.play(sp2Id, 1, 1, 1, 0, 1);
                    return true;
                } else{
                    return false;
                }
            case R.id.button3:
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    sp.play(sp3Id, 1, 1, 1, 0, 1);
                    return true;
                } else{
                    return false;
                }
            case R.id.button4:
                return true;
            case R.id.button5:
                return true;
            case R.id.button6:
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    sp.play(sp6Id, 1, 1, 1, 0, 1);
                    return true;
                } else{
                    return false;
                }
            case R.id.button7:
                return true;
            case R.id.button8:
                return true;
            case R.id.button9:
                return true;
            case R.id.buttonSettings:
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    return true;
                } else{
                    return false;
                }
            default:
                return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do task.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
            case MY_PERMISSIONS_REQUEST_RECORD_AUDIO: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                return;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setVolumeControlStream(AudioManager.STREAM_NOTIFICATION);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        setVolumeControlStream(AudioManager.STREAM_NOTIFICATION);
    }
}
