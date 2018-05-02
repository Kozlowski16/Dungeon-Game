public class Spear extends Monster {
    public Spear(int x, int y) {
        super(x, y);
        looks = '2';
    }
    @Override
    protected void moveX() {
        if(posX!=Player.posX-2 && posX != Player.posX+2)
            if (posX > Player.posX + 2)
                posX--;
            else if (posX > Player.posX)
                posX++;
            else if (posX < Player.posX - 2)
                posX++;
            else if (posX < Player.posX)
                posX--;
    }
    @Override
    protected void moveY() {
        if(posY!=Player.posY-2 && posY != Player.posY+2)
            if (posY > Player.posY + 2)
                posY--;
            else if (posY > Player.posY)
                posY++;
            else if (posY < Player.posY - 2)
                posY++;
            else if (posY < Player.posY)
                posY--;
    }
    @Override
    protected void move(){
        old_posY = posY;
        old_posX = posX;
        Dungeon.getRoom()[posY][posX] = ' ';
        //if only y is different
        if(Player.posX == posX && Player.posY != posY)
            moveY();

        if(posX != Player.posX && posY == Player.posY)
            moveX();

        if(posX != Player.posX && posY != Player.posY){
            int difX = Math.min(Math.abs(posX-Player.posX-2),Math.abs(posX-Player.posX+2));
            int difY = Math.min(Math.abs(posY-Player.posY-2),Math.abs(posY-Player.posY+2));

            if(difX>difY)
                super.moveX();
            else if(difX<difY)
                super.moveY();
            //rolls to see if x or y changes and changes it
            else if (rando.nextInt(2) == 1)
                super.moveX();
            else
                super.moveY();
        }
        if (Dungeon.getRoom()[posY][posX] != ' ') {
            posY = old_posY;
            posX = old_posX;
        }
        Dungeon.getRoom()[posY][posX] = looks;
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
