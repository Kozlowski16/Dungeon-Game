package monsters;

import main.Dungeon;

public class Archer extends Monster {
    private boolean hasShot;

    public Archer(int x, int y, Dungeon d) {
        super(x, y, d);
        looks = '1';
    }

    @Override
    public void attack() {
        if (posY == dun.getPlayer().getY()) {
            int ArrowPos = posX;
            do {
                if (ArrowPos > dun.getPlayer().getX() && ArrowPos != 0)
                    ArrowPos--;
                else if (ArrowPos != 0)
                    ArrowPos++;
                if (dun.getMap()[posY][ArrowPos] == ' ')//erro
                    dun.getMap()[posY][ArrowPos] = '-';
                // Dungeon.printLevel(Dungeon.getMap());

            }
            while (dun.getMap()[dun.getPlayer().getY()][ArrowPos] == '-' && ArrowPos != 0);
            if (dun.getMap()[dun.getPlayer().getY()][ArrowPos] == '@')
                dun.getPlayer().HP = dun.getPlayer().HP - Attack + dun.getPlayer().armor;
            hasShot = true;
        } else if (posX == dun.getPlayer().getX()) {
            int ArrowPos = posY;
            do {

                if (ArrowPos > dun.getPlayer().getY() && ArrowPos != 0)
                    ArrowPos--;
                else if (ArrowPos != 0)
                    ArrowPos++;
                if (dun.getMap()[ArrowPos][posX] == ' ')
                    dun.getMap()[ArrowPos][posX] = '|';
                //Dungeon.printLevel(Dungeon.getMap());

            } while (dun.getMap()[ArrowPos][posX] == '|' && ArrowPos != 0);
            if (dun.getMap()[ArrowPos][posX] == '@')
                dun.getPlayer().HP = dun.getPlayer().HP - Attack + dun.getPlayer().armor;
            hasShot = true;
        }
        /*
        for (int a = 0; a < dun.roomSize; a++)
            for (int b = 0; b < dun.roomSize; b++)
                if (dun.getMap()[a][b] == '-' || dun.getMap()[a][b] == '|')
                    dun.getMap()[a][b] = ' ';
                    */
    }

    public void action() {
        attack();
        if (!hasShot)
            move();
        hasShot = false;
    }
}
