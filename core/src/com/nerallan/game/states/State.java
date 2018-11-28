package com.nerallan.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Nerallan on 11/25/2018.
 */

public abstract class State {
    protected OrthographicCamera mCamera;
    // for (x,y,z) coordinate system vector
    protected Vector3 mMouse;
    // will manage states of games (play state, pause state), works as Stack
    protected GameStateManager mGameStateManager;

    protected State(GameStateManager pGameStateManager){
        this.mGameStateManager = pGameStateManager;
        mCamera = new OrthographicCamera();
        mMouse = new Vector3();
    }

    protected abstract void handleInput();
    // going to take delta time (difference between one and next frame rendered)
    public abstract void update(float delta);
    // SpriteBatch - container for everything that need to render to the screen all of textures and other stuff
    public abstract void render(SpriteBatch pSpriteBatch);
    public abstract void dispose();
}
