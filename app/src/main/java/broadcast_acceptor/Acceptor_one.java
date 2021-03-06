package broadcast_acceptor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 标准广播
 *
 * */
public class Acceptor_one extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "标准广播", Toast.LENGTH_SHORT).show();
    }
}
