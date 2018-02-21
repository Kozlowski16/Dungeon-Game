import java.util.ArrayList;

public class Monster_list {
    static ArrayList<SwordMonster> Monsters = new ArrayList<SwordMonster>();

    public static void clear() {
        Monsters.clear();
    }

    public static void addMonster(char type, int X, int Y) {
        if (type == '0')
            Monsters.add(new SwordMonster(X, Y, type));
    }
    public static void runMonsters(){
        for(SwordMonster baddie:Monsters)
            baddie.action();
    }
    public static void attackMonster(int Ypos,int Xpos){
        if (Dungeon.isInt(Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][Ypos][Xpos])) {
            for (int i = 0; i < Monsters.size(); i++) {

                if ( Monsters.get(i).posX == Xpos &&  Monsters.get(i).posY == Ypos) {

                    Monsters.get(i).takeDamage(Player.Attack);

                    if ( Monsters.get(i).HP <= 0) {
                        Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][Monsters.get(i).getY()][Monsters.get(i).getX()] = ' ';
                        Monsters.remove(i);
                        Dungeon.killCount++;
                        System.out.println("Test Monster died");
                    }
                }
            }
            if (Ypos ==  Player.posY && Xpos ==  Player.posX) {
                Player.posY = Player.posY_Old;
                Player.posX =  Player.posX_Old;
            }
        }
    }


}
