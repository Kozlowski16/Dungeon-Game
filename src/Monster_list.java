import java.util.ArrayList;

public class Monster_list {
    static ArrayList<test> Monsters = new ArrayList<test>();

    public static void clear() {
        Monsters.clear();
    }

    public static void addMonster(int type, int X, int Y) {
        if (type == 0)
            Monsters.add(new test(X, Y));
    }
    public static void runMonsters(){
        for(test baddie:Monsters)
            baddie.action();
    }
    public static void attackMonster(int Ypos,int Xpos){
        if (Dungeon.isInt(Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][Ypos][Xpos])) {
            for (int i = 0; i < Monsters.size(); i++) {

                if ( Monsters.get(i).posX == Xpos &&  Monsters.get(i).posY == Ypos) {
                    System.out.println( Monsters.get(i).HP);
                    Monsters.get(i).takeDamage(Player.Attack);
                    System.out.println( Monsters.get(i).HP);

                    if ( Monsters.get(i).HP <= 0) {
                        Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][Monsters.get(i).getY()][Monsters.get(i).getX()] = ' ';
                        Monsters.remove(i);
                        Dungeon.killCount++;
                    }
                }
            }
            if (Ypos == Dungeon.posY && Xpos == Dungeon.posX) {
                Dungeon.posY = Dungeon.posY_Old;
                Dungeon. posX = Dungeon.posX_Old;
            }
        }
    }


}
