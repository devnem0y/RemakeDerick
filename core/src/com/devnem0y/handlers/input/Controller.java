package com.devnem0y.handlers.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.devnem0y.utils.Constants;

public class Controller {

    public Group group;
    private Skin skin;
    private Touchpad touchpad;
    private Button btnA, btnB;
    private boolean btnAttackInput;

    public Controller() {
        skin = new Skin();
        skin.addRegions(new TextureAtlas(Gdx.files.internal("image/atlas/controller.atlas")));
    }

    public void initialization(Stage stage) {
        group = new Group();
        group.setPosition(0, -290);

        createStick(stage);
        createBtn(stage);

        group.addActor(touchpad);
        group.addActor(btnA);
        group.addActor(btnB);
        stage.addActor(group);
    }

    private void createStick(Stage stage) {
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        Drawable touchBackground = skin.getDrawable("stick");
        Drawable touchKnob = skin.getDrawable("knob");
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;

        touchpad = new Touchpad(10, touchpadStyle);
        touchpad.setBounds(35, 35, 250, 250);

        stage.addActor(touchpad);
    }

    private void createBtn(Stage stage) {
        Button.ButtonStyle btnAStyle = new Button.ButtonStyle();
        btnAStyle.down = skin.getDrawable("btnA_down");
        btnAStyle.up = skin.getDrawable("btnA_up");

        btnA = new Button(btnAStyle);
        btnA.setPosition(Constants.APP_SCREEN_WIDTH - 255, 40);
        btnA.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//
                btnAttackInput = true;
                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                btnAttackInput = false;
                Gdx.app.log("my app", "Released");
            }
        });

        Button.ButtonStyle btnBStyle = new Button.ButtonStyle();
        btnBStyle.down = skin.getDrawable("btnB_down");
        btnBStyle.up = skin.getDrawable("btnB_up");

        btnB = new Button(btnBStyle);
        btnB.setPosition(Constants.APP_SCREEN_WIDTH - 135, 150);
        btnB.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("my app", "Pressed"); //** Usually used to start Game, etc. **//

                return true;
            }
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                Gdx.app.log("my app", "Released");
            }
        });

        stage.addActor(btnA);
        stage.addActor(btnB);
    }

    public Touchpad getTouchpad() {
        return touchpad;
    }

    public boolean isBtnAttackInput() {
        return btnAttackInput;
    }
}
