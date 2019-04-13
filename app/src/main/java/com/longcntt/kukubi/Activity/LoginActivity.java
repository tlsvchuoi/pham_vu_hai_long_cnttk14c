package com.longcntt.kukubi.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.longcntt.kukubi.R;
import com.longcntt.kukubi.Service.APIservice;
import com.longcntt.kukubi.Service.Dataservice;
import com.longcntt.kukubi.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editUser, editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhxa();

        findViewById(R.id.btnDangNhap).setOnClickListener(this);
        findViewById(R.id.btnDangKi).setOnClickListener(this);

    }


    private void anhxa() {
        editUser = findViewById(R.id.editUserName);
        editPass = findViewById(R.id.editPassWord);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDangNhap:
                String user = editUser.getText().toString().trim();
                String pass = editPass.getText().toString().trim();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) &&
                        TextUtils.equals(user, "") || TextUtils.equals(pass, "")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Vui lòng điền dầy đủ thông tin !!!")
                            .setTitle("Thông Báo")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                } else {
                    login();
                }
                break;
            case R.id.btnDangKi:
                register();
                break;
        }
    }

    User u;

    private void register() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private void login() {
        String user = editUser.getText().toString().trim();
        String pass = editPass.getText().toString().trim();
        Dataservice dataservice = APIservice.getDataservice();
        Call<String> callback = dataservice.check(user, pass);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.body();
                Log.d("D", "onResponse: " + result);
                if (result.equals("success")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    /*intent.putExtra("user", u);*/
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Tài Khoản Hoặc Mật Khẩu Sai !!!")
                            .setTitle("Thông Báo")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
