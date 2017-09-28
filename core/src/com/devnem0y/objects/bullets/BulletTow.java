package com.devnem0y.objects.bullets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.devnem0y.objects.GameObject;

import static com.devnem0y.utils.Constants.*;

public class BulletTow extends GameObject {

    private GameObject asteroid, enemy, bonus, boss;
    private int damage;

    public BulletTow() {
        super();
        bounds = new Rectangle();
        bounds.setWidth(16);
        bounds.setHeight(24);
        velocity = 900f;
        damage = 2;
    }

    @Override
    public void spawn(GameObject asteroid, GameObject bonus, GameObject enemy, GameObject boss) {
        this.asteroid = asteroid;
        this.bonus = bonus;
        this.enemy = enemy;
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
            if (bounds.getY() > APP_SCREEN_WIDTH) alive = false;
        }
    }

    @Override
    public void render(SpriteBatch batch, float animDelta) {
        if (alive) batch.draw(texBulletTow, bounds.getX(), bounds.getY());
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public int getDamage() {
        return damage;
    }
}
