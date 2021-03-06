package broadcasthome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 *
 * 静态广播(要再申明一个权限)
 *开机自启动 <action android:name="android.intent.action.BOOT_COMPLETED"/>
 * */
public class Broadcast_2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "静态广播，开机自启动", Toast.LENGTH_SHORT).show();
    }
}
