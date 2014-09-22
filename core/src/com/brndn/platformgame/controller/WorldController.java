package com.brndn.platformgame.controller;

import com.brndn.platformgame.model.MainCharacter;
import com.brndn.platformgame.model.World;
import sun.applet.Main;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brendan on 9/15/2014.
 */
public class WorldController {

    enum Keys {
        LEFT, RIGHT, JUMP, FIRE
    }

    private World world;
    private MainCharacter mainchar;

    static Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();
    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.JUMP, false);
        keys.put(Keys.FIRE, false);
    }

    public WorldController(World world) {
        this.world = world;
        this.mainchar = world.getMainCharacter();
    }

    public void leftPressed() {
        keys.get(keys.put(Keys.LEFT, true));
    }

    public void rightPressed() {
        keys.get(keys.put(Keys.RIGHT, true));
    }

    public void jumpPressed() {
        keys.get(keys.put(Keys.JUMP, true));
    }

    public void firePressed() {
        keys.get(keys.put(Keys.FIRE, false));
    }

    public void leftReleased() {
        keys.get(keys.put(Keys.LEFT, false));
    }

    public void rightReleased() {
        keys.get(keys.put(Keys.RIGHT, false));
    }

    public void jumpReleased() {
        keys.get(keys.put(Keys.JUMP, false));
    }

    public void fireReleased() {
        keys.get(keys.put(Keys.FIRE, false));
    }

    public void update(float delta) {
        processInput();
        mainchar.update(delta);
    }

    private void processInput() {
        if (keys.get(Keys.LEFT)) {
            mainchar.setFacingLeft(true);
            mainchar.setState(MainCharacter.State.WALKING);
            mainchar.getVelocity().x = -MainCharacter.SPEED;
        }
        if (keys.get(Keys.RIGHT)) {
            mainchar.setFacingLeft(false);
            mainchar.setState(MainCharacter.State.WALKING);
            mainchar.getVelocity().x = MainCharacter.SPEED;
        }
        if(keys.get(Keys.JUMP)) {
           // mainchar.setState(MainCharacter.State.JUMPING);
           // mainchar.set
        }

        if ((keys.get(Keys.RIGHT) && keys.get(Keys.LEFT))   ||
                (!keys.get(Keys.RIGHT) && !keys.get(Keys.LEFT)) ) {

            mainchar.setState(MainCharacter.State.IDLE);
            mainchar.getAcceleration().x = 0;
            mainchar.getVelocity().x = 0;
        }

        if(keys.get(Keys.FIRE)) {

        }



    }


}
