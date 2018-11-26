package com.nerallan.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Nerallan on 11/26/2018.
 */

public class Tube {
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;

    private Texture mTopTube, mBottomTube;
    private Vector2 mPosTopTube, mPosBotTube;
    private Random mRandom;


    public Tube(float pX){
        mTopTube = new Texture("toptube.png");
        mBottomTube = new Texture("bottomtube.png");
        mRandom = new Random();

        mPosTopTube = new Vector2(pX, mRandom.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        mPosBotTube = new Vector2(pX, mPosTopTube.y - TUBE_GAP - mBottomTube.getHeight());
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
}
