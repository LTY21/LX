package layoutview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ActivityLog;
import com.example.myapplication.R;

import java.sql.SQLTransactionRollbackException;
import java.util.ArrayList;
import java.util.List;

import adapterHome.Dialogue_Adapter;
import bean.Msg;

/**
 * 聊天界面
 */
public class Dialogue_Text extends ActivityLog {

    private EditText dialogEdtext;
    private List<Msg> msgList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Dialogue_Adapter adapter;
    private Button dialogJs;
    private Button dialogFs;

    @Override
    protected void onPause() {
        if (isFinishing()){
            msgList.clear();
        }
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogue__text);
        initView();
        UserView2.userTitle.setText("聊天界面");
        shipei();
        dialogFs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = dialogEdtext.getText().toString();
                if (!str.equals("")){
                    Msg msg=new Msg(str,Msg.fasong);
                    msgList.add(msg);
                    shipei();
                    adapter.notifyItemInserted(msgList.size()-1);
                    recyclerView.scrollToPosition(msgList.size()-1);
                    dialogEdtext.setText("");
                }
            }
        });
        dialogJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=dialogEdtext.getText().toString();
                if (!str.equals("")){
                    Msg msg=new Msg(str,Msg.jieshou);
                    msgList.add(msg);
                    shipei();
                    adapter.notifyItemInserted(msgList.size()-1);
                    recyclerView.scrollToPosition(msgList.size()-1);
                    dialogEdtext.setText("");

                }
            }
        });
    }

    private void shipei() {
        if (msgList.size()==0){
            Toast.makeText(this, "暂无数据", Toast.LENGTH_SHORT).show();
            //添加数据
        }else {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new Dialogue_Adapter(msgList);
            recyclerView.setAdapter(adapter);
        }
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.dialog_recyclerview);
        dialogEdtext = (EditText) findViewById(R.id.dialog_edtext);
        dialogJs = (Button) findViewById(R.id.dialog_js);
        dialogFs = (Button) findViewById(R.id.dialog_fs);
    }
}