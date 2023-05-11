package com.beatmaker.Activities.BeatMakingActivity;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beatmaker.Activities.BeatResult.BeatResultActivity;
import com.beatmaker.R;
import com.beatmaker.Utils.CSPrefernce;
import com.beatmaker.Utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class BeatMakingActivity extends AppCompatActivity implements View.OnClickListener {
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
    private long startTime123 = 0;
    private double startTime = 0;
//    private double startTimeL = 0;

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    private boolean clicklistner = false;

    RelativeLayout relative_pad1;
    RelativeLayout relative_pad2;
    RelativeLayout relative_pad3;

    RelativeLayout relativePad11;
    RelativeLayout relativePad1;
    RelativeLayout relativePad2;
    RelativeLayout relativePad3;
    RelativeLayout relativePad4;
    RelativeLayout relativePad5;

    ////////
    MediaPlayer mediaPlayer = new MediaPlayer();
    SeekBar seekBar;
    ImageView img_restartSong;
    boolean wasPlaying = false;
    private TextView txt_duration;
    private TextView txt_startduration;
    private double finalTime = 0;

    private Handler myHandler = new Handler();
    public static int oneTimeOnly = 0;


    private Handler mHandler;
    private Runnable mRunnable;
    private ProgressBar progress_bar1;
    private ProgressBar progress_bar2;
    private ProgressBar progress_bar3;
    private ProgressBar progress_bar4;
    private ProgressBar progress_bar5;
    private int progress = 0;
    private int durationvalue;


    private int i1 = 0;
    private int i2 = 0;
    private int i3 = 0;
    private int i4 = 0;
    private int i5 = 0;

    private Handler hdlr = new Handler();

    private ImageView img_cancel;
    private int due;


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

        mHandler = new Handler();

//        mediaPlayer = MediaPlayer.create(this, R.raw.justin);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setClickable(false);

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
        txt_duration = findViewById(R.id.txt_duration);
        txt_startduration = findViewById(R.id.txt_startduration);
        img_restartSong = findViewById(R.id.img_restartSong);
        relative_pad1 = findViewById(R.id.relative_pad1);
        relative_pad2 = findViewById(R.id.relative_pad2);
        relative_pad3 = findViewById(R.id.relative_pad3);

        relativePad1 = findViewById(R.id.relativePad1);
        relativePad11 = findViewById(R.id.relativePad11);
        relativePad2 = findViewById(R.id.relativePad2);
        relativePad3 = findViewById(R.id.relativePad3);
        relativePad4 = findViewById(R.id.relativePad4);
        relativePad5 = findViewById(R.id.relativePad5);


        progress_bar1 = findViewById(R.id.progress_bar1);
        progress_bar2 = findViewById(R.id.progress_bar2);
        progress_bar3 = findViewById(R.id.progress_bar3);
        progress_bar4 = findViewById(R.id.progress_bar4);
        progress_bar5 = findViewById(R.id.progress_bar5);


        img_cancel = findViewById(R.id.img_cancel);

    }

    public void listeners() {

        tv_recording.setOnClickListener(this);
        tv_stop.setOnClickListener(this);
        tv_play.setOnClickListener(this);
        img_restartSong.setOnClickListener(this);

        relative_pad1.setOnClickListener(this);
        relative_pad2.setOnClickListener(this);
        relative_pad3.setOnClickListener(this);

        relativePad11.setOnClickListener(this);
        relativePad1.setOnClickListener(this);
        relativePad2.setOnClickListener(this);
        relativePad3.setOnClickListener(this);
        relativePad4.setOnClickListener(this);
        relativePad5.setOnClickListener(this);

        img_cancel.setOnClickListener(this);


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

//                    mediaPlayer.start();
//                    finalTime = mediaPlayer.getDuration();
//                    startTimeL = mediaPlayer.getCurrentPosition();
//
//                    if (oneTimeOnly == 0) {
//                        seekBar.setMax((int) finalTime);
//                        oneTimeOnly = 1;
//                    }
//
//                    txt_duration.setText(String.format("%d:%d",
//                            TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
//                            TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
//                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
//                                            finalTime)))
//                    );
//
//                    seekBar.setProgress((int) startTimeL);
//                    myHandler.postDelayed(UpdateSongTime, 100);


                 /*   Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        public void run() {
                            // yourMethod();
                            startTimeL = mediaPlayer.getCurrentPosition();

//                            if (startTimeL == 5) {

                            relative_pad1.setBackgroundColor(getResources().getColor(R.color.purple_700));
//                            }

                        }
                    }, 3000);

                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        public void run() {
                            // yourMethod();
                            startTimeL = mediaPlayer.getCurrentPosition();
//                            if (startTimeL == 10) {

                            relative_pad2.setBackgroundColor(getResources().getColor(R.color.colorBlueBase));
//                            }

                        }
                    }, 3000);

                    Handler handler3 = new Handler();
                    handler3.postDelayed(new Runnable() {
                        public void run() {
                            // yourMethod();
                            startTimeL = mediaPlayer.getCurrentPosition();
//                            if (startTimeL == 15) {

                            relative_pad3.setBackgroundColor(getResources().getColor(R.color.colorPinkBase));
//                            }

                        }
                    }, 3000);*/

//                    mediaPlayer = MediaPlayer.create(this, R.raw.sound2);


                  /*  final int Duration=mediaPlayer.getDuration();
                    txt_duration.setText("0:" + Duration);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            seekBar.setMax(mediaPlayer.getDuration());
                            updateSeekBar();
                            mediaPlayer.start();
                        }
                    });*/
//                    pool.play(sounds.get(0), volume, volume, 1, 0, 1f);
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
        } else if (view == tv_stop) {
            pauseRecording();
            timeSwapBuff += timeInMilliseconds;
            customHandler.removeCallbacks(updateTimerThread);

        } else if (view == img_cancel) {
            finish();
        } else if (view == tv_play) {
            playAudio();
        } else if (view == img_restartSong) {
            if (mediaPlayer.isPlaying()) {//check if a song is already playing
                mediaPlayer.stop();
                try {
                    mediaPlayer.prepare();//get the mediaplayer reeady for playback
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                mediaPlayer.start();
            }
        } else if (view == relativePad1) {


            relativePad11.setVisibility(View.VISIBLE);
            relativePad1.setVisibility(View.GONE);
            relativePad2.setVisibility(View.GONE);
            relativePad3.setVisibility(View.GONE);
            relativePad4.setVisibility(View.GONE);
            relativePad5.setVisibility(View.GONE);

            i1 = progress_bar1.getProgress();
            new Thread(new Runnable() {
                public void run() {
                    while (i1 < 100) {
                        i1 += 1;
                        // Update the progress bar and display the current value in text view
                        hdlr.post(new Runnable() {
                            public void run() {
                                progress_bar1.setProgress(i1);
                            }
                        });
                        try {
                            // Sleep for 100 milliseconds to show the progress slowly.
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();


            relativePad11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    relativePad11.setVisibility(View.GONE);
                    relativePad1.setVisibility(View.GONE);
                    relativePad2.setVisibility(View.VISIBLE);
                    relativePad3.setVisibility(View.GONE);
                    relativePad4.setVisibility(View.GONE);
                    relativePad5.setVisibility(View.GONE);

                    i2 = progress_bar2.getProgress();
                    new Thread(new Runnable() {
                        public void run() {
                            while (i2 < 100) {
                                i2 += 1;
                                // Update the progress bar and display the current value in text view
                                hdlr.post(new Runnable() {
                                    public void run() {
                                        progress_bar2.setProgress(i2);
                                    }
                                });
                                try {
                                    // Sleep for 100 milliseconds to show the progress slowly.
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();


                }
            });

            stopPlaying();

            // Initialize media player
            mPlayer = MediaPlayer.create(this, R.raw.justin);

            // Start the media player
            mPlayer.start();
            Toast.makeText(this, "Media Player is playing.", Toast.LENGTH_SHORT).show();

            // Get the current audio stats
            getAudioStats();
            // Initialize the seek bar
            initializeSeekBar();


//            setProgressValue(progress);

//            if (pass == 5) {
//
//                setProgressValue(progress);
//
//                relativePad11.setVisibility(View.GONE);
//                relativePad1.setVisibility(View.GONE);
//                relativePad2.setVisibility(View.VISIBLE);
//                relativePad3.setVisibility(View.GONE);
//                relativePad4.setVisibility(View.GONE);
//                relativePad5.setVisibility(View.GONE);
//
//            }



/*
            if (txt_startduration.getText().toString().equalsIgnoreCase("4 seconds")) {

                relativePad1.setVisibility(View.GONE);
                relativePad2.setVisibility(View.VISIBLE);
                relativePad3.setVisibility(View.GONE);
                relativePad4.setVisibility(View.GONE);
                relativePad5.setVisibility(View.GONE);


            }

            Log.e("startTimeduration", txt_startduration.getText().toString());*/
//                    relative_pad1.setBackgroundColor(getResources().getColor(R.color.purple_700));

//                }
//            }, 3000);

//            Handler handler2 = new Handler();
//            handler2.postDelayed(new Runnable() {
//                public void run() {
//                    // yourMethod();
//                    startTime = mediaPlayer.getCurrentPosition();

  /*          if (txt_startduration.getText().toString().equalsIgnoreCase("8 seconds")) {

                relativePad1.setVisibility(View.GONE);
                relativePad2.setVisibility(View.GONE);
                relativePad3.setVisibility(View.VISIBLE);
                relativePad4.setVisibility(View.GONE);
                relativePad5.setVisibility(View.GONE);

            }

//                    Log.e("startTimeduration2", txt_startduration.getText().toString());
////                    relative_pad2.setBackgroundColor(getResources().getColor(R.color.colorBlueBase));
//
//                }
//            }, 3000);

//            Handler handler3 = new Handler();
//            handler3.postDelayed(new Runnable() {
//                public void run() {
//                    // yourMethod();
//                    startTime = mediaPlayer.getCurrentPosition();

            if (txt_startduration.getText().toString().equalsIgnoreCase("12 seconds")) {

                relativePad1.setVisibility(View.GONE);
                relativePad2.setVisibility(View.GONE);
                relativePad3.setVisibility(View.GONE);
                relativePad4.setVisibility(View.VISIBLE);
                relativePad5.setVisibility(View.GONE);

            }
//                    Log.e("startTimeduration3", txt_startduration.getText().toString());
//                    relative_pad3.setBackgroundColor(getResources().getColor(R.color.colorPinkBase));

//                }
//            }, 3000);

            if (txt_startduration.getText().toString().equalsIgnoreCase("16 seconds")) {

                relativePad1.setVisibility(View.GONE);
                relativePad2.setVisibility(View.GONE);
                relativePad3.setVisibility(View.GONE);
                relativePad4.setVisibility(View.GONE);
                relativePad5.setVisibility(View.VISIBLE);

            }

            if (txt_startduration.getText().toString().equalsIgnoreCase("20 seconds")) {

                relativePad1.setVisibility(View.GONE);
                relativePad2.setVisibility(View.GONE);
                relativePad3.setVisibility(View.GONE);
                relativePad4.setVisibility(View.GONE);
                relativePad5.setVisibility(View.VISIBLE);

            }*/

        } else if (view == relativePad2) {

//            setProgressValue(progress);

            relativePad11.setVisibility(View.GONE);
            relativePad1.setVisibility(View.GONE);
            relativePad2.setVisibility(View.GONE);
            relativePad3.setVisibility(View.VISIBLE);
            relativePad4.setVisibility(View.GONE);
            relativePad5.setVisibility(View.GONE);

            i3 = progress_bar3.getProgress();
            new Thread(new Runnable() {
                public void run() {
                    while (i3 < 100) {
                        i3 += 1;
                        // Update the progress bar and display the current value in text view
                        hdlr.post(new Runnable() {
                            public void run() {
                                progress_bar3.setProgress(i3);
                            }
                        });
                        try {
                            // Sleep for 100 milliseconds to show the progress slowly.
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        } else if (view == relativePad3) {

//            setProgressValue(progress);

            relativePad11.setVisibility(View.GONE);
            relativePad1.setVisibility(View.GONE);
            relativePad2.setVisibility(View.GONE);
            relativePad3.setVisibility(View.GONE);
            relativePad4.setVisibility(View.VISIBLE);
            relativePad5.setVisibility(View.GONE);

            i4 = progress_bar4.getProgress();
            new Thread(new Runnable() {
                public void run() {
                    while (i4 < 100) {
                        i4 += 1;
                        // Update the progress bar and display the current value in text view
                        hdlr.post(new Runnable() {
                            public void run() {
                                progress_bar4.setProgress(i4);
                            }
                        });
                        try {
                            // Sleep for 100 milliseconds to show the progress slowly.
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();


        } else if (view == relativePad4) {

//            setProgressValue(progress);

            relativePad11.setVisibility(View.GONE);
            relativePad1.setVisibility(View.GONE);
            relativePad2.setVisibility(View.GONE);
            relativePad3.setVisibility(View.GONE);
            relativePad4.setVisibility(View.GONE);
            relativePad5.setVisibility(View.VISIBLE);

            i5 = progress_bar5.getProgress();
            new Thread(new Runnable() {
                public void run() {
                    while (i5 < 100) {
                        i5 += 1;
                        // Update the progress bar and display the current value in text view
                        hdlr.post(new Runnable() {
                            public void run() {
                                progress_bar5.setProgress(i5);
                            }
                        });
                        try {
                            // Sleep for 100 milliseconds to show the progress slowly.
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        } else if (view == relativePad5) {

            Intent intent = new Intent(this, BeatResultActivity.class);
            startActivity(intent);

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
        ActivityCompat.requestPermissions(BeatMakingActivity.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
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

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime123;

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

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            txt_startduration.setText(String.format("%d, %d",
                    TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) startTime)))
            );
            seekBar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };


//    @Override
//    public boolean isDestroyed() {
//        if (mediaPlayer != null) {
//            mediaPlayer.stop();
//            mediaPlayer = null;
//        }
//        return super.isDestroyed();
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
    }

    protected void stopPlaying() {
        // If media player is not null then try to stop it
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
            Toast.makeText(this, "Stop playing.", Toast.LENGTH_SHORT).show();
            if (mHandler != null) {
                mHandler.removeCallbacks(mRunnable);
            }
        }
    }

    protected void getAudioStats() {
        int duration = mPlayer.getDuration() / 1000; // In milliseconds
        int due = (mPlayer.getDuration() - mPlayer.getCurrentPosition()) / 1000;
        int pass = duration - due;

        this.durationvalue = pass;

        txt_startduration.setText("" + pass + " seconds");
        txt_startduration.setText("" + duration + " seconds");
        txt_duration.setText("" + due + " seconds");


//        if (due == 0){
//            Intent intent = new Intent(this, BeatResultActivity.class);
//            startActivity(intent);
//        }


        ////////////////////////////

//        Handler handler1 = new Handler();
//        handler1.postDelayed(new Runnable() {
//            public void run() {
        // yourMethod();
        startTime = mediaPlayer.getCurrentPosition();
        finalTime = mediaPlayer.getDuration();


    /*    if (pass == 1) {

            setProgressValue(progress);

            relativePad1.setVisibility(View.GONE);
            relativePad11.setVisibility(View.VISIBLE);
            relativePad2.setVisibility(View.GONE);
            relativePad3.setVisibility(View.GONE);
            relativePad4.setVisibility(View.GONE);
            relativePad5.setVisibility(View.GONE);

        }

        if (pass == 5) {

            setProgressValue(progress);

            relativePad11.setVisibility(View.GONE);
            relativePad1.setVisibility(View.GONE);
            relativePad2.setVisibility(View.VISIBLE);
            relativePad3.setVisibility(View.GONE);
            relativePad4.setVisibility(View.GONE);
            relativePad5.setVisibility(View.GONE);

        }

        if (pass == 10) {

            setProgressValue(progress);

            relativePad11.setVisibility(View.GONE);
            relativePad1.setVisibility(View.GONE);
            relativePad2.setVisibility(View.GONE);
            relativePad3.setVisibility(View.VISIBLE);
            relativePad4.setVisibility(View.GONE);
            relativePad5.setVisibility(View.GONE);

        }

        if (pass == 15) {

            setProgressValue(progress);

            relativePad11.setVisibility(View.GONE);
            relativePad1.setVisibility(View.GONE);
            relativePad2.setVisibility(View.GONE);
            relativePad3.setVisibility(View.GONE);
            relativePad4.setVisibility(View.VISIBLE);
            relativePad5.setVisibility(View.GONE);

        }

        if (pass == 20) {

            setProgressValue(progress);

            relativePad11.setVisibility(View.GONE);
            relativePad1.setVisibility(View.GONE);
            relativePad2.setVisibility(View.GONE);
            relativePad3.setVisibility(View.GONE);
            relativePad4.setVisibility(View.GONE);
            relativePad5.setVisibility(View.VISIBLE);

        }*/

        Log.e("startTimeduration4", txt_startduration.getText().toString());
    }

    protected void initializeSeekBar() {
        seekBar.setMax(mPlayer.getDuration() / 1000);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                if (mPlayer != null) {
                    int mCurrentPosition = mPlayer.getCurrentPosition() / 1000; // In milliseconds
                    seekBar.setProgress(mCurrentPosition);
                    getAudioStats();
                }
                mHandler.postDelayed(mRunnable, 1000);
            }
        };
        mHandler.postDelayed(mRunnable, 1000);

        Log.e("startTimeduration5", txt_startduration.getText().toString());

        if (durationvalue == 27) {
            Intent intent = new Intent(this, BeatResultActivity.class);
            startActivity(intent);
        }
    }

    private void setProgressValue(final int progress) {

        // set the progress
        progress_bar1.setProgress(progress);
        progress_bar2.setProgress(progress);
        progress_bar3.setProgress(progress);
        progress_bar4.setProgress(progress);
        progress_bar5.setProgress(progress);

        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressValue(progress + 25);
            }
        });
        thread.start();
    }

}