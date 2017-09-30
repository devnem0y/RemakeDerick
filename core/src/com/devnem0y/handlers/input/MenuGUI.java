package com.devnem0y.handlers.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import static com.devnem0y.utils.Constants.*;
import static com.devnem0y.utils.PathRes.ATLAS_BUTTONS;

public class MenuGUI {

    public Group group;
    private Skin skin;
    private BitmapFont fontLabel;
    private Button btnPlay;
    public Button btnExit;
    private boolean btnPlayInput, btnExitInput;

    public MenuGUI(BitmapFont fontLabel) {
        this.fontLabel = fontLabel;
        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal(ATLAS_BUTTONS)));
    }

    public void initialization(Stage stage) {
        group = new Group();
        group.setPosition(0, 0);

        Label.LabelStyle labelStyle = new Label.LabelStyle(fontLabel, Color.WHITE);
        Label gameName = new Label("D E R I C K", labelStyle);
        gameName.setAlignment(Align.center);
        gameName.setPosition(APP_WIDTH / 2 - gameName.getWidth() / 2, (APP_HEIGHT / 2 - gameName.getHeight() / 2) + 100);
        createBtn(stage);

        group.addActor(gameName);
        group.addActor(btnPlay);
        stage.addActor(group);
    }

    private void createBtn(Stage stage) {
        Button.ButtonStyle btnPlayStyle = new Button.ButtonStyle();
        btnPlayStyle.down = skin.getDrawable("play_down");
        btnPlayStyle.up = skin.getDrawable("play_up");

        btnPlay = new Button(btnPlayStyle);
        btnPlay.setPosition(APP_WIDTH / 2 - btnPlay.getWidth() / 2, (APP_HEIGHT / 2 - btnPlay.getHeight() / 2) - 50);
        btnPlay.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//
                btnPlayInput = true;
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                btnPlayInput = false;
                Gdx.app.log("my app", "Released");
            }
        });

        Button.ButtonStyle btnExitStyle = new Button.ButtonStyle();
        btnExitStyle.down = skin.getDrawable("exit_down");
        btnExitStyle.up = skin.getDrawable("exit_up");

        btnExit = new Button(btnExitStyle);
        btnExit.setPosition(APP_WIDTH - 100, 25);
        btnExit.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//
                btnExitInput = true;
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                btnExitInput = false;
                Gdx.app.log("my app", "Released");
            }
        });

        stage.addActor(btnPlay);
        stage.addActor(btnExit);
    }

    public boolean isBtnPlayInput() {
        return btnPlayInput;
    }

    public boolean isBtnExitInput() {
        return btnExitInput;
    }
}
