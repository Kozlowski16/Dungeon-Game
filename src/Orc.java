
public class Orc extends Monster {

    public Orc(int x, int y ) {
        super(x, y);
        looks = '0';
    }

    @Override
    public void attack() {
        if (Dungeon.getPlayer().posX + 1 == posX || Dungeon.getPlayer().posX - 1 == posX)
            if (Dungeon.getPlayer().posY == posY)
                Dungeon.getPlayer().HP -= Attack - rando.nextInt(Dungeon.getPlayer().Armor + 1);
        if (Dungeon.getPlayer().posY + 1 == posY || Dungeon.getPlayer().posY - 1 == posY)
            if (Dungeon.getPlayer().posX == posX)
                Dungeon.getPlayer().HP =  Dungeon.getPlayer().HP - Attack + rando.nextInt( Dungeon.getPlayer().Armor + 1);
    }

    @Override
    public void action() {
        attack();
        move();
    }

}
