// Diamond class.
// May contain a list of tileChanges.
// These tileChanges are used to modify
// the tile map upon collection.
package com.nexodus.UnknownTale.Entity;

import com.nexodus.UnknownTale.Manager.Content;
import com.nexodus.UnknownTale.TileMap.TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Diamond extends Entity {
    BufferedImage[] sprites;

    private ArrayList<int[]> tileChanges;

    public Diamond(TileMap tm) {

        super(tm);

        width = 16;
        height = 16;
        cwidth = 12;
        cheight = 12;

        sprites = Content.DIAMOND[0];
        animation.setFrames(sprites);
        animation.setDelay(10);

        tileChanges = new ArrayList<int[]>();

    }

    public void addChange(int[] i) {
        tileChanges.add(i);
    }
    public ArrayList<int[]> getChanges() {
        return tileChanges;
    }

    public void update() {
        animation.update();
    }

    public void draw(Graphics2D g) {
        super.draw(g);
    }
}
