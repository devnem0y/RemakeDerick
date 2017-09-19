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

import static com.devnem0y.utils.Constants.*;

public class Dialog {

    private Rectangle frame;
    private Texture texture;
    private Button next;
    private BitmapFont fontLeader, fontText;
    private String leader = null, text = null;
    private int align;

    public Dialog(int width, int height, String leader, String text, int align, Stage stage) {
        frame = new Rectangle();
        frame.width = width;
        frame.height = height;
        texture = new Texture("image/messageBG.png");
        frame.setPosition(-1000, 0);
        fontLeader = new BitmapFont();
        fontText = new BitmapFont();
        initFont();
        initButtonExit(stage);
        this.leader = leader;
        this.text = text;
        this.align = align;
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
        frame.setPosition(0, 0);
        if (next != null) {
            next.setPosition((frame.getX() + frame.getWidth()) - 90, frame.getY() + 2);
        }
    }

    public void show(String text) {
        this.text = text;
        frame.setPosition(0, 0);
        if (next != null) {
            next.setPosition((frame.getX() + frame.getWidth()) - 90, frame.getY() + 2);
        }
    }

    public void show(String leader, String text) {
        this.text = text;
        this.leader = leader;
        frame.setPosition(0, 0);
        if (next != null) {
            next.setPosition((frame.getX() + frame.getWidth()) - 90, frame.getY() + 2);
        }
    }

    public void hide() {
        frame.setPosition(-1000, 0);
        if (next != null) {
            next.setPosition((frame.getX() + frame.getWidth()) - 90, frame.getY() + 2);
        }
    }

    public void draw(SpriteBatch batch) {
        if (texture != null) batch.draw(texture, frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
        if (leader != null) {
            fontLeader.draw(batch, leader, frame.getX() + 20, (frame.getY() + frame.getHeight() - 20), frame.getWidth() - 40, Align.center, true);
            if (text != null) fontText.draw(batch, text, frame.getX() + 20, (frame.getY() + frame.getHeight() - 45), frame.getWidth() - 40, align, true);
        } else if (text != null) fontText.draw(batch, text, frame.getX() + 72, (frame.getY() + frame.getHeight() - 20), frame.getWidth() - 92, align, true);
    }

    private void initButtonExit(Stage stage) {
        Skin skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("image/atlas/btnNext.atlas")));
        Button.ButtonStyle exitStyle = new Button.ButtonStyle();
        exitStyle.down = skin.getDrawable("btn_next");
        exitStyle.up = skin.getDrawable("btn_next");
        next = new Button(exitStyle);
        next.setPosition((frame.getX() + frame.getWidth()) - 250, frame.getY() + 2);
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

    public void dispose() {
        if (texture != null) texture.dispose();
    }
}
