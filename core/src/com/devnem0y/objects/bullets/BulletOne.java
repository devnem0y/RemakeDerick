package com.devnem0y.objects.bullets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.devnem0y.objects.GameObject;

import static com.devnem0y.utils.Constants.APP_WIDTH;

public class BulletOne extends GameObject {

    private GameObject asteroid, enemy, bonus, boss;

    public BulletOne() {
        super();
        bounds = new Rectangle();
        bounds.setWidth(4);
        bounds.setHeight(4);
        velocity = 700f;
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
            if (bounds.overlaps(asteroid.getBounds()) || bounds.overlaps(bonus.getBounds()) ||
                    bounds.overlaps(enemy.getBounds()) || bounds.overlaps(boss.getBounds())) alive = false;
            if (bounds.getY() > APP_WIDTH) alive = false;
        }
    }

    @Override
    public void render(SpriteBatch batch, float animDelta) {
        if (alive) batch.draw(texBulletOne, bounds.getX(), bounds.getY());
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
