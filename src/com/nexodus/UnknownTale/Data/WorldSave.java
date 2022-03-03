package com.nexodus.UnknownTale.Data;

import java.io.Serializable;

//This is the class which get the object value of what we want to save and load the value from .sav
public class WorldSave implements Serializable {

    private static final long serialVersionUID = 5740715636296004512L;
    public int getx, gety, con1;
    public long time;
    public boolean get1, get2,get3,get4,get5, get6, get7, get8, get9, get10, get11, get12, get13, get14, get15,item1,item2;

    //This is use to get the current value from the game and will be save into the Save function at SaveData class
    public WorldSave(int getx, int gety, int con1,  boolean get1, boolean get2,boolean get3,boolean get4,boolean get5,boolean  get6,boolean  get7,boolean get8, boolean get9, boolean get10, boolean get11, boolean get12,boolean  get13, boolean get14, boolean get15, boolean item1, boolean item2, long time) {
        this.getx = getx;
        this.gety = gety;
        this.con1 = con1;
        this.get1 = get1;
        this.get2 = get2;
        this.get3 = get3;
        this.get4 = get4;
        this.get5 = get5;
        this.get6 = get6;
        this.get7 = get7;
        this.get8 = get8;
        this.get9 = get9;
        this.get10 = get10;
        this.get11 = get11;
        this.get12 = get12;
        this.get13 = get13;
        this.get14 = get14;
        this.get15 = get15;
        this.item1 =item1;
        this.item2=item2;
        this.time=time;
    }


    //This are use when the value has been loaded
    public int getPosX() {
        return getx;
    }
    public int getPosY() { return gety; }
    public int getCon1() {return con1;}
    public boolean getPos1() { return get1; }
    public boolean getPos2() { return get2; }
    public boolean getPos3() { return get3; }
    public boolean getPos4() { return get4; }
    public boolean getPos5() { return get5; }
    public boolean getPos6() { return get6; }
    public boolean getPos7() { return get7; }
    public boolean getPos8() { return get8; }
    public boolean getPos9() { return get9; }
    public boolean getPos10() { return get10; }
    public boolean getPos11() { return get11; }
    public boolean getPos12() { return get12; }
    public boolean getPos13() { return get13; }
    public boolean getPos14() { return get14; }
    public boolean getPos15() { return get15; }
    public boolean getItem1() { return item1; }
    public boolean getItem2() { return item2; }
    public long getTime() { return time; }






}
