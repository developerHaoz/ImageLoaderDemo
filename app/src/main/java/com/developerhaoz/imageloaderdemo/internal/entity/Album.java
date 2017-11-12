package com.developerhaoz.imageloaderdemo.internal.entity;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;

import com.developerhaoz.imageloaderdemo.R;
import com.zhihu.matisse.internal.loader.AlbumLoader;

/**
 * 文件夹的实体类
 *
 * @author Haoz
 * @date 2017/11/11.
 */

public class Album implements Parcelable {

    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel source) {
            return new Album(source);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public static final String ALBUM_ID_ALL = String.valueOf(-1);
    public static final String ALBUM_NAME_ALL = "All";

    private final String mId;
    private final String mCoverPath;
    private final String mDisplayName;
    private long mCount;

    Album(String id, String coverPath, String albumName, long count) {
        mId = id;
        mCoverPath = coverPath;
        mDisplayName = albumName;
        mCount = count;
    }


    Album(Parcel in) {
        this.mId = in.readString();
        this.mCoverPath = in.readString();
        this.mDisplayName = in.readString();
        this.mCount = in.readLong();
    }

    public static Album valueOf(Cursor cursor) {
        return new Album(
                cursor.getString(cursor.getColumnIndex("bucket_id")),
                cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA)),
                cursor.getString(cursor.getColumnIndex("bucket_display_name")),
                cursor.getLong(cursor.getColumnIndex(AlbumLoader.COLUMN_COUNT)));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mId);
        dest.writeString(this.mCoverPath);
        dest.writeString(this.mDisplayName);
        dest.writeLong(this.mCount);
    }

    public String getId() {
        return mId;
    }

    public String getCoverPath() {
        return mCoverPath;
    }

    public String getDisplayName(Context context) {
        if(isAll()){
            return context.getString(R.string.album_name_all);
        }
        return mDisplayName;
    }

    public long getCount() {
        return mCount;
    }

    public void addCaptureCount(){
        mCount++;
    }

    public boolean isEmpty(){
        return mCount ==0;
    }

    /**
     * 兄弟，id = -1，究竟代表什么
     * emmm,好像代表所有的专辑
     *
     * @return
     */
    public boolean isAll(){
        return ALBUM_ID_ALL.equals(mId);
    }
}

















