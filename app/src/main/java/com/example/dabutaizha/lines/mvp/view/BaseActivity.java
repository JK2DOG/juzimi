package com.example.dabutaizha.lines.mvp.view;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.example.dabutaizha.lines.ActivityManager;
import com.example.dabutaizha.lines.R;
import com.example.dabutaizha.lines.ResUtil;
import com.example.dabutaizha.lines.wxapi.AppThemeUtils;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;

/**
 * Copyright (C) 2017 Unicorn, Inc.
 * Description :
 * Created by dabutaizha on 2017/11/29 下午7:36.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(getPageLayoutID());

        //隐藏状态栏
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActivityManager.addActivity(this);
        ButterKnife.bind(this);
        initData();
        initView();
        initViewListener();
        process();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initTheme(AppThemeUtils.getCurrentAppTheme());
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initViewListener();

    protected abstract void initTheme(int themeId);

    protected abstract void process();

    protected abstract int getPageLayoutID();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
        ActivityManager.removeActivity(this);
    }
}
