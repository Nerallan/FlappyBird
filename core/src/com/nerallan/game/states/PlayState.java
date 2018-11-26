package com.nerallan.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nerallan.game.FlappyDemo;
import com.nerallan.game.sprites.Bird;
import com.nerallan.game.sprites.Tube;

/**
 * Created by Nerallan on 11/25/2018.
 */

public class PlayState extends State {
    private Bird mBird;
    private Texture mBackground;
    private Tube mTube;

    public PlayState(GameStateManager pGameStateManager) {
        super(pGameStateManager);
        mBird = new Bird(50, 100);
        // set a viewport to only view a partial area of game world
        mCamera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        mBackground = new Texture("background.png");
        mTube = new Tube(100);
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
    }

    @Override
    public void render(SpriteBatch pSpriteBatch) {
        // draw things on the screen in relation to camera
        pSpriteBatch.setProjectionMatrix(mCamera.combined);
        pSpriteBatch.begin();
        pSpriteBatch.draw(mBackground, mCamera.position.x - (mCamera.viewportWidth / 2), 0);
        pSpriteBatch.draw(mBird.getBird(), mBird.getPosition().x, mBird.getPosition().y);
        pSpriteBatch.draw(mTube.getTopTube(), mTube.getPosTopTube().x, mTube.getPosTopTube().y);
        pSpriteBatch.draw(mTube.getBottomTube(), mTube.getPosBotTube().x, mTube.getPosBotTube().y);
        pSpriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
