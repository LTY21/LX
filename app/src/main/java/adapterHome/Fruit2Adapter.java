package adapterHome;

import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.List;

import bean.Fruit2;
import uiview.UIActivity;
import uiview.UIActivity_XiangQing;

public class Fruit2Adapter extends RecyclerView.Adapter<Fruit2Adapter.ViewHolder> {
    private List<Fruit2>list;
    private Context context;

    public Fruit2Adapter(List<Fruit2> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Fruit2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context==null){
            context=parent.getContext();
        }
        View view=LayoutInflater.from(context).inflate(R.layout.fruit2_item,parent,false);
        //向UIActivity_XiangQing传送数据
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LTY",list.size()+"");
                int postion=holder.getAdapterPosition();
                Fruit2 fruit2=list.get(postion);
                Intent intent=new Intent(context, UIActivity_XiangQing.class);
                intent.putExtra(UIActivity_XiangQing.F_NANE,fruit2.getName());
                intent.putExtra(UIActivity_XiangQing.F_ID,fruit2.getImageid());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Fruit2Adapter.ViewHolder holder, int position) {

        Fruit2 fruit2=list.get(position);
        holder.fruitName.setText(fruit2.getName());
        Glide.with(context).load(fruit2.getImageid()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView imageView;
        private TextView fruitName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView= (CardView) itemView;
            fruitName=itemView.findViewById(R.id.fruit2_name);
            imageView=itemView.findViewById(R.id.fruit2_imageview);
        }
    }
}
