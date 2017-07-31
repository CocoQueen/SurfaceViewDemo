package com.example.coco.surfaceviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by coco on 2017/7/31.
 */

public class SimpleSurfaceView extends SurfaceView {
    private boolean isRunning;
    private Paint mPaint;


    public SimpleSurfaceView(Context context) {
        super(context, null);
    }

    public SimpleSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                isRunning = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (isRunning) {
                            Canvas canvas = null;
                            try {
                                canvas = getHolder().lockCanvas();
                                if (canvas != null) {
                                    paintThing(canvas);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
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
            }
        });
    }

    private void paintThing(Canvas canvas) {
    }
}
