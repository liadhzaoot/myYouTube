package com.example.myapplication;

import android.content.Context;
import android.net.Uri;

public class Item {
    private Uri mVideoUri;
    private String mText1;
    private String mText2;
    private Context mContext;

    public Item(Uri videoUri, String text1, String text2,Context context) {
        mContext = context;
        mVideoUri = videoUri;
        mText1 = text1;
        mText2 = text2;
    }

    public Context getmContext() {
        return mContext;
    }

    public Uri getmVideoUri() {
        return mVideoUri;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

}
