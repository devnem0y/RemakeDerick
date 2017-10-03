package com.devnem0y.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.devnem0y.Application;
import com.devnem0y.managers.GameScreenManager;

import static com.devnem0y.utils.Constants.*;
import static com.devnem0y.utils.PathRes.*;

public class LoadingScreen extends AbstractScreen{

    private float progress;

    public LoadingScreen(final Application app) {
        super(app);
    }

    @Override
    public void show() {
        super.show();
        System.out.println("LOADING");
        Label.LabelStyle labelStyle = new Label.LabelStyle(fontText, Color.WHITE);
        Label label = new Label("Загрузка...", labelStyle);
        label.setPosition(APP_SCREEN_WIDTH / 2 , APP_SCREEN_HEIGHT / 2, Align.center);
        globalStage.addActor(label);
        this.progress = 0f;
        queueAssets();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        progress = MathUtils.lerp(progress, app.assetManager.getProgress(), 0.1f);
        if (app.assetManager.update() && progress >= app.assetManager.getProgress() - 0.001f) {
            app.gsm.setScreen(GameScreenManager.STATE.SPLASH);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        globalStage.draw();
    }

    private void queueAssets() {
        // Texture
		app.assetManager.load(LOGO, Texture.class);
		app.assetManager.load(BACKGROUND, Texture.class);
		app.assetManager.load(STAR, Texture.class);
        // Atlas
		app.assetManager.load(ATLAS_PLAYER_DEATH, TextureAtlas.class);
		app.assetManager.load(ATLAS_PLAYER_IDLE, TextureAtlas.class);
		app.assetManager.load(ATLAS_PLAYER_UP, TextureAtlas.class);
		app.assetManager.load(ATLAS_PLAYER_DOWN, TextureAtlas.class);
		app.assetManager.load(ATLAS_PLAYER_RIGHT, TextureAtlas.class);
		app.assetManager.load(ATLAS_PLAYER_LEFT, TextureAtlas.class);
		app.assetManager.load(ATLAS_PLAYER_ATTACK, TextureAtlas.class);
		app.assetManager.load(ATLAS_PLAYER_ATTACK_R, TextureAtlas.class);
		app.assetManager.load(ATLAS_PLAYER_ATTACK_L, TextureAtlas.class);
		app.assetManager.load(ATLAS_ASTEROID_CRASH, TextureAtlas.class);
        app.assetManager.finishLoading();
    }

    @Override
    public void resize(int width, int height) {
        globalStage.getViewport().update(width, height, true);
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
