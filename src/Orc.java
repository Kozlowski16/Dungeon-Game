
public class Orc extends Monster {

    public Orc(int x, int y ) {
        super(x, y);
        looks = '0';
    }

    @Override
    public void attack() {
        if (Player.posX + 1 == posX || Player.posX - 1 == posX)
            if (Player.posY == posY)
                Player.HP -= Attack - rando.nextInt(Player.Armor + 1);
        if (Player.posY + 1 == posY || Player.posY - 1 == posY)
            if (Player.posX == posX)
                Player.HP =  Player.HP - Attack + rando.nextInt( Player.Armor + 1);
    }

    @Override
    public void action() {
        attack();
        move();
        System.out.println("orcs ran " + getX() + " " + getY());
    }

}
