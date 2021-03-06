package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import adapterHome.Main_Adapter;
import baidumap.MyMap;
import bean.MyAppContext;
import bean.MyLog;
import broadcast_qz.LoginActivity;
import broadcasthome.Broadcast_1;
import fragmenthome.Fragment_Home;
import httpok.Okhttp_baidu;
import layoutview.Dialogue_Text;
import layoutview.Progtrss_Dialog;
import layoutview.Recycler_View;
import layoutview.UserView;
import uiview.UIActivity;

public class MainActivity extends ActivityLog  {
    private ListView mainList;
    private String[] namelist=new String[]{"Intent传参", "生命周期", "进度条、弹窗、加载框", "自定义控件", "RecycleView","聊天界面",
            "碎片","广播","广播实例（强制下机）","OKhttp请求百度","百度地图定位","标题栏设置toolbar"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Main_Adapter adapter=new Main_Adapter(this ,namelist);
        mainList.setAdapter(adapter);//Adapter适配器
        ActivityHome.addActivity(this);
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position){
                    case 0:
                        intent=new Intent(MyAppContext.getContext(), Intent_text.class);
                        startActivity(intent);
                        MyLog.d("LTY",position+"");
                        break;
                    case  1:
                        intent=new Intent(MainActivity.this, ActivityLife.class);
                        MainActivity.this.startActivity(intent);
                        MyLog.d("LTY",position+"");
                        break;
                    case 2:
                        intent=new Intent(MainActivity.this, Progtrss_Dialog.class);
                       startActivity(intent);
                        MyLog.d("LTY",position+"");
                        break;
                    case 3:
                        intent=new Intent(MainActivity.this, UserView.class);
                        startActivity(intent);
                        MyLog.d("LTY",position+"");
                        break;
                    case 4:
                        intent=new Intent(MainActivity.this, Recycler_View.class);
                       startActivity(intent);
                        MyLog.d("LTY",position+"");

                        break;
                    case 5:
                        intent =new Intent(MainActivity.this, Dialogue_Text.class);
                       startActivity(intent);
                        MyLog.d("LTY",position+"");

                        break;
                    case 6:
                        intent=new Intent(MainActivity.this, Fragment_Home.class);
                        startActivity(intent);
                        MyLog.d("LTY",position+"");

                        break;
                    case 7:
                        intent=new Intent(MainActivity.this, Broadcast_1.class);
                        startActivity(intent);
                        MyLog.d("LTY",position+"");

                        break;
                    case 8:
                        intent=new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        MyLog.d("LTY",position+"");

                        break;
                    case 9:
                        intent=new Intent(MainActivity.this, Okhttp_baidu.class);
                        startActivity(intent);
                        MyLog.d("LTY",position+"");

                        break;
                    case 10:
                        intent=new Intent(MainActivity.this, MyMap.class);
                        startActivity(intent);
                        MyLog.d("LTY",position+"");
                        break;
                    case 11:
                        intent=new Intent(MainActivity.this, UIActivity.class);
                        startActivity(intent);
                        MyLog.d("LTY",position+"");
                        break;
                    default:
                        break;
                }
            }
        });

    }

    @Override
    protected void onPause() {
        if (isFinishing()){
            namelist.clone();
        }
        super.onPause();
    }

    //Menu菜单加载
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    //Menu菜单点击事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(this, "你点击了添加", Toast.LENGTH_SHORT).show();
            break;
            case R.id.remove_item:
                Toast.makeText(this, "你点击了减少", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.logout_item:
                Toast.makeText(this, "注销成功", Toast.LENGTH_SHORT).show();
                ActivityHome.finishAll();
                break;
             default:
        }
        return true;
    }

    private void initView() {
        mainList = (ListView) findViewById(R.id.main_list);
    }


}