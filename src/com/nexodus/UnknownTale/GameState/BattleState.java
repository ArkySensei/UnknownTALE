package com.nexodus.UnknownTale.GameState;

        import com.nexodus.UnknownTale.Main.GamePanel;
        import com.nexodus.UnknownTale.Manager.*;

        import java.awt.*;
        import java.awt.image.BufferedImage;
        import java.util.ArrayList;

public class BattleState extends GameState {

    private BufferedImage box;
    private BufferedImage mercy;
    private BufferedImage battlebg;
    private BufferedImage playerheart;
    private BufferedImage diamond;
    private BufferedImage enemy;
    private BufferedImage bone;
    private BufferedImage talk;
    private int eventTick;
    private int i=0, i2=0, i3=0, i4=0;
    private String word="", word2="", word3="", word4="";
    private ArrayList<Rectangle> boxes;
    private boolean eventDone;
    private String dialogue = "";
    private String dialogue2 = "";
    private String dialogue3 = "";
    private String dialogue4 = "";
    private int dspd=1;
    private int dword=1;
    private int hp = 500;
    public static boolean detect;
    private int currentOption;
    private int Option=1;
    public int count=0;
    private int x=55,y=90;
    private int xSPD=1;
    private int xSPD2=1;
    private int x2=1;
    private int x3=100;
    private boolean fight = false;
    private boolean inv = false;
    private int lines=0;


    //To access the abstrat from GameState such as init(), update(), draw() and handleInput()
    public BattleState(GameStateManager gsm) {
        super(gsm);
    }

    //this is to initiate all the required music and content when this Battle State has been selected
    public void init() {
        battlebg = Content.BATTLEBG[0][0];
        playerheart = Content.PLAYERHEART[0][0];
        box = Content.WHITEBOX[0][0];
        enemy = Content.PAPYRUSBATTLE[0][0];
        bone = Content.BONE[0][0];
        mercy = Content.MERCY[0][0];
        talk = Content.TALK[0][0];
        diamond = Content.DIAMOND[0][0];
        JukeBox.load("/SFX/collect.wav", "collect");
        JukeBox.load("/SFX/menuoption.wav", "menuoption");
        // load music
        JukeBox.load("/Music/battle.wav", "battle");
        JukeBox.setVolume("battle", -10);
        JukeBox.loop("battle", 1000, 1000, JukeBox.getFrames("battle") - 1000);
        JukeBox.load("/Music/finish.wav", "finish");
        JukeBox.setVolume("finish", -10);
        boxes = new ArrayList<Rectangle>();
        detect = true;
    }

    //This is to keep update what the player just press from the keyboard and always check whether the event is done. The eventDone mean the battle is over
    // and will go to next state according to the eventDone function
    public void update() {
        handleInput();
        if(eventDone) eventDone();
    }

