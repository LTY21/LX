package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
/**
 * 查看当前活动的Activity
 * */
public class ActivityLog extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("Activity",getClass().getSimpleName());
        super.onCreate(savedInstanceState);
    }
}
