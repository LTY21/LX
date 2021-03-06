package adapterHome;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

import bean.Fruit;

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.ViewHander> {
    private LayoutInflater li;
    private List<Fruit> fruitList;

    private Context content;
    public RecyclerView_Adapter(Context context, List<Fruit> fruitList){
        this.fruitList=fruitList;
        this.content=context;
        li=LayoutInflater.from(context);
    }
    @NonNull
    /**
     * item显示类型
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHander onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view=li.inflate(R.layout.recycler_item1,parent,false);
        ViewHander viewHander=new ViewHander(view);
        return viewHander;
    }

    /**
     * 数据的绑定显示
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final ViewHander holder, int position) {
        Fruit fruit=fruitList.get(position);
        holder.textView.setText(fruit.getName()+" 第"+position+"张");
        holder.imgs.setImageResource(fruit.getImage());
        holder.fruitVIew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Fruit fruit=fruitList.get(position);
                Toast.makeText(content,fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    class  ViewHander extends RecyclerView.ViewHolder {
        private ImageView imgs;
        private TextView textView;
        private View fruitVIew;
        public ViewHander(@NonNull View itemView) {
            super(itemView);
            fruitVIew=itemView;
            imgs=itemView.findViewById(R.id.recycler_img);
            textView=itemView.findViewById(R.id.recycler_text);
        }
    }
}
