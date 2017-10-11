package com.pixarninja.bunny_massacre;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.LinkedHashMap;

public class Bunny extends SpriteProp {

    int hit = 0;

    public Bunny(Resources res, int xRes, int yRes, int width, int height, SpriteController controller, String ID, String transition) {

        if(controller == null) {
            this.controller = new SpriteController();
            this.controller.setXInit(width / 2);
            this.controller.setYInit(5.5 * height / 10);
        }
        else {
            this.controller = controller;
        }
        this.controller.setID(ID);
        this.res = res;
        this.xRes = xRes;
        this.yRes = yRes;
        this.width = width;
        this.height = height;
        count = 0;

        refreshEntity(transition);

    }

    @Override
    public void refreshEntity(String transition) {

        int xSpriteRes;
        int ySpriteRes;

        /* setup sprite via parsing */
        transition = parseID(transition);

        try {
            switch (transition) {
                case "left":
                    controller.setXDelta(-15);
                    render.setTransition(transition);
                    render.setXDimension(1);
                    render.setYDimension(1);
                    render.setLeft(0.25);
                    render.setTop(0.25);
                    render.setRight(0.75);
                    render.setBottom(1);
                    render.setXFrameCount(4);
                    render.setYFrameCount(3);
                    render.setFrameCount(12);
                    render.setDirection("flipped");
                    render.setMethod("loop");
                    spriteScale = 0.3;
                    xSpriteRes = xRes * render.getXFrameCount();
                    ySpriteRes = yRes * render.getYFrameCount();
                    Bitmap flipped = decodeSampledBitmapFromResource(res, R.mipmap.spritesheet_bunny_move, (int)(xSpriteRes * spriteScale), (int)(ySpriteRes * spriteScale));
                    Matrix matrix = new Matrix();
                    matrix.postScale(-1, 1);
                    flipped = Bitmap.createBitmap(flipped, 0, 0, flipped.getWidth(), flipped.getHeight(), matrix, true);
                    render.setSpriteSheet(flipped);
                    render.setFrameWidth(render.getSpriteSheet().getWidth() / render.getXFrameCount());
                    render.setFrameHeight(render.getSpriteSheet().getHeight() / render.getYFrameCount());
                    render.setFrameScale((width * spriteScale) / (double)render.getFrameWidth()); // scale = goal width / original width
                    render.setSpriteWidth((int)(render.getFrameWidth() * render.getFrameScale())); // width = original width * scale
                    render.setSpriteHeight((int)(render.getFrameHeight() * render.getFrameScale())); // height = original height * scale
                    render.setWhereToDraw(new RectF((float) controller.getXPos(), (float) controller.getYPos(), (float) controller.getXPos() + render.getSpriteWidth(), (float) controller.getYPos() + render.getSpriteHeight()));
                    break;
                case "right":
                    controller.setXDelta(15);
                    render.setTransition(transition);
                    render.setXDimension(1);
                    render.setYDimension(1);
                    render.setLeft(0.25);
                    render.setTop(0.25);
                    render.setRight(0.75);
                    render.setBottom(1);
                    render.setXFrameCount(4);
                    render.setYFrameCount(3);
                    render.setFrameCount(12);
                    render.setDirection("forwards");
                    render.setMethod("loop");
                    spriteScale = 0.3;
                    xSpriteRes = xRes * render.getXFrameCount();
                    ySpriteRes = yRes * render.getYFrameCount();
                    render.setSpriteSheet(decodeSampledBitmapFromResource(res, R.mipmap.spritesheet_bunny_move, (int)(xSpriteRes * spriteScale), (int)(ySpriteRes * spriteScale)));
                    render.setFrameWidth(render.getSpriteSheet().getWidth() / render.getXFrameCount());
                    render.setFrameHeight(render.getSpriteSheet().getHeight() / render.getYFrameCount());
                    render.setFrameScale((width * spriteScale) / (double)render.getFrameWidth()); // scale = goal width / original width
                    render.setSpriteWidth((int)(render.getFrameWidth() * render.getFrameScale())); // width = original width * scale
                    render.setSpriteHeight((int)(render.getFrameHeight() * render.getFrameScale())); // height = original height * scale
                    render.setWhereToDraw(new RectF((float) controller.getXPos(), (float) controller.getYPos(), (float) controller.getXPos() + render.getSpriteWidth(), (float) controller.getYPos() + render.getSpriteHeight()));
                    break;
                case "idle":
                    controller.setReacting(false);
                    controller.setXDelta(0);
                    render.setTransition(transition);
                    render.setXDimension(1);
                    render.setYDimension(1);
                    render.setLeft(0.25);
                    render.setTop(0.25);
                    render.setRight(0.75);
                    render.setBottom(1);
                    render.setXFrameCount(4);
                    render.setYFrameCount(3);
                    render.setFrameCount(12);
                    render.setDirection("forwards");
                    render.setMethod("loop");
                    spriteScale = 0.3;
                    xSpriteRes = xRes * render.getXFrameCount();
                    ySpriteRes = yRes * render.getYFrameCount();
                    render.setSpriteSheet(decodeSampledBitmapFromResource(res, R.mipmap.spritesheet_bunny_idle, (int)(xSpriteRes * spriteScale), (int)(ySpriteRes * spriteScale)));
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
                    controller.setXDelta(0);
                    controller.setYDelta(0);
                    refreshEntity("idle");
                    transition = "idle";
                    controller.setXPos(controller.getXInit() - render.getSpriteWidth() / 2);
                    controller.setYPos(controller.getYInit() - render.getSpriteHeight() / 2);
                    render.setXCurrentFrame(0);
                    render.setYCurrentFrame(0);
                    render.setCurrentFrame(0);
                    render.setFrameToDraw(new Rect(0, 0, render.getFrameWidth(), render.getFrameHeight()));
            }
            controller.setEntity(this);
            controller.setTransition(transition);
            updateBoundingBox();
        } catch(NullPointerException e) {
            refreshEntity(transition);
        }

    }

