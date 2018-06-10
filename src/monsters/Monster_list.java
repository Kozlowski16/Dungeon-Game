package monsters;

import main.Dungeon;

import java.util.ArrayList;

public class Monster_list {
    private ArrayList<Monster> monsters;
    private Dungeon dun;

    public Monster_list(Dungeon d) {
        monsters = new ArrayList<>();
        dun = d;
    }

    public void clear() {
        monsters.clear();
    }

    public void addMonster(char type, int X, int Y) {
        switch (type) {
            case '0':
                monsters.add(new Orc(X, Y, dun));
                break;
            case '1':
                monsters.add(new Archer(X, Y, dun));
                break;
            case '2':
                monsters.add(new Spear(X, Y, dun));
                break;
        }
    }

    public void runMonsters() {
        for (Monster baddie : monsters)
            baddie.action();
    }

    public void attackMonster(int Ypos, int Xpos) {
        for (int i = 0; i < monsters.size(); i++) {
            Monster tempMonster = monsters.get(i);
            if (tempMonster.getX() == Xpos && tempMonster.getY() == Ypos) {
                tempMonster.takeDamage(dun.getPlayer().attack);
                if (tempMonster.HP <= 0) {
                    dun.getRoom()[tempMonster.getY()][tempMonster.getX()] = ' ';
                    monsters.remove(i);
                    dun.killCount++;
                    System.out.println("monsters.Monster died");
                }
            }
        }
    }
}
