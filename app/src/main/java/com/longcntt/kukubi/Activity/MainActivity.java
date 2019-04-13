package com.longcntt.kukubi.Activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.longcntt.kukubi.CheDo.ManChoi;
import com.longcntt.kukubi.CheDo.ManChoiKho;
import com.longcntt.kukubi.CheDo.ManChoiTB;
import com.longcntt.kukubi.R;
import com.longcntt.kukubi.model.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDe, btnTb, btnNhanh, btnThoat;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectView();

    }

    private void connectView() {
        btnDe = findViewById(R.id.btnDe);
        btnTb = findViewById(R.id.btnTb);
        btnNhanh = findViewById(R.id.btnNhanh);
        btnThoat = findViewById(R.id.btnThoat);


        btnDe.setOnClickListener(this);
        btnTb.setOnClickListener(this);
        btnNhanh.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDe:
                btnDeClick();
                break;
            case R.id.btnTb:
                btnTbClick();
                break;
            case R.id.btnNhanh:
                btnNhanhClick();
                break;
            case R.id.btnThoat:
                finish();
                break;
        }
    }

    private void btnDeClick() {
        Intent i = new Intent(MainActivity.this, ManChoi.class);
        startActivity(i);
    }

    private void btnTbClick() {
        Intent i = new Intent(MainActivity.this, ManChoiTB.class);
        startActivity(i);
    }

    private void btnNhanhClick() {
        Intent i = new Intent(MainActivity.this, ManChoiKho.class);
        startActivity(i);
    }
}
