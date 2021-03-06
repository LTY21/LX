package baidumap;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MyMap2 extends AppCompatActivity {

    private WebView mwebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_map2);
        initView();
        mwebview.loadUrl("http://baidu.com");
    }

    private void initView() {
        mwebview = (WebView) findViewById(R.id.mwebview);
    }
}