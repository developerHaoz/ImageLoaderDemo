package com.developerhaoz.imageloaderdemo.internal.entity;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.developerhaoz.imageloaderdemo.internal.ui.widget.IncapableDialog;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Haoz
 * @date 2017/12/1.
 */
@SuppressWarnings("unused")
public class IncapableCause {

    public static final int TOAST = 0x00;
    public static final int DIALOG = 0x01;
    public static final int NONE = 0x02;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TOAST, DIALOG, NONE})
    public @interface Form{
    }

    private int mForm = TOAST;
    private String mTitle;
    private String mMessage;

    public IncapableCause(String message){
        this.mMessage = message;
    }

    public IncapableCause(String title, String message){
        this.mTitle = title;
        this.mMessage = message;
    }

    public IncapableCause(@Form int form, String message){
        this.mForm = form;
        this.mMessage = message;
    }

    public IncapableCause(@Form int form, String title, String message){
        this.mForm = form;
        this.mTitle = title;
        this.mMessage = message;
    }

    public static void handleCause(Context context, IncapableCause cause){
        if(cause == null){
            return;
        }

        switch (cause.mForm){
            case NONE:
                // do nothing.
                break;
            case DIALOG:
                IncapableDialog incapableDialog = IncapableDialog.newInstance(cause.mTitle, cause.mMessage);
                incapableDialog.show(((FragmentActivity) context).getSupportFragmentManager(),
                        IncapableDialog.class.getSimpleName());
                break;
            case TOAST:
                Toast.makeText(context, cause.mMessage, Toast.LENGTH_SHORT).show();
                break;
        }
    }

}























