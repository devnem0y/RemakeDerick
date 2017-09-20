package com.devnem0y.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.devnem0y.handlers.dialog.MessageBase;
import com.devnem0y.handlers.dialog.MessagePlayer;

public class DialogManager {

    private int currentDialog = 0;

    public MessageBase messageBase1, messageBase2;
    public MessagePlayer messagePlayer1, messagePlayer2;

    public DialogManager(Stage stage) {
        messageBase1 = new MessageBase(800, 105, "Диалог 1", true, Align.center, stage);
        messagePlayer1 = new MessagePlayer(800, 105, "Диалог 2", false, Align.center, stage);
        messageBase2 = new MessageBase(800, 105, "Диалог 3", false, Align.center, stage);
        messagePlayer2 = new MessagePlayer(800, 105, "Диалог 4", false, Align.center, stage);
    }

    public void render(SpriteBatch batch) {
        messageBase1.draw(batch);
        messageBase2.draw(batch);
        messagePlayer1.draw(batch);
        messagePlayer2.draw(batch);
    }

    public void dispose() {
        messageBase1.dispose();
        messageBase2.dispose();
        messagePlayer1.dispose();
        messagePlayer2.dispose();
    }

    public void setCurrentDialog(int currentDialog) {
        this.currentDialog = currentDialog;
    }

    public int getCurrentDialog() {
        return currentDialog;
    }
}
