package com.pixarninja.bunny_massacre;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;

public class SpriteProp extends SpriteEntity {

    int propID;

    /* for extending the class */
    public SpriteProp() {}

    public SpriteProp(Resources res, double spriteScale, int width, int height, int xRes, int yRes, int propID,
                      double xDelta, double yDelta, double xInit, double yInit, int xFrameCount, int yFrameCount, int frameCount,
                      double xDimension, double yDimension,
                      double left, double top, double right, double bottom, String method, String direction, SpriteController controller, String ID, String transition) {

        if(controller == null) {
            this.controller = new SpriteController();
        }
        else {
            this.controller = controller;
        }
        this.controller.setID(ID);
        this.res = res;
        this.spriteScale = spriteScale;
        this.width = width;
        this.height = height;
        this.xRes = xRes;
        this.yRes = yRes;
        this.propID = propID;
        this.controller.setXDelta(xDelta);
        this.controller.setYDelta(yDelta);
        this.controller.setXInit(xInit);
        this.controller.setYInit(yInit);
        this.controller.setXPos(xInit);
        this.controller.setYPos(yInit);
        this.xFrameCount = xFrameCount;
        this.yFrameCount = yFrameCount;
        this.frameCount = frameCount;
        this.xDimension = xDimension;
        this.yDimension = yDimension;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.method = method;
        this.direction = direction;

        refreshEntity(transition);

    }

    @Override
    public void refreshEntity(String transition) {

        int xSpriteRes;
        int ySpriteRes;

        switch (transition) {
            case "skip":
                break;
            case "init":
            default:
                render = new Sprite();
                render.setTransition(transition);
                render.setXDimension(xDimension);
                render.setYDimension(yDimension);
                render.setLeft(left);
                render.setTop(top);
                render.setRight(right);
                render.setBottom(bottom);
                render.setXFrameCount(xFrameCount);
                render.setYFrameCount(yFrameCount);
                render.setFrameCount(frameCount);
                render.setMethod(method);
                render.setDirection(direction);
                xSpriteRes = xRes * render.getXFrameCount();
                ySpriteRes = yRes * render.getYFrameCount();
                render.setSpriteSheet(decodeSampledBitmapFromResource(res, propID, (int) (xSpriteRes * spriteScale), (int) (ySpriteRes * spriteScale)));
                render.setFrameWidth(render.getSpriteSheet().getWidth() / render.getXFrameCount());
                render.setFrameHeight(render.getSpriteSheet().getHeight() / render.getYFrameCount());
                render.setFrameScale((width * spriteScale) / (double)render.getFrameWidth()); // scale = goal width / original width
                render.setSpriteWidth((int)(render.getFrameWidth() * render.getFrameScale())); // width = original width * scale
                render.setSpriteHeight((int)(render.getFrameHeight() * render.getFrameScale())); // height = original height * scale
                render.setXCurrentFrame(0);
                render.setYCurrentFrame(0);
                render.setCurrentFrame(0);
                render.setFrameToDraw(new Rect(0, 0, render.getFrameWidth(), render.getFrameHeight()));
                render.setWhereToDraw(new RectF((float) controller.getXPos(), (float) controller.getYPos(), (float) controller.getXPos() + render.getSpriteWidth(), (float) controller.getYPos() + render.getSpriteHeight()));
        }
        controller.setTransition(transition);
        controller.setEntity(this);
        controller.setTransition(transition);
        updateBoundingBox();
    }

}