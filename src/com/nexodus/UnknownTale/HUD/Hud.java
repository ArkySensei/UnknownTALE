// Contains a reference to the Player.
// Draws all relevant information at the
// bottom of the screen.
package com.nexodus.UnknownTale.HUD;

import com.nexodus.UnknownTale.Entity.Diamond;
import com.nexodus.UnknownTale.Entity.Player;
import com.nexodus.UnknownTale.Main.GamePanel;
import com.nexodus.UnknownTale.Manager.Content;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Hud {
    private int yoffset;

    private BufferedImage bar;
    private BufferedImage diamond;
    private BufferedImage boat;
    private BufferedImage axe;
    public BufferedImage dbox;
    private Player player;

    private int numDiamonds;

    private Font font;
    private Color textColor;

    public Hud(Player p, ArrayList<Diamond> d) {

        player = p;
        numDiamonds = d.size();
        yoffset = GamePanel.HEIGHT;

        bar = Content.BAR[0][0];
        diamond = Content.DIAMOND[0][0];
        boat = Content.ITEMS[0][0];
        axe = Content.ITEMS[0][1];

        font = new Font("Arial", Font.PLAIN, 1);
        textColor = new Color(47, 64, 126);

    }

    //This is to draw the bar of how many diamond collect, draw the items, and draw the time
    public void draw(Graphics2D g) {

        // draw hud
        g.drawImage(bar, 0, yoffset, null);
        // draw diamond bar
        g.setColor(textColor);
        g.fillRect(8, yoffset + 6, (int)(28.0 * player.numDiamonds() / numDiamonds), 4);

        // draw diamond amount
        g.setColor(textColor);
        g.setFont(font);
        String s = player.numDiamonds() + "/" + numDiamonds;
        Content.drawString(g, s, 40, yoffset + 3);
        if(player.numDiamonds() >= 10) g.drawImage(diamond, 80, yoffset, null);
        else g.drawImage(diamond, 72, yoffset, null);

        // draw items
        if(player.hasBoat()) g.drawImage(boat, 100, yoffset, null);
        if(player.hasAxe()) g.drawImage(axe, 112, yoffset, null);

        // draw time
        int minutes = (int) (player.getTicks() / 5400);
        int seconds = (int) ((player.getTicks() / 90) % 60);
        if(minutes < 10) {
            if(seconds < 10) Content.drawString(g, "0" + minutes + ":0" + seconds, 85, 3);
            else Content.drawString(g, "0" + minutes + ":" + seconds, 85, 3);
        }
        else {
            if(seconds < 10) Content.drawString(g, minutes + ":0" + seconds, 85, 3);
            else Content.drawString(g, minutes + ":" + seconds, 85, 3);
        }



    }
}
