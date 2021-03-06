package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Intent_text2 extends ActivityLog {


    private TextView intentText3;
    private TextView intentText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_text2);
        initView();
        ActivityHome.addActivity(this);
        //接收数据
        intentText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str="";
                str= getIntent().getStringExtra("intent_key");
                if (str!=null) {
                    intentText4.setText(str);
                    Toast.makeText(Intent_text2.this, str, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Intent_text2.this, "无数据", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //点击返回数据给上一个活动
        intentText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("intent_key2", "你更帅!!!");
                setResult(RESULT_OK, intent1);
                finish();
                Toast.makeText(Intent_text2.this, "back", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void initView() {
        intentText3 = (TextView) findViewById(R.id.intent_text3);
        intentText4 = (TextView) findViewById(R.id.intent_text4);
    }
}