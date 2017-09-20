package com.devnem0y.handlers.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.devnem0y.managers.DialogManager;

import static com.devnem0y.utils.Constants.*;

public class Dialog {

    private Rectangle frame;
    private Texture texture;
    private Button next;
    private BitmapFont fontLeader, fontText;
    private int align;

    private float timer;
    private int letterNumber;
    private StringBuilder fullText;
    private StringBuilder partText = new StringBuilder();
    private boolean visible;

    public Dialog(int width, int height, String text, boolean visible, int align, Stage stage) {
        frame = new Rectangle();
        frame.width = width;
        frame.height = height;
        texture = new Texture("image/messageBG.png");
        frame.setPosition(-1000, 0);
        fontLeader = new BitmapFont();
        fontText = new BitmapFont();
        initFont();
        initButtonExit(stage);
        fullText = new StringBuilder(text);
        this.align = align;
        this.visible = visible;
    }

    private void initFont() {
        String FONT_CHARS = "";
        for (int i = 32; i < 127; i++) FONT_CHARS += (char)i;
        for (int i = 1024; i < 1104; i++) FONT_CHARS += (char)i;

        FreeTypeFontGenerator generatorText = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9303.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter paramsText = new FreeTypeFontGenerator.FreeTypeFontParameter();

        paramsText.characters = FONT_CHARS;
        paramsText.size = 12;
        paramsText.color = Color.WHITE;
        fontText = generatorText.generateFont(paramsText);

        FreeTypeFontGenerator generatorLeader = new FreeTypeFontGenerator(Gdx.files.internal("fonts/8417.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter paramsLeader = new FreeTypeFontGenerator.FreeTypeFontParameter();

        paramsLeader.characters = FONT_CHARS;
        paramsLeader.size = 26;
        paramsLeader.color = Color.WHITE;
        fontLeader = generatorLeader.generateFont(paramsLeader);
    }

    public void show() {
        if (visible) {
            frame.setPosition(0, 0);
            if (next != null) {
                next.setPosition((frame.getX() + frame.getWidth()) - 210, frame.getY() + 2);
            }

            timer += Gdx.graphics.getDeltaTime();
            if (letterNumber != fullText.length()) {
                if (timer >= 0.12f) {
                    timer = 0;
                    partText.append(fullText.charAt(letterNumber++));
                }
            }
        }
    }

    public void hide() {
        frame.setPosition(-1000, 0);
        if (next != null) {
            next.setPosition((frame.getX() + frame.getWidth()) - 210, frame.getY() + 2);
        }
        visible = false;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
        fontText.draw(batch, partText, frame.getX() + 180, (frame.getY() + frame.getHeight() - 20), frame.getWidth() - 360, align, true);
        fontText.draw(batch, "ДАЛЕЕ >>", APP_WIDTH - 200, 20);
    }

    private void initButtonExit(Stage stage) {
        Skin skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("image/atlas/btnNext.atlas")));
        Button.ButtonStyle exitStyle = new Button.ButtonStyle();
        exitStyle.down = skin.getDrawable("btn_next");
        exitStyle.up = skin.getDrawable("btn_next");
        next = new Button(exitStyle);
        next.setPosition((frame.getX() + frame.getWidth()) - 210, frame.getY() + 2);
        next.addListener(new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                hide();
            }
        });

        stage.addActor(next);
    }

    protected Rectangle getFrame() {
        return frame;
    }

    public BitmapFont getFontLeader() {
        return fontLeader;
    }

    public BitmapFont getFontText() {
        return fontText;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void dispose() {
        if (texture != null) texture.dispose();
    }
}
