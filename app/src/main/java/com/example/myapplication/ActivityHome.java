package com.example.myapplication;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityHome {
    public static List<Activity>listActivity=new ArrayList<>();
    public static void addActivity(Activity activity){
        listActivity.add(activity);
    }
    //退出某一个活动
    public  static void removeActivity(Activity activity){
        listActivity.remove(activity);
    }
    //退出程序
    public static void finishAll(){
        for (Activity activity:listActivity){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        listActivity.clear();
    }
    

}
