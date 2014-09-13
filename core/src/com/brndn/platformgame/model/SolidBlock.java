package com.brndn.platformgame.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Brendan on 9/11/2014.
 */
public class SolidBlock {
    static final float SIZE = 1f;

    Vector2 position = new Vector2();
    Rectangle bounds = new Rectangle();

    public SolidBlock(Vector2 pos) {
        this.position = pos;
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
    }

    public Rectangle getBounds() {
        return bounds;
    }


    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
