package layoutview;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.myapplication.ActivityHome;
import com.example.myapplication.ActivityLog;
import com.example.myapplication.R;

/**
 * ProgressBar//进度条
 * AlertDialog//弹窗
 * ProgressDialog//加载框
 */
public class Progtrss_Dialog extends ActivityLog implements View.OnClickListener{

    private ProgressBar pdProgressBar;
    private Button pdProgressBarButton;
    private Button pdAlertDialog;
    private Button pdProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progtrss__dialog);
        initView();
        ActivityHome.addActivity(this);
        pdProgressBarButton.setOnClickListener(this);
        pdAlertDialog.setOnClickListener(this);
        pdProgressDialog.setOnClickListener(this);
    }

    private void initView() {
        pdProgressBar = (ProgressBar) findViewById(R.id.pd_progressBar);
        pdProgressBarButton = (Button) findViewById(R.id.pd_progressBar_button);
        pdAlertDialog = (Button) findViewById(R.id.pd_alertDialog);
        pdProgressDialog = (Button) findViewById(R.id.pd_progressDialog);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pd_progressBar_button :
            int progress=pdProgressBar.getProgress();
            progress+=10;
                if (progress>100){
                    progress=0;
                }
            pdProgressBar.setProgress(progress);

            break;

            case R.id.pd_alertDialog:
                AlertDialog.Builder dialog=new AlertDialog.Builder(Progtrss_Dialog.this);
                dialog.setTitle("标题");
                dialog.setMessage("内容");
                dialog.setCancelable(false);//点击空白处不能退出
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Progtrss_Dialog.this, "你点击了确定", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Progtrss_Dialog.this, "取消成功", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNeutralButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Progtrss_Dialog.this, "更新成功", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                break;
            case R.id.pd_progressDialog:
                ProgressDialog progressDialog=new ProgressDialog(Progtrss_Dialog.this);
               progressDialog.setTitle("标题");
               progressDialog.setMessage("内容");
               progressDialog.setCancelable(true);//点击空白处退出
               progressDialog.show();
               break;
            default:
                break;
        }
    }
}