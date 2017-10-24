package com.developerhaoz.imageloaderdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.io.File;
import java.util.HashSet;
import java.util.List;

/**
 * @author Haoz
 * @date 2017/10/22.
 */

public class PhotoWallActivity extends AppCompatActivity {

    private ImageView mIv;

    private int mPicsSize;

    private File mImgDir;

    private List<String> mImgs;

    private RecyclerView mRvPhotoWall;

    private HashSet<String> mDirPaths = new HashSet<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_wall);
    }
}


















