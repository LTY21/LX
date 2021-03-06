package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityLife extends ActivityLog {
    private Button lifeButton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life);
        initView();
        ActivityHome.addActivity(this);
        Log.i("life","onCreate:活动第一次被创建时调用,完成初始化操作");
        lifeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityLife.this,ActivityLife2.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        Log.i("life","onStart:由可见变为不可见时调用");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i("life","onResume:活动准备好和用户进行交互的时候调用，此活动一定位于栈顶，并且属于运行状态");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("life","onPause:系统准备去启动或者回复另一个活动时调用,通常会在此方法中将一些消耗CPU的资源释放，以及保存一些关键数据,但是这方法执行速度要快，否则影响新的栈顶活动的使用");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("life","onStop:活动完全不可见时调用，于onPause区别在于如果启动活动是一个对话框式的活动，那么onPause会执行,onStop不会");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("life","onDestroy:活动销毁前调用,之后为销毁状态");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.i("life","onRestart:活动由停止状态变为运行状态之前调用，即活动被重新启动 ");
        super.onRestart();
    }

    private void initView() {
        lifeButton1 = (Button) findViewById(R.id.life_button1);
    }
}