public class Player {
    int HP;
     int MaxHP;
     int Armor;
     int Attack;
     int Arrows;

     int posX;
     int posY;
     int posX_Old;
     int posY_Old;

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
    public void reset(){
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
