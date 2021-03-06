package uiview;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

/**
 * 可折叠式标题栏
 */
public class UIActivity_XiangQing extends AppCompatActivity {

    private AppBarLayout xqAppbar;
    private ImageView xqImage;
    private Toolbar xqToolbar;
    private TextView qxText;

    public static final String F_NANE = "f_name";
    public static final String F_ID = "f_id";
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_i__xiang_qing);
        initView();
        //获取图片和ID
        Intent intent = getIntent();
        String f_intent = intent.getStringExtra(F_NANE);
        int f_imageid = intent.getIntExtra(F_ID, 0);
        setSupportActionBar(xqToolbar);
        //菜单键设置
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(f_intent);
        Glide.with(this).load(f_imageid).into(xqImage);
        //将得到的内容设置为标题
        String f_content = generateFruitContent(f_intent);
        qxText.setText(f_content);
    }

    //添加数据
    private String generateFruitContent(String f_intent) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            stringBuilder.append(f_intent);
        }
        return stringBuilder.toString();
    }
    //处理HomeAsUP的点击事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        xqAppbar = (AppBarLayout) findViewById(R.id.xq_appbar);
        xqImage = (ImageView) findViewById(R.id.xq_image);
        xqToolbar = (Toolbar) findViewById(R.id.xq_toolbar);
        qxText = (TextView) findViewById(R.id.qx_text);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
    }
}