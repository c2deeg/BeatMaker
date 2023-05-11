package com.beatmaker.Adapter

import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.beatmaker.Fragments.MyMusicJavaFragment
import com.beatmaker.Interfaces.MyRecordsClickListener
import com.beatmaker.Models.AudioModel
import com.beatmaker.Models.PathModelClass
import com.beatmaker.R

class MyRecordsRecyclerAdapter(
    private val activity: FragmentActivity,
    private val tempAudioList: MutableList<AudioModel>,
    private val mediaPlayer: MediaPlayer,
    private val myRecordsClickListener: MyRecordsClickListener,
    private val tempAudioListpath: MutableList<PathModelClass>,
  private val  myMusicJavaFragment: MyMusicJavaFragment
) :
    RecyclerView.Adapter<MyRecordsRecyclerAdapter.ViewHolder>() {


    


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(activity).inflate(R.layout.myrecordsrecyclreitem, parent, false)
        return MyRecordsRecyclerAdapter.ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (tempAudioList.size != 0) {


            holder.tv_songname.text = tempAudioList.get(position).getaName()

            holder.img_playpause.setOnClickListener {

                myMusicJavaFragment.preparemediaplayer(tempAudioList.get(position).getaPath().toString())


//                if (mediaPlayer.isPlaying) {
//                    handler.removeCallbacks(updater)
//                    mediaPlayer.pause()
//                    img_playpause.setImageResource(R.drawable.icplay)
//                } else {
//                    mediaPlayer.start()
//                    img_playpause.setImageResource(R.drawable.pause)
//                    updateseekbar()
//                }

                Toast.makeText(activity, tempAudioList.get(position).getaPath(), Toast.LENGTH_SHORT).show()
                myRecordsClickListener.passData(
                    holder.img_playpause,
                    holder.playerseekbar,
                    holder.texttotalduration,
                    holder.textcurrenttime,
                    mediaPlayer,
                    tempAudioList.get(position).getaPath(),



                )
            }
            val mediaMetadataRetriever = MediaMetadataRetriever()
            for (i in 0..tempAudioList.size - 1) {
                mediaMetadataRetriever.setDataSource(tempAudioList.get(i).getaPath().toString());
                var duration = mediaMetadataRetriever?.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                var timerString = ""
                if (duration != null) {
                    var numpath: Int = duration!!.toInt()

                    var secondString = ""
                    val hours = (numpath / (1000 * 60 * 60)).toInt()
                    val minutes = (numpath % (1000 * 60 * 60) / (1000 * 60)).toInt()
                    val seconds = (numpath % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()
                    if (hours > 0) {
                        timerString = "0$seconds"
                    }
                    secondString = if (seconds < 10) {
                        "0$seconds"
                    } else {
                        "" + seconds
                    }
                    timerString = "$timerString$minutes:$secondString"
                }
                tempAudioListpath.add(PathModelClass(timerString.toString()))


            }
            if (tempAudioListpath.size != 0) {
                holder.texttotalduration.text = tempAudioListpath.get(position).path
                val currentDuration = mediaPlayer.currentPosition.toLong()


                val updater = Runnable {
                    val currentDuration = mediaPlayer.currentPosition.toLong()
                    holder.textcurrenttime.setText(myMusicJavaFragment.millisecondstoTimer(currentDuration))
                }


            }

            Log.d("checklistt",tempAudioList.size.toString())
            Log.d("checklistt",tempAudioListpath.size.toString())


        }


    }

    override fun getItemCount(): Int {
        return tempAudioList.size;
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        var tv_songname: TextView = itemView.findViewById(R.id.tv_songname)
        var img_playpause: ImageView = itemView.findViewById(R.id.img_playpause)
        var playerseekbar: SeekBar = itemView.findViewById(R.id.playerseekbar)
        var texttotalduration: TextView = itemView.findViewById(R.id.texttotalduration)
        var textcurrenttime: TextView = itemView.findViewById(R.id.textcurrenttime)


    }


}