package com.pixarninja.bunny_massacre;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;

public class Sprite {

    private String transition;
    private Bitmap spriteSheet;
    private int xFrameCount;
    private int yFrameCount;
    private int frameCount;
    private int frameWidth;
    private int frameHeight;
    private double frameScale;
    private double xDimension;
    private double yDimension;
    private int spriteWidth;
    private int spriteHeight;
    private int xCurrentFrame;
    private int yCurrentFrame;
    private int currentFrame;
    private Rect frameToDraw;
    private RectF whereToDraw;
    double left;
    double top;
    double right;
    double bottom;
    private RectF boundingBox;
    private String direction;
    private String method;


    public Sprite() {}

    public String getTransition() { return this.transition; }
    public void setTransition(String ID) { this.transition = transition; }

    public Bitmap getSpriteSheet() {
        return this.spriteSheet;
    }
    public void setSpriteSheet(Bitmap spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public int getXFrameCount() {
        return this.xFrameCount;
    }
    public void setXFrameCount(int xFrameCount) {
        this.xFrameCount = xFrameCount;
    }

    public int getYFrameCount() {
        return this.yFrameCount;
    }
    public void setYFrameCount(int yFrameCount) {
        this.yFrameCount = yFrameCount;
    }

    public int getFrameCount() {
        return this.frameCount;
    }
    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public int getFrameWidth() {
        return this.frameWidth;
    }
    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return this.frameHeight;
    }
    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public double getFrameScale() {
        return this.frameScale;
    }
    public void setFrameScale(double frameScale) {
        this.frameScale = frameScale;
    }

    public double getXDimension() {
        return this.xDimension;
    }
    public void setXDimension(double xDimension) {
        this.xDimension = xDimension;
    }

    public double getYDimension() {
        return this.yDimension;
    }
    public void setYDimension(double yDimension) {
        this.yDimension = yDimension;
    }

    public int getSpriteWidth() {
        return this.spriteWidth;
    }
    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public int getSpriteHeight() {
        return this.spriteHeight;
    }
    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    public int getXCurrentFrame() {
        return this.xCurrentFrame;
    }
    public void setXCurrentFrame(int xCurrentFrame) {
        this.xCurrentFrame = xCurrentFrame;
    }

    public int getYCurrentFrame() {
        return this.yCurrentFrame;
    }
    public void setYCurrentFrame(int yCurrentFrame) {
        this.yCurrentFrame = yCurrentFrame;
    }

    public int getCurrentFrame() {
        return this.currentFrame;
    }
    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public Rect getFrameToDraw() {
        return this.frameToDraw;
    }
    public void setFrameToDraw(Rect frameToDraw) {
        this.frameToDraw = frameToDraw;
    }

    public RectF getWhereToDraw() {
        return this.whereToDraw;
    }
    public void setWhereToDraw(RectF whereToDraw) {
        this.whereToDraw = whereToDraw;
    }

    public double getLeft() { return this.left; }
    public void setLeft(double left) { this.left = left; }

    public double getTop() { return this.top; }
    public void setTop(double top) { this.top = top; }

    public double getRight() { return this.right; }
    public void setRight(double right) { this.right = right; }

    public double getBottom() { return this.bottom; }
    public void setBottom(double bottom) { this.bottom = bottom; }

    public RectF getBoundingBox() {
        return this.boundingBox;
    }
    public void setBoundingBox(RectF boundingBox) {
        this.boundingBox = boundingBox;
    }

    public String getDirection() {
        return this.direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getMethod() {
        return this.method;
    }
    public void setMethod(String method) {
        this.method = method;
    }

    public void printSprite() {

        System.out.println("Info of sprite:");
        System.out.println(" - transition: " + transition);
        System.out.println(" - sprite sheet: " + spriteSheet);
        System.out.println(" - x dimension: " + xDimension);
        System.out.println(" - y dimension: " + yDimension);
        System.out.println(" - left: " + left);
        System.out.println(" - top: " + top);
        System.out.println(" - right: " + right);
        System.out.println(" - bottom: " + bottom);
        System.out.println(" - direction: " + direction);
        System.out.println(" - method: " + method);
        System.out.println(" - current frame: " + currentFrame);
        System.out.println(" - x current frame: " + xCurrentFrame);
        System.out.println(" - y current frame: " + yCurrentFrame);
        System.out.println(" - x frame count: " + xFrameCount);
        System.out.println(" - y frame count: " + yFrameCount);
        System.out.println(" - frame count: " + frameCount);
        System.out.println(" - frame width: " + frameWidth);
        System.out.println(" - frame height: " + frameHeight);
        System.out.println(" - frame scale: " + frameScale);
        System.out.println(" - bounding box: " + boundingBox);
        System.out.println(" - sprite width: " + spriteWidth);
        System.out.println(" - sprite height: " + spriteHeight);
        System.out.println(" - frame to draw: " + frameToDraw);
        System.out.println(" - where to draw: " + whereToDraw);

    }

}
