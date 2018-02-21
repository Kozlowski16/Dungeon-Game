public class Player {
    static int HP=100;
    static int MaxHP=100;
    static int Armor=1;
    static int Attack=10;
    static int Arrows=3;

    static int posX;
    static int posY;
    static int posX_Old;
    static int posY_Old;

    public Player() {
        MaxHP = 100;
        HP = MaxHP;
        Armor = 0;
        Attack = 1000;
        Arrows = 3;

        posX = 3;
        posY = 3;
        posX_Old = posX;
        posY_Old = posY;
    }


}
