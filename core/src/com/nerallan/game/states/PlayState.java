package com.nerallan.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nerallan.game.FlappyDemo;
import com.nerallan.game.sprites.Bird;

/**
 * Created by Nerallan on 11/25/2018.
 */

public class PlayState extends State {
    private Bird mBird;

    public PlayState(GameStateManager pGameStateManager) {
        super(pGameStateManager);
        mBird = new Bird(50, 100);
        // set a viewport to only view a partial area of game world
        mCamera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
    }

    @Override
    public void handleInput() {

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
        pSpriteBatch.draw(mBird.getBird(), mBird.getPosition().x, mBird.getPosition().y);
        pSpriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}
