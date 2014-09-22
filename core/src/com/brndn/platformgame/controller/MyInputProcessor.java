package com.brndn.platformgame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import javafx.application.Application;

/**
 * Created by Brendan on 9/15/2014.
 */
public class MyInputProcessor implements InputProcessor {
    private WorldController controller;
    int screenWidth;
    int screenHeight;

    public MyInputProcessor(WorldController con, int screenWidth, int screenHeight) {
        controller = con;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                controller.leftPressed();
                break;
            case Input.Keys.RIGHT:
                controller.rightPressed();
                break;
            case Input.Keys.Z:
                controller.jumpPressed();
                break;
            case Input.Keys.X:
                controller.firePressed();
                break;
        }


        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                controller.leftReleased();
                break;
            case Input.Keys.RIGHT:
                controller.rightReleased();
                break;
            case Input.Keys.Z:
                controller.jumpReleased();
                break;
            case Input.Keys.X:
                controller.fireReleased();
                break;
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(com.badlogic.gdx.Application.ApplicationType.Android)) return false;
        if (screenX < screenWidth / 2 && screenY > screenHeight / 2) {

            controller.leftPressed();

        }

        if (screenX > screenWidth / 2 && screenY > screenHeight / 2) {

            controller.rightPressed();

        }

        return true;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(com.badlogic.gdx.Application.ApplicationType.Android)) return false;
        if (screenX < screenWidth / 2 && screenY > screenHeight / 2) {

            controller.leftReleased();

        }

        if (screenX > screenWidth / 2 && screenY > screenHeight / 2) {

            controller.rightReleased();

        }
        return true;

    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
