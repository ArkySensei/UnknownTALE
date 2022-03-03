package com.nexodus.UnknownTale.GameState;

import com.nexodus.UnknownTale.Manager.Content;
import com.nexodus.UnknownTale.Manager.GameStateManager;
import com.nexodus.UnknownTale.Manager.JukeBox;
import com.nexodus.UnknownTale.Manager.Keys;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DialogueState extends GameState{

    private BufferedImage battlebg;
    private BufferedImage diamond;
    private String dialogue  = "In an Unknown day";
    private String dialogue2 = "a boy live in a";
    private String dialogue3 = "world of monsters";
    private String dialogue4 = "with his monster";
    private String dialogue5 = "parents";
    private String select1="";
    private String select2="";
    private String select3="";
    private int xa=100,xb=200,xc=300,xd=400,xe=500;
    private int dspd=1;
    private int dword=1;
    private int choice=1;
    private int Option=1;
    public int count=0;

    public DialogueState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
        battlebg = Content.BATTLEBG[0][0];
        diamond = Content.DIAMOND[0][0];
        JukeBox.load("/SFX/collect.wav", "collect");
        JukeBox.load("/SFX/menuoption.wav", "menuoption");

    }

    public void update() { handleInput(); }

    public void draw(Graphics2D g) {
        g.drawImage(battlebg, 0, 0, null);
        Content.drawString(g, dialogue, xa, 10);
        Content.drawString(g, dialogue2, xb, 20);
        Content.drawString(g, dialogue3, xc, 30);
        Content.drawString(g, dialogue4, xd, 40);
        Content.drawString(g, dialogue5, xe, 50);
        Content.drawString(g, select1, xe+30, 70);
        Content.drawString(g, select2, xe+30, 80);
        Content.drawString(g, select3, xe+30, 90);
            if(Option==6 && xe==1) {
                if (choice == 1) g.drawImage(diamond, 19, 66, null);
                else if (choice == 2) g.drawImage(diamond, 19, 76, null);
                else if (choice == 3) g.drawImage(diamond, 19, 86, null);
            }
            if(dword <= 100) {dspd = -dspd;}
            if(dword >= -3)   {dspd = -dspd; count++;}
            dword = dword + dspd;
            if(count>=300) {
                Content.drawString(g,"PRESS ENTER TO", 1, 110);
                Content.drawString(g,"CONTINUE...", 1, 120);
            }
            if(xa>1){
                xa--;
            }
            if(xb>1){
                xb--;
            }
            if(xc>1){
                xc--;
            }
            if(xd>1){
                xd--;
            }
            if(xe>1){
                xe--;
            }
    }


    public void handleInput() {
        if(count>=300) {
            if (Keys.isPressed(Keys.ENTER)) {
                Option++;
                selectOption();
                count = 300;
                xa=100;
                xb=200;
                xc=300;
                xd=400;
                xe=500;
            }
        }
        if(Option ==6) {
            if (Keys.isPressed(Keys.UP)) {
                if (choice >= 2) {
                    choice--;
                }
            }
            if (Keys.isPressed(Keys.DOWN)) {
                if (choice <= 2) {
                    choice++;
                }
            }
        }
    }

    private void selectOption() {
        if(Option == 1) {
            dialogue = "In the next day,";
            dialogue2= "the boy went to";
            dialogue3= "a college of";
            dialogue4= "monster call";
            dialogue5= "IDC College";
        }
        if(Option == 2) {
            dialogue = "At the class,";
            dialogue2= "there was a";
            dialogue3= "unknown gas";
            dialogue4= "appear out of";
            dialogue5= "nowhere";
        }
        if(Option == 3) {
            dialogue = "He and his";
            dialogue2= "classmate fall";
            dialogue3= "down and went";
            dialogue4= "into a very";
            dialogue5= "deep sleep...";
        }
        if(Option == 4) {
            dialogue = "When they wake up,";
            dialogue2= "they are in an";
            dialogue3= "unknown world";
            dialogue4= "which is different";
            dialogue5= "from monster world";
        }
        if(Option == 5) {
            dialogue = "So now, the boy";
            dialogue2= "and his classmate";
            dialogue3= "need to find their";
            dialogue4= "way back to their";
            dialogue5= "monster world";
        }
        if(Option == 6) {
            dialogue = "Before that....";
            dialogue2= "please choose the";
            dialogue3= "difficulty that";
            dialogue4= "you want to play";
            dialogue5= "in this game";
            select1="easy";
            select2="hard";
            select3="chaos";
        }
        if(Option == 7) {
            dialogue = "Thank you for";
            dialogue2= "your time...";
            dialogue3= "Your decision";
            dialogue4= "on choosing this";
            dialogue5= "difficulty...";
            select1="";
            select2="";
            select3="";
        }
        if(Option == 8) {
            dialogue = "Will now be";
            dialogue2= "Deleted!!";
            dialogue3= "No one can";
            dialogue4= "choose difficulty";
            dialogue5= "in this game! XD";
        }
        if(Option == 9) {
            dialogue = "Thank you for";
            dialogue2= "your time!!";
            dialogue3= "Have a nice";
            dialogue4= "day and enjoying";
            dialogue5= "this game....";
        }
        if(Option == 10) {
            dialogue = "";
            dialogue2= "";
            dialogue3= "";
            dialogue4= "";
            dialogue5= "";
            gsm.unloadState(GameStateManager.DIALOGUE);
            gsm.setState(GameStateManager.PLAY);
        }
    }
}
