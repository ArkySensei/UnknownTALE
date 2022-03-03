// Congratulations for finishing the game.
// Gives you a rank based on how long it took
// you to beat the game.

// Under two minutes = Speed Demon
// Under three minutes = Adventurer
// Under four minutes = Beginner
// Four minutes or greater = Bumbling Idiot
package com.nexodus.UnknownTale.GameState;

import com.nexodus.UnknownTale.Main.GamePanel;
import com.nexodus.UnknownTale.Manager.Content;
import com.nexodus.UnknownTale.Manager.GameStateManager;
import com.nexodus.UnknownTale.Manager.JukeBox;
import com.nexodus.UnknownTale.Manager.Keys;
import com.nexodus.UnknownTale.Manager.Data;

import java.awt.Color;
import java.awt.Graphics2D;

public class GameOverState extends GameState {
    private Color color;
    private long ticks;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
        color = new Color(164, 198, 222);
        ticks = Data.getTime();
    }

    public void update() {handleInput();}

    public void draw(Graphics2D g) {

        g.setColor(color);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT2);

        Content.drawString(g, "finish time", 20, 6);

        int minutes = (int) (ticks / 1800);
        int seconds = (int) ((ticks / 30) % 60);
        if(minutes < 10) {
            if(seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 44, 16);
            else Content.drawString(g, "0" + minutes + ":" + seconds, 44, 16);
        }
        else {
            if(seconds < 10) Content.drawString(g, minutes + ":0" + seconds, 44, 16);
            else Content.drawString(g, minutes + ":" + seconds, 44, 16);
        }

        Content.drawString(g, "UNKNOWNTALE", 15, 36);
        Content.drawString(g, "THE END", 20, 58);
        Content.drawString(g, "THANK YOU", 20, 68);
        Content.drawString(g, "FOR PLAYING", 20, 78);
        Content.drawString(g, "THIS GAME!", 20, 88);
        Content.drawString(g, "press Enter to", 1, 110);
        Content.drawString(g, "EXit",101,110);

    }

    public void handleInput() {
        if(Keys.isPressed(Keys.ENTER)) {
            gsm.setState(GameStateManager.MENU);
            JukeBox.stop("finish");
            JukeBox.play("collect");
        }
    }
}
