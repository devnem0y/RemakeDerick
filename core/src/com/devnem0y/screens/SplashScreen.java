package com.devnem0y.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.devnem0y.Application;
import com.devnem0y.managers.GameScreenManager;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import static com.devnem0y.utils.Constants.*;
import static com.devnem0y.utils.PathRes.*;

public class SplashScreen extends AbstractScreen{

    private Image splashImg;

    public SplashScreen(final Application app) {
        super(app);
    }

    @Override
    public void show() {
        super.show();
        System.out.println("LOGO");

        Runnable transitionRunnable = new Runnable() {
            @Override
            public void run() {
                app.gsm.setScreen(GameScreenManager.STATE.GAME);
            }
        };

        Texture logo = app.assetManager.get(LOGO, Texture.class);
        splashImg = new Image(logo);
        splashImg.setPosition(APP_SCREEN_WIDTH / 2 - logo.getWidth() / 2, APP_SCREEN_HEIGHT / 2 - logo.getHeight() / 2);
        splashImg.addAction(sequence(alpha(0), fadeIn(3f), delay(0.7f), fadeOut(2f), run(transitionRunnable)));

        globalStage.addActor(splashImg);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        globalStage.draw();
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
        super.dispose();
    }
}
