package com.nexodus.UnknownTale.Main;

import javax.swing.*;

//The window of the game
public class Game {
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("Logo/TaleIcon.jpg"));

    public void play() {
        //main(String args[])
        JFrame window = new JFrame("UNKNOWNTALE");
        window.add(new GamePanel());
        window.setResizable(false);
        window.pack();
        window.setIconImage(img.getImage());
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
