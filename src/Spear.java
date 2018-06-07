public class Spear extends Monster {
    public Spear(int x, int y) {
        super(x, y);
        looks = '2';
    }
    @Override
    protected void moveX() {
        if(posX!=Dungeon.getPlayer().posX-2 && posX != Dungeon.getPlayer().posX+2)
            if (posX > Dungeon.getPlayer().posX + 2)
                posX--;
            else if (posX > Dungeon.getPlayer().posX)
                posX++;
            else if (posX < Dungeon.getPlayer().posX - 2)
                posX++;
            else if (posX < Dungeon.getPlayer().posX)
                posX--;
    }
    @Override
    protected void moveY() {
        if(posY!=Dungeon.getPlayer().posY-2 && posY != Dungeon.getPlayer().posY+2)
            if (posY > Dungeon.getPlayer().posY + 2)
                posY--;
            else if (posY > Dungeon.getPlayer().posY)
                posY++;
            else if (posY < Dungeon.getPlayer().posY - 2)
                posY++;
            else if (posY < Dungeon.getPlayer().posY)
                posY--;
    }
    @Override
    protected void move(){
        old_posY = posY;
        old_posX = posX;
        Dungeon.getRoom()[posY][posX] = ' ';
        //if only y is different
        if(Dungeon.getPlayer().posX == posX && Dungeon.getPlayer().posY != posY)
            moveY();

        if(posX != Dungeon.getPlayer().posX && posY == Dungeon.getPlayer().posY)
            moveX();

        if(posX != Dungeon.getPlayer().posX && posY != Dungeon.getPlayer().posY){
            int difX = Math.min(Math.abs(posX-Dungeon.getPlayer().posX-2),Math.abs(posX-Dungeon.getPlayer().posX+2));
            int difY = Math.min(Math.abs(posY-Dungeon.getPlayer().posY-2),Math.abs(posY-Dungeon.getPlayer().posY+2));

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
        if (posX<0 || posX>=Dungeon.roomSize || posY<0 || posY>=Dungeon.roomSize || Dungeon.getRoom()[posY][posX] != ' ') {
            posY = old_posY;
            posX = old_posX;
        }
        Dungeon.getRoom()[posY][posX] = looks;
    }

    @Override
    public void attack() {
        if ((Dungeon.getPlayer().posX + 2 == posX || Dungeon.getPlayer().posX - 2 == posX)&&Dungeon.getPlayer().posY == posY)
            Dungeon.getPlayer().HP -= Attack - rando.nextInt(Dungeon.getPlayer().Armor + 1);

        else if ((Dungeon.getPlayer().posY + 2 == posY || Dungeon.getPlayer().posY - 2 == posY)&&Dungeon.getPlayer().posX == posX)
            Dungeon.getPlayer().HP =  Dungeon.getPlayer().HP - Attack + rando.nextInt( Dungeon.getPlayer().Armor + 1);
    }

    @Override
    public void action() {
        attack();
        move();
    }

}
