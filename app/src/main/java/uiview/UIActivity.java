package uiview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import adapterHome.Fruit2Adapter;
import bean.Fruit2;

/**
 * UI体验：标题栏toolbar、滑动菜单DrawerLayout,NavigationView，
 * 悬浮按钮FloatingActionButton和可交互提示Snackbar
 * AppBarLayout一个垂直方向的LinearLayout,里面有许多的滚动封装
 * CoordinatorLayout加强版的FrameLayout(UI界面直接调用)
 * CaedView一个提供圆角和阴影的控件
 * SwipeRefreshLayout下拉刷新
 */
public class UIActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toobarV;
    private NavigationView navView;
    private FloatingActionButton fab;
    private Fruit2[] fruit2s = {
            new Fruit2("花1", R.drawable.flowera), new Fruit2("花2", R.drawable.flowerb),
            new Fruit2("花3", R.drawable.flowerc), new Fruit2("花4", R.drawable.flowerd),};
    private List<Fruit2> list = new ArrayList<>();
    private Fruit2Adapter fruit2Adapter;
    private RecyclerView recyclerImg;
    private SwipeRefreshLayout swipeRefresh;
    private boolean isdata = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_i);
        initView();
        //下拉刷新监听
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //这里应该是请求数据与网络交互，没啥合适的我就写本地了
                qingqiu();
            }
        });

        //数据添加
        initFruit2();
        //适配
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerImg.setLayoutManager(layoutManager);
        fruit2Adapter = new Fruit2Adapter(list);
        recyclerImg.setAdapter(fruit2Adapter);
        //悬浮按钮监听
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "删除", Snackbar.LENGTH_SHORT).setAction("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(UIActivity.this, "恢复", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
        //菜单键设置
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.a4);
        }
        //滑动菜单里的。默认选中
        navView.setCheckedItem(R.id.geren);
        //滑动菜单里的，菜单监听
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.geren:
                        Toast.makeText(UIActivity.this, "个人信息", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.qiye:
                        Toast.makeText(UIActivity.this, "企业信息", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.xiaoxi:
                        Toast.makeText(UIActivity.this, "消息通知", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.yaoqing:
                        Toast.makeText(UIActivity.this, "邀请分享", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    //请求数据与加载
    private void qingqiu() {
        if (isdata == true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initFruit2();
                            fruit2Adapter.notifyDataSetChanged();
                            //刷新结束，并隐藏进度条
                            swipeRefresh.setRefreshing(false);
                        }
                    });
                }
            }).start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isdata = false;
    }

    private void initFruit2() {
        list.clear();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int index = random.nextInt(fruit2s.length);
            list.add(fruit2s[index]);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tool_1:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tool_2:
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tool_3:
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                Toast.makeText(this, "错误", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toobarV = (Toolbar) findViewById(R.id.toobar_v);
        setSupportActionBar(toobarV);
        navView = (NavigationView) findViewById(R.id.nav_view);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerImg = (RecyclerView) findViewById(R.id.recycler_img);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
    }
}