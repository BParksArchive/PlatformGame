package com.brndn.platformgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.brndn.platformgame.model.World;
import com.brndn.platformgame.view.WorldRenderer;

/**
 * Created by Brendan on 9/11/2014.
 */
public class PlayScreen implements Screen{

    public World world;
    private WorldRenderer renderer;

    @Override
    public void show() {
        world = new World();
        renderer = new WorldRenderer(world,true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
