package com.beatmaker.Activities;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.beatmaker.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BeatMakingActivity2 extends AppCompatActivity implements View.OnClickListener {
    private SoundPool pool;
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    public static String mFileName = null;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;
    private ArrayList<Integer> sounds;
    private boolean mediaLoaded;
    private TextView tv_recording;
    private TextView tv_recordingstatus;
    private TextView tv_stop;
    private TextView tv_play;
    private long startTime = 0L;

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    private boolean clicklistner = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beat_making);
        sounds = new ArrayList<>();
        mediaLoaded = false;
        init();
        listeners();


        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        pool = new SoundPool.Builder()
                .setMaxStreams(9)
                .setAudioAttributes(audioAttributes)
                .build();
        pool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                System.out.println("Sound " + sampleId + " loading operation returned " + status);
                mediaLoaded = true;
            }
        });

        // Caricamento delle tracce audio (si trovano nella cartella "res/raw"
        sounds.add(pool.load(this, R.raw.sound1, 1));
        sounds.add(pool.load(this, R.raw.sound2, 1));
        sounds.add(pool.load(this, R.raw.sound3, 1));
        sounds.add(pool.load(this, R.raw.sound4, 1));
        sounds.add(pool.load(this, R.raw.sound5, 1));
        sounds.add(pool.load(this, R.raw.sound6, 1));
        sounds.add(pool.load(this, R.raw.sound7, 1));
        sounds.add(pool.load(this, R.raw.sound8, 1));
        sounds.add(pool.load(this, R.raw.sound9, 1));
    }

    public void init() {
        tv_recording = findViewById(R.id.tv_recording);
        tv_recordingstatus = findViewById(R.id.tv_recordingstatus);
        tv_stop = findViewById(R.id.tv_stop);
        tv_play = findViewById(R.id.tv_play);

    }

    public void listeners() {
        tv_recording.setOnClickListener(this);
        tv_stop.setOnClickListener(this);
        tv_play.setOnClickListener(this);

    }

    public void onPadButtonClick(View v) {
        Button button = findViewById(v.getId());
        String tag = v.getTag().toString();

        if (mediaLoaded) {
            // Acquisizione del valore del volume per impostarlo nelle tracce audio
            AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
            float volume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) / (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

            // Selezione della traccia da riprodurre in base al tag del tasto premuto
            switch (tag) {
                case "pad1":
                    pool.play(sounds.get(0), volume, volume, 1, 0, 1f);
                    break;
                case "pad2":
                    pool.play(sounds.get(1), volume, volume, 1, 0, 1f);
                    break;
                case "pad3":
                    pool.play(sounds.get(2), volume, volume, 1, 0, 1f);
                    break;
                case "pad4":
                    pool.play(sounds.get(3), volume, volume, 1, 0, 1f);
                    break;
                case "pad5":
                    pool.play(sounds.get(4), volume, volume, 1, 0, 1f);
                    break;
                case "pad6":
                    pool.play(sounds.get(5), volume, volume, 1, 0, 1f);
                    break;
                case "pad7":
                    pool.play(sounds.get(6), volume, volume, 1, 0, 1f);
                    break;
                case "pad8":
                    pool.play(sounds.get(7), volume, volume, 1, 0, 1f);
                    break;
                case "pad9":
                    pool.play(sounds.get(8), volume, volume, 1, 0, 1f);
                    break;
            }
        }
    }

    public void onPackSelectionClick(View v) {
        Button button = findViewById(v.getId());
        String tag = v.getTag().toString();

        sounds.clear();

        switch (tag) {
            case "pack1":
                sounds.add(pool.load(this, R.raw.sound1, 1));
                sounds.add(pool.load(this, R.raw.sound2, 1));
                sounds.add(pool.load(this, R.raw.sound3, 1));
                sounds.add(pool.load(this, R.raw.sound4, 1));
                sounds.add(pool.load(this, R.raw.sound5, 1));
                sounds.add(pool.load(this, R.raw.sound6, 1));
                sounds.add(pool.load(this, R.raw.sound7, 1));
                sounds.add(pool.load(this, R.raw.sound8, 1));
                sounds.add(pool.load(this, R.raw.sound9, 1));
                break;
            case "pack2":
                sounds.add(pool.load(this, R.raw.sound10, 1));
                sounds.add(pool.load(this, R.raw.sound11, 1));
                sounds.add(pool.load(this, R.raw.sound12, 1));
                sounds.add(pool.load(this, R.raw.sound13, 1));
                sounds.add(pool.load(this, R.raw.sound14, 1));
                sounds.add(pool.load(this, R.raw.sound15, 1));
                sounds.add(pool.load(this, R.raw.sound16, 1));
                sounds.add(pool.load(this, R.raw.sound17, 1));
                sounds.add(pool.load(this, R.raw.sound18, 1));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pool.release();
        pool = null;
    }


    @Override
    public void onClick(View view) {

        if (view == tv_recording) {
            if (clicklistner == false) {
                startRecording();
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
                Toast.makeText(this, "Recording Started", Toast.LENGTH_SHORT).show();
                clicklistner = true;
            } else {
                pauseRecording();
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
                Toast.makeText(this, "Recording Stopped", Toast.LENGTH_SHORT).show();
                clicklistner = false;

            }


//            tv_recordingstatus.setVisibility(View.VISIBLE);
        } else if (view == tv_stop) {
            pauseRecording();
            timeSwapBuff += timeInMilliseconds;
            customHandler.removeCallbacks(updateTimerThread);

        } else if (view == tv_play) {
            playAudio();
        }
    }


    private void startRecording() {
        // check permission method is used to check
        // that the user has granted permission
        // to record nd store the audio.
        if (CheckPermissions()) {
            Random rand = new Random();

            String a = null;


            int rand_int1 = rand.nextInt(1000);
            Toast.makeText(this, rand_int1 + "", Toast.LENGTH_LONG).show();
            String sep = File.separator; // Use this instead of hardcoding the "/"
            String newFolder = "FolderName";
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File myNewFolder = new File(extStorageDirectory + sep + newFolder);
            myNewFolder.mkdir();
//            mediaFile = Environment.getExternalStorageDirectory().toString()
//                    + sep + newFolder + sep + "myRecordings.mp3";
            mFileName = Environment.getExternalStorageDirectory().toString() + sep + newFolder + sep + "myrecording" + rand_int1 + "" + ".mp3";
//            mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
//            mFileName += "/AudioRecording.3gp";
            Log.d("chhhfhfhf", mFileName.toString());


            // below method is used to initialize
            // the media recorder class
            mRecorder = new MediaRecorder();

            // below method is used to set the audio
            // source which we are using a mic.
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

            // below method is used to set
            // the output format of the audio.
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

            // below method is used to set the
            // audio encoder for our recorded audio.
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            // below method is used to set the
            // output file location for our recorded audio
            mRecorder.setOutputFile(mFileName);
            try {
                // below method will prepare
                // our audio recorder class
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e("TAG", "prepare() failed");
            }
            // start method will start
            // the audio recording.
            mRecorder.start();
        } else {
            // if audio recording permissions are
            // not granted by user below method will
            // ask for runtime permission for mic and storage.
            RequestPermissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // this method is called when user will
        // grant the permission for audio recording.
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_AUDIO_PERMISSION_CODE:
                if (grantResults.length > 0) {
                    boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (permissionToRecord && permissionToStore) {
                        Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean CheckPermissions() {
        // this method is used to check permission
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void RequestPermissions() {
        // this method is used to request the
        // permission for audio recording and storage.
        ActivityCompat.requestPermissions(BeatMakingActivity2.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }


    public void playAudio() {
        mPlayer = new MediaPlayer();
        try {
            if (mFileName != null) {
                // below method is used to set the
                // data source which will be our file name
                mPlayer.setDataSource(mFileName);
                Toast.makeText(this, mFileName.toString(), Toast.LENGTH_LONG).show();
                Log.d("checkforval", mFileName.toString());
                // below method will prepare our media player
                mPlayer.prepare();
                // below method will start our media player.
                mPlayer.start();
            }

        } catch (IOException e) {
            Log.e("TAG", "prepare() failed");
        }
    }

    public void pauseRecording() {

        if (null != mRecorder) {
            try {
                mRecorder.prepare();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            mRecorder.stop();
            mRecorder.reset();
            mRecorder.release();

            mRecorder = null;
        }
    }

    public void pausePlaying() {
        // this method will release the media player
        // class and pause the playing of our recorded audio.
        mPlayer.release();
        mPlayer = null;

    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            tv_recording.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }

    };
}