package com.devnem0y.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.devnem0y.Application;
import com.devnem0y.managers.GameScreenManager;

import static com.devnem0y.utils.Constants.*;

public class LoadingScreen extends AbstractScreen{

    private float progress;
    private Stage stageW;

    public LoadingScreen(final Application app) {
        super(app);
        OrthographicCamera widget = new OrthographicCamera();
        widget.setToOrtho(false, APP_SCREEN_WIDTH, APP_SCREEN_HEIGHT);
        stageW = new Stage();
        stageW.setViewport(new FitViewport(APP_SCREEN_WIDTH, APP_SCREEN_HEIGHT, widget));
    }

    @Override
    public void show() {
        super.show();
        System.out.println("LOADING");
        stageW.clear();
        Label.LabelStyle labelStyle = new Label.LabelStyle(fontText, Color.WHITE);
        Label label = new Label("Загрузка...", labelStyle);
        label.setPosition(APP_SCREEN_WIDTH / 2 , APP_SCREEN_HEIGHT / 2, Align.center);
        stageW.addActor(label);
        this.progress = 0f;
        queueAssets();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        stageW.act(delta);
        progress = MathUtils.lerp(progress, app.assetManager.getProgress(), 0.1f);
        if (app.assetManager.update() && progress >= app.assetManager.getProgress() - 0.001f) {
            app.gsm.setScreen(GameScreenManager.STATE.GAME);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stageW.draw();
    }

    private void queueAssets() {
		app.assetManager.load("image/logo.png", Texture.class);
        app.assetManager.finishLoading();
    }

    @Override
    public void resize(int width, int height) {
        stageW.getViewport().update(width, height, true);
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
        stageW.dispose();
    }
}