    @Override
    public void updateView() {

        controller.setXPos(controller.getXPos() + controller.getXDelta());
        controller.setYPos(controller.getYPos() + controller.getYDelta());
        if(controller.getXPos() < 0) {
            controller.setXPos(0);
        }
        else if(controller.getXPos() > width - controller.getEntity().getSprite().getSpriteWidth()) {
            controller.setXPos(width - controller.getEntity().getSprite().getSpriteWidth());
        }
        getCurrentFrame();
        updateBoundingBox();

    }

    @Override
    public LinkedHashMap<String, SpriteController> onCollisionEvent(LinkedHashMap.Entry<String, SpriteController> entry, LinkedHashMap<String, SpriteController> controllerMap) {

        LinkedHashMap<String, SpriteController> map = new LinkedHashMap<>();
        LinkedHashMap<String, SpriteController> additionMap;

        if(!controller.getReacting() && entry.getValue().getEntity().getSprite().getBoundingBox() != null) {
            RectF entryBox = entry.getValue().getEntity().getSprite().getBoundingBox();
            for (LinkedHashMap.Entry<String, SpriteController> test : controllerMap.entrySet()) {
                if (test.getKey().contains("Knife") && !test.getValue().getReacting()) {
                    if ((test.getValue().getEntity().getSprite().getBoundingBox() != null)) {
                        RectF compareBox = test.getValue().getEntity().getSprite().getBoundingBox();
                        /* if the objects intersect, find where they intersect for the entry bounding box */
                        if (entryBox.intersect(compareBox)) {

                            /* first call the knife's collision handler */
                            additionMap = test.getValue().getEntity().onCollisionEvent(test, controllerMap);
                            for(LinkedHashMap.Entry<String, SpriteController> add : additionMap.entrySet()) {
                                map.put(add.getKey(), add.getValue());
                            }

                            hit++;
                            SpriteEntity entity = new SpriteProp(res, spriteScale, width, height, xRes, yRes, R.mipmap.spritesheet_blood,
                                    0, 0, controller.getXPos() + render.getSpriteWidth() / 2, controller.getYPos() + 2 * render.getSpriteHeight() / 3, 1, 1, 1, 1, 1, 0, 0, 1, 1, "loop", "forwards", null, "blood", "init");
                            entity.getController().setXPos(entity.getController().getXPos() - entity.getSprite().getSpriteWidth() / 2);
                            map.put("Blood" + hit + "Controller", entity.getController());

                        }

                    }
                }
            }
        }

        return map;

    }

}
