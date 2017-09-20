package com.devnem0y.handlers.dialog;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.devnem0y.screens.GameScreen.dialogManager;

public class MessagePlayer extends Dialog {

    private Texture avatar;

    public MessagePlayer(int width, int height, String text, boolean visible, int align, Stage stage) {
        super(width, height, text, visible, align, stage);
        avatar = new Texture("image/avatarP.png");
    }

    @Override
    public void hide() {
        super.hide();
        dialogManager.setCurrentDialog(dialogManager.getCurrentDialog() + 1);
        if (dialogManager.getCurrentDialog() == 2) dialogManager.messageBase2.setVisible(true);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        batch.draw(avatar, getFrame().getX() - 35, getFrame().getY() - 60);
    }

    @Override
    public void dispose() {
        super.dispose();
        avatar.dispose();
    }
}
