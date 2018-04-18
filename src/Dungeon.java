/*TODO
 * Comment code better
 * balence the game
 * more types of monsters
 * better monster randimizer
 * different size rooms
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import javax.swing.*;

public class Dungeon {
    private static Random rando = new Random();

    static int floor = 1;
    static int levelposX, levelposY;

    private static String direction;
    private static Scanner scan = new Scanner(System.in);
    private static double door1;
    private static int door2;
    static int RoomSize = 8;
    static int floorsize = 2;

    private static boolean qq;
    static int killCount;
    //4d Array.... what have I done?
    static char[][][][] LevelList = new char[floorsize][floorsize][RoomSize][RoomSize];

    public static void main(String[] args) {
        DungeonGUI hello = new DungeonGUI();
        hello.setVisible(true);

        levelposX = floorsize / 2;
        levelposY = floorsize / 2;
        Player.posX = RoomSize / 2;
        Player.posY = RoomSize / 2;
        Player.posX_Old =  Player.posX;
        Player.posY_Old =  Player.posY;

        generateFloor();

        //prints room and player
        LevelList[levelposX][levelposY][ Player.posY][ Player.posX] = '@';
        printLevel(LevelList[levelposX][levelposY]);

//        while (true) {
//            direction = scan.nextLine();
//            userAction(direction);
//            if (qq)
//                break;
//            if (isDead())
//                break;
//        }
        int score;
        score = killCount * floor;
        System.out.println("Game Over");
        System.out.println("orcs killed: " + killCount + ", Floor: " + floor + " Score: " + score);
        System.out.println("Thanks for playing");
    }

    static void printLevel(char[][] a) {
        String text="<html>";
        System.out.println("HP:" + Player.HP + '/' + Player.MaxHP + " Attack:" + Player.Attack + " Armor: " + Player.Armor + " Arrows: " + Player.Arrows + " Room: (" + levelposX + ',' + levelposY + ") Floor:" + floor);
        for (int i = 0; i < a.length; i++) {
            System.out.println(Arrays.toString(a[i]));
            //text+=Arrays.toString(a[i])+"<br/>";
        }
        for (int y = 0; y < RoomSize; y++) {
            for (int x = 0; x < RoomSize; x++) {
                text+=a[y][x]+ ",&nbsp;";
               // System.out.println("Y: " + y + " X: " + x + " " + a[y][x]);
            }
            text+=" <br/>";
        }

        text+="</html>";
        //System.out.println(text);

        DungeonGUI.map.setText(text);
    }

    private static void generateFloor() {
        //Fills every level with  ' '.
        for (int a = 0; a < floorsize; a++)
            for (int b = 0; b < floorsize; b++)
                for (int c = 0; c < RoomSize; c++)
                    Arrays.fill(LevelList[a][b][c], ' ');

        //determines if there should be 1 or 2 wide doors
        door1 = (double) RoomSize / 2;
        if (RoomSize % 2 == 0)
            door2 = (int) door1 - 1;
        else
            door2 = (int) door1;

        //Fills in walls of each level
        for (int a = 0; a < floorsize; a++)
            for (int b = 0; b < floorsize; b++) {

                //fills north wall
                for (int i = 0; i < RoomSize; i++)
                    if (b == 0)
                        LevelList[a][b][0][i] = '#';
                    else if (i != door1 && i != door2)
                        LevelList[a][b][0][i] = '#';

                //fills west wall
                for (int i = 0; i < RoomSize; i++)
                    if (a == 0)
                        LevelList[a][b][i][0] = '#';
                    else if (i != door1 && i != door2)
                        LevelList[a][b][i][0] = '#';

                //fill south wall
                for (int i = 0; i < RoomSize; i++)
                    if (b == floorsize - 1)
                        LevelList[a][b][RoomSize - 1][i] = '#';
                    else if (i != door1 && i != door2)
                        LevelList[a][b][RoomSize - 1][i] = '#';

                //fills east wall
                for (int i = 0; i < RoomSize; i++)
                    if (a == floorsize - 1)
                        LevelList[a][b][i][RoomSize - 1] = '#';
                    else if (i != door1 && i != door2)
                        LevelList[a][b][i][RoomSize - 1] = '#';
            }
        //Create monster randomly
        for (int a = 0; a < floorsize; a++)
            for (int b = 0; b < floorsize; b++) {
                if (a == levelposX && b == levelposY) ;
                else
                    for (int w = rando.nextInt(5); w > 0; w--) {
                        LevelList[a][b][rando.nextInt(RoomSize - 2) + 1][rando.nextInt(RoomSize - 2) + 1] = '0';
                    }
            }
        //generates all objects such as food and arrows
        for (int a = 0; a < floorsize; a++)
            for (int b = 0; b < floorsize; b++) {
                if (rando.nextInt(5) == 0)
                    LevelList[a][b][rando.nextInt(RoomSize - 2) + 1][rando.nextInt(RoomSize - 2) + 1] = 'F';
                else if (rando.nextInt(5) == 0)
                    LevelList[a][b][rando.nextInt(RoomSize - 2) + 1][rando.nextInt(RoomSize - 2) + 1] = 'A';
                else if (rando.nextInt(5) == 0)
                    LevelList[a][b][rando.nextInt(RoomSize - 2) + 1][rando.nextInt(RoomSize - 2) + 1] = '>';
                else if (rando.nextInt(5) == 0)
                    LevelList[a][b][rando.nextInt(RoomSize - 2) + 1][rando.nextInt(RoomSize - 2) + 1] = 'D';
                else if(rando.nextInt(3) == 0);
                	LevelList[a][b][rando.nextInt(RoomSize - 2) + 1][rando.nextInt(RoomSize - 2) + 1] = 'T';
            }

        int w = rando.nextInt(floorsize);
        int v = rando.nextInt(floorsize);

        LevelList[w][v][rando.nextInt(RoomSize - 2) + 1][rando.nextInt(RoomSize - 2) + 1] = 'L';
        //System.out.println("x "+w);
        //System.out.println("y "+v);

        //creating  test objects
        LevelList[1][0][3][4] = 'L';
        LevelList[1][0][4][4] = 'F';
        LevelList[0][1][2][4] = '2';
        LevelList[1][0][4][4] = '1';

        //updates player stats
        if (floor != 1) {
            Player.HP = (int) (Player.HP * 1.1 + floor);
            Player.MaxHP = (int) (Player.MaxHP * 1.1 + floor);
            Player.Attack = (int) (Player.Attack * 1.1 + floor);
            Player.Armor = Player.Armor + floor / 2;
        }
        enterRoom();
    }

    private static void enterRoom() {
        //clears monsters
        Monster_list.clear();
        //checks every spot in room and adds integers to orcs array
        for (int y = 0; y < RoomSize; y++)
            for (int x = 0; x < RoomSize; x++)
                if (isInt(LevelList[levelposX][levelposY][y][x])) {
                    System.out.println("adding a monster");
                    //orcs.add(new Monster(floor, x, y, levelposX, levelposY));
                    //TODO add a way to get monster type
                    Monster_list.addMonster(LevelList[levelposX][levelposY][y][x], x, y);
                }
    }

    private static void upDateLevel(){

        if (LevelList[levelposX][levelposY][Player.posY][Player.posX] != '#') {
            LevelList[levelposX][levelposY][Player.posY_Old][Player.posX_Old] = ' ';

            if (LevelList[levelposX][levelposY][Player.posY][Player.posX] == 'F') {
                Player.HP += Player.MaxHP * 0.1;
                LevelList[levelposX][levelposY][Player.posY][Player.posX] = ' ';
                if (Player.HP > Player.MaxHP)
                    Player.HP = Player.MaxHP;
            } else if (LevelList[levelposX][levelposY][Player.posY][Player.posX] == 'L') {
                floor++;
                LevelList[levelposX][levelposY][Player.posY][Player.posX] = ' ';
                generateFloor();
            } else if (LevelList[levelposX][levelposY][Player.posY][Player.posX] == 'A') {
                Player.Attack += floor;
                LevelList[levelposX][levelposY][Player.posY][Player.posX] = ' ';
            } else if (LevelList[levelposX][levelposY][Player.posY][Player.posX] == '>') {
                Player. Arrows += 3;
                LevelList[levelposX][levelposY][Player.posY][Player.posX] = ' ';
            } else if (LevelList[levelposX][levelposY][Player.posY][Player.posX] == 'D') {
                Player.Armor++;
                LevelList[levelposX][levelposY][Player.posY][Player.posX] = ' ';
            }else if (LevelList[levelposX][levelposY][Player.posY][Player.posX] == 'T') {
                LevelList[levelposX][levelposY][Player.posY][Player.posX] = ' ';
                do{
                    levelposX=rando.nextInt(floorsize);
                    levelposX=rando.nextInt(floorsize);
                    Player.posY=rando.nextInt(RoomSize - 2) + 1;
                    Player.posX=rando.nextInt(RoomSize - 2) + 1;
                }while(LevelList[levelposX][levelposY][Player.posY][Player.posX] != ' ');
                enterRoom();
            }

            Monster_list.attackMonster(Player.posY, Player.posX);


            Player.posX_Old = Player.posX;
            Player.posY_Old = Player.posY;
        } else {
            System.out.println("You cant move into a wall");
            Player.posY = Player.posY_Old;
            Player.posX = Player. posX_Old;
        }

        //marks position of character
        LevelList[levelposX][levelposY][ Player.posY][ Player.posX] = '@';

        Monster_list.runMonsters();

        printLevel(LevelList[levelposX][levelposY]);
    }

    public static void userAction(String command) {
            if (command.equals("w")) //up
                if ( Player.posY == 0) {
                    LevelList[levelposX][levelposY][ Player.posY_Old][Player.posX_Old] = ' ';
                    Player.posY = RoomSize - 1;
                    Player.posY_Old = Player.posY;
                    levelposY -= 1;
                    enterRoom();
                } else
                    Player.posY--;

            else if (command.equals("s"))//down
                if (Player.posY == RoomSize - 1) {
                    LevelList[levelposX][levelposY][Player.posY_Old][ Player.posX_Old] = ' ';
                    Player. posY = 0;
                    Player. posY_Old = Player.posY;
                    levelposY += 1;
                    enterRoom();
                } else
                    Player. posY++;

            else if (command.equals("a"))//left
                if (Player.posX == 0) {
                    LevelList[levelposX][levelposY][Player.posY_Old][Player.posX_Old] = ' ';
                    Player.posX = RoomSize - 1;
                    Player. posX_Old = Player.posX;
                    levelposX -= 1;
                    enterRoom();
                } else
                    Player.posX--;

            else if (command.equals("d"))//right
                if (Player.posX == RoomSize - 1) {
                    LevelList[levelposX][levelposY][Player.posY_Old][Player.posX_Old] = ' ';
                    Player. posX = 0;
                    Player.  posX_Old = Player.posX;
                    levelposX += 1;
                    enterRoom();
                } else
                    Player. posX++;
            else if (command.equals("shoot")) {
                if (Player.Arrows > 0) {
                    direction = scan.nextLine();
                    if (direction.equals("d") || direction.equals("a")) {
                        int ArrowPos = Player.posX;
                        do {
                            if (direction.equals("d") && ArrowPos != RoomSize - 1)
                                ArrowPos++;
                            else if (ArrowPos != 0)
                                ArrowPos--;
                            if (LevelList[levelposX][levelposY][Player.posY][ArrowPos] == ' ')
                                LevelList[levelposX][levelposY][Player.posY][ArrowPos] = '-';
                            printLevel(LevelList[levelposX][levelposY]);

                        } while (LevelList[levelposX][levelposY][ Player.posY][ArrowPos] == '-' && ArrowPos != 0 && ArrowPos != RoomSize - 1);
                        Monster_list.attackMonster(Player.posY, ArrowPos);
                      //  attackMonster(posY, ArrowPos);
                    } else if (direction.equals("s") || direction.equals("w")) {

                        int ArrowPos = Player.posY;
                        do {
                            if (LevelList[levelposX][levelposY][ArrowPos][Player.posX] != '@')
                                LevelList[levelposX][levelposY][ArrowPos][Player.posX] = '|';
                            if (direction.equals("s") && ArrowPos != RoomSize - 1)
                                ArrowPos++;
                            else if (ArrowPos != 0)
                                ArrowPos--;
                            printLevel(LevelList[levelposX][levelposY]);

                        } while (LevelList[levelposX][levelposY][ArrowPos][Player.posX] == ' ' || LevelList[levelposX][levelposY][ArrowPos][Player.posX] == '@' && ArrowPos != 0 && ArrowPos != RoomSize - 1);
                        Monster_list.attackMonster(ArrowPos, Player.posX);
                    }
                    Player.Arrows--;

                    for (int a = 0; a < RoomSize; a++)
                        for (int b = 0; b < RoomSize; b++)
                            if (LevelList[levelposX][levelposY][a][b] == '-' || LevelList[levelposX][levelposY][a][b] == '|')
                                LevelList[levelposX][levelposY][a][b] = ' ';
                } else {
                    System.out.println("You need arrows to shoot.");
                    direction = "q";
                }
            }
            //cheater dev command
            else if(command.equals("n"));

            else if (command.equals("stop"))
                qq = true;
            else {
                System.out.println("please input a valid command");
                direction = "q";
            }
            if(!direction.equals("q"))
                upDateLevel();
    }

    public static boolean isInt(char character) {
        return (int) character >= 48 && (int) character <= 57;
    }

    static public boolean isDead() {
        return  Player.HP <= 0;
    }

    public char getPosition(int Y,int X){
        return LevelList[levelposX][levelposY][Y][X];
    }

    public void setPositionChar(int Y,int X, char looks){
        LevelList[levelposX][levelposY][Y][X]=looks;
    }
}
