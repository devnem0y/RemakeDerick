package com.devnem0y.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {

    protected Rectangle bounds;
    TextureAtlas atlasPDeath, atlasPIdle, atlasPUp, atlasPDown, atlasPRight, atlasPLeft, atlasPAttack, atlasPAttackRight, atlasPAttackLeft;
    Animation animPDeath, animPIdle, animPUp, animPDown, animPRight, animPLeft, animPAttack, animPAttackRight, animPAttackLeft;
    protected Texture texBulletOne;
    protected float velocity;
    protected boolean alive;

    public GameObject() {animInit();}

    private void animInit() {
        if (atlasPDeath == null) {
            atlasPDeath = new TextureAtlas("image/atlas/player/death.atlas");
            animPDeath = new Animation<TextureRegion>(1f/10f, atlasPDeath.findRegions("frame"), Animation.PlayMode.LOOP);
        }
        if (atlasPIdle == null) {
            atlasPIdle = new TextureAtlas("image/atlas/player/idle.atlas");
            animPIdle = new Animation<TextureRegion>(1f/10f, atlasPIdle.findRegions("frame"), Animation.PlayMode.LOOP);
        }
        if (atlasPUp == null) {
            atlasPUp = new TextureAtlas("image/atlas/player/up.atlas");
            animPUp = new Animation<TextureRegion>(1f/10f, atlasPUp.findRegions("frame"), Animation.PlayMode.LOOP);
        }
        if (atlasPDown == null) {
            atlasPDown = new TextureAtlas("image/atlas/player/down.atlas");
            animPDown = new Animation<TextureRegion>(1f/10f, atlasPDown.findRegions("frame"), Animation.PlayMode.LOOP);
        }
        if (atlasPRight == null) {
            atlasPRight = new TextureAtlas("image/atlas/player/right.atlas");
            animPRight = new Animation<TextureRegion>(1f/10f, atlasPRight.findRegions("frame"), Animation.PlayMode.LOOP);
        }
        if (atlasPLeft == null) {
            atlasPLeft = new TextureAtlas("image/atlas/player/left.atlas");
            animPLeft = new Animation<TextureRegion>(1f/10f, atlasPLeft.findRegions("frame"), Animation.PlayMode.LOOP);
        }
        if (atlasPAttack == null) {
            atlasPAttack = new TextureAtlas("image/atlas/player/attack.atlas");
            animPAttack = new Animation<TextureRegion>(1f/10f, atlasPAttack.findRegions("frame"), Animation.PlayMode.LOOP);
        }
        if (atlasPAttackRight == null) {
            atlasPAttackRight = new TextureAtlas("image/atlas/player/attackRight.atlas");
            animPAttackRight = new Animation<TextureRegion>(1f/10f, atlasPAttackRight.findRegions("frame"), Animation.PlayMode.LOOP);
        }
        if (atlasPAttackLeft == null) {
            atlasPAttackLeft = new TextureAtlas("image/atlas/player/attackLeft.atlas");
            animPAttackLeft = new Animation<TextureRegion>(1f/10f, atlasPAttackLeft.findRegions("frame"), Animation.PlayMode.LOOP);
        }
    }

    public void spawn(GameObject object_0) {}
    public void spawn(GameObject object_0, GameObject object_1) {}
    public void spawn(GameObject object_0, GameObject object_1, GameObject object_2) {}
    public void spawn(GameObject object_0, GameObject object_1, GameObject object_2, GameObject object_3) {}
    public void spawn(GameObject object_0, GameObject object_1, GameObject object_2, GameObject object_3, GameObject object_4) {}

    public void setup(float x, float y) {
        this.bounds.setPosition(x, y);
        alive = true;
    }

    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch, float animDelta);
    public void dispose() {
        if (atlasPDeath != null) atlasPDeath.dispose();
        if (atlasPIdle != null) atlasPIdle.dispose();
        if (atlasPUp != null) atlasPUp.dispose();
        if (atlasPDown != null) atlasPDown.dispose();
        if (atlasPRight != null) atlasPRight.dispose();
        if (atlasPLeft != null) atlasPLeft.dispose();
        if (atlasPAttack != null) atlasPAttack.dispose();
        if (atlasPAttackRight != null) atlasPAttackRight.dispose();
        if (atlasPAttackLeft != null) atlasPAttackLeft.dispose();
        if (texBulletOne != null) texBulletOne.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Animation getAnimPDeath() {
        return animPDeath;
    }

    public Animation getAnimPIdle() {
        return animPIdle;
    }

    public Animation getAnimPUp() {
        return animPUp;
    }

    public Animation getAnimPDown() {
        return animPDown;
    }

    public Animation getAnimPRight() {
        return animPRight;
    }

    public Animation getAnimPLeft() {
        return animPLeft;
    }

    public Animation getAnimPAttack() {
        return animPAttack;
    }

    public Animation getAnimPAttackRight() {
        return animPAttackRight;
    }

    public Animation getAnimPAttackLeft() {
        return animPAttackLeft;
    }


}
