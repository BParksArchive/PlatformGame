package com.brndn.platformgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.brndn.platformgame.controller.MyInputProcessor;
import com.brndn.platformgame.controller.WorldController;
import com.brndn.platformgame.model.World;
import com.brndn.platformgame.view.WorldRenderer;

/**
 * Created by Brendan on 9/11/2014.
 */
public class PlayScreen implements Screen {

    public World world;
    private WorldRenderer renderer;
    int screenWidth;
    int screenHeight;

    private MyInputProcessor inputProcessor;
    private WorldController controller;

    @Override
    public void show() {
        world = new World();
        renderer = new WorldRenderer(world,false);
        controller = new WorldController(world);
        Gdx.input.setInputProcessor((inputProcessor = new MyInputProcessor(controller, screenWidth, screenHeight)));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        controller.update(delta);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        renderer.setWorldSize(width, height);
        screenWidth = width;
        screenHeight = height;
    }


    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);

    }
}
