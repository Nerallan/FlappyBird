package com.nerallan.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

import javax.swing.Spring;

/**
 * Created by Nerallan on 11/25/2018.
 */

public class GameStateManager {

    private Stack<State> mStates;

    public GameStateManager(){
        mStates = new Stack<State>();
    }

    public void push(State pState){
        mStates.push(pState);
    }

    // going to get it off the stack
    public void pop(){
        mStates.pop();
    }

    // pop and instantly push a new state
    public void set(State pState){
        mStates.pop();
        mStates.push(pState);
    }

    public void update(float pDelta){
        mStates.peek().update(pDelta);
    }

    public void render(SpriteBatch pSpriteBatch){
        mStates.peek().render(pSpriteBatch);
    }
}
