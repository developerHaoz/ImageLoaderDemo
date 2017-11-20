package com.developerhaoz.imageloaderdemo.test;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.developerhaoz.imageloaderdemo.R;

/**
 * @author Haoz
 * @date 2017/11/19.
 */

public class MediaActivity extends AppCompatActivity implements MediaCollection.MediaCallbacks,
        AlbumCollectionTest.AlbumCallbacksTest{

    private AlbumCollectionTest albumCollectionTest;
    private RecyclerView mRvPhotoWall;
    private MediaCollection mMediaCollection;
    private Button mBtnTest;

    private static final String TAG = "MediaActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        mRvPhotoWall = (RecyclerView) findViewById(R.id.media_rv_photo_wall);
        mBtnTest = (Button) findViewById(R.id.meida_btn_test);
        Log.d(TAG, "onCreate: hello world");
        mBtnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MediaActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    albumCollectionTest = new AlbumCollectionTest();
                    albumCollectionTest.onCreate(MediaActivity.this, MediaActivity.this);
                    albumCollectionTest.loadAlbums();
                }else{
                    ActivityCompat.requestPermissions(MediaActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }
            }
        });



    }

    @Override
    public void onMediaLoad(Cursor cursor) {

    }

    @Override
    public void onMediaReset() {

    }

    @Override
    public void onAlbumLoad(Cursor cursor) {

        Log.d(TAG, "onAlbumLoad: " + "hello world");
        Log.d(TAG, "onAlbumLoad: cursor'size: " + cursor.getCount());
        while(cursor.moveToNext()){
            Log.d(TAG, "onAlbumLoad: " + cursor.getString(cursor.getColumnIndex("bucket_display_name")));
        }
    }

    @Override
    public void onAlbumReset() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    albumCollectionTest = new AlbumCollectionTest();
                    albumCollectionTest.onCreate(this, this);
                    albumCollectionTest.loadAlbums();
                }
                break;
            default:break;
        }
    }
}















