import java.util.ArrayList;

class Monster_list {
    private ArrayList<Monster> monsters ;
    public Monster_list(){
        monsters = new ArrayList<>();
    }
    public  void clear() {
        monsters.clear();
    }

    public void addMonster(char type, int X, int Y) {
        switch (type){
            case '0':
                monsters.add(new Orc(X, Y));
                break;
            case '1':
                monsters.add(new Archer(X, Y));
                break;
            case '2':
                monsters.add(new Spear(X, Y));
                break;
        }
    }
    public  void runMonsters(){
        for(Monster baddie: monsters)
            baddie.action();
    }
    public  void attackMonster(int Ypos,int Xpos){
        for (int i = 0; i < monsters.size(); i++) {
            Monster tempMonster = monsters.get(i);
            if (tempMonster.getX() == Xpos &&  tempMonster.getY() == Ypos) {
                tempMonster.takeDamage(Dungeon.getPlayer().Attack);
                if (tempMonster.HP <= 0) {
                    Dungeon.getRoom()[tempMonster.getY()][tempMonster.getX()] = ' ';
                    monsters.remove(i);
                    Dungeon.killCount++;
                    System.out.println("Monster died");
                }
            }
        }
    }
}
