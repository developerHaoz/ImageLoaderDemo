package com.developerhaoz.imageloaderdemo.internal.entity;

import android.content.pm.ActivityInfo;
import android.support.annotation.StyleRes;

import com.developerhaoz.imageloaderdemo.MimeType;
import com.developerhaoz.imageloaderdemo.R;
import com.developerhaoz.imageloaderdemo.engine.ImageEngine;
import com.developerhaoz.imageloaderdemo.engine.impl.GlideEngine;

import java.util.List;
import java.util.Set;
import java.util.logging.Filter;

/**
 * @author Haoz
 * @date 2017/11/11.
 */

public class SelectionSpec {

    public Set<MimeType> mimeTypeSet;
    public boolean mediaTypeExclusive;
    public boolean showSingleMediaType;
    @StyleRes
    public int themeId;
    public int orientation;
    public boolean countable;
    public int maxSelectable;
    public List<Filter> filters;
    public boolean capture;
    public CaptureStrategy captureStrategy;
    public int spanCount;
    public int gridExpectedSize;
    public float thumbnailScale;
    public ImageEngine imageEngine;

    private SelectionSpec(){}

    public static SelectionSpec getInstance(){
        return InstanceHolder.INSTANCE;
    }

    public static SelectionSpec getCleanInstance(){
        SelectionSpec selectionSpec = getInstance();
        selectionSpec.reset();
        return selectionSpec;
    }

    private void reset() {
        mimeTypeSet = null;
        mediaTypeExclusive = true;
        showSingleMediaType = false;
        themeId = R.style.Matisse_Zhihu;
        orientation = 0;
        countable = false;
        maxSelectable = 1;
        filters = null;
        capture = false;
        captureStrategy  = null;
        spanCount = 3;
        gridExpectedSize = 0;
        thumbnailScale = 0.5f;
        imageEngine = new GlideEngine();
    }

    public boolean singleSelectionModeEnabled(){
        return !countable && maxSelectable == 1;
    }

    public boolean needOrientationRestriction(){
        return orientation != ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
    }

    public boolean onlyShowImages(){
        return showSingleMediaType && MimeType.ofImage().contains(mimeTypeSet);
    }

    public boolean onlyShowVideos(){
        return showSingleMediaType && MimeType.ofVideo().containsAll(mimeTypeSet);
    }

    public static final class InstanceHolder{
        public static final SelectionSpec INSTANCE = new SelectionSpec();
    }
}























