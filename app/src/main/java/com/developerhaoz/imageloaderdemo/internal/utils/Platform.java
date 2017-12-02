package com.developerhaoz.imageloaderdemo.internal.utils;

import android.os.Build;

/**
 * @author Haoz
 * @date 2017/12/1.
 */

public class Platform {

    public static boolean hasICS(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    public static boolean hasKitKat(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }
}
















