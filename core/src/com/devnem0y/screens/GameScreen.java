package com.devnem0y.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.devnem0y.Application;
import com.devnem0y.handlers.input.Controller;
import com.devnem0y.managers.GameScreenManager;

import static com.devnem0y.utils.Constants.*;

public class GameScreen extends AbstractScreen{

    private Stage stageW;

    private Controller controller;

    public GameScreen(final Application app) {
        super(app);
        OrthographicCamera widget = new OrthographicCamera();
        widget.setToOrtho(false, APP_SCREEN_WIDTH, APP_SCREEN_HEIGHT);
        this.stageW = new Stage();
        stageW.setViewport(new FitViewport(APP_SCREEN_WIDTH, APP_SCREEN_HEIGHT, widget));

        controller = new Controller();
    }

    @Override
    public void show() {
        System.out.println("GAME");

        controller.createJoy(stageW);
        Gdx.input.setInputProcessor(stageW);
        stageW.clear();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {
            app.gsm.setScreen(GameScreenManager.STATE.MENU);
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        stageW.act(delta);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        app.batch.begin();
        fontLog.draw(app.batch, "press BACKSPACE return MenuScreen", 10, APP_HEIGHT - 10);
        fontLog.draw(app.batch, "press ESC to the exit", 10, APP_HEIGHT - 25);
        app.batch.end();
        stage.draw();
        stageW.draw();
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
