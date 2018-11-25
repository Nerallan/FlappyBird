package com.nerallan.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nerallan.game.states.GameStateManager;
import com.nerallan.game.states.MenuState;

public class FlappyDemo extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Flappy Bird";

	private GameStateManager mGameStateManager;
	private SpriteBatch mBatch;
	Texture img;
	
	@Override
	public void create () {
		mBatch = new SpriteBatch();
		mGameStateManager = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		mGameStateManager.push(new MenuState(mGameStateManager));
	}

	@Override
	public void render () {
		// wipes the screen clean
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mGameStateManager.update(Gdx.graphics.getDeltaTime());
		mGameStateManager.render(mBatch);
	}
}
