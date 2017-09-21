package com.devnem0y.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import static com.devnem0y.utils.Constants.*;

public class PlayerBase extends GameObject {

    private Texture texture;
    private int command;

    public PlayerBase() {
        super();
        texture = new Texture("image/playerBase.png");
        bounds = new Rectangle();
        bounds.setWidth(166);
        bounds.setHeight(244);
        bounds.setPosition(APP_WIDTH / 2 + 120, -250);
        velocity = 140f;
        command = 0;
    }


    @Override
    public void update(float delta) {
        if (command == 0) {
            if (bounds.getY() <= APP_HEIGHT - 320) {
                bounds.setY(bounds.getY() + velocity * delta);
            }
        } else if (command == 1) {
            if (bounds.getY() + bounds.getHeight() > -10) {
                bounds.setY(bounds.getY() - velocity * delta);
            }
        }
    }

    @Override
    public void render(SpriteBatch batch, float animDelta) {
        batch.draw(texture, getBounds().getX(), getBounds().getY());
    }

    @Override
    public void dispose() {
        super.dispose();
        if (texture != null) texture.dispose();
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }
}
