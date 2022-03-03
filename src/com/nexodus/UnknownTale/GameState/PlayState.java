// The main playing GameState.
// Contains everything you need for gameplay:
// Player, TileMap, Diamonds, Load, Save and etc.
// Updates and draws all game objects.
package com.nexodus.UnknownTale.GameState;

import com.nexodus.UnknownTale.Data.SaveData;
import com.nexodus.UnknownTale.Data.WorldSave;
import com.nexodus.UnknownTale.Entity.*;
import com.nexodus.UnknownTale.HUD.Hud;
import com.nexodus.UnknownTale.Login.LoginSession;
import com.nexodus.UnknownTale.Main.GamePanel;
import com.nexodus.UnknownTale.Manager.*;
import com.nexodus.UnknownTale.TileMap.TileMap;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


public class PlayState extends GameState{
    boolean lmao;
    String tempname= LoginSession.Nickname;
    String sentences;
    String sentences2;
    String word = "", word2= "";
    String name;
    int i=0, i2=0;
    int location;
    int conversation2=-1;
    int conversation3=0;
    int conversation;
    boolean dialogue,save=false;
    boolean d1=false,d2=false,d3=false,d4=false,d5=false,d6=false,d7=false,d8=false,d9=false,d10=false,d11=false,d12=false,d13=false,d14=false,d15=false, Items1=false, Items2=false;
    public static boolean loadX=false;

    // player and npc
    public static Player player;
    private NPC opponent;
    // tilemap
    private TileMap tileMap;

    // diamonds
    private ArrayList<Diamond> diamonds;

    // items
    private ArrayList<Item> items;

    // sparkles
    private ArrayList<Sparkle> sparkles;

    // camera position
    private int xsector;
    private int ysector;
    private int sectorSize;

    // hud
    private Hud hud;

    // events
    private boolean blockInput;
    private boolean eventStart;
    private boolean eventFinish;
    private int eventTick;

    // transition box
    private ArrayList<Rectangle> boxes;


    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    public void init() {
        // create lists
        diamonds = new ArrayList<Diamond>();
        sparkles = new ArrayList<Sparkle>();
        items = new ArrayList<Item>();

        // load map
        tileMap = new TileMap(16);
        tileMap.loadTiles("/Tilesets/testtileset.gif");
        tileMap.loadMap("/Maps/testmap.map");

        // create player
        opponent = new NPC(tileMap);
        player = new Player(tileMap);


        // initialize player
        opponent.setTilePosition(21, 20);

        //load save data
        if(loadX) {
            WorldSave loading = SaveData.load();
            conversation=loading.getCon1();
            d1=loading.getPos1();
            d2=loading.getPos2();
            d3=loading.getPos3();
            d4=loading.getPos4();
            d5=loading.getPos5();
            d6=loading.getPos6();
            d7=loading.getPos7();
            d8=loading.getPos8();
            d9=loading.getPos9();
            d10=loading.getPos10();
            d11=loading.getPos11();
            d12=loading.getPos12();
            d13=loading.getPos13();
            d14=loading.getPos14();
            d15=loading.getPos15();
            Items1=loading.getItem1();
            Items2=loading.getItem2();
            Player.ticks=loading.getTime();
            player.setTilePosition(loading.getPosX() / 31 * 2, loading.getPosY() / 31 * 2);
        }else{
            player.setTilePosition(17, 17);
        }

        System.out.println(d1+""+d2+""+d3+""+d4+""+d5+""+d6);
        tileMap.setTile(21, 20, 23 );
        player.setTotalDiamonds(diamonds.size());

        // fill lists
        populateDiamonds();
        populateItems();
        player.setTilePosition(player.getx() / 31 * 2, player.gety() / 31 * 2);
        // set up camera position
        sectorSize = GamePanel.WIDTH;
        xsector = player.getx() / sectorSize;
        ysector = player.gety() / sectorSize;
        tileMap.setPositionImmediately(-xsector * sectorSize, -ysector * sectorSize);

        // load hud
        hud = new Hud(player, diamonds);
        location=0;

        // load music
        JukeBox.load("/Music/bgmusic.wav", "music1");
        JukeBox.setVolume("music1", -10);
        JukeBox.loop("music1", 1000, 1000, JukeBox.getFrames("music1") - 1000);
        JukeBox.load("/Music/finish.wav", "finish");
        JukeBox.setVolume("finish", -10);

        // load sfx
        JukeBox.load("/SFX/collect.wav", "collect");
        JukeBox.load("/SFX/mapmove.wav", "mapmove");
        JukeBox.load("/SFX/tilechange.wav", "tilechange");
        JukeBox.load("/SFX/splash.wav", "splash");

        // start event
        boxes = new ArrayList<Rectangle>();
        eventStart = true;
        eventStart();

    }

