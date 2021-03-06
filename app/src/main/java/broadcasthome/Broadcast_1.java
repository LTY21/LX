package broadcasthome;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.myapplication.R;

/**
 * 动态广播：动态注册，静态广播：静态注册
 * 标准广播：全部发送，无法截断
 * 有序广播：有序发送，注册时决定先后顺序
 * 本地广播: 只能动态注册，自己内部传递
 */
public class Broadcast_1 extends AppCompatActivity implements View.OnClickListener {

    private IntentFilter intentFilter;
    private IntentFilter intentFilter_bd;
    private Guangbo guangbo;
    private Button bzBroadcast;
    private Button yxBroadcast;
    private LocalBroadcastManager localBroadcastManager;//使用本地广播
    private Button bdBroadcast;
    private BenDiBroadcast bendi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_1);
        guangbo = new Guangbo();
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(guangbo, intentFilter);
        initView();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        bzBroadcast.setOnClickListener(this);
        yxBroadcast.setOnClickListener(this);
        bdBroadcast.setOnClickListener(this);

        intentFilter_bd=new IntentFilter();
        intentFilter_bd.addAction("BENDI");
        bendi=new BenDiBroadcast();
        localBroadcastManager.registerReceiver(bendi,intentFilter_bd);
    }

    private void initView() {
        bzBroadcast = (Button) findViewById(R.id.bz_broadcast);
        yxBroadcast = (Button) findViewById(R.id.yx_broadcast);
        bdBroadcast = (Button) findViewById(R.id.bd_broadcast);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.bz_broadcast:
//                如果是再同一个包内接收广播，在发送广播时需要添加接收的广播的完整路径和类名
//                Intent intent=new Intent();
//                intent.setComponent(new ComponentName("broadcast_acceptor","broadcast_acceptor.Acceptor_one"));
//                sendBroadcast(intent);

                //如果是需要在不同的包里接收，两个及以上的module，需要修改代码如下：
                intent = new Intent("text_one");
                if (Build.VERSION.SDK_INT >= 26) {
                    intent.addFlags(0x01000000);
                }
                sendBroadcast(intent);

                break;
            case R.id.yx_broadcast:
//                如果是再同一个包内接收广播，在发送广播时需要添加接收的广播的完整路径和类名
//                Intent intent=new Intent();
//                intent.setComponent(new ComponentName("broadcast_acceptor","broadcast_acceptor.Acceptor_one"));
//                sendOrderedBroadcast(intent,null);

                //如果是需要在不同的包里接收，两个及以上的module，需要修改代码如下：
                intent = new Intent("text_two");
                if (Build.VERSION.SDK_INT >= 26) {
                    intent.addFlags(0x01000000);
                }
                sendOrderedBroadcast(intent, null);

                break;
            case R.id.bd_broadcast:
                intent=new Intent("BENDI");
                localBroadcastManager.sendBroadcast(intent);
                break;

            default:
                break;
        }
    }


    private class Guangbo extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {


            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //Build.VERSION.SDK_INT>=23版本判断
            if (Build.VERSION.SDK_INT >= 23) {
                NetworkCapabilities nc = cm.getNetworkCapabilities(cm.getActiveNetwork());
                if (nc != null) {
                    Toast.makeText(context, "有网", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "无网", Toast.LENGTH_SHORT).show();
                }
            } else {
                NetworkInfo nwif = cm.getActiveNetworkInfo();
                if (nwif != null) {
                    Toast.makeText(context, "有网", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "无网", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(guangbo);
        localBroadcastManager.unregisterReceiver(bendi);
    }

    private class BenDiBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "本地广播", Toast.LENGTH_SHORT).show();
        }
    }
}