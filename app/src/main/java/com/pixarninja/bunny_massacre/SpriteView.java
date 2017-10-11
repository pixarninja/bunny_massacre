package com.pixarninja.bunny_massacre;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ConcurrentModificationException;
import java.util.LinkedHashMap;

public class SpriteView extends SurfaceView {

    public LinkedHashMap<String, SpriteController> controllerMap;
    public volatile boolean poke = false;
    public volatile boolean move = false;
    public volatile boolean jump = false;
    public volatile float xTouchedPos;
    public volatile float yTouchedPos;
    private SpriteThread spriteThread;
    private Resources res;
    private int width;
    private int height;
    private int maxRes;
    private Context context;

    private int knifeCount = 0;
    private int spawnCounter = 0;
    private int spawnTime = 28; //spawn every second

    public SpriteView(Context context) {
        super(context);
        this.context = context;

        initView();

    }

    public SpriteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        initView();

    }

    public SpriteView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;

        initView();

    }

    public LinkedHashMap<String, SpriteController> getControllerMap() { return this.controllerMap; }
    public void setControllerMap(LinkedHashMap<String, SpriteController> controllerMap) { this.controllerMap = controllerMap; }

    public SpriteThread getSpriteThread() { return this.spriteThread; }
    public void setSpriteThread(SpriteThread spriteThread) { this.spriteThread = spriteThread; }

    public Resources getResources() { return res; }
    public void setResources(Resources res) {
        this.res = res;
    }

    public int getViewWidth() { return width; }
    public void setViewWidth(int width) {
        this.width = width;
    }

    public int getViewHeight() { return height;}
    public void setViewHeight(int height) {
        this.height = height;
    }

    public int getMaxRes() { return maxRes; }
    public void setMaxRes(int maxRes) {
        this.maxRes = maxRes;
    }

    public int getFrameRate() {
        if(controllerMap != null) {
            for (LinkedHashMap.Entry<String, SpriteController> entry : controllerMap.entrySet()) {
                return entry.getValue().getFrameRate();
            }
        }
        return 35;
    }

    private void initView(){

        /* start thread */
        spriteThread = new SpriteThread(this);
        setZOrderOnTop(true);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        surfaceHolder.addCallback(new SurfaceHolder.Callback(){

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                /*if (!spriteThread.isAlive()) {
                    spriteThread.start();
                }
                spriteThread.setRunning(true);*/
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                spriteThread.setRunning(false);
                while (retry) {
                    try {
                        spriteThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                        ;
                    }
                }
            }

        });

    }

    public void onResume(){
        spriteThread = new SpriteThread(this);
        spriteThread.setRunning(true);
        spriteThread.start();
    }

    public void onPause(){
        spriteThread.setRunning(false);
        boolean retry = true;
        while(retry){
            try {
                spriteThread.join();
                spriteThread.setRunning(true);
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void drawSprite() {

        Canvas canvas;
        SpriteEntity entity;
        spawnCounter++;

        try {
            canvas = getHolder().lockCanvas();
        } catch(IllegalStateException e) {
            return;
        } catch(IllegalArgumentException e) {
            return;
        }

        if(canvas != null && spriteThread.getRunning()){
            synchronized (getHolder()) {
                /* refresh scene */
                canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);

                /* render scene */
                if(controllerMap != null) {

                    try {

                        spriteThread.setRunning(false);

                        LinkedHashMap<String, SpriteController> deletionMap = new LinkedHashMap<>();
                        LinkedHashMap<String, SpriteController> additionMap = new LinkedHashMap<>();

                        /* render all entities to the screen */
                        for (LinkedHashMap.Entry<String, SpriteController> entry : controllerMap.entrySet()) {

                            SpriteController controller = entry.getValue();
                            entity = controller.getEntity();

                            /* check for collisions */
                            LinkedHashMap<String, SpriteController> map = new LinkedHashMap<>();
                            if (entry.getValue().getEntity() != null && (entry.getKey().equals("BunnyController"))) {
                                map = entry.getValue().getEntity().onCollisionEvent(entry, controllerMap);
                            }

                            /* add controllers */
                            for(LinkedHashMap.Entry<String, SpriteController> add : map.entrySet()) {
                                additionMap.put(add.getKey(), add.getValue());
                            }

                            /* remove any dead controllers */
                            if (!entry.getValue().getAlive()) {
                                deletionMap.put(entry.getKey(), entry.getValue());
                            }

                            /* update the entity */
                            entity.updateView();

                            Sprite sprite = entity.getSprite();

                            if (sprite.getSpriteSheet() != null && sprite.getFrameToDraw() != null && sprite.getWhereToDraw() != null) {

                                Paint paint = null;

                                /* for debugging bounding boxes
                                paint = new Paint();
                                paint.setStyle(Paint.Style.STROKE);
                                paint.setColor(Color.rgb(0, 0, 0));
                                paint.setStrokeWidth(5);
                                canvas.drawRect(sprite.getBoundingBox(), paint);
                                if(entry.getKey().equals("PlayerController")) {
                                    float left = sprite.getWhereToDraw().left;
                                    float top = sprite.getWhereToDraw().top;
                                    float right = sprite.getWhereToDraw().right;
                                    float bottom = sprite.getWhereToDraw().bottom;
                                    float width = right - left;
                                    float height = bottom - top;
                                    RectF entryLeft = new RectF(left, top + height / 3f, left + width / 3f, top + 2 * height / 3f);
                                    canvas.drawRect(entryLeft, paint);
                                    RectF entryTopLeft = new RectF(left, top, left + width / 3f, top + height / 3f);
                                    canvas.drawRect(entryTopLeft, paint);
                                    RectF entryTop = new RectF(left + width / 3f, top, left + 2 * width / 3f, top + height / 3f);
                                    canvas.drawRect(entryTop, paint);
                                    RectF entryTopRight = new RectF(left + 2 * width / 3f, top, right, top + height / 3f);
                                    canvas.drawRect(entryTopRight, paint);
                                    RectF entryRight = new RectF(left + 2 * width / 3f, top + height / 3f, right, top + 2 * height / 3f);
                                    canvas.drawRect(entryRight, paint);
                                    RectF entryBottomRight = new RectF(left + 2 * width / 3f, top + 2 * height / 3f, right, bottom);
                                    canvas.drawRect(entryBottomRight, paint);
                                    RectF entryBottom = new RectF(left + width / 3f, top + 2 * height / 3f, left + 2 * width / 3f, bottom);
                                    canvas.drawRect(entryBottom, paint);
                                }*/

                                /* for debugging flipped spritesheets
                                paint = new Paint();
                                paint.setStyle(Paint.Style.STROKE);
                                paint.setColor(Color.rgb(0, 0, 0));
                                paint.setStrokeWidth(5);
                                if(entry.getKey().equals("BoxController")) {
                                    Matrix matrix = new Matrix();
                                    matrix.postScale(-1, 1);
                                    matrix.postTranslate(entity.getSprite().getSpriteSheet().getWidth(), 0);
                                    canvas.drawBitmap(entity.getSprite().getSpriteSheet(), matrix, null);

                                    canvas.drawRect(entity.getSprite().getFrameToDraw(), paint);
                                }*/

                                if(entry.getKey().equals("BunnyController")) {
                                    Bunny bunny = (Bunny) controller.getEntity();
                                    switch(bunny.hit) {
                                        case 1:
                                            paint = new Paint();
                                            paint.setColorFilter(new LightingColorFilter(0x00ffe6e6, 0));
                                            break;
                                        case 2:
                                            paint = new Paint();
                                            paint.setColorFilter(new LightingColorFilter(0x00ffb3b3, 0));
                                            break;
                                        case 3:
                                            paint = new Paint();
                                            paint.setColorFilter(new LightingColorFilter(0x00ff8080, 0));
                                            break;
                                        case 4:
                                            paint = new Paint();
                                            paint.setColorFilter(new LightingColorFilter(0x00ff4d4d, 0));
                                            break;
                                        case 5:
                                            paint = new Paint();
                                            paint.setColorFilter(new LightingColorFilter(0x00ff1a1a, 0));
                                            break;
                                        case 6:
                                            paint = new Paint();
                                            paint.setColorFilter(new LightingColorFilter(0x00e60000, 0));
                                            break;
                                        case 7:
                                            paint = new Paint();
                                            paint.setColorFilter(new LightingColorFilter(0x00b30000, 0));
                                            break;
                                        case 8:
                                            paint = new Paint();
                                            paint.setColorFilter(new LightingColorFilter(0x00800000, 0));
                                            break;
                                        case 9:
                                            paint = new Paint();
                                            paint.setColorFilter(new LightingColorFilter(0x004d0000, 0));
                                            break;
                                        case 10:
                                            paint = new Paint();
                                            paint.setColorFilter(new LightingColorFilter(0x001a0000, 0));
                                            break;
                                    }
                                    if(bunny.hit > 10) {
                                        paint = new Paint();
                                        paint.setColorFilter(new LightingColorFilter(0x00000000, 0));
                                    }
                                }

                                canvas.drawBitmap(sprite.getSpriteSheet(), sprite.getFrameToDraw(), sprite.getWhereToDraw(), paint);

                            }
                        }

                        /* check if another knife needs to be spawned */
                        if(spawnCounter >= spawnTime) {

                            spawnCounter = 0;
                            knifeCount++;
                            /* initialize another knife controller */
                            entity = new Knife(getResources(), width, height, maxRes, maxRes, "knife");
                            entity.getController().setYPos(-entity.getSprite().getSpriteHeight());
                            additionMap.put("Knife" + knifeCount + "Controller", entity.getController());

                        }

                        /* add any entities to the scene that need to be added */
                        for(LinkedHashMap.Entry<String, SpriteController> addition : additionMap.entrySet()) {
                            controllerMap.put(addition.getKey(), addition.getValue());
                        }

                        /* remove any dead controllers */
                        for(LinkedHashMap.Entry<String, SpriteController> deletion : deletionMap.entrySet()) {
                            controllerMap.remove(deletion.getKey());
                        }

                        /* refresh bunny controller */
                        SpriteController bunnyController = controllerMap.get("BunnyController");
                        controllerMap.remove("BunnyController");
                        controllerMap.put("BunnyController", bunnyController);

                        spriteThread = new SpriteThread(this);
                        spriteThread.setRunning(true);
                        spriteThread.start();

                    } catch (ConcurrentModificationException e) {
                        spriteThread = new SpriteThread(this);
                        spriteThread.setRunning(true);
                        spriteThread.start();
                    }
                }
            }

        }
        try {
            getHolder().unlockCanvasAndPost(canvas);
        } catch(IllegalStateException e) {
            return;
        } catch(IllegalArgumentException e) {
            return;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        xTouchedPos = event.getX();
        yTouchedPos = event.getY();

        int action = event.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                //System.out.println("DOWN -- X: " + xTouchedPos + ", Y: " + yTouchedPos);
                poke = true;
                move = true;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                jump = true;
                System.out.println("JUMP!!!");
                break;
            case MotionEvent.ACTION_MOVE:
                //System.out.println("MOVED -- X: " + xTouchedPos + ", Y: " + yTouchedPos);
                poke = false;
                move = true;
                break;
            case MotionEvent.ACTION_UP:
                //System.out.println("LIFT -- X: " + xTouchedPos + ", Y: " + yTouchedPos);
                poke = false;
                move = false;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                jump = false;
                break;
            case MotionEvent.ACTION_CANCEL:
                poke = false;
                move = false;
                break;
            case MotionEvent.ACTION_OUTSIDE:
                poke = false;
                move = false;
                break;
            default:
        }
        if (controllerMap != null) {
            try {
                /* call the on touch events for all entities */
                for (LinkedHashMap.Entry<String, SpriteController> entry : controllerMap.entrySet()) {
                    if (entry.getValue().getEntity() != null) {
                        entry.getValue().getEntity().onTouchEvent(this, entry, controllerMap, poke, move, jump, xTouchedPos, yTouchedPos);
                    }
                }
            } catch (ConcurrentModificationException e) {
                ;
            }
        }

        return true;
    }

}