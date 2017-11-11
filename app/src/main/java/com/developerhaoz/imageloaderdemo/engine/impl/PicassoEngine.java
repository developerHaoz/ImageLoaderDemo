package com.developerhaoz.imageloaderdemo.engine.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.developerhaoz.imageloaderdemo.engine.ImageEngine;
import com.squareup.picasso.Picasso;

/**
 * @author Haoz
 * @date 2017/11/11.
 */

public class PicassoEngine implements ImageEngine {

    @Override
    public void loadThumnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        Picasso.with(context)
                .load(uri)
                .placeholder(placeholder)
                .resize(resize, resize)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        loadThumnail(context, resize, placeholder, imageView, uri);
    }

    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Picasso.with(context)
                .load(uri)
                .resize(resizeX, resizeY)
                .priority(Picasso.Priority.HIGH)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        loadImage(context, resizeX, resizeY, imageView, uri);
    }

    @Override
    public boolean supportAnimatedGif() {
        return false;
    }
}
