public class Archer extends MonsterBase {
    public Archer(int x, int y, char type) {
        super(x, y, type);
    }
    private boolean hasShot;
    @Override
   public void attack() {
        if (posY==Player.posY) {
            int ArrowPos = posX;
            do {
                if (ArrowPos>Player.posX && ArrowPos != Dungeon.RoomSize - 1)
                    ArrowPos--;
                else if (ArrowPos != 0 )
                    ArrowPos++;
                if (Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][posY][ArrowPos] == ' ')
                    Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][posY][ArrowPos] = '-';
               // Dungeon.printLevel(Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY]);

            } while (Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][ Player.posY][ArrowPos] == '-' && ArrowPos != Dungeon.RoomSize - 1 && ArrowPos != 0);
            if(Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][ Player.posY][ArrowPos] =='@')
                Player.HP=Player.HP-Attack +Player.Armor;
            hasShot=true;
        } else if (posX==Player.posX) {
            int ArrowPos = posY;
            do {

                if (ArrowPos>Player.posY && ArrowPos != Dungeon.RoomSize - 1)
                    ArrowPos--;
                else if (ArrowPos != 0)
                    ArrowPos++;
                if (Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][ArrowPos][posX] == ' ')
                    Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][ArrowPos][posX] = '|';
               //Dungeon.printLevel(Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY]);

            } while (Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][ArrowPos][posX] == '|' && ArrowPos != Dungeon.RoomSize - 1 && ArrowPos != 0);
            if(Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][ArrowPos][posX] =='@')
                Player.HP=Player.HP-Attack +Player.Armor;
            hasShot=true;
        }
        for (int a = 0; a < Dungeon.RoomSize; a++)
            for (int b = 0; b < Dungeon.RoomSize; b++)
                if (Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][a][b] == '-' || Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][a][b] == '|')
                    Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][a][b] = ' ';
    }

    public void action() {
        attack();
        if(!hasShot)
            move();
        hasShot=false;
        System.out.println("Archer ran " + getX() + " " + getY());
    }
}
