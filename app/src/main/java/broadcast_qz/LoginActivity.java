package broadcast_qz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

public class LoginActivity extends BaseActivity {

    private EditText loginEduser;
    private EditText loginEdpwd;
    private Button loginBt;
    private CheckBox cbRoot;
    private  SharedPreferences sp;
    private  SharedPreferences.Editor editor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        
        sp=getSharedPreferences("root",MODE_PRIVATE);
        boolean bl=sp.getBoolean("bl",false);
        if(bl==true){
            String user=sp.getString("user","");
            String pwd=sp.getString("pwd","");
            loginEduser.setText(user);
            loginEdpwd.setText(pwd);
            cbRoot.setChecked(true);
        }
        loginBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = loginEduser.getText().toString();
                String pwd = loginEdpwd.getText().toString();
                if (user.equals("123") && pwd.equals("123")) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    editor=sp.edit();
                    if (cbRoot.isChecked()){
                        editor.putString("user",user);
                        editor.putString("pwd",pwd);
                        editor.putBoolean("bl",true);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, XiaXian.class);
                    startActivity(intent);

                } else {
                    loginEduser.setText("");
                    loginEdpwd.setText("");
                    Toast.makeText(LoginActivity.this, "账户错误", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void initView() {
        loginEduser = (EditText) findViewById(R.id.login_eduser);
        loginEdpwd = (EditText) findViewById(R.id.login_edpwd);
        loginBt = (Button) findViewById(R.id.login_bt);
        cbRoot = (CheckBox) findViewById(R.id.cb_root);
    }
}