    private void populateDiamonds() {

        Diamond d;
        if(!d1) {
            d = new Diamond(tileMap);
            d.setTilePosition(20, 20);
            d.addChange(new int[]{23, 19, 1});
            d.addChange(new int[]{23, 20, 1});
            diamonds.add(d);
        }else if(d1){
            d = new Diamond(tileMap);
            d.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            d.addChange(new int[]{23, 19, 1});
            d.addChange(new int[]{23, 20, 1});
            diamonds.add(d);
            }
        if(!d2) {
            d = new Diamond(tileMap);
            d.setTilePosition(12, 36);
            d.addChange(new int[]{31, 17, 1});
            diamonds.add(d);
        }else if(d2){
            d = new Diamond(tileMap);
            d.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            d.addChange(new int[]{31, 17, 1});
            diamonds.add(d);}

        if(!d3) {
            d = new Diamond(tileMap);
            d.setTilePosition(28, 4);
            d.addChange(new int[]{27, 7, 1});
            d.addChange(new int[]{28, 7, 1});
            diamonds.add(d);
        }else if(d3){
            d = new Diamond(tileMap);
            d.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            d.addChange(new int[]{27, 7, 1});
            d.addChange(new int[]{28, 7, 1});
            diamonds.add(d);}

        if(!d4) {
            d = new Diamond(tileMap);
            d.setTilePosition(4, 34);
            d.addChange(new int[]{31, 21, 1});
            diamonds.add(d);
        }else if(d4){
            d = new Diamond(tileMap);
            d.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            d.addChange(new int[]{31, 21, 1});
            diamonds.add(d);}

        if(!d5) {
            d = new Diamond(tileMap);
            d.setTilePosition(28, 19);
            diamonds.add(d);
        }else if(d5){
            d = new Diamond(tileMap);
            d.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            diamonds.add(d);}

        if(!d6) {
            d = new Diamond(tileMap);
            d.setTilePosition(35, 26);
            diamonds.add(d);
        }else if(d6){
            d = new Diamond(tileMap);
            d.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            diamonds.add(d);}

        if(!d7) {
            d = new Diamond(tileMap);
            d.setTilePosition(38, 36);
            diamonds.add(d);
        }else if(d7){
            d = new Diamond(tileMap);
            d.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            diamonds.add(d);}

        if(!d8) {
            d = new Diamond(tileMap);
            d.setTilePosition(27, 28);
            diamonds.add(d);
        }else if(d8){
            d = new Diamond(tileMap);
            d.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            diamonds.add(d);}

        if(!d9) {
            d = new Diamond(tileMap);
            d.setTilePosition(20, 30);
            diamonds.add(d);
        }else if(d9){
            d = new Diamond(tileMap);
            d.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            diamonds.add(d);}

        if(!d10) {
            d = new Diamond(tileMap);
            d.setTilePosition(14, 25);
            diamonds.add(d);
        }else if(d10){
            d = new Diamond(tileMap);
            d.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            diamonds.add(d);}

        if(!d11) {
            d = new Diamond(tileMap);
            d.setTilePosition(4, 21);
            diamonds.add(d);
        }else if(d11){
            d = new Diamond(tileMap);
            d.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            diamonds.add(d);}

        if(!d12) {
            d = new Diamond(tileMap);
            d.setTilePosition(9, 14);
            diamonds.add(d);
        }else if(d12){
            d = new Diamond(tileMap);
            d.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            diamonds.add(d);}

        if(!d13) {
            d = new Diamond(tileMap);
            d.setTilePosition(4, 3);
            diamonds.add(d);
        }else if(d13){
            d = new Diamond(tileMap);
            d.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            diamonds.add(d);}

        if(!d14) {
            d = new Diamond(tileMap);
            d.setTilePosition(20, 14);
            diamonds.add(d);
        }else if(d14){
            d = new Diamond(tileMap);
            d.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            diamonds.add(d);}

        if(!d15) {
            d = new Diamond(tileMap);
            d.setTilePosition(13, 20);
            diamonds.add(d);
        }else if(d15){
            d = new Diamond(tileMap);
            d.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            diamonds.add(d);;}

    }


