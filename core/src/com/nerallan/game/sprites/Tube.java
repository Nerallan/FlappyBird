package com.nerallan.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Nerallan on 11/26/2018.
 */

public class Tube {
    public static final int TUBE_WIDTH = 52;

    private static final int FLUCTUATION = 130;
    // the difference between the openings
    private static final int TUBE_GAP = 100;
    // lowest available opening for top tube
    private static final int LOWEST_OPENING = 120;

    private Rectangle mBoundsTop, mBoundsBot;
    private Texture mTopTube, mBottomTube;
    private Vector2 mPosTopTube, mPosBotTube;
    private Random mRandom;


    public Tube(float pX){
        mTopTube = new Texture("toptube.png");
        mBottomTube = new Texture("bottomtube.png");
        mRandom = new Random();

        mPosTopTube = new Vector2(pX, mRandom.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        mPosBotTube = new Vector2(pX, mPosTopTube.y - TUBE_GAP - mBottomTube.getHeight());

        // set invisible rectangles for tubes
        mBoundsTop = new Rectangle(mPosTopTube.x, mPosTopTube.y, mTopTube.getWidth(), mTopTube.getHeight());
        mBoundsTop = new Rectangle(mPosBotTube.x, mPosBotTube.y, mBottomTube.getWidth(), mBottomTube.getHeight());

    }

    public Texture getTopTube() {
        return mTopTube;
    }

    public Texture getBottomTube() {
        return mBottomTube;
    }

    public Vector2 getPosTopTube() {
        return mPosTopTube;
    }

    public Vector2 getPosBotTube() {
        return mPosBotTube;
    }


    public void reposition(float pX){
        mPosTopTube.set(pX, mRandom.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        mPosBotTube.set(pX, mPosTopTube.y - TUBE_GAP - mBottomTube.getHeight());
//        mBoundsTop.setPosition(mPosTopTube.x, mPosTopTube.y);
//        mBoundsBot.setPosition(mPosBotTube.x, mPosBotTube.y);
    }

    // return true if the player and tube overlaps each other
    public boolean collides(Rectangle pPlayer){
        return pPlayer.overlaps(mBoundsTop) || pPlayer.overlaps(mBoundsBot);
    }

    public void dispose(){
        mTopTube.dispose();
        mBottomTube.dispose();
    }
}
