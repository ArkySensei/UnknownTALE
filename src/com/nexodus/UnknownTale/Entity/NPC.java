package com.nexodus.UnknownTale.Entity;

import com.nexodus.UnknownTale.Manager.Content;
import com.nexodus.UnknownTale.TileMap.TileMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class NPC extends Entity {
    BufferedImage[] sprites;

    private ArrayList<int[]> tileChanges;

    public NPC(TileMap tm) {

        super(tm);

        width = 16;
        height = 16;
        cwidth = 12;
        cheight = 12;

        sprites = Content.PAPYRUS[0];
        animation.setFrames(sprites);
        animation.setDelay(10);

        tileChanges = new ArrayList<int[]>();

    }



    public void update() {
        animation.update();
    }

    public void draw(Graphics2D g) {
        super.draw(g);
    }
}
