package com.devnem0y.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.devnem0y.handlers.input.Controller;
import com.devnem0y.objects.animations.AnimStateMachine;
import com.devnem0y.objects.animations.Attack;
import com.devnem0y.objects.animations.AttackLeft;
import com.devnem0y.objects.animations.AttackRight;
import com.devnem0y.objects.animations.Down;
import com.devnem0y.objects.animations.Idle;
import com.devnem0y.objects.animations.Left;
import com.devnem0y.objects.animations.Right;
import com.devnem0y.objects.animations.Up;

import static com.devnem0y.utils.Constants.*;

public class Player extends GameObject {

    private GameObject asteroid, enemy, bonus;
    private Controller controller;
    private float posX, posY;
    private AnimStateMachine animStateMachine;
    private float animDt = 0f;

    public Player(Controller controller) {
        super();
        this.controller = controller;
        posX = controller.getTouchpad().getWidth() / 2;
        posY = controller.getTouchpad().getHeight() / 2;
        bounds = new Rectangle();
        bounds.setWidth(22);
        bounds.setHeight(45);
        velocity = 300f;
    }

    private void setState(AnimStateMachine s) {this.animStateMachine = s;}
    private void running(SpriteBatch batch, float dt) {animStateMachine.running(this, batch, dt);}

    @Override
    public void spawn(GameObject object_0, GameObject object_1, GameObject object_2, GameObject object_3, GameObject object_4) {
        this.asteroid = object_0;
        this.enemy = object_1;
        this.bonus = object_2;
        bounds.setPosition(APP_WIDTH / 2 - bounds.getWidth() / 2, 30);
        alive = true;
        setState(new Idle());
    }

    @Override
    public void update(float delta) {
        if (controller.getTouchpad().isTouched()) {
            bounds.setX(bounds.getX() + controller.getTouchpad().getKnobPercentX() * (velocity * delta));
            bounds.setY(bounds.getY() + controller.getTouchpad().getKnobPercentY() * (velocity * delta));
            if (controller.getTouchpad().getKnobX() == controller.getTouchpad().getWidth() / 2) {
                if (controller.isBtnAttackInput()) {
                    if (controller.getTouchpad().getKnobY() > posY) setState(new Attack());
                    else if (controller.getTouchpad().getKnobY() < posY) setState(new Attack());
                } else {
                    if (controller.getTouchpad().getKnobY() > posY) setState(new Up());
                    else if (controller.getTouchpad().getKnobY() < posY) setState(new Down());
                }
            } else if (controller.getTouchpad().getKnobX() > posX + 35) {
                if (controller.isBtnAttackInput()) setState(new AttackRight());
                else setState(new Right());
            } else if (controller.getTouchpad().getKnobX() < posX - 35) {
                if (controller.isBtnAttackInput()) setState(new AttackLeft());
                else setState(new Left());
            }
        } else {
            if (controller.isBtnAttackInput()) setState(new Attack());
            else setState(new Idle());
        }
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        running(batch, animDt);
        animDt += delta;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}