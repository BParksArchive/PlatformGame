package com.brndn.platformgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.brndn.platformgame.model.MainCharacter;
import com.brndn.platformgame.model.SolidBlock;
import com.brndn.platformgame.model.World;

/**
 * Created by Brendan on 9/11/2014.
 */
public class WorldRenderer {

    private static final float CAM_WIDTH = 10f;
    private static final float CAM_HEIGHT = 7f;

    private World world;
    private OrthographicCamera cam;

    private SpriteBatch spriteBatch; //Takes care of t

    ShapeRenderer debugRenderer = new ShapeRenderer();

    //Textures here
    private Texture mainTexture;
    private Texture blockTexture; //texture mapping, displaying, etc.
    private boolean debug = false;
    private int width, height; //Screen size in pixels, passed by OS in resize()
    private float ppuX, ppuY; //Pixels per unit

    public void setWorldSize(int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float) width / CAM_WIDTH;
        ppuY = (float) height / CAM_HEIGHT;
    }



    public WorldRenderer(World world, boolean debug) {
        this.world = world;
        //Shows 10 boxes on x axis, 7 boxes on Y axis
        //Results in an aspect ratio of 10:7
        this.cam = new OrthographicCamera(CAM_WIDTH, CAM_HEIGHT); //10 units wide, 7 units tall
        this.cam.position.set(CAM_WIDTH / 2f, CAM_HEIGHT / 2f, 0); //Center camera at middle of room
        this.cam.update();
        this.debug = debug;
        spriteBatch = new SpriteBatch();
        loadTextures();
    }

    private void loadTextures() {
        mainTexture = new Texture(Gdx.files.internal("mainchar_01.png"));
        blockTexture = new Texture(Gdx.files.internal("solidblock.png"));
    }

    public void render() {
        spriteBatch.begin();
        drawBlocks();
        drawMainCharacter();
        spriteBatch.end();

        if(debug) drawDebug();
    }

    private void drawBlocks() {
        for (SolidBlock block : world.getBlocks()) {
            spriteBatch.draw(blockTexture, block.getPosition().x * ppuX,
                                block.getPosition().y * ppuY,
                                SolidBlock.SIZE * ppuX,
                                SolidBlock.SIZE * ppuY);

        }
    }

    private void drawMainCharacter() {
        MainCharacter character = world.getMainCharacter();
        spriteBatch.draw(mainTexture, character.getPosition().x*ppuX,
                                character.getPosition().y*ppuY,
                                MainCharacter.SIZE*ppuX,
                                MainCharacter.SIZE*ppuY);
    }

    private void drawDebug() {
        debugRenderer.setProjectionMatrix(cam.combined);

        debugRenderer.begin(ShapeRenderer.ShapeType.Line);

        for (SolidBlock block : world.getBlocks()) {
            Rectangle rect = block.getBounds();
            float x1 = block.getPosition().x + rect.x;
            float y1 = block.getPosition().y + rect.y;
            debugRenderer.setColor(new Color(1, 0, 0, 1));
            debugRenderer.rect(x1, y1, rect.width, rect.height);
        }

        //Render main character
        MainCharacter character = world.getMainCharacter();
        Rectangle rect = character.getBounds();
        float x1 = character.getPosition().x + rect.x;
        float y1 = character.getPosition().y + rect.y;

        debugRenderer.setColor(new Color(0, 1, 0, 1));
        debugRenderer.rect(x1, y1, rect.width, rect.height);

        debugRenderer.end();
    }


}
