package broadcast_acceptor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Acceptor_two extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "有序广播", Toast.LENGTH_SHORT).show();
        abortBroadcast();//截断，这条广播结束后不在发送
    }
}
