package com.developerhaoz.imageloaderdemo.test;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.developerhaoz.imageloaderdemo.R;

/**
 * @author Haoz
 * @date 2017/11/19.
 */

public class MediaActivity extends AppCompatActivity implements MediaCollection.MediaCallbacks{

    private RecyclerView mRvPhotoWall;
    MediaCollection mMediaCollection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        mRvPhotoWall = (RecyclerView) findViewById(R.id.media_rv_photo_wall);
        MediaAdapter adapter = new MediaAdapter(this);
        mMediaCollection = new MediaCollection();
        mMediaCollection.onCreate(this, this);
    }

    @Override
    public void onMediaLoad(Cursor cursor) {

    }

    @Override
    public void onMediaReset() {

    }
}
