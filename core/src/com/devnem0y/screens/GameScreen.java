package com.devnem0y.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.devnem0y.Application;
import com.devnem0y.handlers.input.Controller;
import com.devnem0y.managers.GameScreenManager;

import static com.devnem0y.utils.Constants.APP_HEIGHT;
import static com.devnem0y.utils.Constants.APP_SCREEN_HEIGHT;
import static com.devnem0y.utils.Constants.APP_SCREEN_WIDTH;

public class GameScreen extends AbstractScreen {

    private OrthographicCamera widget;
    private Stage stageW;

    private Controller controller;

    public GameScreen(final Application app) {
        super(app);
        widget = new OrthographicCamera();
        widget.setToOrtho(false, APP_SCREEN_WIDTH, APP_SCREEN_HEIGHT);
        stageW = new Stage();
        stageW.setViewport(new FitViewport(APP_SCREEN_WIDTH, APP_SCREEN_HEIGHT, widget));

        controller = new Controller();
    }

    @Override
    public void show() {
        super.show();
        System.out.println("GAME");
        stageW.clear();
        controller.createJoy(stageW);
        Gdx.input.setInputProcessor(stageW);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        stageW.act(delta);
        controller.joyGroup.addAction(Actions.moveTo(0, 0, 0.7f));
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
            app.gsm.setScreen(GameScreenManager.STATE.MENU);
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        app.batch.setProjectionMatrix(widget.combined);
        app.batch.begin();
        fontLog.draw(app.batch, "press BACKSPACE return MenuScreen", 10, APP_HEIGHT - 10);
        fontLog.draw(app.batch, "press ESC to the exit", 10, APP_HEIGHT - 25);
        app.batch.end();
        stageW.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
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
        super.dispose();
        stageW.dispose();
    }
}
