package com.developerhaoz.imageloaderdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * 测试 Loader 机制的 Activity
 *
 * @author Haoz
 * @date 2017/10/28.
 */

public class LoaderActivity extends AppCompatActivity {

    private LoaderManager mManager;
    private ListView mListView;
    private AlertDialog mAlertDialog;
    private SimpleCursorAdapter mAdapter;
    private static final String TAG = "LoaderActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        mListView = (ListView) findViewById(R.id.loader_lv);
        mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                null, new String[] {"name"}, new int[] {android.R.id.text1}, 0);
    }
}





















