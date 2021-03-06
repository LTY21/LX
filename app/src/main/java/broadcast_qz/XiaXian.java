package broadcast_qz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class XiaXian extends BaseActivity {

    private Button xiaxianBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xia_xian);
        initView();
        xiaxianBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("xiaxian_lty");
                sendBroadcast(intent);
            }
        });
    }

    private void initView() {
        xiaxianBt = (Button) findViewById(R.id.xiaxian_bt);
    }
}