package com.devnem0y.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import static com.devnem0y.utils.Constants.*;

public class Bullet extends GameObject {

    private GameObject[] asteroid, bomb;
    private GameObject boss;
    private Texture texture;
    private int damage;

    public Bullet(String texturePath, float width, float height, float velocity, int damage) {
        bounds = new Rectangle();
        bounds.setWidth(width);
        bounds.setHeight(height);
        texture = new Texture(texturePath);
        this.velocity = velocity;
        this.damage = damage;
    }

    @Override
    public void spawn(GameObject[] asteroid, GameObject[] bomb, GameObject boss) {
        this.asteroid = asteroid;
        this.bomb = bomb;
        this.boss = boss;
    }

    @Override
    public void setup(float x, float y) {
        super.setup(x, y);
    }

    @Override
    public void update(float delta) {
        if (alive) {
            bounds.y += velocity * delta;
//            if (bounds.overlaps(asteroid.getBounds()) || bounds.overlaps(bonus.getBounds()) ||
//                    bounds.overlaps(enemy.getBounds()) || bounds.overlaps(boss.getBounds())) alive = false;
            if (bounds.getY() > APP_HEIGHT) alive = false;
        }
    }

    @Override
    public void render(SpriteBatch batch, float animDelta) {
        if (alive) batch.draw(texture, bounds.getX(), bounds.getY());
    }

    @Override
    public void dispose() {
        super.dispose();
        texture.dispose();
    }

    public int getDamage() {
        return damage;
    }
}
