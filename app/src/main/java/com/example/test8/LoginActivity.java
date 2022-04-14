package com.example.test8;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private SharedPreferences pref;//通过pref读取SharedPreferences的数据
    private SharedPreferences.Editor editor;//editor将数据写入SharedPreferences
    private CheckBox rememberPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberPass=(CheckBox)findViewById(R.id.remember_pass);
        login = (Button) findViewById(R.id.login);
        boolean isRemenber=pref.getBoolean("remember_password",false);//读取上次登陆时存入"remember_password"的信息，没有读取到则默认为false
        if(isRemenber)//如果读取为true，则将account和password，checkbox的信息写入文本框
        {
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accout = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (accout.equals("123") && password.equals("123")) {
                    editor=pref.edit();
                    if(rememberPass.isChecked()){//如果勾选了checkbox框，则将account，password，checkbox信息写入
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",accout);
                        editor.putString("password",password);
                    }else {
                        editor.clear();//若没有，清除SharedPreferences存储的信息
                    }
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, Library_MainActivity.class);
                    startActivity(intent);
                    finish();
                } else
                    Toast.makeText(LoginActivity.this, "account or password is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
