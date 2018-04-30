import java.util.ArrayList;

class Monster_list {
    private static ArrayList<Monster> Monster = new ArrayList<>();

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
        if (Dungeon.isInt(Dungeon.getRoom()[Ypos][Xpos])) {
            for (int i = 0; i < Monster.size(); i++) {
                Monster tempMonster = Monster.get(i);
                if (tempMonster.getX() == Xpos &&  tempMonster.getY() == Ypos) {

                    tempMonster.takeDamage(Player.Attack);

                    if (tempMonster.HP <= 0) {
                        Dungeon.getRoom()[tempMonster.getY()][tempMonster.getX()] = ' ';
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
