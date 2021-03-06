package layoutview;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

/**
 * 自定义控件
 */
public class UserView2 extends LinearLayout {
    private Button titleBack;
    private Button titleEdit;
    public static TextView userTitle;

    public UserView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.user_view, this);
        initView();
        //加载布局后就得获取控件，别再犯了，靠!!!2020 1019
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //((Activity)getContext())获取当前活动的Activity
                ((Activity) getContext()).finish();

            }
        });
        titleEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //context是指当前类的上下文，而这需要获取的是目前Activity的上下文
                Toast.makeText(getContext(), "点击成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        titleBack = (Button) findViewById(R.id.title_back);
        titleEdit = (Button) findViewById(R.id.title_edit);
        userTitle = (TextView) findViewById(R.id.user_title);
    }
}
