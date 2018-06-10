package monsters;

import main.Dungeon;

public class Orc extends Monster {

    public Orc(int x, int y, Dungeon d) {
        super(x, y, d);
        looks = '0';
    }

    @Override
    public void attack() {
        if (dun.getPlayer().posX + 1 == posX || dun.getPlayer().posX - 1 == posX)
            if (dun.getPlayer().posY == posY)
                dun.getPlayer().HP -= Attack - rando.nextInt(dun.getPlayer().armor + 1);
        if (dun.getPlayer().posY + 1 == posY || dun.getPlayer().posY - 1 == posY)
            if (dun.getPlayer().posX == posX)
                dun.getPlayer().HP = dun.getPlayer().HP - Attack + rando.nextInt(dun.getPlayer().armor + 1);
    }

    @Override
    public void action() {
        attack();
        move();
    }

}
