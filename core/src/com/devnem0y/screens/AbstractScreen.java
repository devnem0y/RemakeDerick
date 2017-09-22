package com.devnem0y.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.devnem0y.Application;

import static com.devnem0y.utils.Constants.*;

public abstract class AbstractScreen implements Screen{

    protected final Application app;
    protected OrthographicCamera camera;
    protected Stage stage;

    protected BitmapFont fontLog;
    BitmapFont fontLeader, fontText;

    public AbstractScreen(final Application app) {
        this.app = app;
        this.stage = new Stage();
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, APP_WIDTH, APP_HEIGHT);
        stage = new Stage(new FitViewport(APP_WIDTH, APP_HEIGHT, camera));
        fontLog = new BitmapFont();
        fontLog.setColor(Color.SKY);
        fontLeader = new BitmapFont();
        fontText = new BitmapFont();
        initFont();
    }

    private void initFont() {
        String FONT_CHARS = "";
        for (int i = 32; i < 127; i++) FONT_CHARS += (char)i;
        for (int i = 1024; i < 1104; i++) FONT_CHARS += (char)i;

        FreeTypeFontGenerator generatorText = new FreeTypeFontGenerator(Gdx.files.internal("fonts/10468.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter paramsText = new FreeTypeFontGenerator.FreeTypeFontParameter();

        paramsText.characters = FONT_CHARS;
        paramsText.size = 18;
        paramsText.color = Color.WHITE;
        fontText = generatorText.generateFont(paramsText);

        FreeTypeFontGenerator generatorLeader = new FreeTypeFontGenerator(Gdx.files.internal("fonts/8417.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter paramsLeader = new FreeTypeFontGenerator.FreeTypeFontParameter();

        paramsLeader.characters = FONT_CHARS;
        paramsLeader.size = 150;
        paramsLeader.color = Color.WHITE;
        fontLeader = generatorLeader.generateFont(paramsLeader);
    }

    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        update(delta);
        app.batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
