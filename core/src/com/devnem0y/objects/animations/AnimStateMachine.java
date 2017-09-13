package com.devnem0y.objects.animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.devnem0y.objects.GameObject;

public interface AnimStateMachine {

    void running(GameObject context, SpriteBatch batch, float delta);
}
