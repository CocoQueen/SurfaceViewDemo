package com.example.coco.surfaceviewdemo;

/**
 * Created by coco on 2017/7/31.
 */

public class Heart {
    public static final int DEFAULT=0;
    public static final int PINK=1;
    public static final int CYAN=2;
    public static final int GREEN=3;
    public static final int YELLOW=4;
    public static final int BLUE=5;

    private float x;
    private float y;

    private float startX;
    private float startY;

    private float endX;
    private float endY;

    private float ctrl1X;
    private float ctrl1Y;

    private float ctrl2X;
    private float ctrl2Y;

    private float t=0;
    private float speed;
    private int type= DEFAULT;
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }

    public float getCtrl1X() {
        return ctrl1X;
    }

    public void setCtrl1X(float ctrl1X) {
        this.ctrl1X = ctrl1X;
    }

    public float getCtrl1Y() {
        return ctrl1Y;
    }

    public void setCtrl1Y(float ctrl1Y) {
        this.ctrl1Y = ctrl1Y;
    }

    public float getCtrl2X() {
        return ctrl2X;
    }

    public void setCtrl2X(float ctrl2X) {
        this.ctrl2X = ctrl2X;
    }

    public float getCtrl2Y() {
        return ctrl2Y;
    }

    public void setCtrl2Y(float ctrl2Y) {
        this.ctrl2Y = ctrl2Y;
    }

    public float getT() {
        return t;
    }

    public void setT(float t) {
        this.t = t;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public void init(float x,float y){
        this.x=x;
        this.y=y;
    }
    public void initStartAndEnd(float width,float height){
        this.startX=width/2;
        this.startY=height;
        this.endX=width/2;
        this.endY=0;
        init(startX,startY);
    }
    public void initCtrl(float width,float height){
        this.ctrl1X= (float) (Math.random()*width);
        this.ctrl1Y= (float) (Math.random()*height);
        this.ctrl2X= (float) (Math.random()*width);
        this.ctrl2Y= (float) (Math.random()*height);
        if (this.ctrl1X==this.ctrl2X&&this.ctrl1Y==this.ctrl2Y){
            initCtrl(width,height);
        }
    }
    public void initSpeed(){
        this.speed= (float) (Math.random()*0.01+0.003);
    }


}
