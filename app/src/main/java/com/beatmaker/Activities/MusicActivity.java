package com.beatmaker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.beatmaker.R;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);


        ListView mAudioListView = (ListView) findViewById(R.id.audioListView);

        ArrayList<String> mAudioList = new ArrayList<>();

        //detail of each audio
        String[] mAudioDetailArray = {MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DISPLAY_NAME};

        //INTERNAL_CONTENT_URI to display audio from internal storage
        //EXTERNAL_CONTENT_URI to display audio from external storage
        Cursor mAudioCursor = getContentResolver().query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, mAudioDetailArray, null, null, null);

        if (mAudioCursor != null) {
            if (mAudioCursor.moveToFirst()) {
                do {
                    int audioIndex = mAudioCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME);

                    mAudioList.add(mAudioCursor.getString(audioIndex));
                } while (mAudioCursor.moveToNext());
            }
        }
        mAudioCursor.close();

        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mAudioList);
        mAudioListView.setAdapter(mAdapter);


    }
}