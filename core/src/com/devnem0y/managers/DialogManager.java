package com.devnem0y.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.devnem0y.handlers.dialog.MessageBase;

public class DialogManager {

    public MessageBase messageBase;

    public DialogManager(Stage stage) {
        messageBase = new MessageBase(800, 105, null, null, Align.center, stage);
    }

    public void render(SpriteBatch batch) {
        messageBase.draw(batch);
    }

    public void dispose() {
        messageBase.dispose();
    }
}
