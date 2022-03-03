// Loads and splits all sprites on start up.
// The sprites can easily be accessed as they
// are public and static.

package com.nexodus.UnknownTale.Manager;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Content {
    public static BufferedImage[][] MENUBG = load("/HUD/menuscreen.gif", 128, 144);
    public static BufferedImage[][] BATTLEBG = load("/HUD/battlescreen.gif", 128, 144);
    public static BufferedImage[][] WHITEBOX = load("/HUD/whitebox.png", 128, 55);
    public static BufferedImage[][] MERCY = load("/HUD/mercy.png", 42, 18);
    public static BufferedImage[][] TALK = load("/HUD/talk.png", 42, 18);
    public static BufferedImage[][] BONE = load("/Attack/bones.png", 30, 25);
    public static BufferedImage[][] PLAYERHEART =  load("/Sprites/playerheart.png", 14, 11);
    public static BufferedImage[][] PAPYRUSBATTLE =  load("/Sprites/papyrusbattle.png", 36, 66);
    public static BufferedImage[][] BAR = load("/HUD/bar.gif", 128, 16);
    public static BufferedImage[][] PLAYER = load("/Sprites/playersprites.gif", 16, 16);
    public static BufferedImage[][] DIAMOND = load("/Sprites/diamond.gif", 16, 16);
    public static BufferedImage[][] SPARKLE = load("/Sprites/sparkle.gif", 16, 16);
    public static BufferedImage[][] ITEMS = load("/Sprites/items.gif", 16, 16);
    public static BufferedImage[][] PAPYRUS = load("/Sprites/papyrusx.png", 24, 24);
    public static BufferedImage[][] font = load("/HUD/font3.gif", 8, 8);


    public static BufferedImage[][] load(String s, int w, int h) {
        BufferedImage[][] ret;
        try {
            BufferedImage spritesheet = ImageIO.read(Content.class.getResourceAsStream(s));
            int width = spritesheet.getWidth() / w;
            int height = spritesheet.getHeight() / h;
            ret = new BufferedImage[height][width];
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    ret[i][j] = spritesheet.getSubimage(j * w, i * h, w, h);
                }
            }
            return ret;
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("Error loading graphics.");
            System.exit(0);
        }
        return null;
    }

    //Adjustment on the alphabet according to ACSII table for dialogue purpose
    public static void drawString(Graphics2D g, String s, int x, int y) {
        s = s.toUpperCase();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == 47) c = 36; // slash
            if(c == 58) c = 37; // colon
            if(c == 39) c = 43; //'
            if(c == 32) c = 39; // space
            if(c == 33) c = 38; //!
            if(c == 63) c = 40; //?
            if(c == 46) c = 41; //.
            if(c == 44) c = 42; //,
            if(c >= 65 && c <= 90) c -= 65; // letters
            if(c >= 48 && c <= 57) c -= 22; // numbers
            int row = c / font[0].length;
            int col = c % font[0].length;
            g.drawImage(font[row][col], x + 7 * i, y, null);
        }
    }
}
