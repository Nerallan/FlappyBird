package com.nerallan.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Nerallan on 11/26/2018.
 */

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private Vector3 mPosition;
    private Vector3 mVelocity;
    private Texture mBird;

    public Bird(int x, int y){
        mPosition = new Vector3(x, y, 0);
        mVelocity = new Vector3(0, 0, 0);
        mBird = new Texture("bird.png");
    }

    public Vector3 getPosition() {
        return mPosition;
    }

    public Texture getBird() {
        return mBird;
    }

    public void update(float pDelta){
        if(mPosition.y > 0){
            mVelocity.add(0, GRAVITY, 0);
        }
        // going to multiply everything by delta time
        mVelocity.scl(pDelta);
        mPosition.add(MOVEMENT * pDelta, mVelocity.y, 0);
        if(mPosition.y < 0){
            mPosition.y = 0;
        }
        mVelocity.scl(1/pDelta);
    }

    public void jump(){
        mVelocity.y = 250;
    }
}
