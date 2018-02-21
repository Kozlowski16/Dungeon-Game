
public class test extends MonsterAbstract {

    public test( int x, int y) {
        super(x, y);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void attack() {
        if (Player.posX + 1 == posX || Player.posX - 1 == posX)
            if (Player.posY == posY)
                Player.HP -= Attack - rando.nextInt(Player.Armor + 1);
        if (Player.posY + 1 == posY || Player.posY - 1 == posY)
            if (Player.posX == posX)
                Dungeon.playerHP = Dungeon.playerHP - Attack + rando.nextInt(Dungeon.playerArmor + 1);
    }

    @Override
    public void action() {
        attack();
        move();
    }

}
