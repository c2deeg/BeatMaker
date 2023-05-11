package com.beatmaker.Fragments.MyMusic

import android.content.ContextWrapper
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beatmaker.Activities.BeatMakingActivity.BeatMakingActivity
import com.beatmaker.Adapter.RecentplayedRecyclerAdapter
import com.beatmaker.R
import com.beatmaker.Utils.CSPrefernce
import com.beatmaker.Utils.Utils
import com.google.android.material.bottomsheet.BottomSheetDialog


class MyMusicFragment : Fragment(), View.OnClickListener {
    lateinit var recentplayedrecylerview: RecyclerView
    lateinit var myrecordsrecyclerview: RecyclerView
    lateinit var recentplayedRecyclerAdapter: RecentplayedRecyclerAdapter
    lateinit var img_play: ImageView
    lateinit var seekbar: SeekBar
    lateinit var mediaPlayer: MediaPlayer
    lateinit var tv_totduration: TextView
    lateinit var img_menu:ImageView
    var handler: Handler = Handler()
    var bottomSheetDialog: BottomSheetDialog? = null
    var tv_rename: TextView? = null
    var beatMakingActivity:BeatMakingActivity?=null
   lateinit var musicname:String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_my_music, container, false)
        init(view)
        listenrs(view)
        recentplayedRecyclerAdapter = RecentplayedRecyclerAdapter(activity as FragmentActivity)
        recentplayedrecylerview?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recentplayedrecylerview.adapter = recentplayedRecyclerAdapter

        mediaPlayer = MediaPlayer()
        seekbar.setMax(mediaPlayer.getDuration());


        img_play.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                handler.removeCallbacks(updater)
                mediaPlayer.pause()
                img_play.setImageResource(R.drawable.icplay)

            } else {
                mediaPlayer.start()
                img_play.setImageResource(R.drawable.playbtn)
                upadteseekbar()

            }
        }
        preparemediaplayer()



        return view
    }
    private fun preparemediaplayer(){
        try {
            var path = "/storage/emulated/0/AudioRecording.3gp"
            mediaPlayer.setDataSource(path)
            mediaPlayer.prepare()
            tv_totduration.text = milliseconds(mediaPlayer.duration.toLong())

        }catch (exception:Exception){
            Toast.makeText(activity, exception.message, Toast.LENGTH_SHORT).show()
        }

    }



    private val updater =
        Runnable {
            upadteseekbar()
            val currentDuration: Long = mediaPlayer.currentPosition.toLong()
            tv_totduration.text = milliseconds(currentDuration)
        }

    private fun upadteseekbar() {
        if (mediaPlayer.isPlaying) {
            val mCurrentPosition: Int = mediaPlayer.getCurrentPosition() / 1000
            seekbar.setProgress(mCurrentPosition)
            handler.postDelayed(updater, 1000)

        }
    }


    private fun milliseconds(milliseconds: Long): String? {
        var timeString = ""
        var secondString = ""
        var hours = (milliseconds / (1000 * 60 * 60)).toInt()
        val minutes = (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        val seconds = (milliseconds % (1000 * 60 * 60)) % (1000 * 60)
        if (hours > 0) {
            timeString = "$hours:"
        }
        if (seconds < 10) {
            secondString = "0$seconds"
        } else {
            secondString = "$seconds"
        }
        timeString = timeString + minutes + ":" + secondString
        return timeString
    }






    private fun init(view: View?) {
        recentplayedrecylerview = view?.findViewById(R.id.recentplayedrecylerview)!!
        img_play = view?.findViewById(R.id.img_play)!!
        seekbar = view?.findViewById(R.id.seekbar)!!
        img_menu = view?.findViewById(R.id.img_menu)!!
        tv_totduration = view?.findViewById(R.id.tv_totduration)!!

    }

    private fun listenrs(view: View?) {
        img_menu?.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
      if (p0==img_menu){
            showBottomSheetDialog(view)

        }else if (p0==tv_rename){

        }
    }

    private fun showBottomSheetDialog(view: View?) {
        bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
        bottomSheetDialog?.setContentView(R.layout.bottom_sheet_dialog)
        tv_rename = bottomSheetDialog?.findViewById<TextView>(R.id.tv_rename)
        bottomSheetDialog?.show()




        //TextViews
        tv_rename?.setOnClickListener(this)

    }



}