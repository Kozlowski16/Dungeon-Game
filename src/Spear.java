public class Spear extends Monster {
    public Spear(int x, int y, char type) {
        super(x, y, type);
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void moveX() {
        System.out.println("Player Posx- Monster posX: " + (Player.posX - posX));
        if (Player.posX < posX){
            if (Player.posX - posX == -2) ;
            else if (Player.posX - posX == -1)
                posX++;
            else
                posX--;
        }
        else
            if (Player.posX - posX == 2) ;
            else if (Player.posX - posX == 1)
                posX--;
            else
                posX++;
    }
    @Override
    protected void moveY() {
        System.out.println("Player PosY- Monster posY: " + (Player.posY - posY));
        if (Player.posY < posY){
            if (Player.posY - posY == -2) ;
            else if (Player.posY - posY == -1)
                posY++;
            else
                posY--;
        }
        else
        if (Player.posY - posY == 2) ;
        else if (Player.posY - posY == 1)
            posY--;
        else
            posY++;
    }

    @Override
    public void attack() {
        if ((Player.posX + 2 == posX || Player.posX - 2 == posX)&&Player.posY == posY)
            Player.HP -= Attack - rando.nextInt(Player.Armor + 1);
        else if ((Player.posY + 2 == posY || Player.posY - 2 == posY)&&Player.posX == posX)
            Player.HP =  Player.HP - Attack + rando.nextInt( Player.Armor + 1);
    }

    @Override
    public void action() {
        attack();
        move();
        System.out.println("Spear ran " + getX() + " " + getY());
    }

}
