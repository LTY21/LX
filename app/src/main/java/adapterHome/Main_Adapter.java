package adapterHome;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * ListView布局
 * */
public class Main_Adapter extends BaseAdapter {
    private String[] namelist;//数据源
    private Context context;//上下文
    private LayoutInflater li;//布局装载对象

    // 通过构造方法将数据源与数据适配器关联起来
    // MainActivity:要使用当前的Adapter的界面对象
    public Main_Adapter(Context context, String[] namelist){
        this.context=context;
        li=LayoutInflater.from(context);
        this.namelist=namelist;

    }
    //ListView需要显示的数据数量
    @Override
    public int getCount() {
        return namelist.length;
    }
    //指定的索引对应的数据项
    @Override
    public Object getItem(int position) {
        return null;
    }

    //指定的索引对应的数据项ID
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //返回每一项的显示内容
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final View view;
        final ViewHandler viewHandler;
        if (convertView==null){
            view= li.inflate(R.layout.main_list,null);
            viewHandler=new ViewHandler();
            viewHandler.mainButton=view.findViewById(R.id.main_button);
            view.setTag(viewHandler);
        }else {
            view=convertView;
            viewHandler= (ViewHandler) view.getTag();

        }
        viewHandler.mainButton.setText(namelist[position]);
        return view;
    }

    private class ViewHandler {
            TextView mainButton;
    }
}
