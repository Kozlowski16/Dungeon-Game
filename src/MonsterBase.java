import java.util.Random;

public abstract class MonsterBase {
    Random rando = new Random();
    public int Attack;
    public int HP;
    public int armor;
    public int posY;
    public int posX;
    public int old_posY, old_posX;
    public char looks;

    public MonsterBase(int x, int y, char type) {

        Attack = (int) (4 + Dungeon.floor * 1.1);
        HP = (int) ((8 + Math.pow(Dungeon.floor, 1.4)) * 2);
        posX = x;
        posY = y;
        looks = type;
        System.out.println("looks: " + looks);
        old_posY = posY;
        old_posX = posX;
        armor = 1;
    }

    public void move() {
        old_posY = posY;
        old_posX = posX;
        Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][posY][posX] =' ';
        //System.out.println("level: "+ Attack+ " posX: " + posX + " posY: " + posY );
        //if both x and y are different
        if (Player.posX != posX && Player.posY != posY) {
            //System.out.println("none");
            //rolls to see if x or y changes and changes it
            if (rando.nextInt(2) == 1)
                if (Player.posX < posX)
                    posX--;
                else
                    posX++;
            else if (Player.posY < posY)
                posY--;
            else
                posY++;
        }
        // if x values are equal but not y
        else if (Player.posX == posX && Player.posY != posY) {
            //System.out.println("X=X");
            if (Player.posY + 1 != posY && Player.posY - 1 != posY)
                if (Player.posY < posY)
                    posY--;
                else
                    posY++;
        }
        else if (Player.posX != posX && Player.posY == posY) {
            //System.out.println("X=X");
            if (Player.posX + 1 != posX && Player.posX - 1 != posX)
                if (Player.posX < posX)
                    posX--;
                else
                    posX++;
        }

        if (Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][posY][posX] != ' ') {
            posY = old_posY;
            posX = old_posX;
        }
        Dungeon.LevelList[Dungeon.levelposX][Dungeon.levelposY][posY][posX] =looks;

    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public void takeDamage(int dmg) {
        HP = HP - dmg + armor;
        System.out.println("orcs took " + (dmg-armor) + "damaage" );
    }
    public void takeAttack(){
    	
    }

    public abstract void attack();

    public abstract void action();

}
