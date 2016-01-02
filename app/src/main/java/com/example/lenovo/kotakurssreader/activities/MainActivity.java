package com.example.lenovo.kotakurssreader.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.lenovo.kotakurssreader.R;
import com.example.lenovo.kotakurssreader.common.CommonUtils;
import com.example.lenovo.kotakurssreader.fragments.RssNewsFragment;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CommonUtils.startFragmentSlideVerticalDownUp(new RssNewsFragment(), R.id.container, getSupportFragmentManager());
    }
}
