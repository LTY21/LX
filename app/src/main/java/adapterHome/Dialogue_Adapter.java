package adapterHome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

import bean.Msg;
/**
 * 主界面适配
 * */
public class Dialogue_Adapter extends RecyclerView.Adapter<Dialogue_Adapter.ViewHolder> {
    private List<Msg>msgList;
    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout letflayout;
        LinearLayout rightlayout;
        TextView leftMsg;
        TextView rightMsg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            letflayout=itemView.findViewById(R.id.msg_layout_left);
            rightlayout=itemView.findViewById(R.id.msg_layout_right);
            leftMsg=itemView.findViewById(R.id.msg_left);
            rightMsg=itemView.findViewById(R.id.msg_right);
        }
    }
    public  Dialogue_Adapter(List<Msg>msgList){
        this.msgList=msgList;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Msg msg=msgList.get(position);
        if (msg.getType()==Msg.jieshou){
            //如果是接收消息，显示左边,将右边隐藏
            holder.letflayout.setVisibility(View.VISIBLE);
            holder.rightlayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msgList.get(position).getContent());

        }else {
            holder.letflayout.setVisibility(View.GONE);
            holder.rightlayout.setVisibility(View.VISIBLE);
            holder.rightMsg.setText(msgList.get(position).getContent());

        }

    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }


}
