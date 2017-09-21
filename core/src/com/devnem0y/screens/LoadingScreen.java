package com.devnem0y.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Align;
import com.devnem0y.Application;
import com.devnem0y.managers.GameScreenManager;

import static com.devnem0y.utils.Constants.*;

public class LoadingScreen extends AbstractScreen{

    private float progress;

    public LoadingScreen(final Application app) {
        super(app);
    }

    @Override
    public void show() {
        super.show();
        System.out.println("LOADING");
        this.progress = 0f;
        queueAssets();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        progress = MathUtils.lerp(progress, app.assetManager.getProgress(), 0.1f);
        if (app.assetManager.update() && progress >= app.assetManager.getProgress() - 0.001f) {
            app.gsm.setScreen(GameScreenManager.STATE.GAME);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        app.batch.begin();
        fontText.draw(app.batch, "Загрузка...", 0, APP_HEIGHT / 2, APP_WIDTH, Align.center, true);
        app.batch.end();
        stage.draw();
    }

    private void queueAssets() {
		app.assetManager.load("image/logo.png", Texture.class);
        app.assetManager.finishLoading();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        fontLeader.dispose();
        fontText.dispose();
        fontLog.dispose();
    }
}
