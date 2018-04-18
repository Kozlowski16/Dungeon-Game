import java.util.ArrayList;

public class Monster_list {
    static ArrayList<Monster> Monster = new ArrayList<Monster>();

    public static void clear() {
    	Monster.clear();
    }

    public static void addMonster(char type, int X, int Y) {
        switch (type){
            case '0':
            	Monster.add(new Orc(X, Y, type));
                break;
            case '1':
            	Monster.add(new Archer(X, Y, type));
                break;
            case '2':
                Monster.add(new Spear(X, Y, type));
                break;
        }
    }
    public static void runMonsters(){
        for(Monster baddie: Monster)
            baddie.action();
    }
    public static void attackMonster(int Ypos,int Xpos){
        if (Dungeon.isInt(Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][Ypos][Xpos])) {
            for (int i = 0; i < Monster.size(); i++) {
            	
                if (Monster.get(i).posX == Xpos &&  Monster.get(i).posY == Ypos) {
                	
                	Monster.get(i).takeDamage(Player.Attack);

                    if (Monster.get(i).HP <= 0) {
                        Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][Monster.get(i).getY()][Monster.get(i).getX()] = ' ';
                        Monster.remove(i);
                        Dungeon.killCount++;
                        System.out.println("Monster died");
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
