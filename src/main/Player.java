package main;

public class Player {
    public int HP;
    public int maxHP;
    public int armor;
    public int attack;
    public int arrows;

    private int posX;
    private int posY;
    //public int posX_Old;
    //public int posY_Old;

    public Player() {
        maxHP = 1000;
        HP = maxHP;
        armor = 0;
        attack = 1000;
        arrows = 3;

        posX = 3;
        posY = 3;
        //posX_Old = posX;
        //posY_Old = posY;
    }

    public void reset() {
        maxHP = 100;
        HP = maxHP;
        armor = 0;
        attack = 1000;
        arrows = 3;

        posX = 3;
        posY = 3;
        //posX_Old = posX;
        //posY_Old = posY;
    }

    public void setPosition(Point p) {
        posX = p.getX();
        posY = p.getY();
    }

    public void setX(int x) {
        this.posX = x;
    }

    public int getX() {
        return posX;
    }

    public void setY(int y) {
        this.posY = y;
    }

    public int getY() {
        return posY;
    }
/*
    public void updateOld(){
        posX_Old=posX;
        posY_Old=posY;
    }
    public void revert(){
        posX=posX_Old;
        posY=posY_Old;
    }
*/

}
