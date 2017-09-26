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
import com.devnem0y.handlers.input.MenuGUI;
import com.devnem0y.managers.DialogManager;
import com.devnem0y.objects.Background;
import com.devnem0y.objects.Player;
import com.devnem0y.objects.PlayerBase;
import com.devnem0y.utils.GameState;

import static com.devnem0y.utils.Constants.*;

public class GameScreen extends AbstractScreen {

    private Stage stageW;
    private GameState gameState;

    private MenuGUI menuGUI;
    public static DialogManager dialogManager;
    private Controller controller;
    private Background bg;
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
        bg = new Background();
        dialogManager = new DialogManager(stageW);
        menuGUI = new MenuGUI(fontLeader);
        menuGUI.initialization(stage);
        controller = new Controller();
        controller.initialization(stageW);
        player = new Player(controller);
        player.spawn(null, null, null, null, null);
        playerBase = new PlayerBase();
        Gdx.input.setInputProcessor(stage);

        gameState = GameState.MENU;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        stageW.act(delta);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        app.batch.begin();
        bg.render(app.batch);
        dialogManager.render(app.batch);
        gameStateRender(delta, app.batch);
        app.batch.end();
        stage.draw();
        stageW.draw();
    }

    private void gameStateRender(float delta, SpriteBatch batch) {
        switch (gameState) {
            case MENU:
                menuGUI.group.addAction(Actions.moveTo(0, 0, 0.7f));
                menuGUI.btnExit.setPosition(APP_WIDTH - 100, 25);
                bg.update(delta);
                if (menuGUI.isBtnPlayInput()) {
                    Gdx.input.setInputProcessor(stageW);
                    gameState = GameState.SCREENSAVER;
                } else if (menuGUI.isBtnExitInput()) Gdx.app.exit();
                break;
            case SCREENSAVER:
                menuGUI.group.addAction(Actions.moveTo(0, 480, 4f));
                menuGUI.btnExit.setPosition(APP_WIDTH + 100, 25);
                bg.update(delta);
                playerBase.update(delta);
                playerBase.render(app.batch, delta);
                player.update(delta);
                player.render(batch, delta);
                if (playerBase.getBounds().getY() >= APP_HEIGHT - 320) {
                    dialogManager.messageBase1.show();
                    dialogManager.messageBase2.show();
                    dialogManager.messagePlayer1.show();
                    dialogManager.messagePlayer2.show();
                }
                if (dialogManager.getCurrentDialog() == 4) {
                    playerBase.setCommand(1);
                    if (playerBase.getBounds().getY() + playerBase.getBounds().getHeight() < -10) {
                        player.establishPosition(111);
                        gameState = GameState.PLAY;
                    }
                }
                break;
            case PLAY:
                bg.update(delta);
                player.setCommand(1);
                controller.group.addAction(Actions.moveTo(0, 0, 0.7f));
                player.update(delta);
                player.render(batch, delta);
                pauseGame();
                break;
            case PAUSE:
                controller.group.addAction(Actions.moveTo(0, -290, 0.7f));
                player.render(batch, delta);
                pauseGame();
                break;
            case BOSS_SCREENSAVER:
                bg.update(delta);
                controller.group.addAction(Actions.moveTo(0, -290, 0.7f));
                break;
            case WIN:
                bg.update(delta);
                controller.group.addAction(Actions.moveTo(0, -290, 0.7f));
                break;
            case GAME_OVER:
                bg.update(delta);
                controller.group.addAction(Actions.moveTo(0, -290, 0.7f));
                break;
            default:
                break;
        }
    }

    private void pauseGame() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            if (gameState != GameState.PAUSE) gameState = GameState.PAUSE;
            else gameState = GameState.PLAY;
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
        bg.dispose();
        if (player != null) player.dispose();
        if (playerBase != null) playerBase.dispose();
    }
}
