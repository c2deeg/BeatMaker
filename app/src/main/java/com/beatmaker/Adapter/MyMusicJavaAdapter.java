package com.beatmaker.Adapter;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.recyclerview.widget.RecyclerView;

import com.beatmaker.R;

import java.io.File;

class MyAdapter2 {
}
// extends RecyclerView.Adapter<MyAdapter2.AudioItemsViewHolder>
//
//    static MediaPlayer mediaPlayer;
//    Activity activity;
//
//
//    private final ArrayList<GroupItems> audioItems;//change it() to your items
//    private int currentPlayingPosition;
//    private final SeekBarUpdater seekBarUpdater;
//    private AudioItemsViewHolder playingHolder;
//
//
//
//    public MyAdapter2(Activity activity, ArrayList<GroupItems> items_pro) {
//        this.audioItems = items_pro;
//        this.currentPlayingPosition = -1;
//        seekBarUpdater = new SeekBarUpdater();
//        this.activity = activity;
//
//    }
//
//
//    @Override
//    public AudioItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        //put YourItemsLayout;
//        return new AudioItemsViewHolder(LayoutInflater.from(parent.getContext()).inflate(YourItemsLayout, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(AudioItemsViewHolder holder, int position) {
//        if (position == currentPlayingPosition) {
//            playingHolder = holder;
//            updatePlayingView();
//        } else {
//            updateNonPlayingView(holder);
//        }
//    }
//    private void updateNonPlayingView(AudioItemsViewHolder holder) {
//        holder.sbProgress.removeCallbacks(seekBarUpdater);
//        holder.sbProgress.setEnabled(false);
//        holder.sbProgress.setProgress(0);
//        holder.ivPlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
//    }
//
//    private void updatePlayingView() {
//        playingHolder.sbProgress.setMax(mediaPlayer.getDuration());
//        playingHolder.sbProgress.setProgress(mediaPlayer.getCurrentPosition());
//        playingHolder.sbProgress.setEnabled(true);
//        if (mediaPlayer.isPlaying()) {
//            playingHolder.sbProgress.postDelayed(seekBarUpdater, 100);
//            playingHolder.ivPlayPause.setImageResource(R.drawable.ic_pause);
//        } else {
//            playingHolder.sbProgress.removeCallbacks(seekBarUpdater);
//            playingHolder.ivPlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
//        }
//    }
//    private class SeekBarUpdater implements Runnable {
//        @Override
//        public void run() {
//            if (null != playingHolder && null != mediaPlayer) {
//                playingHolder.sbProgress.setProgress(mediaPlayer.getCurrentPosition());
//                playingHolder.sbProgress.postDelayed(this, 100);
//            }
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return audioItems.size();
//    }
//
//    class AudioItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
//        SeekBar sbProgress;
//        ImageView ivPlayPause;
//        AudioItemsViewHolder(View itemView) {
//            super(itemView);
//            ivPlayPause = itemView.findViewById(R.id.sound_btn);
//            ivPlayPause.setOnClickListener(this);
//            sbProgress = itemView.findViewById(R.id.seekBar);
//            sbProgress.setOnSeekBarChangeListener(this);
//        }
//        @Override
//        public void onClick(View v) {
//
//
//            switch (v.getId()) {
//                case R.id.seekBar:
//                    break;
//
//                case R.id.sound_btn: {
//                    if (getAdapterPosition() == currentPlayingPosition) {
//                        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//                            mediaPlayer.pause();
//                        } else {
//                            if (mediaPlayer != null)
//                                mediaPlayer.start();
//                        }
//                    } else {
//                        currentPlayingPosition = getAdapterPosition();
//                        if (mediaPlayer != null) {
//                            if (null != playingHolder) {
//                                updateNonPlayingView(playingHolder);
//                            }
//                            mediaPlayer.release();
//                        }
//                        playingHolder = this;
//
//
//
//
//                        PlaySound("YOUR AUDIO FILE");//put your audio file
//
//
//                    }
//                    if (mediaPlayer != null)
//                        updatePlayingView();
//                }
//                break;
//            }
//
//
//        }
//
//
//        @Override    static MediaPlayer mediaPlayer;
//    Activity activity;
//
//
//    private final ArrayList<GroupItems> audioItems;//change it() to your items
//    private int currentPlayingPosition;
//    private final SeekBarUpdater seekBarUpdater;
//    private AudioItemsViewHolder playingHolder;
//
//
//
//    public MyAdapter2(Activity activity, ArrayList<GroupItems> items_pro) {
//        this.audioItems = items_pro;
//        this.currentPlayingPosition = -1;
//        seekBarUpdater = new SeekBarUpdater();
//        this.activity = activity;
//
//    }
//
//
//    @Override
//    public AudioItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        //put YourItemsLayout;
//        return new AudioItemsViewHolder(LayoutInflater.from(parent.getContext()).inflate(YourItemsLayout, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(AudioItemsViewHolder holder, int position) {
//        if (position == currentPlayingPosition) {
//            playingHolder = holder;
//            updatePlayingView();
//        } else {
//            updateNonPlayingView(holder);
//        }
//    }
//    private void updateNonPlayingView(AudioItemsViewHolder holder) {
//        holder.sbProgress.removeCallbacks(seekBarUpdater);
//        holder.sbProgress.setEnabled(false);
//        holder.sbProgress.setProgress(0);
//        holder.ivPlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
//    }
//
//    private void updatePlayingView() {
//        playingHolder.sbProgress.setMax(mediaPlayer.getDuration());
//        playingHolder.sbProgress.setProgress(mediaPlayer.getCurrentPosition());
//        playingHolder.sbProgress.setEnabled(true);
//        if (mediaPlayer.isPlaying()) {
//            playingHolder.sbProgress.postDelayed(seekBarUpdater, 100);
//            playingHolder.ivPlayPause.setImageResource(R.drawable.ic_pause);
//        } else {
//            playingHolder.sbProgress.removeCallbacks(seekBarUpdater);
//            playingHolder.ivPlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
//        }
//    }
//    private class SeekBarUpdater implements Runnable {
//        @Override
//        public void run() {
//            if (null != playingHolder && null != mediaPlayer) {
//                playingHolder.sbProgress.setProgress(mediaPlayer.getCurrentPosition());
//                playingHolder.sbProgress.postDelayed(this, 100);
//            }
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return audioItems.size();
//    }
//
//    class AudioItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
//        SeekBar sbProgress;
//        ImageView ivPlayPause;
//        AudioItemsViewHolder(View itemView) {
//            super(itemView);
//            ivPlayPause = itemView.findViewById(R.id.sound_btn);
//            ivPlayPause.setOnClickListener(this);
//            sbProgress = itemView.findViewById(R.id.seekBar);
//            sbProgress.setOnSeekBarChangeListener(this);
//        }
//        @Override
//        public void onClick(View v) {
//
//
//            switch (v.getId()) {
//                case R.id.seekBar:
//                    break;
//
//                case R.id.sound_btn: {
//                    if (getAdapterPosition() == currentPlayingPosition) {
//                        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//                            mediaPlayer.pause();
//                        } else {
//                            if (mediaPlayer != null)
//                                mediaPlayer.start();
//                        }
//                    } else {
//                        currentPlayingPosition = getAdapterPosition();
//                        if (mediaPlayer != null) {
//                            if (null != playingHolder) {
//                                updateNonPlayingView(playingHolder);
//                            }
//                            mediaPlayer.release();
//                        }
//                        playingHolder = this;
//
//
//
//
//                        PlaySound("YOUR AUDIO FILE");//put your audio file
//
//
//                    }
//                    if (mediaPlayer != null)
//                        updatePlayingView();
//                }
//                break;
//            }
//
//
//        }
//
//
//        @Override
//        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//            if (fromUser) {
//                mediaPlayer.seekTo(progress);
//            }
//        }
//
//        @Override
//        public void onStartTrackingTouch(SeekBar seekBar) {
//        }
//
//        @Override
//        public void onStopTrackingTouch(SeekBar seekBar) {
//        }
//    }
//    private void PlaySound(File filesound) {
//
//        mediaPlayer = MediaPlayer.create(activity, Uri.parse(String.valueOf(filesound)));
//
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                releaseMediaPlayer();
//            }
//        });
//        mediaPlayer.start();
//    }
//    private void releaseMediaPlayer() {
//        if (null != playingHolder) {
//            updateNonPlayingView(playingHolder);
//        }
//        if (outputFile.exists())
//            outputFile.delete();
//
//        mediaPlayer.release();
//        mediaPlayer = null;
//        currentPlayingPosition = -1;
//    }
//
//        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//            if (fromUser) {
//                mediaPlayer.seekTo(progress);
//            }
//        }
//
//        @Override
//        public void onStartTrackingTouch(SeekBar seekBar) {
//        }
//
//        @Override
//        public void onStopTrackingTouch(SeekBar seekBar) {
//        }
//    }
//    private void PlaySound(File filesound) {
//
//        mediaPlayer = MediaPlayer.create(activity, Uri.parse(String.valueOf(filesound)));
//
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                releaseMediaPlayer();
//            }
//        });
//        mediaPlayer.start();
//    }
//    private void releaseMediaPlayer() {
//        if (null != playingHolder) {
//            updateNonPlayingView(playingHolder);
//        }
//        if (outputFile.exists())
//            outputFile.delete();
//
//        mediaPlayer.release();
//        mediaPlayer = null;
//        currentPlayingPosition = -1;
//    }




