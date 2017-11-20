package com.developerhaoz.imageloaderdemo.test;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.lang.ref.WeakReference;

/**
 * @author Haoz
 * @date 2017/11/19.
 */

public class AlbumCollectionTest extends LoaderManager.LoaderCallbacks<Cursor> {

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
            return;
        }

        mCallbacks.onAlbumLoad();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public interface AlbumCallbacksTest{
        void onAlbumLoad(Cursor cursor);

        void onAlbumReset();
    }
}
