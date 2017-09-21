package com.devnem0y.objects.animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.devnem0y.objects.GameObject;

public class Down implements AnimStateMachine {

    @Override
    public void running(GameObject context, SpriteBatch batch, float delta) {
        batch.draw((TextureRegion) context.getAnimPDown().getKeyFrame(delta), context.getBounds().getX(), context.getBounds().getY());
    }
}
