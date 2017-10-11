package com.pixarninja.bunny_massacre;

import android.graphics.RectF;
import java.util.LinkedHashMap;

public class SpriteCharacter extends SpriteEntity {

    int count;
    int delta;

    public int getCount() { return this.count; }
    public void setCount(int count) { this.count = count; }

    public int getDelta() { return this.delta; }
    public void setDelta(int delta) { this.delta = delta; }

    @Override
    public void onTouchEvent(SpriteView spriteView, LinkedHashMap.Entry<String, SpriteController> entry, LinkedHashMap<String, SpriteController> controllerMap, boolean poke, boolean move, boolean jump, float xTouchedPos, float yTouchedPos) {
        if(poke && !controller.getReacting()) {
            RectF boundingBox = render.getBoundingBox();
            if (xTouchedPos >= boundingBox.left && xTouchedPos <= boundingBox.right) {
                /* center of the sprite */
                if (yTouchedPos >= boundingBox.top && yTouchedPos <= boundingBox.bottom) {
                    refreshEntity("center");
                }
                /* top of the sprite */
                else if (yTouchedPos < boundingBox.top) {
                    refreshEntity("top");
                }
                /* bottom of the sprite */
                else if (yTouchedPos > boundingBox.bottom) {
                    refreshEntity("bottom");
                }
            }
            else if (xTouchedPos < boundingBox.left) {
                /* left side of the sprite */
                if (yTouchedPos >= boundingBox.top && yTouchedPos <= boundingBox.bottom) {
                    refreshEntity("left");
                }
                    /* top left side of the sprite */
                else if (yTouchedPos < boundingBox.top) {
                    refreshEntity("topLeft");
                }
                    /* bottom right side of the sprite */
                else if (yTouchedPos > boundingBox.bottom) {
                    refreshEntity("bottomLeft");
                }
            }
            /* right side of the sprite */
            else if (xTouchedPos > boundingBox.right) {
                /* right side of the screen */
                if (yTouchedPos >= boundingBox.top && yTouchedPos <= boundingBox.bottom) {
                    refreshEntity("right");
                }
                    /* top right side of the sprite */
                else if (yTouchedPos < boundingBox.top) {
                    refreshEntity("topRight");
                }
                    /* bottom right side of the sprite */
                else if (yTouchedPos > boundingBox.bottom) {
                    refreshEntity("bottomRight");
                }
            }
        }
    }

}