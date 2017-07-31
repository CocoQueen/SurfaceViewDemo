package com.example.coco.surfaceviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImg;
    private MyHeart myHeart;
    private MyText mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (MyText) findViewById(R.id.mTv);
        mImg = (ImageView) findViewById(R.id.iv_bg);
        myHeart = (MyHeart) findViewById(R.id.heart_view);

        mImg.setOnClickListener(this);
        myHeart.setOnClickListener(this);
//        mTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        myHeart.addHeart(new Random().nextInt(6));

    }
}
