package com.longcntt.kukubi.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.longcntt.kukubi.R;
import com.longcntt.kukubi.Service.APIservice;
import com.longcntt.kukubi.Service.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editUser, editPass;
    Toolbar toolbarDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhxa();

        findViewById(R.id.btnHuy).setOnClickListener(this);
        findViewById(R.id.btnXacNhan).setOnClickListener(this);
    }

    private void anhxa() {
        editUser = findViewById(R.id.editUserName);
        editPass = findViewById(R.id.editPassWord);

        toolbarDangKy = findViewById(R.id.toolbarDangKy);

        setSupportActionBar(toolbarDangKy);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarDangKy.setTitle("ĐĂNG KÝ");
        toolbarDangKy.setTitleTextColor(getResources().getColor(R.color.pink));

        toolbarDangKy.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHuy:
                huy();
                break;
            case R.id.btnXacNhan:
                String user = editUser.getText().toString().trim();
                String pass = editPass.getText().toString().trim();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) &&
                        TextUtils.equals(user, "") || TextUtils.equals(pass, "")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("Vui lòng điền dầy đủ thông tin !!!")
                            .setTitle("Thông Báo")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }
                    XacNhan();
                break;
        }
    }

    private void XacNhan() {
        String user = editUser.getText().toString().trim();
        String pass = editPass.getText().toString().trim();

        Dataservice dataservice = APIservice.getDataservice();
        Call<String> callback = dataservice.insert(user, pass);

        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.body();
                if (result.equals("Success")) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
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

            }
        });
    }

    private void huy() {
        editPass.setText("");
        editUser.setText("");
    }
}
