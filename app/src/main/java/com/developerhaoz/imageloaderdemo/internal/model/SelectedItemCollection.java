package com.developerhaoz.imageloaderdemo.internal.model;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.developerhaoz.imageloaderdemo.internal.entity.Item;
import com.developerhaoz.imageloaderdemo.internal.entity.SelectionSpec;
import com.developerhaoz.imageloaderdemo.internal.utils.PathUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * {@link Item}的容器
 *
 * @author Haoz
 * @date 2017/11/30.
 */

public class SelectedItemCollection {

    public static final String STATE_SELECTION = "state_selection";
    public static final String STATE_COLLECTION_TYPE = "state_collection_type";

    public static final int COLLECTION_UNDEFINED = 0x00;

    public static final int COLLECTION_IMAGE = 0x01;

    public static final int COLLECTION_VIDEO = 0x01 << 1;

    public static final int COLLECTION_MIXED = COLLECTION_IMAGE | COLLECTION_VIDEO;
    private final Context mContext;
    private Set<Item> mItems;
    private int mCollectionType = COLLECTION_UNDEFINED;

    public SelectedItemCollection(Context context) {
        mContext = context;
    }

    public void onCreate(Bundle bundle) {
        if (bundle == null) {
            mItems = new LinkedHashSet<>();
        } else {
            List<Item> saved = bundle.getParcelableArrayList(STATE_SELECTION);
            mItems = new LinkedHashSet<>(saved);
            mCollectionType = bundle.getInt(STATE_COLLECTION_TYPE, COLLECTION_UNDEFINED);
        }
    }

    public void setDefaultSelection(List<Item> uris) {
        mItems.addAll(uris);
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(STATE_SELECTION, new ArrayList<>(mItems));
        outState.putInt(STATE_COLLECTION_TYPE, mCollectionType);
    }

    public Bundle getDataWithBundle() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(STATE_SELECTION, new ArrayList<>(mItems));
        bundle.putInt(STATE_COLLECTION_TYPE, mCollectionType);
        return bundle;
    }

    public boolean add(Item item) {
        if (typeConflict(item)) {
            throw new IllegalArgumentException("Can't select images and videos at the same time");
        }

        boolean added = mItems.add(item);
        if (added) {
            if (mCollectionType == COLLECTION_UNDEFINED) {
                if (item.isImage()) {
                    mCollectionType = COLLECTION_IMAGE;
                } else if (item.isVideo()) {
                    mCollectionType = COLLECTION_VIDEO;
                }
            }else if(mCollectionType == COLLECTION_IMAGE){
                if(item.isVideo()) {
                    mCollectionType = COLLECTION_MIXED;
                }
            }else if(mCollectionType == COLLECTION_VIDEO){
                if(item.isImage()){
                    mCollectionType = COLLECTION_MIXED;
                }
            }
        }
        return added;
    }

    public boolean remove(Item item){
        boolean removed = mItems.remove(item);
        if(removed){
            if(mItems.size() == 0){
                mCollectionType = COLLECTION_UNDEFINED;
            }else {
                if(mCollectionType == COLLECTION_MIXED){
                    refineCollectionType();
                }
            }
        }
        return removed;
    }

    public void overwrite(ArrayList<Item> items, int collectionType){
        if(items.size() == 0){
            mCollectionType = COLLECTION_UNDEFINED;
        }else {
            mCollectionType = collectionType;
        }

        mItems.clear();
        mItems.addAll(items);
        refineCollectionType();
    }

    public List<Item> asList() {
        return new ArrayList<>(mItems);
    }

    public List<Uri> asListOfUri() {
        List<Uri> uris = new ArrayList<>();
        for (Item item : mItems) {
            uris.add(item.getContentUri());
        }
        return uris;
    }

    public List<String> asListOfString(){
        List<String> paths = new ArrayList<>();
        for (Item item : mItems) {
            paths.add(PathUtils.getPath(mContext, item.getContentUri()));
        }
        return paths;
    }

    public boolean isEmpty(){
        return mItems == null || mItems.isEmpty();
    }

    public boolean isSelected(Item item){
        return mItems.contains(item);
    }

//    public IncapableCause isAcceptable(Item item){
//        if(maxSelectableReached()){
//            int maxSelectable = SelectionSpec.getInstance().maxSelectable;
//            String cause;
//
//            cause = mContext.getString(
//                    R.string.error_over_count,
//                    maxSelectable);
//            )
//
//            return new IncapableCause(cause);
//        }else if(typeConflict(item)){
//            return new IncapableCause(mContext.getString(R.string.error_type_conflict));
//        }
//
//        return
//    }

    public boolean maxSelectableReached(){
        return mItems.size() == SelectionSpec.getInstance().maxSelectable;
    }

    private void refineCollectionType(){
        boolean hasImage = false;
        boolean hasVideo = false;
        for (Item item : mItems) {
            if(item.isImage() && !hasImage) hasImage = true;
            if(item.isVideo() && !hasVideo) hasVideo = true;
        }

        if(hasImage && hasVideo){
            mCollectionType = COLLECTION_MIXED;
        }else if(hasImage) {
            mCollectionType = COLLECTION_IMAGE;
        } else if(hasVideo) {
            mCollectionType = COLLECTION_VIDEO;
        }
    }

    public boolean typeConflict(Item item) {
        return SelectionSpec.getInstance().mediaTypeExclusive
                && ((item.isImage() && (mCollectionType == COLLECTION_VIDEO || mCollectionType == COLLECTION_MIXED))
                || (item.isVideo() && (mCollectionType == COLLECTION_IMAGE || mCollectionType == COLLECTION_MIXED)));
    }
}



















