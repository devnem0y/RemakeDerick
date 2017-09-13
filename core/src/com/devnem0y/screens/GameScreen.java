package com.devnem0y.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.devnem0y.Application;
import com.devnem0y.handlers.input.Controller;
import com.devnem0y.objects.Player;
import com.devnem0y.utils.GameState;

import static com.devnem0y.utils.Constants.*;

public class GameScreen extends AbstractScreen {

    private OrthographicCamera widget;
    private Stage stageW;

    private Controller controller;

    private GameState gameState;

    private Player player;

    public GameScreen(final Application app) {
        super(app);
        widget = new OrthographicCamera();
        widget.setToOrtho(false, APP_SCREEN_WIDTH, APP_SCREEN_HEIGHT);
        stageW = new Stage();
        stageW.setViewport(new FitViewport(APP_SCREEN_WIDTH, APP_SCREEN_HEIGHT, widget));
    }

    @Override
    public void show() {
        super.show();
        System.out.println("GAME");
        stageW.clear();
        controller = new Controller();
        controller.createJoy(stageW);
        player = new Player(controller);
        player.spawn(null, null, null, null, null);
        Gdx.input.setInputProcessor(stageW);

        gameState = GameState.MENU;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        stageW.act(delta);
        controller.joyGroup.addAction(Actions.moveTo(0, 0, 0.7f));
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        app.batch.setProjectionMatrix(widget.combined);
        app.batch.begin();
        gameStateRender(delta, app.batch);
        fontLog.draw(app.batch, "press ESC to the exit", 10, APP_HEIGHT - 10);
        app.batch.end();
        stageW.draw();
    }

    private void gameStateRender(float delta, SpriteBatch batch) {
        switch (gameState) {
            case MENU:
                gameState = GameState.PLAY;
                break;
            case PLAY:
                player.update(delta);
                player.render(batch, delta);
                break;
            case PAUSE:
                break;
            default:
                break;
        }
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
        if (player != null) player.dispose();
    }
}
