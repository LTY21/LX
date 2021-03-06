package httpok;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Okhttp_baidu extends AppCompatActivity implements View.OnClickListener {
    private Button baiduButton;
    private TextView baiduText;
    private boolean aBoolean=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_baidu);
        initView();
        baiduButton.setOnClickListener(this);
    }

    private void initView() {
        baiduButton = (Button) findViewById(R.id.baidu_button);
        baiduText = (TextView) findViewById(R.id.baidu_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.baidu_button:
                qingqiu();
                break;
            default:
                break;
        }
    }

    private void qingqiu() {
        Log.d("LTY",aBoolean+"");
        if (aBoolean==true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder().url("http://www.baidu.com").build();
                    try {
                        Response response= okHttpClient.newCall(request).execute();
                        final String str = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                baiduText.setText(str);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    protected void onDestroy() {
        aBoolean=false;
        super.onDestroy();
    }
}