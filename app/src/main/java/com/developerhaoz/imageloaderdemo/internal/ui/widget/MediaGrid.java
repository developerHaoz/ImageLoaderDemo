package com.developerhaoz.imageloaderdemo.internal.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developerhaoz.imageloaderdemo.R;
import com.developerhaoz.imageloaderdemo.internal.entity.Item;
import com.developerhaoz.imageloaderdemo.internal.entity.SelectionSpec;

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
        LayoutInflater.from(context).inflate(R.layout.media_grid_content, this, false);

        mThumbnail = (ImageView) findViewById(R.id.media_thumbnail);
        mCheckView = (CheckView) findViewById(R.id.check_view);
        mGifTag = (ImageView) findViewById(R.id.gif);
        mVideoDuration = (TextView) findViewById(R.id.video_duration);

        mThumbnail.setOnClickListener(this);
        mCheckView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(mListener != null){
            if(v == mThumbnail){
                mListener.onThumbnailClicked(mThumbnail, mMedia, mPreBindInfo.mViewHolder);
            }else if (v == mCheckView){
                mListener.onCheckViewClicked(mCheckView, mMedia, mPreBindInfo.mViewHolder);
            }
        }
    }

    public void preBindMedia(PreBindInfo info){
        mPreBindInfo = info;
    }

    public void bindMedia(Item item){
        mMedia = item;
        setGifTag();
        initCheckView();
        setImage();
        setVideoDuration();
    }

    public Item getMedia(){
        return this.mMedia;
    }

    private void setVideoDuration() {
        if(mMedia.isVideo()){
            mVideoDuration.setVisibility(View.VISIBLE);
            mVideoDuration.setText(DateUtils.formatElapsedTime(mMedia.duration / 1000));
        }else{
            mVideoDuration.setVisibility(GONE);
        }

    }

    private void setImage() {
        if(mMedia.isGif()){
            SelectionSpec.getInstance().imageEngine.loadGifThumbnail(getContext(), mPreBindInfo.mResize,
                    mPreBindInfo.mPlaceholder, mThumbnail, mMedia.getContentUri());
        }else{
            SelectionSpec.getInstance().imageEngine.loadThumnail(getContext(), mPreBindInfo.mResize,
                    mPreBindInfo.mPlaceholder, mThumbnail, mMedia.getContentUri());
        }
    }

    private void initCheckView() {
        mCheckView.setCountable(mPreBindInfo.mCheckViewCountable);
    }

    public void setCheckEnabled(boolean enabled){
        mCheckView.setEnabled(enabled);
    }

    public void setCheckNum(int checkNum){
        mCheckView.setCheckNum(checkNum);
    }

    public void setChecked(boolean checked){
        mCheckView.setChecked(checked);
    }

    private void setGifTag() {
        if(mMedia.isGif()){
            mGifTag.setVisibility(View.VISIBLE);
        }else {
            mGifTag.setVisibility(View.GONE);
        }
    }

    public void setOnMediaGridClickListener(OnMediaGridClickListener listener){
        this.mListener = listener;
    }

    public void removeOnMediaGridClickListener(){
        mListener = null;
    }

    public interface OnMediaGridClickListener{

        void onThumbnailClicked(ImageView thumbnail, Item item, RecyclerView.ViewHolder holder);

        void onCheckViewClicked(CheckView checkView, Item item, RecyclerView.ViewHolder holder);
    }

    public static class PreBindInfo{
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