package com.developerhaoz.imageloaderdemo.engine;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

/**
 * @author Haoz
 * @date 2017/11/11.
 */

public interface ImageEngine {

    void loadThumnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri);

    void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri);

    void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri);

    void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri);

    boolean supportAnimatedGif();
}





















