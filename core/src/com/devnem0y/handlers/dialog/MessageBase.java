package com.devnem0y.handlers.dialog;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import static com.devnem0y.screens.GameScreen.dialogManager;

public class MessageBase extends Dialog {

    private Texture avatar;

    public MessageBase(int width, int height, String text, boolean visible, int align, Stage stage) {
        super(width, height, text, visible, align, stage);
        avatar = new Texture("image/avatarB.png");
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        batch.draw(avatar, getFrame().getX() + 635, getFrame().getY() - 60);
    }

    @Override
    public void hide() {
        super.hide();
        dialogManager.setCurrentDialog(dialogManager.getCurrentDialog() + 1);
        if (dialogManager.getCurrentDialog() == 1) dialogManager.messagePlayer1.setVisible(true);
        else if (dialogManager.getCurrentDialog() == 3) dialogManager.messagePlayer2.setVisible(true);
    }

    @Override
    public void dispose() {
        super.dispose();
        avatar.dispose();
    }
}
