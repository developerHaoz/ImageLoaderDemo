package com.developerhaoz.imageloaderdemo.internal.loader;

import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

/**
 * @author Haoz
 * @date 2017/11/11.
 */

public class AlbumMediaLoader extends CursorLoader {

    private static final Uri QUERY_URI = MediaStore.Files.getContentUri("external");
    private static final String[] PROJECTION = {
            MediaStore.Files.FileColumns._ID,
            MediaStore.MediaColumns.DISPLAY_NAME,
            MediaStore.MediaColumns.DISPLAY_NAME,
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

    private static final String SELECTION_ALL_FOR_SINGLE_MEDIA_TYPE =
            MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
                    + " AND " + MediaStore.MediaColumns.SIZE + ">0";

    private static String[] getSelectionArgsForSingleMediaType(int mediaType) {
        return new String[]{String.valueOf(mediaType)};
    }

    private static final String SELECTION_ALBUM =
            "(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
            + " OR "
            + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?)"
            + " AND "
            + " bucket_id=?"
            + " AND " + MediaStore.MediaColumns.SIZE + ">0";

    private static String[] getSelectionAllbumArgs(String albumId){
        return new String[]{
                String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE),
                String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO),
                albumId
        };
    }

    private static final String SELECTION_ALBUM_FOR_SINGLE_MEDIA_TYPE =
            MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
            + " AND "
            + " bucket_id =?"
            + " AND " + MediaStore.MediaColumns.SIZE + ">0";

    private static String[] getSelectionAlnumArgsForSingleMediaType(int mediaType, String albumId){
        return new String[]{String.valueOf(mediaType), albumId};
    }

    private static final String ORDER_BY = MediaStore.Images.Media.DATE_TAKEN + " DESC";
    private final boolean mEnableCapture;

    private AlbumMediaLoader(Context context,String selection, String[] selectionArgs, boolean capture){
        super(context, QUERY_URI, PROJECTION, selection, selectionArgs, ORDER_BY);
        mEnableCapture = capture;
    }

//    public static CursorLoader newInstance(Context context, Album album, boolean capture){
//        String selection;
//        String[] selectionArgs;
//        boolean enableCapture;
//
//        if(album.isAll()){
//            if(SelectionSpec.getInstance().onlyShowImages()){
//                selection = SELECTION_ALL_FOR_SINGLE_MEDIA_TYPE;
//                selectionArgs = getSelectionArgsForSingleMediaType(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE);
//            }else if (SelectionSpec.getInstance().onlyShowVideos()){
//                selection = SELECTION_ALL_FOR_SINGLE_MEDIA_TYPE;
//                selectionArgs = getSelectionArgsForSingleMediaType(MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO);
//            }else{
//                selection = SELECTION_ALL;
//                selectionArgs = SELECTION_ALL_ARGS;
//            }
//            enableCapture = capture;
//        }else {
//            if(SelectionSpec.getInstance().onlyShowImages()){
//
//            }
//        }
//    }
}























