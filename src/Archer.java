public class Archer extends Monster {
    public Archer(int x, int y) {
        super(x, y);
        looks = '1';
    }
    private boolean hasShot;
    @Override
   public void attack() {
        if (posY==Dungeon.getPlayer().posY) {
            int ArrowPos = posX;
            do {
                if (ArrowPos>Dungeon.getPlayer().posX && ArrowPos != Dungeon.roomSize - 1)
                    ArrowPos--;
                else if (ArrowPos != 0 )
                    ArrowPos++;
                if (Dungeon.getRoom()[posY][ArrowPos] == ' ')
                    Dungeon.getRoom()[posY][ArrowPos] = '-';
               // Dungeon.printLevel(Dungeon.getRoom());

            } while (Dungeon.getRoom()[ Dungeon.getPlayer().posY][ArrowPos] == '-' && ArrowPos != Dungeon.roomSize - 1 && ArrowPos != 0);
            if(Dungeon.getRoom()[ Dungeon.getPlayer().posY][ArrowPos] =='@')
                Dungeon.getPlayer().HP=Dungeon.getPlayer().HP-Attack +Dungeon.getPlayer().Armor;
            hasShot=true;
        } else if (posX==Dungeon.getPlayer().posX) {
            int ArrowPos = posY;
            do {

                if (ArrowPos>Dungeon.getPlayer().posY && ArrowPos != Dungeon.roomSize - 1)
                    ArrowPos--;
                else if (ArrowPos != 0)
                    ArrowPos++;
                if (Dungeon.getRoom()[ArrowPos][posX] == ' ')
                    Dungeon.getRoom()[ArrowPos][posX] = '|';
               //Dungeon.printLevel(Dungeon.getRoom());

            } while (Dungeon.getRoom()[ArrowPos][posX] == '|' && ArrowPos != Dungeon.roomSize - 1 && ArrowPos != 0);
            if(Dungeon.getRoom()[ArrowPos][posX] =='@')
                Dungeon.getPlayer().HP=Dungeon.getPlayer().HP-Attack +Dungeon.getPlayer().Armor;
            hasShot=true;
        }
        for (int a = 0; a < Dungeon.roomSize; a++)
            for (int b = 0; b < Dungeon.roomSize; b++)
                if (Dungeon.getRoom()[a][b] == '-' || Dungeon.getRoom()[a][b] == '|')
                    Dungeon.getRoom()[a][b] = ' ';
    }

    public void action() {
        attack();
        if(!hasShot)
            move();
        hasShot=false;
    }
}
