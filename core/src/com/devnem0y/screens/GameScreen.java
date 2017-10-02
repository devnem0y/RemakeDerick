package com.devnem0y.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.devnem0y.Application;
import com.devnem0y.handlers.input.Controller;
import com.devnem0y.handlers.input.MenuGUI;
import com.devnem0y.managers.DialogManager;
import com.devnem0y.objects.Asteroid;
import com.devnem0y.objects.Background;
import com.devnem0y.objects.Bullet;
import com.devnem0y.objects.Player;
import com.devnem0y.objects.PlayerBase;
import com.devnem0y.utils.GameState;

import static com.devnem0y.utils.Constants.*;
import static com.devnem0y.utils.PathRes.*;

public class GameScreen extends AbstractScreen {

    private GameState gameState;

    private MenuGUI menuGUI;
    public static DialogManager dialogManager;
    private Controller controller;
    private Label infoRocket;

    // Game objects
    private Background bg;
    private Player player;
    private PlayerBase playerBase;
    private Bullet[] bulletsOne;
    private Bullet[] bulletsTow;
    private Bullet rockets;
    private Asteroid[] asteroids;


    public GameScreen(final Application app) {
        super(app);
        OrthographicCamera widget = new OrthographicCamera();
        widget.setToOrtho(false, APP_SCREEN_WIDTH, APP_SCREEN_HEIGHT);
    }

    @Override
    public void show() {
        super.show();
        System.out.println("GAME");
        globalStage.clear();
        bg = new Background();
        dialogManager = new DialogManager(globalStage);
        menuGUI = new MenuGUI(fontLeader);
        menuGUI.initialization(stage);
        controller = new Controller();
        controller.initialization(globalStage);
        createGameObjects();
        spawnObjects();
        createInterface();

        Gdx.input.setInputProcessor(stage);
        gameState = GameState.MENU;
    }

    private void createGameObjects() {
        player = new Player(controller);
        bulletsOne = new Bullet[5];
        bulletsTow = new Bullet[5];
        rockets = new Bullet(ROCKET, 16f, 30f, 350f, 20);
        playerBase = new PlayerBase();
        asteroids = new Asteroid[4];
    }

    private void spawnObjects() {
        for (int i = 0; i < bulletsOne.length; i++) {
            bulletsOne[i] = new Bullet(BULLET_1, 6f, 17f, 900f, 1);
            bulletsOne[i].spawn(null, null, null);
        }
        for (int i = 0; i < bulletsTow.length; i++) {
            bulletsTow[i] = new Bullet(BULLET_2, 16f, 24f, 900f, 2);
            bulletsTow[i].spawn(null, null, null);
        }
        rockets.spawn(null, null, null);
        player.spawn(null, null, null, bulletsOne, bulletsTow, rockets);
        for (int i = 0; i < asteroids.length; i++) {
            asteroids[i] = new Asteroid();
            asteroids[i].spawn(player, null, bulletsOne, bulletsTow, rockets);
        }
    }

    private void createInterface() {
        Label.LabelStyle labelStyle = new Label.LabelStyle(fontText, Color.WHITE);
        infoRocket = new Label("",labelStyle);
        infoRocket.setPosition(APP_SCREEN_WIDTH - 145, APP_SCREEN_HEIGHT / 2 + 40);
        globalStage.addActor(infoRocket);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        globalStage.act(delta);
        if (gameState != GameState.PLAY) infoRocket.setVisible(false);
        else infoRocket.setVisible(true);
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
        globalStage.draw();
    }

    private void gameStateRender(float delta, SpriteBatch batch) {
        switch (gameState) {
            case MENU:
                menu(delta);
                break;
            case SCREENSAVER:
                screensaver(delta, batch);
                break;
            case PLAY:
                play(delta, batch);
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

    private void play(float delta, SpriteBatch batch) {
        bg.update(delta);
        player.setCommand(1);
        controller.group.addAction(Actions.moveTo(0, 0, 0.7f));
        player.update(delta);
        player.render(batch, delta);
        for (Bullet b : bulletsOne) {
            b.update(delta);
            b.render(batch, delta);
        }
        for (Bullet bt : bulletsTow) {
            bt.update(delta);
            bt.render(batch, delta);
        }
        rockets.update(delta);
        rockets.render(batch, delta);
        infoRocket.setText("ROCKETS: " + player.getRockets());
        for (Asteroid a : asteroids) {
            a.update(delta);
            a.render(batch, delta);
        }
        pauseGame();
    }

    private void menu(float delta) {
        menuGUI.group.addAction(Actions.moveTo(0, 0, 0.7f));
        menuGUI.btnExit.setPosition(APP_WIDTH - 100, 25);
        bg.update(delta);
        if (menuGUI.isBtnPlayInput()) {
            Gdx.input.setInputProcessor(globalStage);
            gameState = GameState.SCREENSAVER;
        } else if (menuGUI.isBtnExitInput()) Gdx.app.exit();
    }

    private void screensaver(float delta, SpriteBatch batch) {
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
        bg.dispose();
        if (player != null) player.dispose();
        if (playerBase != null) playerBase.dispose();
        for (Bullet b : bulletsOne) {
            if (b != null) b.dispose();
        }
        for (Bullet bt : bulletsTow) {
            if (bt != null) bt.dispose();
        }
        if (rockets != null) rockets.dispose();
        for (Asteroid a : asteroids) {
            if (a != null) a.dispose();
        }
    }
}
