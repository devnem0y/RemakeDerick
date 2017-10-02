package com.devnem0y.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.devnem0y.handlers.input.Controller;
import com.devnem0y.objects.animations.AnimStateMachine;
import com.devnem0y.objects.animations.Attack;
import com.devnem0y.objects.animations.AttackLeft;
import com.devnem0y.objects.animations.AttackRight;
import com.devnem0y.objects.animations.Death;
import com.devnem0y.objects.animations.Down;
import com.devnem0y.objects.animations.Idle;
import com.devnem0y.objects.animations.Left;
import com.devnem0y.objects.animations.Right;
import com.devnem0y.objects.animations.Up;

import static com.devnem0y.utils.Constants.*;

public class Player extends GameObject {

    private GameObject rocket;
    private GameObject[] bulletsOne, bulletsTow, asteroid, bomb, bonus;
    private Controller controller;
    private float posX, posY;
    private AnimStateMachine animStateMachine;
    private float animDt = 0f;
    private int command;
    private int fireCount;
    private boolean superFire;
    private int rockets;

    public Player(Controller controller) {
        super();
        this.controller = controller;
        posX = controller.getTouchpad().getWidth() / 2;
        posY = controller.getTouchpad().getHeight() / 2;
        bounds = new Rectangle();
        bounds.setWidth(22);
        bounds.setHeight(45);
        velocity = 300f;
        command = 0;
    }

    private void setState(AnimStateMachine s) {this.animStateMachine = s;}
    private void running(SpriteBatch batch, float dt) {animStateMachine.running(this, batch, dt);}

    @Override
    public void spawn(GameObject[] asteroid, GameObject[] bomb, GameObject[] bonus, GameObject[] bulletsOne, GameObject[] bulletsTow, GameObject rocket) {
        this.asteroid = asteroid;
        this.bomb = bomb;
        this.bonus = bonus;
        this.bulletsOne = bulletsOne;
        this.bulletsTow = bulletsTow;
        this.rocket = rocket;
        bounds.setPosition(APP_WIDTH / 2 - bounds.getWidth() / 2, -480);
        alive = true;
        rockets = 25;
        superFire = false;
        setState(new Idle());
    }

    public void establishPosition(float positionY) {
        bounds.setPosition(APP_WIDTH / 2 - bounds.getWidth() / 2, positionY);
    }

    @Override
    public void update(float delta) {
        if (command == 0) {
            if (bounds.getY() <= 110) {
                bounds.setY(bounds.getY() + velocity * delta);
            }
        } else if (command == 1) {
            if (alive) {
                if (controller.getTouchpad().isTouched()) {
                    bounds.setX(bounds.getX() + controller.getTouchpad().getKnobPercentX() * (velocity * delta));
                    bounds.setY(bounds.getY() + controller.getTouchpad().getKnobPercentY() * (velocity * delta));
                    if (controller.getTouchpad().getKnobX() == controller.getTouchpad().getWidth() / 2) {
                        if (controller.isBtnAInput()) {
                            if (controller.getTouchpad().getKnobY() > posY) setState(new Attack());
                            else if (controller.getTouchpad().getKnobY() < posY)
                                setState(new Attack());
                        } else {
                            if (controller.getTouchpad().getKnobY() > posY) setState(new Up());
                            else if (controller.getTouchpad().getKnobY() < posY)
                                setState(new Down());
                        }
                    } else if (controller.getTouchpad().getKnobX() > posX + 35) {
                        if (controller.isBtnAInput()) setState(new AttackRight());
                        else setState(new Right());
                    } else if (controller.getTouchpad().getKnobX() < posX - 35) {
                        if (controller.isBtnAInput()) setState(new AttackLeft());
                        else setState(new Left());
                    }
                } else {
                    if (controller.isBtnAInput()) setState(new Attack());
                    else setState(new Idle());
                }
                if (controller.isBtnAInput()) fire();
                else fireCount = 14;
                if (controller.isBtnBInput()) fRocket();

                if (bounds.y + bounds.getHeight() >= APP_HEIGHT)
                    bounds.y = APP_HEIGHT - bounds.getHeight();
                else if (bounds.y <= 0) bounds.y = 0;
                if (bounds.x + bounds.getWidth() >= APP_WIDTH)
                    bounds.x = APP_WIDTH - bounds.getWidth();
                else if (bounds.x <= 0) bounds.x = 0;

//                for (GameObject a : asteroid) {
//                    if (a.isAlive()) {
//                        if (bounds.overlaps(a.getBounds())) {
//                            setState(new Death());
//                            alive = false;
//                        }
//                    }
//                }
            }
        }
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        running(batch, animDt);
        animDt += delta;
    }

    private void fire() {
        fireCount++;
        if (fireCount > 15) {
            fireCount = 0;
            if (superFire) {
                for (GameObject bt : bulletsTow) {
                    if (!bt.isAlive()) {
                        bt.setup((bounds.getX() + bounds.getWidth() / 2) - 8, (bounds.getY() + bounds.getHeight()) + 5);
                        break;
                    }
                }
            } else {
                for (GameObject b : bulletsOne) {
                    if (!b.isAlive()) {
                        b.setup((bounds.getX() + bounds.getWidth() / 2) - 3, (bounds.getY() + bounds.getHeight()) + 5);
                        break;
                    }
                }
            }
        }
    }

    private void fRocket() {
        if (rockets > 0) {
            if (!rocket.isAlive()) {
                rocket.setup((bounds.getX() + bounds.getWidth() / 2) - 8, (bounds.getY() + bounds.getHeight()) + 5);
                rockets--;
            }
        }
    }

    public int getRockets() {
        return rockets;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}