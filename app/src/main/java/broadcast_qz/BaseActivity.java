package broadcast_qz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private Anew anew;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("xiaxian_lty");
        anew=new Anew();
        registerReceiver(anew,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anew!=null){{
        unregisterReceiver(anew);}
        anew=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    //强制下线，重新登录（广播）
    private class Anew extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {
            AlertDialog.Builder dialog=new AlertDialog.Builder(context);
            dialog.setTitle("强制下线");
            dialog.setMessage("emmm。。。。，要维护了，就这样");
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finshAll();
                    Intent i=new Intent(context,LoginActivity.class);
                    context.startActivity(i);
                }
            });
            dialog.show();
        }
    }
}
