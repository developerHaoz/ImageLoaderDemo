package com.developerhaoz.imageloaderdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

import com.developerhaoz.imageloaderdemo.ui.MatisseActivity;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Set;

/**
 * @author Haoz
 * @date 2017/12/2.
 */

public final class Matisse {

    private final WeakReference<Activity> mContext;
    private final WeakReference<Fragment> mFragment;

    private Matisse(Activity activity){
        this(activity, null);
    }

    private Matisse(Fragment fragment){
        this(fragment.getActivity(), fragment);
    }

    private Matisse(Activity activity, Fragment fragment) {
        this.mContext = new WeakReference<>(activity);
        this.mFragment = new WeakReference<>(fragment);
    }

    public static Matisse from(Activity activity){
        return new Matisse(activity);
    }

    public static Matisse from(Fragment fragment){
        return new Matisse(fragment);
    }

    public static List<Uri> obtainResult(Intent data){
        return data.getParcelableArrayListExtra(MatisseActivity.EXTRA_RESULT_SELECTION);
    }

    public static List<String> obtainPathResult(Intent data){
        return data.getStringArrayListExtra(MatisseActivity.EXTRA_RESULT_SELECTION_PATH);
    }

    public SelectionCreator choose(Set<MimeType> mimeTypes){
        return choose(mimeTypes, true);
    }

    public SelectionCreator choose(Set<MimeType> mimeTypes, boolean mediaTypeExclusive){
        return new SelectionCreator(this, mimeTypes, mediaTypeExclusive);
    }

    public Activity getActivity(){
        return mContext.get();
    }

    public Fragment getFragment(){
        if(mFragment != null){
            return mFragment.get();
        }
        return null;
    }
}





















