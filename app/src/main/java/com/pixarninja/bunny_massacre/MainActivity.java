package com.pixarninja.bunny_massacre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {

    SpriteView spriteView;
    LinkedHashMap<String, SpriteController> controllerMap;
    SpriteEntity entity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* initialize the render and controller maps */
        controllerMap = new LinkedHashMap<>();

        /* set center view */
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        spriteView = (SpriteView) findViewById(R.id.spriteView);
        SpriteThread spriteThread = new SpriteThread(spriteView);
        spriteThread.setRunning(true);
        spriteThread.start();
        spriteView.setSpriteThread(spriteThread);

        /* width and height are multiplied by the factor the SpriteView is set in comparison to the screen */
        int height = (int)(displayMetrics.heightPixels * 1.0);
        int width = (int)(displayMetrics.widthPixels * 1.0);
        int maxRes = width / 2;

        /* background */
        spriteView.setBackgroundResource(R.drawable.background);

        /* left button */
        entity = new SpriteButton(getResources(), 0.2, width, height, maxRes, maxRes, R.mipmap.button_arrow_left_off, R.mipmap.button_arrow_left_off, R.mipmap.button_arrow_left_off,
                0, 0, (0.75 * width / 3), height, 1, 1, 1, 1, 1, 0, 0, 1, 1, "loop", null, "red button", "init on");
        entity.getController().setYPos(entity.getController().getYPos() - entity.getSprite().getSpriteHeight() * 2);
        controllerMap.put("LeftButtonController", entity.getController());

        /* right button */
        entity = new SpriteButton(getResources(), 0.2, width, height, maxRes, maxRes, R.mipmap.button_arrow_right_off, R.mipmap.button_arrow_right_off, R.mipmap.button_arrow_right_off,
                0, 0, (2.25 * width / 3), height, 1, 1, 1, 1, 1, 0, 0, 1, 1, "loop", null, "blue button", "init off");
        entity.getController().setYPos(entity.getController().getYPos() - entity.getSprite().getSpriteHeight() * 2);
        controllerMap.put("RightButtonController", entity.getController());

        /* initialize bunny controller */
        entity = new Bunny(getResources(), maxRes, maxRes, width, height, null, "bunny idle", "init");
        controllerMap.put("BunnyController", entity.getController());

        /* set frame rate for all controllers */
        for(LinkedHashMap.Entry<String,SpriteController> controller : controllerMap.entrySet()) {
            controller.getValue().setFrameRate(35);
        }

        /* initialize the entity for the sprite view */
        spriteView.setControllerMap(controllerMap);
        spriteView.setViewWidth(width);
        spriteView.setViewHeight(height);
        spriteView.setMaxRes(maxRes);
        spriteView.setResources(getResources());

        /* print memory statistics */
        final Runtime runtime = Runtime.getRuntime();
        final long usedMemInMB=(runtime.totalMemory() - runtime.freeMemory()) / 1048576L;
        final long maxHeapSizeInMB=runtime.maxMemory() / 1048576L;
        final long availHeapSizeInMB = maxHeapSizeInMB - usedMemInMB;
        System.out.println("Memory Used: " + usedMemInMB + "MB");
        System.out.println("Max Heap Size: " + maxHeapSizeInMB + "MB");
        System.out.println("Available Heap Size: " + availHeapSizeInMB + "MB");

    }

    @Override
    protected void onResume() {
        super.onResume();
        spriteView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        spriteView.onPause();
    }

}
