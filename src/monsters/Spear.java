package monsters;

import main.Dungeon;

public class Spear extends Monster {
    public Spear(int x, int y, Dungeon d) {
        super(x, y, d);
        looks = '2';
    }

    @Override
    protected void moveX() {
        if (posX != dun.getPlayer().posX - 2 && posX != dun.getPlayer().posX + 2)
            if (posX > dun.getPlayer().posX + 2)
                posX--;
            else if (posX > dun.getPlayer().posX)
                posX++;
            else if (posX < dun.getPlayer().posX - 2)
                posX++;
            else if (posX < dun.getPlayer().posX)
                posX--;
    }

    @Override
    protected void moveY() {
        if (posY != dun.getPlayer().posY - 2 && posY != dun.getPlayer().posY + 2)
            if (posY > dun.getPlayer().posY + 2)
                posY--;
            else if (posY > dun.getPlayer().posY)
                posY++;
            else if (posY < dun.getPlayer().posY - 2)
                posY++;
            else if (posY < dun.getPlayer().posY)
                posY--;
    }

    @Override
    protected void move() {
        old_posY = posY;
        old_posX = posX;
        dun.getRoom()[posY][posX] = ' ';
        //if only y is different
        if (dun.getPlayer().posX == posX && dun.getPlayer().posY != posY)
            moveY();

        if (posX != dun.getPlayer().posX && posY == dun.getPlayer().posY)
            moveX();

        if (posX != dun.getPlayer().posX && posY != dun.getPlayer().posY) {
            int difX = Math.min(Math.abs(posX - dun.getPlayer().posX - 2), Math.abs(posX - dun.getPlayer().posX + 2));
            int difY = Math.min(Math.abs(posY - dun.getPlayer().posY - 2), Math.abs(posY - dun.getPlayer().posY + 2));

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
        if (posX < 0 || posX >= dun.roomSize || posY < 0 || posY >= dun.roomSize || dun.getRoom()[posY][posX] != ' ') {
            posY = old_posY;
            posX = old_posX;
        }
        dun.getRoom()[posY][posX] = looks;
    }

    @Override
    public void attack() {
        if ((dun.getPlayer().posX + 2 == posX || dun.getPlayer().posX - 2 == posX) && dun.getPlayer().posY == posY)
            dun.getPlayer().HP -= Attack - rando.nextInt(dun.getPlayer().armor + 1);

        else if ((dun.getPlayer().posY + 2 == posY || dun.getPlayer().posY - 2 == posY) && dun.getPlayer().posX == posX)
            dun.getPlayer().HP = dun.getPlayer().HP - Attack + rando.nextInt(dun.getPlayer().armor + 1);
    }

    @Override
    public void action() {
        attack();
        move();
    }

}
