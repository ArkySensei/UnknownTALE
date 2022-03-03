package com.nexodus.UnknownTale.GameState;

import com.nexodus.UnknownTale.Manager.Content;
import com.nexodus.UnknownTale.Manager.GameStateManager;
import com.nexodus.UnknownTale.Manager.JukeBox;
import com.nexodus.UnknownTale.Manager.Keys;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HelpState extends GameState {

    private BufferedImage bg;

    public HelpState(GameStateManager gsm) {
        super(gsm);
    }
    public void init() {
        bg = Content.BATTLEBG[0][0];
    }

    public void update() {
        handleInput();
    }

    public void draw(Graphics2D g) {
        g.drawImage(bg, 0, 0, null);
        Content.drawString(g, "Help", 40, 10);

        Content.drawString(g, "Arrow", 10, 26);
        Content.drawString(g, "keys", 14, 34);
        Content.drawString(g, ": move", 50, 26);

        Content.drawString(g, "Space", 12, 46);
        Content.drawString(g, ": action to", 46, 46);
        Content.drawString(g, "  use tools",46,56);
        Content.drawString(g, "Enter", 12, 76);
        Content.drawString(g, ": action to", 50, 76);
        Content.drawString(g, "  talk npc", 50, 86);

        Content.drawString(g, "F1:", 35, 112);
        Content.drawString(g, "return", 67, 108);
        Content.drawString(g, "to menu", 67, 116);

        Content.drawString(g, "F2:", 35, 125);
        Content.drawString(g, "Save", 67, 125);

    }

    public void handleInput() {
        if(Keys.isPressed(Keys.F1)) {
            gsm.setState(GameStateManager.MENU);
        }
    }

}
