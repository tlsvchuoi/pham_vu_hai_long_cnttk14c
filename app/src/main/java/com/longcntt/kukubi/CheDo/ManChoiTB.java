package com.longcntt.kukubi.CheDo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.longcntt.kukubi.Adapter.Adapter;
import com.longcntt.kukubi.R;
import com.longcntt.kukubi.Setting.Support;

import java.util.ArrayList;

public class ManChoiTB extends AppCompatActivity {
    private Support sp = new Support();
    private EditText tvTime;
    private EditText tvPoint;
    private GridView gvMau;

    private int point;
    private int cols;
    private int check = 0;

    private ArrayList arr;
    Toolbar toolbar;

    CountDownTimer tm = new CountDownTimer(3000, 10) {
        @Override
        public void onTick(long millisUntilFinished) {
            if (point < 10) {
                tvTime.setText(millisUntilFinished / 10 + "s");
            } else {
                tvTime.setText(millisUntilFinished / point + "s");
            }
        }

        @Override
        public void onFinish() {
            check = 1;
            tvTime.setText(0 + "s");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_choi);
        connect();
        setting();
        setData();
        setEvent();
    }

    private void connect() {
        gvMau = findViewById(R.id.gvMau);
        tvPoint = findViewById(R.id.tvPoint);
        tvTime = findViewById(R.id.tvTime);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.pink));
        toolbar.setTitle("Màn Dễ");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManChoiTB.this);
                builder.setMessage("Thoát Chứ !!!")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });

                builder.show();
            }
        });
    }


    private void setting() {
        if (point < 10) {
            gvMau.setNumColumns(2);
            cols = 4;
        } else {
            gvMau.setNumColumns((point / 10) * 2);
            cols = ((point / 10) * 2) * ((point / 10) * 2);
        }
    }

    private void setData() {
        arr = new ArrayList(sp.TaoMau(cols));
        Adapter adapter = new Adapter(ManChoiTB.this, R.layout.activity_man_choi, arr);
        gvMau.setAdapter(adapter);
        tvPoint.setText(Integer.toString(point));
    }

    MediaPlayer mediaPlayer;

    private void setEvent() {
        gvMau.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == sp.result && check == 0) {
                    setting();
                    point++;
                    tm.start();
                    setData();

                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.dung);
                    mediaPlayer.start();
                } else {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sai);
                    mediaPlayer.start();
                }
            }
        });
    }
}
