package com.nerallan.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nerallan.game.FlappyDemo;

/**
 * Created by Nerallan on 11/25/2018.
 */

public class MenuState extends State {
    private Texture mBackground;
    private Texture mPlayButton;

    public MenuState(GameStateManager pGameStateManager) {
        super(pGameStateManager);
        mCamera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        mBackground = new Texture("background.png");
        mPlayButton = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            mGameStateManager.set(new PlayState(mGameStateManager));
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch pSpriteBatch) {
        pSpriteBatch.setProjectionMatrix(mCamera.combined);
        pSpriteBatch.begin();
        pSpriteBatch.draw(mBackground, 0, 0, FlappyDemo.WIDTH, FlappyDemo.HEIGHT);
        pSpriteBatch.draw(mPlayButton, mCamera.position.x - mPlayButton.getWidth() / 2, mCamera.position.y);
        pSpriteBatch.end();
    }

    @Override
    public void dispose() {
        mBackground.dispose();
        mPlayButton.dispose();
        System.out.println("Menu state disposed");
    }
}