    private void populateItems() {

        Item item;
        if(!Items1) {
            item = new Item(tileMap);
            item.setType(Item.AXE);
            item.setTilePosition(26, 37);
            items.add(item);
        }else if(Items1){
            item = new Item(tileMap);
            item.setType(Item.AXE);
            item.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            items.add(item);
        }

        if(!Items2) {
            item = new Item(tileMap);
            item.setType(Item.BOAT);
            item.setTilePosition(12, 4);
            items.add(item);
        }else if(Items2){
            item = new Item(tileMap);
            item.setType(Item.BOAT);
            item.setTilePosition(player.getx()/ 31 * 2, player.gety()/ 31 * 2);
            items.add(item);
        }
    }

    public void update() {

        // check keys
        handleInput();

        // check events
        if(eventStart) eventStart();
        if(eventFinish) eventFinish();

        // update camera
        int oldxs = xsector;
        int oldys = ysector;
        xsector = player.getx() / sectorSize;
        ysector = player.gety() / sectorSize;
        tileMap.setPosition(-xsector * sectorSize, -ysector * sectorSize);
        tileMap.update();

        if(oldxs != xsector || oldys != ysector) {
            JukeBox.play("mapmove");
        }

        if(tileMap.isMoving()) return;


        // update player
        player.update();
        opponent.update();


        // update diamonds
        for(int i = 0; i < diamonds.size(); i++) {

            Diamond d = diamonds.get(i);
            d.update();

            // player collects diamond
            if(player.intersects(d) && conversation>=6) {

                // remove from list
                diamonds.remove(i);
                i--;

                // increment amount of collected diamonds
                player.collectedDiamond();

                // play collect sound
                JukeBox.play("collect");

                // add new sparkle
                Sparkle s = new Sparkle(tileMap);
                s.setPosition(d.getx(), d.gety());
                sparkles.add(s);

                // make any changes to tile map
                ArrayList<int[]> ali = d.getChanges();
                for(int[] j : ali) {
                    tileMap.setTile(j[0], j[1], j[2]);
                }
                if(ali.size() != 0) {
                    JukeBox.play("tilechange");
                }
                System.out.println(player.getx()+" "+player.gety());
                int xx=player.getx();
                int yy=player.gety();
                if((xx/31*2)==20){
                    if((yy/31*2)==20) {
                        d1 = true;
                    }
                }
                if((xx/31*2)==36){
                    if((yy/31*2)==12) {
                        d2 = true;
                    }
                }
                if((xx)==72){
                    if((yy)==466) {
                        d3 = true;
                    }
                }
                if((xx/31*2)==34){
                    if((yy/31*2)==4) {
                        d4 = true;
                    }
                }
                if((xx/31*2)==20){
                    if((yy/31*2)==28) {
                        d5 = true;
                    }
                }
                if((xx/31*2)==26){
                    if((yy/31*2)==36) {
                        d6 = true;
                    }
                }
                if((xx/31*2)==36){
                    if((yy/31*2)==38) {
                        d7 = true;
                    }
                }
                if((xx/31*2)==28){
                    if((yy/31*2)==28) {
                        d8 = true;
                    }
                }
                if((xx/31*2)==30){
                    if((yy/31*2)==20) {
                        d9 = true;
                    }
                }
                if((xx/31*2)==26){
                    if((yy/31*2)==14) {
                        d10 = true;
                    }
                }
                if((xx/31*2)==20){
                    if((yy/31*2)==4) {
                        d11 = true;
                    }
                }
                if((xx)==232){
                    if((yy)==162) {
                        d12 = true;
                    }
                }
                if((xx)==56){
                    if((yy)==82) {
                        d13 = true;
                    }
                }
                if((xx/31*2)==14){
                    if((yy/31*2)==20) {
                        d14 = true;
                    }
                }
                if((xx/31*2)==56){
                    if((yy/31*2)==82) {
                        d15 = true;
                    }
                }
                //System.out.println(d1+" "+d2+""+d3+" "+d4+""+d5+" "+d6+""+d7+" "+d8+""+d9+" "+d10+""+d11+" "+d12+""+d13+" "+d14+""+d15);
            }

        }



        // update sparkles
        for(int i = 0; i < sparkles.size(); i++) {
            Sparkle s = sparkles.get(i);
            s.update();
            if(s.shouldRemove()) {
                sparkles.remove(i);
                i--;
            }
        }

        // update items
        for(int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if(player.intersects(item)) {
                items.remove(i);
                i--;
                item.collected(player);
                JukeBox.play("collect");
                Sparkle s = new Sparkle(tileMap);
                s.setPosition(item.getx(), item.gety());
                sparkles.add(s);

                //items get
                System.out.println(player.getx()+" "+player.gety());
                if((player.getx())==590){
                    if((player.gety())==424) {
                        Items1 = true;
                    }
                }
                if((player.getx())==72){
                    if((player.gety())==210) {
                        Items2 = true;
                    }
                }
                System.out.println(Items1+" "+Items2);
            }
        }

       // System.out.println("X:" + player.getx() + "Y:" + player.gety() );
    }

