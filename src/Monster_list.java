import java.util.ArrayList;

public class Monster_list {
    static ArrayList<Orc> orcs = new ArrayList<Orc>();
    static ArrayList<Archer> archers = new ArrayList<Archer>();

    public static void clear() {
        orcs.clear();
        archers.clear();
    }

    public static void addMonster(char type, int X, int Y) {
        switch (type){
            case '0':
                orcs.add(new Orc(X, Y, type));
                break;
            case '1':
                archers.add(new Archer(X, Y, type));
                break;
        }
    }
    public static void runMonsters(){
        for(Orc baddie: orcs)
            baddie.action();
        for(Archer baddie: archers)
            baddie.action();
    }
    public static void attackMonster(int Ypos,int Xpos){
        if (Dungeon.isInt(Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][Ypos][Xpos])) {
            for (int i = 0; i < orcs.size(); i++) {

                if (orcs.get(i).posX == Xpos &&  orcs.get(i).posY == Ypos) {

                    orcs.get(i).takeDamage(Player.Attack);

                    if (orcs.get(i).HP <= 0) {
                        Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][orcs.get(i).getY()][orcs.get(i).getX()] = ' ';
                        orcs.remove(i);
                        Dungeon.killCount++;
                        System.out.println("Test Monster died");
                    }
                }
            }
            if (Ypos ==  Player.posY && Xpos ==  Player.posX) {
                Player.posY = Player.posY_Old;
                Player.posX = Player.posX_Old;
            }
        }
    }

}
