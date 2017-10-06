package com.devnem0y.managers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import java.util.HashMap;

public class AnimationManager {

    private String currentAnim;
    private HashMap<String, Animation> animList;

    public AnimationManager() {
    }

    public void create(String name, TextureAtlas textureAtlas, float frameDuration, PlayMode playMode) {
        this.animList = new HashMap<String, Animation>();
        this.animList.put(name, new Animation(frameDuration, textureAtlas, playMode));
        currentAnim = name;
    }

    public void draw(SpriteBatch batch) {
        
    }
}
