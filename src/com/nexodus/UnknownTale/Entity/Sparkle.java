// Simple class that plays animation
// once and is removed.
package com.nexodus.UnknownTale.Entity;

import com.nexodus.UnknownTale.Manager.Content;
import com.nexodus.UnknownTale.TileMap.TileMap;

import java.awt.Graphics2D;

public class Sparkle extends Entity {
    private boolean remove;

    public Sparkle(TileMap tm) {
        super(tm);
        animation.setFrames(Content.SPARKLE[0]);
        animation.setDelay(5);
        width = height = 16;
    }

    public boolean shouldRemove() {
        return remove;
    }

    public void update() {
        animation.update();
        if(animation.hasPlayedOnce()) remove = true;
    }

    public void draw(Graphics2D g) {
        super.draw(g);
    }
}
