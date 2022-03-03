// This class takes in an array of images.
// Calling getImage() gives you the appropriate
// image in the animation cycle.
package com.nexodus.UnknownTale.Entity;

import java.awt.image.BufferedImage;
public class Animation {
    private BufferedImage[] frames;
    private int currentFrame;
    private int numFrames;

    private int count;
    private int delay;

    private int timesPlayed;

    public Animation() {
        timesPlayed = 0;
    }

    //this is to set the frames per second of the animation using image
    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        currentFrame = 0;
        count = 0;
        timesPlayed = 0;
        delay = 2;
        numFrames = frames.length;
    }

    //this is to set some delay to the animation
    public void setDelay(int i) { delay = i; }

    //To update everytime so it will keep display the animated motion of the sprite.
    public void update() {

        if(delay == -1) return;

        count++;

        if(count == delay) {
            currentFrame++;
            count = 0;
        }
        if(currentFrame == numFrames) {
            currentFrame = 0;
            timesPlayed++;
        }

    }

    //get the next frame
    public BufferedImage getImage() { return frames[currentFrame]; }
    //show how many times the frames has been play
    public boolean hasPlayedOnce() { return timesPlayed > 0; }
}
