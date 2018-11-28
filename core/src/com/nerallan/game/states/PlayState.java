package com.nerallan.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.nerallan.game.FlappyDemo;
import com.nerallan.game.sprites.Bird;
import com.nerallan.game.sprites.Tube;

import java.util.ArrayList;

/**
 * Created by Nerallan on 11/25/2018.
 */

public class PlayState extends State {
    // spacing between tubes not including tube itself
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -30;

    private Bird mBird;
    private Texture mBackground;
    private Texture mGround;
    private Vector2 mGroundPos1, mGroundPos2;

    private Array<Tube> mTubes;

    public PlayState(GameStateManager pGameStateManager) {
        super(pGameStateManager);
        mBird = new Bird(50, 300);
        // set a viewport to only view a partial area of game world
        mCamera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        mBackground = new Texture("background.png");
        mGround = new Texture("ground.png");
        mGroundPos1 = new Vector2(mCamera.position.x - mCamera.viewportWidth / 2, GROUND_Y_OFFSET);
        mGroundPos2 = new Vector2((mCamera.position.x - mCamera.viewportWidth / 2) + mGround.getWidth(), GROUND_Y_OFFSET);


        mTubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++){
            mTubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            mBird.jump();
        }
    }

    @Override
    public  void update(float pDelta) {
        handleInput();
        updateGround();
        mBird.update(pDelta);
        // update cam position based off where bird is in the game world
        mCamera.position.x = mBird.getPosition().x + 80;
        // remove and reposition a tube when it gets off the camera viewport
        for(int i = 0; i < mTubes.size; i++){
            Tube tube = mTubes.get(i);
            // if the tube is left side of the screen
            // camera is centered in the middle of the screen
            if(mCamera.position.x - (mCamera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

//            if(tube.collides(mBird.getBounds())){
//                mGameStateManager.set(new PlayState(mGameStateManager));
//            }
        }
        if (mBird.getPosition().y <= mGround.getHeight() + GROUND_Y_OFFSET){
            mGameStateManager.set(new PlayState(mGameStateManager));
        }
        // tell libxdg about camera reposition
        mCamera.update();
    }

    @Override
    public void render(SpriteBatch pSpriteBatch) {
        // draw things on the screen in relation to camera
        pSpriteBatch.setProjectionMatrix(mCamera.combined);
        pSpriteBatch.begin();
        pSpriteBatch.draw(mBackground, mCamera.position.x - (mCamera.viewportWidth / 2), 0);
        pSpriteBatch.draw(mBird.getTexture(), mBird.getPosition().x, mBird.getPosition().y);

        for(Tube tube : mTubes){
            pSpriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            pSpriteBatch.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        pSpriteBatch.draw(mGround, mGroundPos1.x, mGroundPos1.y);
        pSpriteBatch.draw(mGround, mGroundPos2.x, mGroundPos2.y);
        pSpriteBatch.end();
    }

    @Override
    public void dispose() {
        mBackground.dispose();
        mBird.dispose();
        mGround.dispose();
        for(Tube tube: mTubes){
            tube.dispose();
        }
        System.out.println("Play state disposed");
    }

    private void updateGround(){
        if(mCamera.position.x - (mCamera.viewportWidth / 2) > mGroundPos1.x + mGround.getWidth()){
            mGroundPos1.add(mGround.getWidth() * 2, 0);
        }
        if(mCamera.position.x - (mCamera.viewportWidth / 2) > mGroundPos2.x + mGround.getWidth()){
            mGroundPos2.add(mGround.getWidth() * 2, 0);
        }
    }
}
