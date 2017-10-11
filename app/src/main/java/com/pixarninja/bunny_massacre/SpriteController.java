package com.pixarninja.bunny_massacre;

public class SpriteController {

    private String ID;
    private String transition;
    private SpriteEntity entity;
    private double xInit;
    private double yInit;
    private double xPos;
    private double yPos;
    private volatile double xDelta;
    private volatile double yDelta;
    private int frameLengthInMilliseconds = 35;
    private long lastFrameChangeTime;
    private boolean reacting = false;
    private boolean alive = true;


    public String getID() { return this.ID; }
    public void setID(String ID) { this.ID = ID; }

    public SpriteEntity getEntity() { return this.entity; }
    public void setEntity(SpriteEntity entity) { this.entity = entity; }

    public String getTransition() { return this.transition; }
    public void setTransition(String transition) { this.transition = transition; }

    public double getXInit() { return this.xInit; }
    public void setXInit(double xInit) { this.xInit = xInit; }

    public double getYInit() { return this.yInit; }
    public void setYInit(double yInit) { this.yInit = yInit; }

    public double getXPos() { return this.xPos; }
    public void setXPos(double xPos) { this.xPos = xPos; }

    public double getYPos() { return this.yPos; }
    public void setYPos(double yPos) { this.yPos = yPos; }

    public double getXDelta() {
        return this.xDelta;
    }
    public void setXDelta(double xDelta) {
        this.xDelta = xDelta;
    }

    public double getYDelta() {
        return this.yDelta;
    }
    public void setYDelta(double yDelta) {
        this.yDelta = yDelta;
    }

    public int getFrameRate() { return this.frameLengthInMilliseconds; }
    public void setFrameRate(int frameLengthInMilliseconds) { this.frameLengthInMilliseconds = frameLengthInMilliseconds; }

    public long getLastFrameChangeTime() { return this.lastFrameChangeTime; }
    public void setLastFrameChangeTime(long lastFrameChangeTime) { this.lastFrameChangeTime = lastFrameChangeTime; }

    public boolean getReacting() { return this.reacting; }
    public void setReacting(boolean reacting) { this.reacting = reacting; }

    public boolean getAlive() { return this.alive; }
    public void setAlive(boolean alive) { this.alive = alive; }

    public void printController() {

        System.out.println("Info of controller " + ID + ":");
        System.out.println(" - entity: " + entity);
        System.out.println(" - x initial position: " + xInit);
        System.out.println(" - y initial position: " + yInit);
        System.out.println(" - x position: " + xPos);
        System.out.println(" - y position: " + yPos);
        System.out.println(" - x delta: " + xDelta);
        System.out.println(" - y delta: " + yDelta);
        System.out.println(" - frame rate: " + frameLengthInMilliseconds);
        System.out.println(" - last frame change time: " + lastFrameChangeTime);
        System.out.println(" - reacting: " + reacting);

    }

}