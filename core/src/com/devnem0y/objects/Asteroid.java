package com.devnem0y.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.devnem0y.objects.animations.AnimStateMachine;
import com.devnem0y.objects.animations.AsteroidCrash;
import com.devnem0y.objects.animations.AsteroidIdle;

import static com.devnem0y.utils.Constants.*;

public class Asteroid extends GameObject {

    private GameObject player, rocket;
    private GameObject[] bulletOne, bulletTow, bomb;
    private AnimStateMachine animStateMachine;
    private float animDt = 0f;

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
        spriteAsteroid.set(sprite);
        spriteAsteroid.setPosition(-100, 0);
        bounds.setPosition(MathUtils.random(APP_WIDTH), APP_HEIGHT + MathUtils.random(300));
        velocity = 50 + (float)Math.random() * 120;
        degrees = MathUtils.random(-10, 10);
        alive = true;
        setState(new AsteroidIdle());
    }

    @Override
    public void update(float delta) {
        if (alive) {
            spriteAsteroid.rotate(degrees * delta);
            spriteAsteroid.setPosition(bounds.getX(), bounds.getY());
            bounds.y -= velocity * delta;
            if (bounds.getY() + bounds.getHeight() < 0) alive = false;
            collision(null, player);
//            collision(bulletOne, null);
//            collision(bulletTow, null);
            collision(null, rocket);
//            collision(bomb, null);
        } else {
            if (timer > 0.4) {
                timer = 0f;
                recreate();
            }
            timer += delta;
        }
    }

    private void collision(GameObject[] gameObjectsArray, GameObject gameObject) {
        if (gameObjectsArray != null) {
            for (GameObject object : gameObjectsArray) {
                if (bounds.overlaps(object.getBounds())) {
                    animDt = 0;
                    setState(new AsteroidCrash());
                    alive = false;
                }
            }
        } else if (gameObject != null) {
            if (bounds.overlaps(gameObject.getBounds())) {
                animDt = 0;
                setState(new AsteroidCrash());
                alive = false;
            }
        }
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        running(batch, animDt);
        animDt += delta;
    }

    private void setState(AnimStateMachine s) {this.animStateMachine = s;}
    private void running(SpriteBatch batch, float dt) {
        animStateMachine.running(this, batch, dt);
    }

    @Override
    public void dispose() {
        super.dispose();
        atlas.dispose();
    }


}
