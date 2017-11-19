package com.developerhaoz.imageloaderdemo.test;

import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;

/**
 * @author Haoz
 * @date 2017/11/19.
 */

public abstract class BaseCursorAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private Cursor mCursor;
    private int mRowIDColumn;

    BaseCursorAdapter(Cursor cursor) {
        setHasStableIds(true);
        swapCursor(cursor);
    }

    protected abstract void onBindViewHolder(VH holder, Cursor cursor);

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (!isDataValid(mCursor)) {
            throw new IllegalStateException("Cannot no bind view holder when cursor is in invalid state");
        }

        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("Could not move cursor to position " + position
                    + " when trying to bind view holder");
        }
        onBindViewHolder(holder, mCursor);
    }

    protected abstract int getItemViewType(int position, Cursor mCursor);

    @Override
    public int getItemViewType(int position) {
        if(!mCursor.moveToPosition(position)){
            throw new IllegalStateException("Could not move cusrso to position " +
            " when trying to get item view type");
        }
        return getItemViewType(position, mCursor);
    }

    @Override
    public int getItemCount() {
        if(isDataValid(mCursor)){
            return mCursor.getCount();
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        if(!isDataValid(mCursor)){
            throw new IllegalStateException("Cannot lookup item id when cursor is in invalid state.");
        }

        if(!mCursor.moveToPosition(position)){
            throw new IllegalStateException("Could not move cursor to position " + position
            + " when trying to get an item id");
        }

        return mCursor.getLong(mRowIDColumn);
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor == newCursor) {
            return;
        }

        if(mCursor != null){
            mCursor = newCursor;
            mRowIDColumn = mCursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID);
            notifyDataSetChanged();
        }else{
            notifyItemRangeRemoved(0, getItemCount());
            mCursor = null;
            mRowIDColumn = -1;
        }
    }

    public Cursor getCursor() {
        return mCursor;
    }

    private boolean isDataValid(Cursor cursor) {
        return cursor != null && !cursor.isClosed();
    }
}