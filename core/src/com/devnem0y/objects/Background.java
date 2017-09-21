package com.devnem0y.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.devnem0y.utils.Constants.*;

public class Background {

    private Texture texBg, texStar;
    private Star[] stars;
    private int starsCount = 200;

    public Background() {
        texBg = new Texture("image/background.png");
        texStar = new Texture("image/star.png");
        stars = new Star[starsCount];
        for(int i = 0; i < starsCount; i++) {
            stars[i] = new Star();
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texBg, 0, 0, APP_WIDTH, APP_HEIGHT);
        for (int i = 0; i < starsCount; i++) {
            batch.draw(texStar, stars[i].getPosition().x, stars[i].getPosition().y);
        }
    }

    public void update(float delta) {
        for (int i = 0; i < starsCount; i++) {
            stars[i].update(delta);
        }
    }

    public void dispose() {
        texBg.dispose();
        texStar.dispose();
    }
}
