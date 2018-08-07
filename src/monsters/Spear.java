package monsters;

import main.Dungeon;

public class Spear extends Monster {
    public Spear(int x, int y, Dungeon d) {
        super(x, y, d);
        looks = '2';
    }

    @Override
    protected void moveX() {
        if (posX != dun.getPlayer().getX() - 2 && posX != dun.getPlayer().getX() + 2)
            if (posX > dun.getPlayer().getX() + 2)
                posX--;
            else if (posX > dun.getPlayer().getX())
                posX++;
            else if (posX < dun.getPlayer().getX() - 2)
                posX++;
            else if (posX < dun.getPlayer().getX())
                posX--;
    }

    @Override
    protected void moveY() {
        if (posY != dun.getPlayer().getY() - 2 && posY != dun.getPlayer().getY() + 2)
            if (posY > dun.getPlayer().getY() + 2)
                posY--;
            else if (posY > dun.getPlayer().getY())
                posY++;
            else if (posY < dun.getPlayer().getY() - 2)
                posY++;
            else if (posY < dun.getPlayer().getY())
                posY--;
    }

    @Override
    protected void move() {
        int old_posY = posY;
        int old_posX = posX;
        dun.getMap()[posY][posX] = ' ';
        //if only y is different
        if (dun.getPlayer().getX() == posX && dun.getPlayer().getY() != posY)
            moveY();

        if (posX != dun.getPlayer().getX() && posY == dun.getPlayer().getY())
            moveX();

        if (posX != dun.getPlayer().getX() && posY != dun.getPlayer().getY()) {
            int difX = Math.min(Math.abs(posX - dun.getPlayer().getX() - 2), Math.abs(posX - dun.getPlayer().getX() + 2));
            int difY = Math.min(Math.abs(posY - dun.getPlayer().getY() - 2), Math.abs(posY - dun.getPlayer().getY() + 2));

            if (difX > difY)
                super.moveX();
            else if (difX < difY)
                super.moveY();
                //rolls to see if x or y changes and changes it
            else if (rando.nextInt(2) == 1)
                super.moveX();
            else
                super.moveY();
        }
        if (dun.getMap()[posY][posX] != ' ') {
            posY = old_posY;
            posX = old_posX;
        }
        dun.getMap()[posY][posX] = looks;
    }

    @Override
    public void attack() {
        if ((dun.getPlayer().getX() + 2 == posX || dun.getPlayer().getX() - 2 == posX) && dun.getPlayer().getY() == posY)
            dun.getPlayer().HP -= Attack - rando.nextInt(dun.getPlayer().armor + 1);

        else if ((dun.getPlayer().getY() + 2 == posY || dun.getPlayer().getY() - 2 == posY) && dun.getPlayer().getX() == posX)
            dun.getPlayer().HP = dun.getPlayer().HP - Attack + rando.nextInt(dun.getPlayer().armor + 1);
    }

    @Override
    public void action() {
        attack();
        move();
    }

}
