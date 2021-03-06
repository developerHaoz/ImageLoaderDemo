package com.developerhaoz.imageloaderdemo.test;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.lang.ref.WeakReference;

/**
 * @author Haoz
 * @date 2017/11/19.
 */

public class AlbumCollectionTest implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_ID = 1;
    private static final String STATE_CURRENT_SELECTION = "state_current_selection";
    private WeakReference<Context> mContext;
    private LoaderManager mLoaderManager;
    private AlbumCallbacksTest mCallbacks;
    private int mCurrentSelection;

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Context context = mContext.get();
        if(context == null){
            return null;
        }

        return AlbumLoaderTest.newInstance(context);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        Context context = mContext.get();
        if(context == null){
            return;
        }

        mCallbacks.onAlbumLoad(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Context context = mContext.get();
        if(context == null){
            return;
        }

        mCallbacks.onAlbumReset();
    }

    public void onCreate(FragmentActivity activity, AlbumCallbacksTest callbacks){
        mContext = new WeakReference<Context>(activity);
        mLoaderManager = activity.getSupportLoaderManager();
        mCallbacks = callbacks;
    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
        if(savedInstanceState == null){
            return;
        }
        mCurrentSelection = savedInstanceState.getInt(STATE_CURRENT_SELECTION);
    }

    public void onSaveInstanceState(Bundle state){
        state.putInt(STATE_CURRENT_SELECTION, mCurrentSelection);
    }

    public void onDestroy(){
        mLoaderManager.destroyLoader(LOADER_ID);
        mCallbacks = null;
    }

    public void loadAlbums(){
        mLoaderManager.initLoader(LOADER_ID, null, this);
    }

    public int getCurrentSelection(){
        return mCurrentSelection;
    }

    public void setCurrentSelection(int currentSelection){
        this.mCurrentSelection = currentSelection;
    }

    public interface AlbumCallbacksTest{
        void onAlbumLoad(Cursor cursor);

        void onAlbumReset();
    }
}
