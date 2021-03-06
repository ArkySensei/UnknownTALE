// The main menu GameState.
package com.nexodus.UnknownTale.GameState;

import com.nexodus.UnknownTale.Data.SaveData;
import com.nexodus.UnknownTale.Manager.Content;
import com.nexodus.UnknownTale.Manager.GameStateManager;
import com.nexodus.UnknownTale.Manager.JukeBox;
import com.nexodus.UnknownTale.Manager.Keys;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MenuState extends GameState {
    private BufferedImage bg;
    private BufferedImage diamond;


    private int currentOption = 0;
    private String[] options = {
            "START",
            "LOAD",
            "HELP",
            "QUIT"
    };

    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
        bg = Content.MENUBG[0][0];
        diamond = Content.DIAMOND[0][0];

        JukeBox.load("/SFX/collect.wav", "collect");
        JukeBox.load("/SFX/menuoption.wav", "menuoption");
    }

    public void update() {
        handleInput();
    }

    public void draw(Graphics2D g) {

        g.drawImage(bg, 0, 0, null);


        Content.drawString(g, options[0], 44, 90);
        Content.drawString(g, options[1], 48, 100);
        Content.drawString(g, options[2], 48, 110);
        Content.drawString(g, options[3], 48, 120);


        if(currentOption == 0) g.drawImage(diamond, 25, 86, null);
        else if(currentOption == 1) g.drawImage(diamond, 25, 96, null);
        else if(currentOption == 2) g.drawImage(diamond, 25, 106, null);
        else if(currentOption == 3) g.drawImage(diamond, 25, 116, null);


    }

    public void handleInput() {
        if(Keys.isPressed(Keys.DOWN) && currentOption < options.length - 1) {
            JukeBox.play("menuoption");
            currentOption++;
        }
        if(Keys.isPressed(Keys.UP) && currentOption > 0) {
            JukeBox.play("menuoption");
            currentOption--;
        }
        if(Keys.isPressed(Keys.ENTER)) {
            JukeBox.play("collect");
            selectOption();
        }
    }

    private void selectOption() {
        if(currentOption == 0) {
            gsm.setState(GameStateManager.DIALOGUE);
        }
        if(currentOption == 1) {
            if(SaveData.checkFileExists()) {
                PlayState.loadX = true;
            }
            gsm.setState(GameStateManager.PLAY);
        }
        if(currentOption == 2) {
            gsm.setState(GameStateManager.HELP);
        }
        if(currentOption == 3) {
            System.exit(0);
        }
    }
}
