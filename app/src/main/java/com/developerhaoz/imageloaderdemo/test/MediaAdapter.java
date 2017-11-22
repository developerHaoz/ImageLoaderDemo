package com.developerhaoz.imageloaderdemo.test;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.developerhaoz.imageloaderdemo.R;

/**
 * @author Haoz
 * @date 2017/11/19.
 */

public class MediaAdapter extends BaseCursorAdapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_CAPTURE = 0x01;
    private static final int VIEW_TYPE_MEDIA = 0x02;

    public MediaAdapter(Context context) {
        super(null);
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor) {
        Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Files.FileColumns._ID));
        Uri imageUri = ContentUris.withAppendedId(contentUri, id);
        MediaViewHolder mediaViewHolder = (MediaViewHolder) holder;
        Glide.with(((MediaViewHolder) holder).mImageView.getContext())
                .load(imageUri)
                .into(((MediaViewHolder) holder).mImageView);
        ContactsContract.Contacts._ID
    }

    @Override
    protected int getItemViewType(int position, Cursor mCursor) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lv, parent, false);
        MediaViewHolder holder = new MediaViewHolder(view);
        return holder;
    }

    private static class MediaViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImageView;


        public MediaViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView;
        }
    }
}















