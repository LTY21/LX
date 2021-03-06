package layoutview;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;

import com.example.myapplication.ActivityHome;
import com.example.myapplication.ActivityLog;
import com.example.myapplication.R;

/**
 * 自定义控件:可复用式标题栏（虽然android有自带的，但是这个可以更改样式，并且很常用）
 * 引入布局
 * */
public class UserView extends ActivityLog {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);
        //隐藏标题栏
        ActivityHome.addActivity(this);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
    }
}