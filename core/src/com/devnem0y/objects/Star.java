package com.devnem0y.objects;

import com.badlogic.gdx.math.Vector2;

import static com.devnem0y.utils.Constants.*;

public class Star {

    private Vector2 position;
    private float speed;

    public Vector2 getPosition() {
        return position;
    }

    public Star() {
        position = new Vector2(APP_WIDTH * (float)Math.random(), APP_HEIGHT * (float)Math.random());
        speed = 100 + (float)Math.random() * 300;
    }

    public void update(float delta) {
        position.y -= speed * delta;
        if(position.y < 0) {
            position.y = APP_HEIGHT + 10;
            position.x = (float)Math.random() * APP_WIDTH;
        }
    }
}