    //this is to draw the graphics of this game such as the player's heart , the npc and etc
    public void draw(Graphics2D g) {
        g.drawImage(battlebg, 0, 0, null);
        g.drawImage(box, 0, 70, null);
        g.drawImage(enemy, 45,0, null);
        Content.drawString(g, "HP "+hp/5, 0, 60);
        Content.drawString(g, word, 1, 75);
        Content.drawString(g, word2, 1, 85);
        Content.drawString(g, word3, 1, 95);
        Content.drawString(g, word4, 1, 105);

        if(i < dialogue.length()){
            word += String.format("%s",dialogue.charAt(i));
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
        if(i==dialogue.length() && i2 < dialogue2.length()){
            word2 += String.format("%s",dialogue2.charAt(i2));
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i2++;
        }
        if(i2==dialogue2.length() && i3 < dialogue3.length()){
            word3 += String.format("%s",dialogue3.charAt(i3));
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i3++;
        }
        if(i3==dialogue3.length() && i4 < dialogue4.length()){
            word4 += String.format("%s",dialogue4.charAt(i4));
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i4++;
        }

        if(fight == false) {
            if(inv==false) {
                g.drawImage(talk, 18, 126, null);
                g.drawImage(mercy, 86, 126, null);
                if (Option == 1) g.drawImage(diamond, 4, 126, null);
                else if (Option == 2) g.drawImage(diamond, 72, 126, null);
            }
            if(dword <= 100) {dspd = -dspd;}
            if(dword >= -3)   {dspd = -dspd; count++;}
            dword = dword + dspd;
            if(hp==0){
                word = "You lost! but your";
                word2 = "Determination";
                word3 = "refused";
                word4 = "HP recovered";
                hp=500;
            }
            while(count==460) {
                word ="";
                word2 = "";
                word3 = "";
                word4 = "";
                count =0;
                inv=false;
                if(lines>=0 && lines<=5){
                    fight = true;
                }
                if(lines==9) {
                    eventDone=true;
                }
            }
        }


        if(y<=72) {
            y=72;
        }else if(y>=112){
            y=112;
        }
        if(x<=2) {
            x=2;
        }
        if(x>=112) {
            x=112;
        }
        if(fight == true) {
            if(currentOption == 0) { g.drawImage(playerheart, x, y, null);}
            if(currentOption == 1) { g.drawImage(playerheart, x, y, null);}
            if(currentOption == 2) { g.drawImage(playerheart, x, y, null);}
            if(currentOption == 3) { g.drawImage(playerheart, x, y, null);}
            if(currentOption == 4) { g.drawImage(playerheart, 25, 126, null);}
            Content.drawString(g, "7", 115, 1);
            Content.drawString(g, "/", 105, 1);
            Content.drawString(g, count/100+"", 95, 1);
            if(x2 >= 103) {xSPD = -xSPD;}
            if(x2 <= -3)   {xSPD = -xSPD; count++;}
            if(x3 <= 100) {xSPD2 = -xSPD2;}
            if(x3 >= -3)   {xSPD2 = -xSPD2; count++;}
            x2 = x2 + xSPD;
            x3 = x3+ xSPD2;
            g.drawImage(bone,x2,100, null);
            g.drawImage(bone,x3,70, null);
            if(x>=x2 && x<=x2+25){
                if(y>=94 && y<=113){
                    if(hp!=0){
                        hp--;
                    }
                }
            }else if( x>=x3 && x<=x3+25){
                if(y>=65 && y<=95){
                    if(hp!=0){
                        hp--;
                    }
                }
            }
            if(hp ==0) {
                fight = false;
            }
            while(count ==760){
                fight = false;
                x=55;
                y=90;
                count =0;
                x2=1;
                x3=100;
            }

        }

    }

    //This is the keyboard input
    public void handleInput() {

        if(Keys.isDown(Keys.DOWN)) {
            if(fight==true) {
                currentOption = 0;
                y++;
            }
        }
        if(Keys.isDown(Keys.UP)) {
            if(fight==true) {
                currentOption = 1;
                y--;
            }
        }
        if(Keys.isDown(Keys.LEFT)) {
            if(fight==false && Option==2) {
                JukeBox.play("menuoption");
                Option--;
            }
            if(fight==true) {
                currentOption = 2;
                x--;
            }
        }
        if(Keys.isDown(Keys.RIGHT)) {
            if(fight==false && Option==1) {
                JukeBox.play("menuoption");
                Option++;
            }
            if(fight==true) {
                currentOption = 3;
                x++;
            }
        }
        if(Keys.isPressed(Keys.ENTER)) {
            if(inv==false ) {
                if(fight == false) {
                    JukeBox.play("collect");
                    selectOption();
                }
            }
        }
        if(Keys.isPressed(Keys.ESCAPE)) {
            gsm.setState(GameStateManager.PLAY);
        }
    }

    //this is the dialog conversation which will changes the more the player press the talk button in the battle system
    private void selectOption() {
        if(Option == 1) {
            inv=true;
            word="";
            word2="";
            word3="";
            word4="";
            i=0;
            i2=0;
            i3=0;
            i4=0;
            if(lines==0) {
                dialogue = "What you still";
                dialogue2 = "don't want to";
                dialogue3 = "admit that you";
                dialogue4 = "kill them!";
            }else if(lines==1) {
                dialogue =  "I don't believe";
                dialogue2 = "you Human!!";
                dialogue3 = "I must revenge";
                dialogue4 = "for them!!";
            }else if(lines==2) {
                dialogue =  "Come on attack";
                dialogue2 = "me now come";
                dialogue3 = "on! You think";
                dialogue4 = "im scared";
            }else if(lines==3) {
                dialogue =  "Why don't you";
                dialogue2 = "kill me!! Don't";
                dialogue3 = "act like you did";
                dialogue4 = "not kill at all";
            }else if(lines==4) {
                dialogue =  "Why, I don't";
                dialogue2 = "understand....";
                dialogue3 = "You really didn't";
                dialogue4 = "kill our classmates?";
            }else if(lines==5) {
                dialogue =  "Erm, Im sorry";
                dialogue2 = "I jump out of";
                dialogue3 = "conclusion";
                dialogue4 = "trying to kill you";
            }else if(lines==6) {
                dialogue =  "Im scare cause";
                dialogue2 = "I just met you";
                dialogue3 = "im not sure you a";
                dialogue4 = "good or bad person";
            }else if(lines==7) {
                dialogue =  "Im sorry";
                dialogue2 = "Can we be";
                dialogue3 = "friends again?";
                dialogue4 = "";
            }else if(lines==8) {
                dialogue =  "Thank you friend!";
                dialogue2 = "Let find others,";
                dialogue3 = "our way home to";
                dialogue4 = "grab some burgers";
            }
            lines++;
        }
        if(Option == 2) {
            hp+=10;
            if(lines>=0 && lines<=5){
                fight = true;
            }
        }
    }

    //when the player forgive the npc dialog appear this function will be activated and change the music from battle into finish and went to the next game state
    private void eventDone() {
        eventTick++;
        if(eventTick == 1) {
            boxes.clear();
            for(int i = 0; i < 9; i++) {
                if(i % 2 == 0) boxes.add(new Rectangle(-128, i * 16, GamePanel.WIDTH, 16));
                else boxes.add(new Rectangle(128, i * 16, GamePanel.WIDTH, 16));
            }
            JukeBox.stop("battle");
            JukeBox.play("finish");
        }
        if(eventTick > 1) {
            for(int i = 0; i < boxes.size(); i++) {
                Rectangle r = boxes.get(i);
                if(i % 2 == 0) {
                    if(r.x < 0) r.x += 4;
                }
                else {
                    if(r.x > 0) r.x -= 4;
                }
            }
        }

        if(eventTick > 33) {
            if(JukeBox.isPlaying("finish")) {
                gsm.setState(GameStateManager.GAMEOVER);
            }
        }
    }
}