    public void draw(Graphics2D g) {

        // draw tilemap
        tileMap.draw(g);

        // draw player
        player.draw(g);
        opponent.draw(g);

        // draw diamonds
        if(conversation>=6) {
            for (Diamond d : diamonds) {
                d.draw(g);
            }
        }

        // draw sparkles
        for(Sparkle s : sparkles) {
            s.draw(g);
        }

        // draw items
        for(Item i : items) {
            i.draw(g);
        }

        // draw hud
        hud.draw(g);

        // draw transition boxes
        g.setColor(java.awt.Color.BLACK);
        for(int i = 0; i < boxes.size(); i++) {
            g.fill(boxes.get(i));
        }


        if(dialogue == true){

            if(conversation == 1) {
                name="Papyrus";
                sentences = "Hey Human whats";
               sentences2 = "your name?";
                lmao= g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT-50, null);
            }
            else if(conversation == 2) {
                name="Papyrus";
                sentences = tempname+" you say?";
               sentences2 = "Im papyrus!!";
                lmao= g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT-50, null);
            }else if(conversation == 3){
                name="Papyrus";
                sentences = tempname+", where";
                sentences2 = "are the others?";
                lmao= g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT-50, null);
            }else if(conversation == 4){
                name="Papyrus";
                sentences = "You don't know?";
                sentences2 = "Its ok for now";
                lmao= g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT-50, null);
            }
            else if(conversation == 5){
                name="Papyrus";
                sentences = "Let find our way";
                sentences2 = "out from here!";
                lmao= g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT-50, null);
            }
            else if(conversation == 6){
                name="Papyrus";
                sentences = "Oh!! a diamond";
                sentences2 = "just appeared!!";
                lmao= g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT-50, null);
            }
            else if(conversation2 == 1){
                name="Papyrus";
                sentences = "Oh you almost";
                sentences2 = "collected all";
                lmao= g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT-50, null);
            }
            else if(conversation2 == 2){
                name="Papyrus";
                sentences = "The diamond";
                sentences2 = "Good! But...";
                lmao= g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT-50, null);
            }
            else if(conversation2 == 3){
                name="Papyrus";
                sentences = "Im not gonna";
                sentences2 = "let you go..";
                lmao= g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT-50, null);
            }
            else if(conversation2 == 4){
                name="Papyrus";
                sentences = "Don't fool me";
                sentences2 = "I know you";
                lmao= g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT-50, null);
            }
            else if(conversation2 == 5){
                name="Papyrus";
                sentences = "Kill our";
                sentences2 = "classmate";
                lmao= g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT-50, null);
            }
            else if(conversation2 == 6){
                name="Papyrus";
                sentences = "You human!";
                sentences2 = "like genocide!";
                lmao= g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT-50, null);
            }
            else if(conversation2 == 7){
                name="Papyrus";
                sentences = "I cannot trust";
                sentences2 = "you!!!";
                lmao= g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT-50, null);
            }
            else if(conversation2 == 8){
                name="Papyrus";
                sentences = "Cause human are";
                sentences2 = "different than us!";
                lmao= g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT-50, null);
            }
            else if(conversation2 == 9){
                name="Papyrus";
                sentences = "And now Im gonna";
                sentences2 = "kill you!";
                lmao= g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT-50, null);
            }
            else if(conversation2 == 10){
                name="";
                sentences = "";
                sentences2 = "";
                eventFinish();
            }
            else {
                name="";
                sentences = "";
                sentences2 = "";
                dialogue=false;
            }


            Content.drawString(g, name, 6, 88);
            Content.drawString(g, word, 3, 98);
            Content.drawString(g, word2, 3, 108);
            if(i < sentences.length()){
                word += String.format("%s",sentences.charAt(i));
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
            if(i==sentences.length() && i2 < sentences2.length()){
                word2 += String.format("%s",sentences2.charAt(i2));
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i2++;
            }


        }
        if(save=true) {
            if (conversation3 == 1) {
                lmao = g.drawImage(Content.load("/HUD/dbox.png", 128, 96)[0][0], 0, GamePanel.HEIGHT - 50, null);
                sentences = "Game saved!";
                sentences2 = "Press F2 to exit";
            } else {
                sentences = "";
                sentences2 = "";
                conversation3 = 0;
                save = false;
            }
            Content.drawString(g, sentences, 3, 98);
            Content.drawString(g, sentences2, 3, 108);
        }

    }

    public void handleInput() {
        if(Keys.isPressed(Keys.ESCAPE)) {
            JukeBox.stop("music1");
            gsm.setPaused(true);
        }

        if (player.numDiamonds() == 15 && location==0) {
            boxes= new ArrayList<Rectangle>();
            eventStart = true;
            eventStart();
            tileMap.setTile(21, 20, 1);
            opponent.setTilePosition(18, 17);
            tileMap.setTile(18, 17, 23);
            player.setTilePosition(17,17);
            conversation2=0;
            location=1;
        }

        if(blockInput) return;
        if(dialogue==false) {
            if(save==false) {
                if (Keys.isDown(Keys.LEFT)) player.setLeft();
                if (Keys.isDown(Keys.RIGHT)) player.setRight();
                if (Keys.isDown(Keys.UP)) player.setUp();
                if (Keys.isDown(Keys.DOWN)) player.setDown();
            }
        }
        if(Keys.isPressed(Keys.SPACE)) player.setAction();
        if(Keys.isPressed(Keys.F2)) {
            save = true;
            conversation3++;
            WorldSave saving = new WorldSave(player.getx(), player.gety(), conversation, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, Items1, Items2, player.getTicks());
            SaveData.save(saving);
            System.out.println("Save Data...." + player.getx() + " " + player.gety() + " " + player.getx() / 31 * 2 + " " + player.gety() / 31 * 2);
        }
        //if(Keys.isPressed(Keys.F3)) {
            //WorldSave loading = SaveData.load();
            //player.setTilePosition(loading.getPosY()/31*2,loading.getPosX()/31*2);
            //System.out.println("Loading...." + loading.getPosX()+ " " + loading.getPosY()+ " " + loading.getPos1()+ " " + loading.getPos2()+ " " + loading.getPos3()+ " " + loading.getPos4()+ " " + loading.getPos5()+ " " + loading.getPos6()+ " " + loading.getPos7()+ " " + loading.getPos8()+ " " + loading.getPos9()+ " " + loading.getPos10()+ " " + loading.getPos11()+ " " + loading.getPos12()+ " " + loading.getPos13()+ " " + loading.getPos14()+ " " + loading.getPos15());
        //}
        if(Keys.isPressed(Keys.ENTER)) {
            if (conversation < 11) {
                if (player.gety() == 344) {
                    if ((player.getx() - 16) == 328) {
                        dialogue = true;
                        conversation++;
                        word="";
                        word2="";
                        i=0;
                        i2=0;
                    }
                    if ((player.getx() + 16) == 328) {
                        dialogue = true;
                        conversation++;
                        word="";
                        word2="";
                        i=0;
                        i2=0;
                    }
                }
                if (player.getx() == 328) {
                    if ((player.gety() - 16) == 344) {
                        dialogue = true;
                        conversation++;
                        word="";
                        word2="";
                        i=0;
                        i2=0;
                    }
                    if ((player.gety() + 16) == 344) {
                        dialogue = true;
                        conversation++;
                        word="";
                        word2="";
                        i=0;
                        i2=0;
                    }
                }
                if (conversation2 >= 0) {
                    conversation2++;
                    dialogue = true;
                    word="";
                    word2="";
                    i=0;
                    i2=0;
                }
            }
        }
    }



    private void eventStart() {
        eventTick++;
        if(eventTick == 1) {
            boxes.clear();
            for(int i = 0; i < 9; i++) {
                boxes.add(new Rectangle(0, i * 16, GamePanel.WIDTH, 16));
            }
        }
        if(eventTick > 1 && eventTick < 32) {
            for(int i = 0; i < boxes.size(); i++) {
                Rectangle r = boxes.get(i);
                if(i % 2 == 0) {
                    r.x -= 4;
                }
                else {
                    r.x += 4;
                }
            }
        }
        if(eventTick == 33) {
            boxes.clear();
            eventStart = false;
            eventTick = 0;
        }
    }

    private void eventFinish() {
        eventTick++;
        if(eventTick == 1) {
            boxes.clear();
            for(int i = 0; i < 9; i++) {
                if(i % 2 == 0) boxes.add(new Rectangle(-128, i * 16, GamePanel.WIDTH, 16));
                else boxes.add(new Rectangle(128, i * 16, GamePanel.WIDTH, 16));
            }
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
            if(JukeBox.isPlaying("music1")) {
                Data.setTime(player.getTicks());
                gsm.setState(GameStateManager.BATTLE);
                JukeBox.stop("music1");
            }
        }
    }
}
