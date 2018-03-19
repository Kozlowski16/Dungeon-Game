import java.util.ArrayList;

public class Monster_list {
    static ArrayList<MonsterBase> MonsterBase = new ArrayList<MonsterBase>();

    public static void clear() {
    	MonsterBase.clear();
    }

    public static void addMonster(char type, int X, int Y) {
        switch (type){
            case '0':
            	MonsterBase.add(new Orc(X, Y, type));
                break;
            case '1':
            	MonsterBase.add(new Archer(X, Y, type));
                break;
        }
    }
    public static void runMonsters(){
        for(MonsterBase baddie: MonsterBase)
            baddie.action();
    }
    public static void attackMonster(int Ypos,int Xpos){
        if (Dungeon.isInt(Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][Ypos][Xpos])) {
            for (int i = 0; i < MonsterBase.size(); i++) {
            	
                if (MonsterBase.get(i).posX == Xpos &&  MonsterBase.get(i).posY == Ypos) {
                	
                	MonsterBase.get(i).takeDamage(Player.Attack);

                    if (MonsterBase.get(i).HP <= 0) {
                        Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][MonsterBase.get(i).getY()][MonsterBase.get(i).getX()] = ' ';
                        MonsterBase.remove(i);
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
