// The pause GameState can only be activated
// by calling GameStateManager#setPaused(true).
package com.nexodus.UnknownTale.GameState;

import com.nexodus.UnknownTale.Data.SaveData;
import com.nexodus.UnknownTale.Data.WorldSave;
import com.nexodus.UnknownTale.Entity.Player;
import com.nexodus.UnknownTale.Manager.GameStateManager;
import com.nexodus.UnknownTale.Manager.JukeBox;
import com.nexodus.UnknownTale.Manager.Keys;
import com.nexodus.UnknownTale.Manager.Content;
import com.nexodus.UnknownTale.Entity.Entity;
import java.awt.Graphics2D;

public class PauseState extends GameState {

    public PauseState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {}

    public void update() {
        handleInput();
    }

    public void draw(Graphics2D g) {

        Content.drawString(g, "paused", 40, 30);

        Content.drawString(g, "arrow", 12, 56);
        Content.drawString(g, "keys", 16, 64);
        Content.drawString(g, ": move", 52, 60);

        Content.drawString(g, "space", 12, 76);
        Content.drawString(g, ": action", 52, 76);

        Content.drawString(g, "F1:", 36, 92);
        Content.drawString(g, "return", 68, 92);
        Content.drawString(g, "to menu", 68, 101);

        Content.drawString(g, "F2:", 35, 115);
        Content.drawString(g, "Save", 67, 115);
    }



    public void handleInput() {
        if(Keys.isPressed(Keys.ESCAPE)) {
            gsm.setPaused(false);
            JukeBox.resumeLoop("music1");
        }
        if(Keys.isPressed(Keys.F1)) {
            gsm.setPaused(false);
            PlayState.loadX=false;
            gsm.setState(GameStateManager.MENU);
        }

    }
}
