package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class FirstScreen extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Item> exampleList;
    private Button deleteBTN;
    private Button exitBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        exampleList = new ArrayList<>();

        deleteBTN = findViewById(R.id.deleteBTN);
        exitBTN = findViewById(R.id.exitBTN);

        String videoPath1 = "android.resource://" + getPackageName() + "/" + R.raw.video1;
        String videoPath2 = "android.resource://" + getPackageName() + "/" + R.raw.video2;
        String videoPath3 = "android.resource://" + getPackageName() + "/" + R.raw.video3;


        exampleList.add(new Item(Uri.parse(videoPath1), "Line 1", "Line 2",this));
        exampleList.add(new Item(Uri.parse(videoPath2), "Line 3", "Line 4",this));
        exampleList.add(new Item(Uri.parse(videoPath3), "Line 5", "Line 6",this));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Apdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        deleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exampleList.clear();
                mAdapter.notifyDataSetChanged();
            }
        });
        exitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
