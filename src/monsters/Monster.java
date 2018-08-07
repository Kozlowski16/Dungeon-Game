package monsters;

import main.Dungeon;

import java.util.Random;

public abstract class Monster {
    protected int Attack;
    protected int HP;
    protected int armor;
    protected int posY;
    protected int posX;
    protected char looks;
    Dungeon dun;
    Random rando = new Random();

    public Monster(int x, int y, Dungeon d) {
        dun = d;

        Attack = (int) (4 + dun.getFloorNumber() * 1.1);
        HP = (int) ((8 + Math.pow(dun.getFloorNumber(), 1.4)) * 2);
        posX = x;
        posY = y;
        armor = 1;
    }

    protected void moveX() {
        if (dun.getPlayer().getX() < posX)
            posX--;
        else
            posX++;
    }

    protected void moveY() {
        if (dun.getPlayer().getY() < posY)
            posY--;
        else
            posY++;
    }

    protected void move() {
        int old_posY = posY;
        int old_posX = posX;
        dun.getMap()[posY][posX] = ' ';
        //if both x and y are different
        if (dun.getPlayer().getX() != posX && dun.getPlayer().getY() != posY) {
            //rolls to see if x or y changes and changes it
            if (rando.nextInt(2) == 1)
                moveX();
            else
                moveY();
        }
        // if x values are equal but not y
        else if (dun.getPlayer().getX() == posX && dun.getPlayer().getY() != posY) {
            moveY();
        } else if (dun.getPlayer().getX() != posX && dun.getPlayer().getY() == posY) {
            moveX();
        }

        if (dun.getMap()[posY][posX] != ' ') {
            posY = old_posY;
            posX = old_posX;
        }
        dun.getMap()[posY][posX] = looks;

    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public void takeDamage(int dmg) {
        HP = HP - dmg + armor;
        System.out.println("monsters.Monster took " + (dmg - armor) + "damaage");
    }

    public void takeAttack() {

    }

    public abstract void attack();

    public abstract void action();

}
