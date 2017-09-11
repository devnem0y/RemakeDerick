package com.devnem0y.utils;

import com.badlogic.gdx.Gdx;
import com.devnem0y.managers.LanguagesManager;

public class Constants {

    public static final String APP_TITLE = "Derick";
    public static final String APP_VERSION = "1.0";

    public static final int APP_FPS = 60;

    // Размер экрана для запуска DESKTOP
    public static final int DESKTOP_WIDTH = 1280;
    public static final int DESKTOP_HEIGHT = 720;

    // Заданный размер экрана для всех устройств
    public static final int APP_WIDTH = 1280;
    public static final int APP_HEIGHT = 720;

    // Данный размер экрана устройства
    public static final int APP_SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static final int APP_SCREEN_HEIGHT = Gdx.graphics.getHeight();
}
