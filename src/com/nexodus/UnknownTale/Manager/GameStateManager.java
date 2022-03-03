package com.nexodus.UnknownTale.Manager;

import com.nexodus.UnknownTale.GameState.*;

import java.awt.Graphics2D;

//This is to manage the Game state so we can change the other gamestate like pause, or back to menu or play the game in playstate
public class GameStateManager {
    private boolean paused;
    private PauseState pauseState;
    private GameState[] gameStates;
    private int currentState;
    private int previousState;

    public static final int NUM_STATES = 8;
    public static final int INTRO = 0;
    public static final int MENU = 1;
    public static final int PLAY = 2;
    public static final int GAMEOVER = 3;
    public static final int LOAD = 4;
    public static final int HELP = 5;
    public static final int DIALOGUE = 6;
    public static final int BATTLE = 7;

    public GameStateManager() {

        JukeBox.init();

        paused = false;
        pauseState = new PauseState(this);
        gameStates = new GameState[NUM_STATES];
        setState(INTRO);
    }

    //set what state to be use
    public void setState(int i) {
        previousState = currentState;
        unloadState(previousState);
        currentState = i;
        if(i == INTRO) {
            gameStates[i] = new IntroState(this);
            gameStates[i].init();
        }
        else if(i == MENU) {
            gameStates[i] = new MenuState(this);
            gameStates[i].init();
        }
        else if(i == PLAY) {
            gameStates[i] = new PlayState(this);
            gameStates[i].init();
        }
        else if(i == GAMEOVER) {
            gameStates[i] = new GameOverState(this);
            gameStates[i].init();
        }
        else if(i == LOAD) {
            gameStates[i] = new LoadState(this);
            gameStates[i].init();
        }
        else if(i == HELP) {
            gameStates[i] = new HelpState(this);
            gameStates[i].init();
        }
        else if(i == DIALOGUE) {
            gameStates[i] = new DialogueState(this);
            gameStates[i].init();
        }
        else if(i == BATTLE) {
            gameStates[i] = new BattleState(this);
            gameStates[i].init();
        }
    }


    //to unload previous state
    public void unloadState(int i) {
        gameStates[i] = null;
    }

    //To pause the game in PlayState
    public void setPaused(boolean b) {
        paused = b;
    }

    //Update on whether the pause state is used or not, to find out whether the current state we used is null or not before we update the currentState we used
    public void update() {
        if(paused) {
            pauseState.update();
        }
        else if(gameStates[currentState] != null) {
            gameStates[currentState].update();
        }
    }

    //To draw the pauseState and find out whether the current state is null or not before we draw the current state
    public void draw(Graphics2D g) {

        if(paused) {
            pauseState.draw(g);
        }
        else if(gameStates[currentState] != null) {
            gameStates[currentState].draw(g);
        }
    }
}
