package com.beatmaker.Fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MergeCursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.beatmaker.Activities.MusicActivity;
import com.beatmaker.Adapter.MyRecordsRecyclerAdapter;
import com.beatmaker.Adapter.RecentplayedRecyclerAdapter;
import com.beatmaker.Interfaces.MyRecordsClickListener;
import com.beatmaker.Models.AudioModel;
import com.beatmaker.Models.PathModelClass;
import com.beatmaker.R;
import com.beatmaker.Utils.CSPrefernce;
import com.beatmaker.Utils.CSPrefernces;
import com.beatmaker.Utils.Utils;
import com.beatmaker.Utils.Utilsjava;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MyMusicJavaFragment extends Fragment implements View.OnClickListener, MyRecordsClickListener {

    private View view;
    private ImageView img_playpause;
    private TextView textcurrenttime, texttotalduration;
    private SeekBar playerseekbar;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private RecyclerView recentplayedrecylerview;
    private RecentplayedRecyclerAdapter recentplayedRecyclerAdapter;
    private BottomSheetDialog bottomSheetDialog;
    private ImageView img_menu;
    private TextView tv_delete;
    private TextView tv_rename;
    private TextView tv_songname;
    private ImageView img_close;
    private Dialog dialog;
    private EditText et_renamefile;
    private Button bt_rename;
    private RecyclerView myrecordrecylerview;
    private MyRecordsRecyclerAdapter myRecordsRecyclerAdapter;
    final List<AudioModel> tempAudioList = new ArrayList<>();
    final List<PathModelClass> tempAudioListpath = new ArrayList<>();
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_music_java, container, false);
        recentplayedrecylerview = view.findViewById(R.id.recentplayedrecylerview);
        myrecordrecylerview = view.findViewById(R.id.myrecordrecylerview);



        recentplayedRecyclerAdapter = new RecentplayedRecyclerAdapter(getActivity());
        recentplayedrecylerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recentplayedrecylerview.setAdapter(recentplayedRecyclerAdapter);
        mediaPlayer = new MediaPlayer();

        //mainmethodgetlistfromstorage____________________________________________________________________________________________________________________________________________
        requestRead();
        myRecordsRecyclerAdapter = new MyRecordsRecyclerAdapter(getActivity(), tempAudioList,mediaPlayer, this,tempAudioListpath,this);
        myrecordrecylerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        myrecordrecylerview.setAdapter(myRecordsRecyclerAdapter);



        return view;

    }


    private void showBottomSheetDialog() {
        bottomSheetDialog = new BottomSheetDialog(requireContext(), R.style.SheetDialog);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
        tv_delete = bottomSheetDialog.findViewById(R.id.tv_delete);
        tv_rename = bottomSheetDialog.findViewById(R.id.tv_rename);
        bottomSheetDialog.show();
        tv_delete.setOnClickListener(this);
        tv_rename.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == tv_delete) {

        } else if (view == tv_rename) {
//            showDialog();
//            bottomSheetDialog.dismiss();

        }

    }


    private void showDialog() {
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.logoutdialog);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.black);


//        Button btn_signout = (Button) dialog.findViewById(R.id.btn_signout);
//        img_close = dialog.findViewById(R.id.img_close);
//        et_renamefile = dialog.findViewById(R.id.et_renamefile);
//        bt_rename = dialog.findViewById(R.id.bt_rename);
//
//
//        img_close.setOnClickListener(this);
//        bt_rename.setOnClickListener(this);


        dialog.show();

    }



    public void requestRead() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            getAllAudioFromDevice(getActivity());
        }
    }







    public List<AudioModel> getAllAudioFromDevice(final Context context) {
        tempAudioList.clear();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.AudioColumns.DATA, MediaStore.Audio.AudioColumns.TITLE, MediaStore.Audio.AudioColumns.ALBUM, MediaStore.Audio.ArtistColumns.ARTIST,};
        Cursor c = context.getContentResolver().query(uri, projection, MediaStore.Audio.Media.DATA + " like ? ", new String[]{"%FolderName%"}, null);
        if (c != null) {
            while (c.moveToNext()) {
                AudioModel audioModel = new AudioModel();
                String path = c.getString(0);   // Retrieve path.
                String name = c.getString(1);   // Retrieve name.
                String album = c.getString(2);  // Retrieve album name.
                String artist = c.getString(3); // Retrieve artist name.
                // Set data to the model object.

                audioModel.setaName(name);
                audioModel.setaAlbum(album);
                audioModel.setaArtist(artist);
                audioModel.setaPath(path);

                tempAudioList.add(audioModel);

            }
            c.close();
        }
        return tempAudioList;
    }


    //mediaplayer___________________________________________________________________________________________________________________________
    public void preparemediaplayer(String paths ) {

        Log.d("pathupdate",paths.toString());
        try {
            mediaPlayer.setDataSource(paths);
            mediaPlayer.prepare();
//            mediaPlayer.start();

        } catch (Exception exception) {
            Toast.makeText(getActivity(), exception.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateseekbar();
            long currentDuration = mediaPlayer.getCurrentPosition();
            textcurrenttime.setText(millisecondstoTimer(currentDuration));
        }
    };

    private void updateseekbar() {
        if (mediaPlayer.isPlaying()) {
            playerseekbar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            handler.postDelayed(updater, 1000);

        }
    }




    @Override
    public void passData(@NonNull ImageView imgPlaypause, @NonNull SeekBar playerseekbar, @NonNull TextView texttotalduration, @NonNull TextView textcurrenttime, @NonNull MediaPlayer mediaPlayer, @Nullable String getaPath) {

        this.img_playpause = imgPlaypause;
        this.playerseekbar = playerseekbar;
        this.texttotalduration = texttotalduration;
        this.textcurrenttime = textcurrenttime;

//        preparemediaplayer();


    }

    public String millisecondstoTimer(long milliseconds) {
        String timerString = "";
        String secondString = "";
        int hours = (int) (milliseconds / (1000 * 60*  60));
        int minutes = (int) (milliseconds % (1000 * 60 *  60) / (1000 * 60));
        int seconds = (int) (milliseconds % (1000  *60*  60) % (1000 * 60) / 1000);
        if (hours > 0) {
            timerString = "0" + seconds;
        }
        if (seconds < 10) {
            secondString = "0" + seconds;
        } else {
            secondString = "" + seconds;
        }

        timerString = timerString + minutes + ":" + secondString;
        return timerString;


    }


}