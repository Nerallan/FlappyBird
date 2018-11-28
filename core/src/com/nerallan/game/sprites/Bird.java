package com.nerallan.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by Nerallan on 11/26/2018.
 */

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private Vector3 mPosition;
    private Vector3 mVelocity;
    private Texture mTexture;
    private Rectangle mBounds;
    private Animation mBirdAnimation;
    private Sound mFlap;

    public boolean mColliding;

    public Bird(int x, int y){
        mPosition = new Vector3(x, y, 0);
        mVelocity = new Vector3(0, 0, 0);
        mTexture = new Texture("birdanimation.png");
        mBirdAnimation = new Animation(new TextureRegion(mTexture), 3, 0.5f);
        mBounds = new Rectangle(x, y, mTexture.getWidth() / 3, mTexture.getHeight());
        mFlap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
        mColliding = false;
    }


    public void update(float pDelta){
        mBirdAnimation.update(pDelta);
        mVelocity.add(0, GRAVITY, 0);
        // going to multiply everything by delta time
        mVelocity.scl(pDelta);
        if(!mColliding){
            // move related to delta time
            mPosition.add(MOVEMENT * pDelta, mVelocity.y, 0);
        }

        if(mPosition.y < 82){
            mPosition.y = 82;
        }
        mVelocity.scl(1/pDelta);
        updateBounds();
    }


    public Vector3 getPosition() {
        return mPosition;
    }

    public TextureRegion getTexture() {
        return mBirdAnimation.getFrame();
    }

    public void updateBounds(){
        mBounds.setPosition(mPosition.x, mPosition.y);
    }


    public void jump(){
        mVelocity.y = 250;
        mFlap.play(0.5f);
    }

    public Rectangle getBounds(){
        return mBounds;
    }

    public void dispose(){
        mTexture.dispose();
        mFlap.dispose();
    }
}
