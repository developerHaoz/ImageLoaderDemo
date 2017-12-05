package com.developerhaoz.imageloaderdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * @author Haoz
 * @date 2017/12/3.
 */

public class TestActivity extends AppCompatActivity {

    private PopupWindow mPopupWindow;
    private Button mBtnHello;

    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mBtnHello = (Button) findViewById(R.id.test_btn_hello);
        Log.d(TAG, "onCreate: " + "hello");
        mBtnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this, "Hello world", Toast.LENGTH_SHORT).show();
                showPopupWindow();
            }
        });
    }

    private void showPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_test, null, false);
        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);

        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        mPopupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);

    }
}















