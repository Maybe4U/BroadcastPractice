package com.example.android.broadcastpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/10/28.
 */
public class LoginActivity extends BaseActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        final EditText account = (EditText)findViewById(R.id.account_et);
        final EditText password = (EditText)findViewById(R.id.password_et);
        final CheckBox rememberPass = (CheckBox)findViewById(R.id.rem_password);
        Button login = (Button)findViewById(R.id.login_button);
        /*每个应用有一个默认的偏好文件preferences.xml，使用getDefaultSharedPreferences获取
        * 这是一个静态方法，它接收一个Context参数，并自动使用当前应用程序的包名作为前缀来命名
        * SharedPreferences文件。得到了SharedPreferences对象之后，就可以开始向SharedPreferences
        * 文件中存储数据了，主要分为三步实现：
        * 1.调用SharedPreferences对象的edit()方法来获取一个SharedPreferences.Editor对象。
        * 2.向SharedPreferences.Editor对象中添加数据
        * 3.调用commit()方法将添加的数据提交，从而完成数据存储操作*/
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = pref.getBoolean("remember_password",false);
        if(isRemember){
            String accountName = pref.getString("account","");
            String passwordName = pref.getString("password","");
            account.setText(accountName);
            password.setText(passwordName);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountName = account.getText().toString();
                String passwordName = password.getText().toString();
                if((accountName.equals("admin")&&(passwordName.equals("123456")))){
                    editor = pref.edit();
                    if(rememberPass.isChecked()){
                        editor.putString("account",accountName);
                        editor.putString("password",passwordName);
                        editor.putBoolean("remember_password",true);
                    }else{
                        editor.clear();
                    }
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"账户或密码错误，请重新输入！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
