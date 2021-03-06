package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import layoutview.UserView2;

public class Intent_text extends ActivityLog {


    private TextView intentText1;
    private TextView intentText2;
    //向上一参数传参，接收事件
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){//requestCode:之前intent中的第二个参数
            case 1:
                if (resultCode==RESULT_OK){//判断处理结果值是否成功
                        String str=data.getStringExtra("intent_key2");
                        intentText2.setText(str);
                }
                break;
            default:
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_text);
        initView();
        UserView2.userTitle.setText("intent 传参");
        ActivityHome.addActivity(this);
        //向下一活动传参
        intentText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Intent_text.this, "点击接收", Toast.LENGTH_SHORT).show();
                String str = "你真帅！！！";
                Intent intent = new Intent(Intent_text.this, Intent_text2.class);
                intent.putExtra("intent_key", str);
                startActivity(intent);
            }
        });

        //向上一活动传参
        intentText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Intent_text.this, "点击发送", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent_text.this, Intent_text2.class);
                startActivityForResult(intent,1);//第二个参数是请求码，用于回调中判断数据来源
            }
        });

    }

    private void initView() {

        intentText1 = (TextView) findViewById(R.id.intent_text1);
        intentText2 = (TextView) findViewById(R.id.intent_text2);
    }
}