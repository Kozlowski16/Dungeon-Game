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
        if (posY == dun.getPlayer().posY) {
            int ArrowPos = posX;
            do {
                if (ArrowPos > dun.getPlayer().posX && ArrowPos != 0)
                    ArrowPos--;
                else if (ArrowPos != 0)
                    ArrowPos++;
                if (dun.getRoom()[posY][ArrowPos] == ' ')//erro
                    dun.getRoom()[posY][ArrowPos] = '-';
                // Dungeon.printLevel(Dungeon.getRoom());

            }
            while (dun.getRoom()[dun.getPlayer().posY][ArrowPos] == '-' && ArrowPos != dun.roomSize - 1 && ArrowPos != 0);
            if (dun.getRoom()[dun.getPlayer().posY][ArrowPos] == '@')
                dun.getPlayer().HP = dun.getPlayer().HP - Attack + dun.getPlayer().armor;
            hasShot = true;
        } else if (posX == dun.getPlayer().posX) {
            int ArrowPos = posY;
            do {

                if (ArrowPos > dun.getPlayer().posY && ArrowPos != 0)
                    ArrowPos--;
                else if (ArrowPos != 0)
                    ArrowPos++;
                if (dun.getRoom()[ArrowPos][posX] == ' ')
                    dun.getRoom()[ArrowPos][posX] = '|';
                //Dungeon.printLevel(Dungeon.getRoom());

            } while (dun.getRoom()[ArrowPos][posX] == '|' && ArrowPos != dun.roomSize - 1 && ArrowPos != 0);
            if (dun.getRoom()[ArrowPos][posX] == '@')
                dun.getPlayer().HP = dun.getPlayer().HP - Attack + dun.getPlayer().armor;
            hasShot = true;
        }
        for (int a = 0; a < dun.roomSize; a++)
            for (int b = 0; b < dun.roomSize; b++)
                if (dun.getRoom()[a][b] == '-' || dun.getRoom()[a][b] == '|')
                    dun.getRoom()[a][b] = ' ';
    }

    public void action() {
        attack();
        if (!hasShot)
            move();
        hasShot = false;
    }
}
