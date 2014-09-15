package com.brndn.platformgame.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Brendan on 9/11/2014.
 */
public class World {

    //Used to make world
    Array<SolidBlock> blocks = new Array<SolidBlock>();
    MainCharacter character;

    public Array<SolidBlock> getBlocks() {
        return blocks;
    }

    public MainCharacter getMainCharacter() {
        return character;
    }

    public World() {
        createDemoWorld();
    }

    private void createDemoWorld() {
        character = new MainCharacter(new Vector2(7,2));

        for (int i = 0; i<10;i++) {
            blocks.add(new SolidBlock(new Vector2(i, 0)));
            blocks.add(new SolidBlock(new Vector2(i,1)));
        }

        blocks.add(new SolidBlock(new Vector2(9,2)));
        blocks.add(new SolidBlock(new Vector2(9,3)));
        blocks.add(new SolidBlock(new Vector2(9,4)));
        blocks.add(new SolidBlock(new Vector2(9,5)));
        blocks.add(new SolidBlock(new Vector2(9,6)));

        blocks.add(new SolidBlock(new Vector2(6,3)));
        blocks.add(new SolidBlock(new Vector2(6,4)));
        blocks.add(new SolidBlock(new Vector2(6,5)));
        blocks.add(new SolidBlock(new Vector2(6,6)));

    }

}
