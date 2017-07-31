package com.example.coco.surfaceviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by coco on 2017/7/31.
 */

public class MyText extends View implements View.OnClickListener {
    private int index;
    private Paint paint;

    public MyText(Context context) {
        super(context);
    }

    public MyText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        String string = "哈" + index;
        Rect rect = new Rect();
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(30);
        paint.getTextBounds(string, 0, string.length(), rect);
        paint.setColor(Color.BLUE);
        canvas.drawText(string, width / 2 - rect.width(), height / 2 - rect.height(), paint);
    }

    @Override
    public void onClick(View v) {
        index++;
        invalidate();
    }
}
