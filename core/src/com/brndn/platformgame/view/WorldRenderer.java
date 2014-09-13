package com.brndn.platformgame.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.brndn.platformgame.model.MainCharacter;
import com.brndn.platformgame.model.SolidBlock;
import com.brndn.platformgame.model.World;

/**
 * Created by Brendan on 9/11/2014.
 */
public class WorldRenderer {

    private World world;
    private OrthographicCamera cam;

    ShapeRenderer debugRenderer = new ShapeRenderer();

    public WorldRenderer(World world) {
        this.world = world;
        //Shows 10 boxes on x axis, 7 boxes on Y axis
        //Results in an aspect ratio of 10:7
        this.cam = new OrthographicCamera(10, 7); //10 units wide, 7 units tall
        this.cam.position.set(5, 3.5f, 0); //Center camera at middle of room
        this.cam.update();
    }

    public void render() {
        debugRenderer.setProjectionMatrix(cam.combined);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (SolidBlock block : world.getBlocks()) {
            Rectangle rect = block.getBounds();
            float x1 = block.getPosition().x + rect.x;
            float y1 = block.getPosition().y + rect.y;
            debugRenderer.setColor(new Color(1, 0, 0, 1));
            debugRenderer.rect(x1, y1, rect.width, rect.height);
        }

        MainCharacter character = world.getMainCharacter();
        Rectangle rect = character.getBounds();
        float x1 = character.getPosition().x + rect.x;
        float y1 = character.getPosition().y + rect.y;

        debugRenderer.setColor(new Color(0, 1, 0, 1));
        debugRenderer.rect(x1, y1, rect.width, rect.height);



    }


}
