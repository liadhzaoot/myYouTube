package com.example.myapplication;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Iterator;

public class Apdapter extends RecyclerView.Adapter<Apdapter.viewHolder> {
    private ArrayList<Item> mExampleList;

public class viewHolder extends RecyclerView.ViewHolder{
    public ImageView mImageView;
    public Button mButtonView;
    public TextView mTextView2;
    public VideoView mVideoView;
    public viewHolder(@NonNull View itemView) {
        super(itemView);
        mVideoView = itemView.findViewById(R.id.videoView);
        mButtonView = itemView.findViewById(R.id.itemFavoriteBTN);
        mTextView2 = itemView.findViewById(R.id.textView2);
    }
}
    public Apdapter(ArrayList<Item> exampleList) {
        mExampleList = exampleList;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        viewHolder evh = new viewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Item currentItem = mExampleList.get(position);
        holder.mVideoView.setVideoURI(currentItem.getmVideoUri());
        //holder.mButtonView.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());

//        holder.mVideoView.setVideoURI(u)
//        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video1;
//        Uri uri = Uri.parse(videoPath);
//        mVideoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(currentItem.getmContext());
        holder.mVideoView.setMediaController(mediaController);
        mediaController.setAnchorView(holder.mVideoView);

        holder.mButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
