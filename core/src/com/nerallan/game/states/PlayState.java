package com.nerallan.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.nerallan.game.FlappyDemo;
import com.nerallan.game.sprites.Bird;
import com.nerallan.game.sprites.Tube;

import java.util.ArrayList;

/**
 * Created by Nerallan on 11/25/2018.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Bird mBird;
    private Texture mBackground;

    private Array<Tube> mTubes;

    public PlayState(GameStateManager pGameStateManager) {
        super(pGameStateManager);
        mBird = new Bird(50, 100);
        // set a viewport to only view a partial area of game world
        mCamera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        mBackground = new Texture("background.png");

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
        mBird.update(pDelta);
        mCamera.position.x = mBird.getPosition().x + 80;

        for(Tube tube : mTubes){
            if(mCamera.position.x - (mCamera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
        }
        mCamera.update();
    }

    @Override
    public void render(SpriteBatch pSpriteBatch) {
        // draw things on the screen in relation to camera
        pSpriteBatch.setProjectionMatrix(mCamera.combined);
        pSpriteBatch.begin();
        pSpriteBatch.draw(mBackground, mCamera.position.x - (mCamera.viewportWidth / 2), 0);
        pSpriteBatch.draw(mBird.getBird(), mBird.getPosition().x, mBird.getPosition().y);

        for(Tube tube : mTubes){
            pSpriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            pSpriteBatch.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        pSpriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
