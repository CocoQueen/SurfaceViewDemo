package com.example.coco.surfaceviewdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by coco on 2017/7/31.
 */

public class MyHeart extends SurfaceView {

    private static final String TAG = "HeartView";
    private boolean isRunning = false;
    private Paint mPaint;
    private ConcurrentLinkedQueue<Heart> queue = null;
    private float mWidth;
    private float mHeight;
    private Matrix matrix;
    private SparseArray<Bitmap> array;

    public MyHeart(Context context) {
        super(context, null);
    }

    public MyHeart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                isRunning = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (isRunning) {
                            Canvas canvas = null;
                            try {
                                canvas = getHolder().lockCanvas();
                                if (canvas != null) {
                                    drawHeart(canvas);
                                }
                            } catch (Exception e) {
                                Log.e(TAG, "run: " + e.getMessage());
                            } finally {
                                if (canvas != null) {
                                    getHolder().unlockCanvasAndPost(canvas);
                                }
                            }

                        }
                    }
                }).start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                isRunning = false;
                for (int i = 0; i < array.size(); i++) {
                    if (array.valueAt(i) != null) {
                        array.valueAt(i).recycle();
                    }
                }
            }
        });
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);

        queue = new ConcurrentLinkedQueue<>();
        matrix = new Matrix();
        array = new SparseArray<>();
        initBitmap(context);


    }

    private void drawHeart(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);//清屏
        for (Heart heart : queue) {
            if (array.get(heart.getType()) == null) {
                continue;
            }
            matrix.setTranslate(0, 0);
            matrix.postTranslate(heart.getX(), heart.getY());
            canvas.drawBitmap(array.get(heart.getType()), matrix, mPaint);
            if (heart.getT() < 1) {
                heart.setT(heart.getT() + heart.getSpeed());
                handleXY(heart);
            } else {
                removeHeart(heart);
            }
        }

    }

    private void removeHeart(Heart heart) {
        queue.remove(heart);
    }

    private void handleXY(Heart heart) {
        float x = (float) (Math.pow((1 - heart.getT()), 3) * heart.getStartX() +
                3 * heart.getT() * Math.pow((1 - heart.getT()), 2) * heart.getCtrl1X() +
                3 * Math.pow(heart.getT(), 2) * (1 - heart.getT()) * heart.getCtrl2X() +
                Math.pow(heart.getT(), 3) * heart.getEndX());

        float y = (float) (Math.pow((1 - heart.getT()), 3) * heart.getStartY() +
                3 * heart.getT() * Math.pow((1 - heart.getT()), 2) * heart.getCtrl1Y() +
                3 * Math.pow(heart.getT(), 2) * (1 - heart.getT()) * heart.getCtrl2Y() +
                Math.pow(heart.getT(), 3) * heart.getEndY());

        heart.setX(x);
        heart.setY(y);
    }

    private void initBitmap(Context context) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.heart);
        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.heart2);
        Bitmap bitmap3 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.heart3);
        Bitmap bitmap4 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.heart4);
        Bitmap bitmap5 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.heart5);
        Bitmap bitmap6 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.star);
//        Bitmap bitmap6= BitmapFactory.decodeResource(context.getResources(), R.mipmap.xx);
        array.put(Heart.DEFAULT, bitmap);
        array.put(Heart.PINK, bitmap2);
        array.put(Heart.BLUE, bitmap3);
        array.put(Heart.CYAN, bitmap4);
        array.put(Heart.GREEN, bitmap5);
        array.put(Heart.YELLOW, bitmap6);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
    }



    private void initHeart(Heart heart) {
        heart.initStartAndEnd(mWidth, mHeight);
        heart.initCtrl(mWidth, mHeight);
        heart.initSpeed();
    }
    public void addHeart(int type){
        Heart heart = new Heart();
        initHeart(heart);
        heart.setType(type);
        queue.add(heart);
    }
}
