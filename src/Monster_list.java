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

}
