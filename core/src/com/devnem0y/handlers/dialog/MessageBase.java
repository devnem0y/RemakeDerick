package com.devnem0y.handlers.dialog;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MessageBase extends Dialog {

    private Texture avatar;

    public MessageBase(int width, int height, String leader, String text, int align, Stage stage) {
        super(width, height, leader, text, align, stage);
        avatar = new Texture("image/avatarB.png");
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        batch.draw(avatar, getFrame().getX() + 680, getFrame().getY() - 60);
    }

    @Override
    public void dispose() {
        super.dispose();
        avatar.dispose();
    }
}
