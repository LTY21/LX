package bean;

import android.util.Log;

/**
 * 项目庞大时，一个个删除Log明显是不明智的，如果不删，又会照成产品延迟，或者信息泄露，封装一个自己Log可以很好的解决这问题
 * 定义自己的Log
 *
 * */
public class MyLog {
    public static final int VERBOSE=1;
    public static final int DEBUG=2;
    public static final int INFO=3;
    public static final int WARN=4;
    public static final int ERROR=5;
    public static final int NOTJING=6;//单产品上线时就将level设置为NOTHING
    public static int level=VERBOSE;
    public static void v(String tag,String msg){
        if (level<=VERBOSE){
            Log.v(tag, msg);
        }
    }
    public static void d(String tag,String msg){
        if (level<=DEBUG){
            Log.d(tag, msg);
        }
    }
    public static void i(String tag,String msg){
        if (level<=INFO){
            Log.i(tag, msg);
        }
    }
    public static void w(String tag,String msg){
        if (level<=WARN){
            Log.w(tag, msg);
        }
    }
    public static void e(String tag,String msg){
        if (level<=ERROR){
            Log.e(tag, msg);
        }
    }

}
