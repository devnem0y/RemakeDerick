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
import com.devnem0y.objects.PlayerBase;
import com.devnem0y.utils.GameState;

import static com.devnem0y.utils.Constants.*;

public class GameScreen extends AbstractScreen {

    private Stage stageW;
    private GameState gameState;

    private Controller controller;
    private Player player;
    private PlayerBase playerBase;

    public GameScreen(final Application app) {
        super(app);
        OrthographicCamera widget = new OrthographicCamera();
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
        controller.initialization(stageW);
        player = new Player(controller);
        player.spawn(null, null, null, null, null);
        playerBase = new PlayerBase();
        Gdx.input.setInputProcessor(stageW);

        gameState = GameState.MENU;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        stageW.act(delta);
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            if (gameState != GameState.PAUSE) gameState = GameState.PAUSE;
            else gameState = GameState.PLAY;
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        app.batch.begin();
        gameStateRender(delta, app.batch);
        fontLog.draw(app.batch, "press ESC to the exit", 10, APP_HEIGHT - 10);
        app.batch.end();
        stageW.draw();
    }

    private void gameStateRender(float delta, SpriteBatch batch) {
        switch (gameState) {
            case MENU:
                gameState = GameState.SCREENSAVER;
                break;
            case SCREENSAVER:
                playerBase.update(delta);
                playerBase.render(app.batch, delta);
                //gameState = GameState.PLAY;
                break;
            case PLAY:
                controller.group.addAction(Actions.moveTo(0, 0, 0.7f));
                player.update(delta);
                player.render(batch, delta);
                break;
            case PAUSE:
                controller.group.addAction(Actions.moveTo(0, -270, 0.7f));
                player.render(batch, delta);
                break;
            case BOSS_SCREENSAVER:
                controller.group.addAction(Actions.moveTo(0, -270, 0.7f));
                break;
            case WIN:
                controller.group.addAction(Actions.moveTo(0, -270, 0.7f));
                break;
            case GAME_OVER:
                controller.group.addAction(Actions.moveTo(0, -270, 0.7f));
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
        if (playerBase != null) playerBase.dispose();
    }
}
