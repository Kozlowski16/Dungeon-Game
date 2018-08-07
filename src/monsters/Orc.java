package monsters;

import main.Dungeon;

public class Orc extends Monster {

    public Orc(int x, int y, Dungeon d) {
        super(x, y, d);
        looks = '0';
    }

    @Override
    public void attack() {
        if (dun.getPlayer().getX() + 1 == posX || dun.getPlayer().getX() - 1 == posX)
            if (dun.getPlayer().getY() == posY)
                dun.getPlayer().HP -= Attack - rando.nextInt(dun.getPlayer().armor + 1);
        if (dun.getPlayer().getY() + 1 == posY || dun.getPlayer().getY() - 1 == posY)
            if (dun.getPlayer().getX() == posX)
                dun.getPlayer().HP = dun.getPlayer().HP - Attack + rando.nextInt(dun.getPlayer().armor + 1);
    }

    @Override
    public void action() {
        attack();
        move();
    }

}
