package com.pixarninja.bunny_massacre;

import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.RectF;
import java.util.LinkedHashMap;

public class SpriteButton extends SpriteEntity {

    private int onID;
    private int pokedID;
    private int offID;

    public SpriteButton(Resources res, double spriteScale, int width, int height, int xRes, int yRes, int onID, int pokedID, int offID,
                        double xDelta, double yDelta, double xInit, double yInit, int xFrameCount, int yFrameCount, int frameCount,
                        double xDimension, double yDimension,
                        double left, double top, double right, double bottom, String method, SpriteController controller, String ID, String transition) {

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
        this.onID = onID;
        this.pokedID = pokedID;
        this.offID = offID;
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

        refreshEntity(transition);

    }

    @Override
    public void refreshEntity(String transition) {

        int xSpriteRes;
        int ySpriteRes;

        /* setup sprite via parsing */
        transition = parseID(transition);

        switch (transition) {
            case "off":
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
                render.setMethod("loop");
                render.setDirection("forwards");
                xSpriteRes = xRes * render.getXFrameCount();
                ySpriteRes = yRes * render.getYFrameCount();
                render.setSpriteSheet(decodeSampledBitmapFromResource(res, offID, (int) (xSpriteRes * spriteScale), (int) (ySpriteRes * spriteScale)));
                render.setFrameWidth(render.getSpriteSheet().getWidth() / render.getXFrameCount());
                render.setFrameHeight(render.getSpriteSheet().getHeight() / render.getYFrameCount());
                render.setFrameScale((width * spriteScale) / (double)render.getFrameWidth()); // scale = goal width / original width
                render.setSpriteWidth((int)(render.getFrameWidth() * render.getFrameScale())); // width = original width * scale
                render.setSpriteHeight((int)(render.getFrameHeight() * render.getFrameScale())); // height = original height * scale
                render.setWhereToDraw(new RectF((float) controller.getXPos(), (float) controller.getYPos(), (float) controller.getXPos() + render.getSpriteWidth(), (float) controller.getYPos() + render.getSpriteHeight()));
                break;
            case "poked":
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
                render.setMethod("once");
                render.setDirection("forwards");
                xSpriteRes = xRes * render.getXFrameCount();
                ySpriteRes = yRes * render.getYFrameCount();
                render.setSpriteSheet(decodeSampledBitmapFromResource(res, pokedID, (int) (xSpriteRes * spriteScale), (int) (ySpriteRes * spriteScale)));
                render.setFrameWidth(render.getSpriteSheet().getWidth() / render.getXFrameCount());
                render.setFrameHeight(render.getSpriteSheet().getHeight() / render.getYFrameCount());
                render.setFrameScale((width * spriteScale) / (double)render.getFrameWidth()); // scale = goal width / original width
                render.setSpriteWidth((int)(render.getFrameWidth() * render.getFrameScale())); // width = original width * scale
                render.setSpriteHeight((int)(render.getFrameHeight() * render.getFrameScale())); // height = original height * scale
                render.setWhereToDraw(new RectF((float) controller.getXPos(), (float) controller.getYPos(), (float) controller.getXPos() + render.getSpriteWidth(), (float) controller.getYPos() + render.getSpriteHeight()));
                break;
            case "on":
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
                render.setMethod("loop");
                render.setDirection("forwards");
                xSpriteRes = xRes * render.getXFrameCount();
                ySpriteRes = yRes * render.getYFrameCount();
                render.setSpriteSheet(decodeSampledBitmapFromResource(res, onID, (int) (xSpriteRes * spriteScale), (int) (ySpriteRes * spriteScale)));
                render.setFrameWidth(render.getSpriteSheet().getWidth() / render.getXFrameCount());
                render.setFrameHeight(render.getSpriteSheet().getHeight() / render.getYFrameCount());
                render.setFrameScale((width * spriteScale) / (double)render.getFrameWidth()); // scale = goal width / original width
                render.setSpriteWidth((int)(render.getFrameWidth() * render.getFrameScale())); // width = original width * scale
                render.setSpriteHeight((int)(render.getFrameHeight() * render.getFrameScale())); // height = original height * scale
                render.setWhereToDraw(new RectF((float) controller.getXPos(), (float) controller.getYPos(), (float) controller.getXPos() + render.getSpriteWidth(), (float) controller.getYPos() + render.getSpriteHeight()));
                break;
            case "skip":
                break;
            case "init":
            default:
                render = new Sprite();
                refreshEntity("off");
                controller.setXPos(controller.getXInit() - render.getSpriteWidth() / 2);
                controller.setYPos(controller.getYInit() - render.getSpriteHeight() / 2);
                render.setFrameToDraw(new Rect(0, 0, render.getFrameWidth(), render.getFrameHeight()));
        }
        controller.setTransition(transition);
        controller.setEntity(this);
        controller.setTransition(transition);
        updateBoundingBox();
    }

    @Override
    public void onTouchEvent(SpriteView spriteView, LinkedHashMap.Entry<String, SpriteController> entry, LinkedHashMap<String, SpriteController> controllerMap, boolean poke, boolean move, boolean jump, float xTouchedPos, float yTouchedPos) {
        if(poke) {

            RectF boundingBox = this.render.getBoundingBox();
            if (xTouchedPos >= boundingBox.left && xTouchedPos <= boundingBox.right) {
                /* center of the sprite */
                if (yTouchedPos >= boundingBox.top && yTouchedPos <= boundingBox.bottom) {

                    /* change the sprite if needed */
                    if(entry.getKey().equals("RightButtonController")) {

                        controllerMap.get("BunnyController").getEntity().refreshEntity("inherit right");

                    }
                    else if(entry.getKey().equals("LeftButtonController")) {

                        controllerMap.get("BunnyController").getEntity().refreshEntity("inherit left");

                    }
                }
            }

        }

        else if(!move) {

            controllerMap.get("BunnyController").getEntity().refreshEntity("inherit idle");

        }

    }

}
