package com.devnem0y.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import static com.devnem0y.utils.Constants.*;

public class Asteroid extends GameObject {

    private GameObject player, rocket;
    private GameObject[] bulletOne, bulletTow, bomb;

    private TextureAtlas atlas;
    private Sprite sprite;
    private float degrees;
    private float timer = 0f;

    public Asteroid() {
        bounds = new Rectangle();
        bounds.setWidth(24);
        bounds.setHeight(27);
        atlas = new TextureAtlas("image/atlas/asteroid.atlas");
    }

    @Override
    public void spawn(GameObject player, GameObject[] bomb, GameObject[] bulletOne, GameObject[] bulletTow, GameObject rocket) {
        this.player = player;
        this.bomb = bomb;
        this.bulletOne = bulletOne;
        this.bulletTow = bulletTow;
        this.rocket = rocket;
        recreate();
    }

    private void recreate() {
        sprite = new Sprite(atlas.findRegion("asteroid", MathUtils.random(1, 5)));
        sprite.setPosition(bounds.getX(), bounds.getY());
        bounds.setPosition(MathUtils.random(APP_WIDTH), APP_HEIGHT + MathUtils.random(300));
        velocity = 50 + (float)Math.random() * 120;
        degrees = MathUtils.random(-10, 10);
        alive = true;
    }

    @Override
    public void update(float delta) {
        if (alive) {
            sprite.rotate(degrees * delta);
            sprite.setPosition(bounds.getX(), bounds.getY());
            bounds.y -= velocity * delta;
//            if (bounds.overlaps(asteroid.getBounds()) || bounds.overlaps(bonus.getBounds()) ||
//                    bounds.overlaps(enemy.getBounds()) || bounds.overlaps(boss.getBounds())) alive = false;

            for (GameObject b : bulletOne) {
                if (bounds.overlaps(b.getBounds())) {
                    System.out.println("BOOM!");
                }
            }

            if ((bounds.getY() + bounds.getHeight() < 0) || (bounds.overlaps(player.getBounds()))) alive = false;

        } else {
            if (timer > 0.5) {
                timer = 0f;
                recreate();
            }
            timer += delta;
        }
    }

    @Override
    public void render(SpriteBatch batch, float animDelta) {
        sprite.draw(batch);
    }

    @Override
    public void dispose() {
        atlas.dispose();
    }


}
