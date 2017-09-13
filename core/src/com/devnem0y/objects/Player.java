package com.devnem0y.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import static com.devnem0y.utils.Constants.*;

public class Player extends GameObject {

    private GameObject asteroid, enemy, 

    public Player() {
        bounds = new Rectangle();
    }

    @Override
    public void spawn(GameObject object_0, GameObject object_1, GameObject object_2, GameObject object_3, GameObject object_4) {
        bounds.setWidth(64);
        bounds.setHeight(64);
        bounds.setPosition(APP_WIDTH / 2 - bounds.getWidth() / 2, 30);
        velocity = 7f;
        alive = true;

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch, float animDelta) {

    }

    @Override
    public void dispose() {

    }
}
