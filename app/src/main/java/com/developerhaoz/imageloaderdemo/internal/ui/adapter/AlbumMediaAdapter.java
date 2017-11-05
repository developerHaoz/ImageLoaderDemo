package com.developerhaoz.imageloaderdemo.internal.ui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.developerhaoz.imageloaderdemo.R;

/**
 * @author Haoz
 * @date 2017/10/31.
 */

public class AlbumMediaAdapter extends
        RecyclerViewCursorAdapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_CAPTURE = 0x01;
    private static final int VIEW_TYPE_MEDIA = 0x02;
    private final Drawable mPlaceholder;
    private final RecyclerView mRecyclerView;
    private int mImageResize;

    public AlbumMediaAdapter(Context context, RecyclerView recyclerView) {
        super(null);

        TypedArray ta = context.getTheme().obtainStyledAttributes(new int[]{R.attr.item_placeholder});
        mPlaceholder = ta.getDrawable(0);
        ta.recycle();

        mRecyclerView = recyclerView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         if(viewType == VIEW_TYPE_CAPTURE) {
         }

         return new RecyclerView.ViewHolder(new View(parent.getContext())) {
             @Override
             public String toString() {
                 return super.toString();
             }
         };
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor) {

    }

    @Override
    protected int getItemViewType(int position, Cursor cursor) {
        return 0;
    }
}





























