package com.nerallan.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Nerallan on 11/28/2018.
 */

public class Animation {
    private Array<TextureRegion> mFrames; // store all frames
    private float mMaxFrameTime;          // hot long we telling a frame to stay in view before switch
    private float mCurrentFrameTime;
    private int mFrameCount;
    private int mFrame;

    public Animation(TextureRegion pRegion, int pFrameCount, float pCycleTime){
        mFrames = new Array<TextureRegion>();
        TextureRegion temp;
        // the width of single frame
        int frameWidth = pRegion.getRegionWidth() / pFrameCount;
        for(int i = 0; i < pFrameCount; i++){
            temp = new TextureRegion(pRegion, i * frameWidth, 0, frameWidth, pRegion.getRegionHeight());
            // cut region into splices
            mFrames.add(temp);
        }
        this.mFrameCount = pFrameCount;
        mMaxFrameTime = pCycleTime / mFrameCount;
        mFrame = 0;
    }

    public void update(float delta){
        mCurrentFrameTime += delta;
        if(mCurrentFrameTime > mMaxFrameTime){
            mFrame++;
            mCurrentFrameTime = 0;
        }
        if(mFrame >= mFrameCount){
            mFrame = 0;
        }
    }

    public void flip(){
        for(TextureRegion region : mFrames){
            region.flip(true, false);
        }
    }

    public TextureRegion getFrame(){
        return mFrames.get(mFrame);
    }
}
