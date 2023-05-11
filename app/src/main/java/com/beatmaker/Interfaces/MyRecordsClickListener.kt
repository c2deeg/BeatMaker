package com.beatmaker.Interfaces

import android.media.MediaPlayer
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView

interface MyRecordsClickListener  {
    fun passData(
        imgPlaypause: ImageView,
        playerseekbar: SeekBar,
        texttotalduration: TextView,
        textcurrenttime: TextView,
        mediaPlayer: MediaPlayer,
        getaPath: String?
    )



}