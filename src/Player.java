public class Player {
    static int HP;
    static int MaxHP;
    static int Armor;
    static int Attack;
    static int Arrows;

    static int posX;
    static int posY;
    static int posX_Old;
    static int posY_Old;

    public Player() {
        MaxHP = 100;
        HP = MaxHP;
        Armor = 0;
        Attack = 10;
        Arrows = 3;

        posX = 3;
        posY = 3;
        posX_Old = posX;
        posY_Old = posY;
    }


}
