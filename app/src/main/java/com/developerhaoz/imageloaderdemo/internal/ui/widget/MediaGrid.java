package com.developerhaoz.imageloaderdemo.internal.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developerhaoz.imageloaderdemo.R;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.ui.widget.CheckView;

/**
 * @author Haoz
 * @date 2017/11/1.
 */

public class MediaGrid extends SquareFrameLayout implements View.OnClickListener {

    private ImageView mThumbnail;
    private CheckView mCheckView;
    private ImageView mGifTag;
    private TextView mVideoDuration;

    private Item mMedia;
    private PreBindInfo mPreBindInfo;
    private OnMediaGridClickListener mListener;

    public MediaGrid(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MediaGrid(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.meida)
    }


    @Override
    public void onClick(View v) {

    }

    public interface OnMediaGridClickListener {

        void onThumbnailClicked(ImageView thumnail, Item item, RecyclerView.ViewHolder holder);

        void onCheckViewClick(CheckView checkView, Item item, RecyclerView.ViewHolder holder);
    }

    public static class PreBindInfo {
        int mResize;
        Drawable mPlaceholder;
        boolean mCheckViewCountable;
        RecyclerView.ViewHolder mViewHolder;


        public PreBindInfo(int resize, Drawable placeholder, boolean checkViewCountable, RecyclerView.ViewHolder viewHolder) {
            mResize = resize;
            mPlaceholder = placeholder;
            mCheckViewCountable = checkViewCountable;
            mViewHolder = viewHolder;
        }
    }
}




















