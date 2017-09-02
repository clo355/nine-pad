package com.clww.nine_pad;

import android.media.SoundPool;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.annotation.TargetApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import java.util.Random;
import java.io.IOException;
import android.view.MotionEvent;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    //app will crash if widgets are initialized here
    private SoundPool sp;
    private int sp1Id, sp2Id, sp3Id, sp6Id;
    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 0;
    private final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 2;
    Button buttonStart, buttonStop, buttonPlayLastRecordAudio, buttonStopPlayingRecording;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder;
    Random random;
    String RandomAudioFileName = "sounds";
    public static final int RequestPermissionCode = 1;
    MediaPlayer mediaPlayer;

    @TargetApi(21)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_main);

        buttonStart = (Button) findViewById(R.id.buttonstart);
        buttonStop = (Button) findViewById(R.id.buttonstop);
        buttonPlayLastRecordAudio = (Button) findViewById(R.id.buttonplaylast);
        buttonStopPlayingRecording = (Button) findViewById(R.id.buttonstopplay);

        buttonStop.setEnabled(false);
        buttonPlayLastRecordAudio.setEnabled(false);
        buttonStopPlayingRecording.setEnabled(false);

        random = new Random();


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

        buttonPlayLastRecordAudio.setOnClickListener(this);
        buttonStopPlayingRecording.setOnClickListener(this);
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
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

    public void MediaRecorderReady() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }
    public String CreateRandomAudioFileName(int string){
        StringBuilder stringBuilder = new StringBuilder( string );
        int i = 0 ;
        while(i < string ) {
            stringBuilder.append(RandomAudioFileName.
                    charAt(random.nextInt(RandomAudioFileName.length())));

            i++ ;
        }
        return stringBuilder.toString();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(MainActivity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onClick(View v) throws IllegalArgumentException,
            SecurityException, IllegalStateException { //from "implements View.OnClickListener"
        switch (v.getId()) {
            case R.id.buttonstart:
                if (checkPermission()) {
                    AudioSavePathInDevice =
                            Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                                    CreateRandomAudioFileName(5) + "AudioRecording.3gp";

                    MediaRecorderReady();

                    try {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    buttonStart.setEnabled(false);
                    buttonStop.setEnabled(true);

                    Toast.makeText(MainActivity.this, "Recording started",
                            Toast.LENGTH_LONG).show();
                } else {
                    requestPermission();
                }
                break;
            case R.id.buttonstop:
                mediaRecorder.stop();
                buttonStop.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);

                Toast.makeText(MainActivity.this, "Recording Completed",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.buttonplaylast:
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(false);
                buttonStopPlayingRecording.setEnabled(true);

                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(AudioSavePathInDevice);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.start();
                Toast.makeText(MainActivity.this, "Recording Playing",
                        Toast.LENGTH_LONG).show();
                break;
            case R.id.buttonstopplay:
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);

                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    MediaRecorderReady();
                }
                break;
        }

    }
    @Override
    public boolean onTouch(View v, MotionEvent event)  { //from "implements View.OnClickListener"
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
                if(event.getAction() == MotionEvent.ACTION_UP){
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
    public void onBackPressed() {
        super.onBackPressed();
        setVolumeControlStream(AudioManager.STREAM_NOTIFICATION);
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        setVolumeControlStream(AudioManager.STREAM_NOTIFICATION);
    }
}
