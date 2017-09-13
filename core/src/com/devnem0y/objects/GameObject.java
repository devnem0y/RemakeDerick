package com.devnem0y.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject {

    public Rectangle bounds;

    public float velocity;
    public boolean alive;

    public GameObject() {
    }

    public void spawn(GameObject object_0) {

    }
    public void spawn(GameObject object_0, GameObject object_1) {

    }
    public void spawn(GameObject object_0, GameObject object_1, GameObject object_2) {

    }
    public void spawn(GameObject object_0, GameObject object_1, GameObject object_2, GameObject object_3) {

    }
    public void spawn(GameObject object_0, GameObject object_1, GameObject object_2, GameObject object_3, GameObject object_4) {

    }

    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch, float animDelta);
    public abstract void dispose();

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public int getVelocity() {
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
}
