package com.developerhaoz.imageloaderdemo.test;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import com.developerhaoz.imageloaderdemo.internal.entity.Album;
import com.developerhaoz.imageloaderdemo.internal.entity.Item;
import com.zhihu.matisse.internal.utils.MediaStoreCompat;

/**
 * @author Haoz
 * @date 2017/11/19.
 */

public class MediaLoader extends CursorLoader {

    private static final Uri QUERY_URI = MediaStore.Files.getContentUri("external");

    private static final String[] PROJECTION = {
            MediaStore.Files.FileColumns._ID,
            MediaStore.MediaColumns.DISPLAY_NAME,
            MediaStore.MediaColumns.MIME_TYPE,
            MediaStore.MediaColumns.SIZE,
            "duration"};

    private static final String SELECTION_ALL =
            "(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
                    + " OR "
                    + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?)"
                    + " AND " + MediaStore.MediaColumns.SIZE + ">0";

    private static final String[] SELECTION_ALL_ARGS = {
            String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE),
            String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO)
    };

    private static final String ORDER_BY = MediaStore.Images.Media.DATE_TAKEN + " DESC";
    private final boolean mEnableCapture;


    private MediaLoader(Context context, String selection, String[] selectionArgs, boolean capture) {
        super(context, QUERY_URI, PROJECTION, selection, selectionArgs, ORDER_BY);
        mEnableCapture  = capture;
    }

    public static CursorLoader newInstance(Context context, Album album, boolean capture){
        String selection;
        String[] selectionArgs;
        boolean enableCapture;

        selection = SELECTION_ALL;
        selectionArgs = SELECTION_ALL_ARGS;
        enableCapture = capture;

        return new MediaLoader(context, selection, selectionArgs, enableCapture);
    }

    @Override
    public Cursor loadInBackground() {
        Cursor result = super.loadInBackground();
        if(!mEnableCapture || !MediaStoreCompat.hasCameraFeature(getContext())){
            return result;
        }

        MatrixCursor dummy = new MatrixCursor(PROJECTION);

        // ITEM_ID_CAPTURE = -1, ITEM_DISPLAY_NAME_CAPTURE = "Capture";
        dummy.addRow(new Object[]{Item.ITEM_ID_CAPTURE, Item.ITEM_DISPLAY_NAME_CAPTURE, "", 0, 0});
        return new MergeCursor(new Cursor[]{dummy, result});
    }
}

























