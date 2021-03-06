package layoutview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.ActivityHome;
import com.example.myapplication.ActivityLog;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import adapterHome.RecyclerView_Adapter;
import bean.Fruit;
/**
 * Recyclerview适配器的基本应用
 * */
public class Recycler_View extends ActivityLog {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<Fruit> fruitList = new ArrayList<>();
    private Button addRecycler;
    private Button removeRecycler;

    @Override
    protected void onPause() {
        if (isFinishing()){
            fruitList.clear();
        }
        super.onPause();
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view);
        initView();
        ActivityHome.addActivity(this);
        //添加数据
        for (int i = 0; i < 5; i++) {
            Fruit fruit1 = new Fruit("花1", R.drawable.flowera);
            fruitList.add(fruit1);
            Fruit fruit2 = new Fruit("花2", R.drawable.flowerb);
            fruitList.add(fruit2);
            Fruit fruit3 = new Fruit("花3", R.drawable.flowerc);
            fruitList.add(fruit3);
            Fruit fruit4 = new Fruit("花4", R.drawable.flowerd);
            fruitList.add(fruit4);
            Fruit fruit5 = new Fruit("花5", R.drawable.flowere);
            fruitList.add(fruit5);
        }

        //瀑布布局
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);


        //垂直方向
        //创建线性布局
//        layoutManager = new LinearLayoutManager(this);
//        //给RecyclerView设置布局管理器
//        recyclerView.setLayoutManager(layoutManager);

        //水平布局
//        layoutManager=new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(layoutManager);


        //创建适配器，并且设置
        final RecyclerView_Adapter adapter = new RecyclerView_Adapter(this, fruitList);
        recyclerView.setAdapter(adapter);

        //添加数据
        addRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fruit fruit6 = new Fruit("花5", R.drawable.flowerf);
                fruitList.add(fruit6);
                adapter.notifyItemInserted(fruitList.size());
            }
        });
        //删除数据
        removeRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fruitList.size()==0){
                    Toast.makeText(Recycler_View.this, "没了", Toast.LENGTH_SHORT).show();
                }else {
                    for (int i=0;i<fruitList.size();i++){
                        if (i==fruitList.size()-1){
                            fruitList.remove(i);
                            adapter.notifyItemRemoved(i);
                            if (i != fruitList.size())
                                adapter.notifyItemRangeChanged(i,fruitList.size() - i);
                        }
                    }
                }
            }
        });

    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        addRecycler = (Button) findViewById(R.id.add_recycler);
        removeRecycler = (Button) findViewById(R.id.remove_recycler);
    }
}