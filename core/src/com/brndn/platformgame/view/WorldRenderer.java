package com.brndn.platformgame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    private static final float RUNNING_FRAME_DURATION = 0.06f;

    private World world;
    private OrthographicCamera cam;

    private SpriteBatch spriteBatch; //Takes care of t

    ShapeRenderer debugRenderer = new ShapeRenderer();

    //MainChar textures
    private TextureRegion mainTexture;  //Reference to main character texture to draw
                                        // depending on state
    private TextureRegion mainIdleLeft;
    private TextureRegion mainIdleRight;
    private TextureRegion blockTexture;
    private TextureRegion charFrame;

    private Animation walkLeftAnimation;
    private Animation walkRightAnimation;

    private boolean debug = false;
    private int width, height; //Screen size in pixels, passed by OS in resize()
    private float ppuX, ppuY; //Pixels per unit

    public void setWorldSize(int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float) width / CAM_WIDTH;
        ppuY = (float) height / CAM_HEIGHT;
        System.out.println("hi ppux is " + ppuX + " and ppuy is " + ppuY);
    }

    float angle = 300f;
    public void testDistort() {

        //System.out.println("Hello rotation world!");
        //this.cam.position.set(CAM_WIDTH / 2f, CAM_HEIGHT / 2f, 0); //Center camera at middle of room
        //this.cam.update();
    }

    public WorldRenderer(World world, boolean debug) {
        this.world = world;
        //Shows 10 boxes on x axis, 7 boxes on Y axis
        //Results in an aspect ratio of 10:7
        this.cam = new OrthographicCamera(CAM_WIDTH, CAM_HEIGHT); //10 units wide, 7 units tall/
        this.cam.position.set(CAM_WIDTH / 2f, CAM_HEIGHT / 2f, 0); //Center camera at middle of room

        this.debug = debug;
        spriteBatch = new SpriteBatch();

        loadTextures();
    }

    private void loadTextures() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/textures/textures.pack"));
        mainIdleLeft = atlas.findRegion("mainchar01");
        mainIdleRight = new TextureRegion(mainIdleLeft);
        mainIdleRight.flip(true,false);
        blockTexture = atlas.findRegion("block");
        TextureRegion[] walkLeftFrames = new TextureRegion[5];

        for (int i = 0; i < 5; i++) {
            walkLeftFrames[i] = atlas.findRegion("mainchar0" + (i+2));
        }

        walkLeftAnimation = new Animation(RUNNING_FRAME_DURATION, walkLeftFrames);
        TextureRegion[] walkRightFrames = new TextureRegion[5];
        for (int i = 0; i < 5; i++) {
            walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
            walkRightFrames[i].flip(true,false);
        }
        walkRightAnimation = new Animation(RUNNING_FRAME_DURATION, walkRightFrames);


    }

    public void render() {
       // spriteBatch.setProjectionMatrix(cam.view);
        spriteBatch.begin();
        testDistort();
        this.cam.update();
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

        float stateDelta = character.getStateTime();

        switch (character.getState()) {
            case IDLE:
                mainTexture = character.isFacingLeft() ? mainIdleLeft : mainIdleRight;
                break;
            case WALKING:
                mainTexture = character.isFacingLeft() ?
                               walkLeftAnimation.getKeyFrame(character.getStateTime(),true) :
                               walkRightAnimation.getKeyFrame(character.getStateTime(), true);
                break;
            case JUMPING:
                mainTexture = character.isFacingLeft() ?
                        walkLeftAnimation.getKeyFrame(character.getStateTime(),true) :
                        walkRightAnimation.getKeyFrame(character.getStateTime(), true);
                break;
            case BOOSTING:
                break;
            case DYING:
                break;
        }

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
