/*TODO
 * Comment code better
 * balence the game
 * more types of monsters
 * better monster randimizer
 * different size rooms
 */
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Dungeon {
    private static Random rando = new Random();

    static int floor = 1;
    static int levelPosX, levelPosY;

    private static String direction;
    private static Scanner scan = new Scanner(System.in);
    public static int roomSize = 8;
    private static int floorSize = 2;

    private static boolean qq;
    static int killCount;
    //4d Array.... what have I done?
    static char[][][][] LevelList = new char[floorSize][floorSize][roomSize][roomSize];

    public static void main(String[] args) {
        DungeonGUI hello = new DungeonGUI();
        hello.setVisible(true);

        levelPosX = floorSize / 2;
        levelPosY = floorSize / 2;
        Player.posX = roomSize / 2;
        Player.posY = roomSize / 2;
        Player.posX_Old =  Player.posX;
        Player.posY_Old =  Player.posY;

        generateFloor();

        //prints room and player
        LevelList[levelPosX][levelPosY][ Player.posY][ Player.posX] = '@';
        printLevel(LevelList[levelPosX][levelPosY]);

        while (true) {
            direction = scan.nextLine();
            userAction(direction);
            if (qq)
                break;
            if (isDead())
                break;
        }
        int score;
        score = killCount * floor;
        System.out.println("Game Over");
        System.out.println("orcs killed: " + killCount + ", Floor: " + floor + " Score: " + score);
        System.out.println("Thanks for playing");
    }

    static void printLevel(char[][] a) {
        String text="<html><font size=\"5\"><p style=\"font-family:'Courier New', Courier, monospace\">";
        text+="HP:" + Player.HP + '/' + Player.MaxHP + " Attack:" + Player.Attack + " Armor: " + Player.Armor + " Arrows: " + Player.Arrows + " Room: (" + levelPosX + ',' + levelPosY + ") Floor:" + floor+" <br/> ";
        System.out.println("HP:" + Player.HP + '/' + Player.MaxHP + " Attack:" + Player.Attack + " Armor: " + Player.Armor + " Arrows: " + Player.Arrows + " Room: (" + levelPosX + ',' + levelPosY + ") Floor:" + floor);
        for (int i = 0; i < a.length; i++) {
            System.out.println(Arrays.toString(a[i]));
            //text+=Arrays.toString(a[i])+"<br/>";
        }
        for (int y = 0; y < roomSize; y++) {
            for (int x = 0; x < roomSize; x++) {
                if(a[y][x]==' ')
                    text+="&nbsp;";
                else
                    text+=a[y][x];
                if(x!= roomSize -1)
                    text+=",";
               // System.out.println("Y: " + y + " X: " + x + " " + a[y][x]);
            }
            text+=" <br/> ";
        }

        text+="</p></font></html>";
        //System.out.println(text);

        DungeonGUI.map.setText(text);
    }

    private static void generateFloor() {
        //Fills every level with  ' '.
        for (int a = 0; a < floorSize; a++)
            for (int b = 0; b < floorSize; b++)
                for (int c = 0; c < roomSize; c++)
                    Arrays.fill(LevelList[a][b][c], ' ');

        //determines if there should be 1 or 2 wide doors
        int door2;
        double door1 = (double) roomSize / 2;
        if (roomSize % 2 == 0)
            door2 = (int) door1 - 1;
        else
            door2 = (int) door1;

        //Fills in walls of each level
        for (int a = 0; a < floorSize; a++)
            for (int b = 0; b < floorSize; b++) {

                //fills north wall
                for (int i = 0; i < roomSize; i++)
                    if (b == 0)
                        LevelList[a][b][0][i] = '#';
                    else if (i != door1 && i != door2)
                        LevelList[a][b][0][i] = '#';

                //fills west wall
                for (int i = 0; i < roomSize; i++)
                    if (a == 0)
                        LevelList[a][b][i][0] = '#';
                    else if (i != door1 && i != door2)
                        LevelList[a][b][i][0] = '#';

                //fill south wall
                for (int i = 0; i < roomSize; i++)
                    if (b == floorSize - 1)
                        LevelList[a][b][roomSize - 1][i] = '#';
                    else if (i != door1 && i != door2)
                        LevelList[a][b][roomSize - 1][i] = '#';

                //fills east wall
                for (int i = 0; i < roomSize; i++)
                    if (a == floorSize - 1)
                        LevelList[a][b][i][roomSize - 1] = '#';
                    else if (i != door1 && i != door2)
                        LevelList[a][b][i][roomSize - 1] = '#';
            }
        //Create monster randomly
        for (int a = 0; a < floorSize; a++)
            for (int b = 0; b < floorSize; b++) {
                if (a == levelPosX && b == levelPosY) ;
                else
                    for (int w = rando.nextInt(5); w > 0; w--) {
                        LevelList[a][b][rando.nextInt(roomSize - 2) + 1][rando.nextInt(roomSize - 2) + 1] = '0';
                    }
            }
        //generates all objects such as food and arrows
        for (int a = 0; a < floorSize; a++)
            for (int b = 0; b < floorSize; b++) {
                if (rando.nextInt(5) == 0)
                    LevelList[a][b][rando.nextInt(roomSize - 2) + 1][rando.nextInt(roomSize - 2) + 1] = 'F';
                else if (rando.nextInt(5) == 0)
                    LevelList[a][b][rando.nextInt(roomSize - 2) + 1][rando.nextInt(roomSize - 2) + 1] = 'A';
                else if (rando.nextInt(5) == 0)
                    LevelList[a][b][rando.nextInt(roomSize - 2) + 1][rando.nextInt(roomSize - 2) + 1] = '>';
                else if (rando.nextInt(5) == 0)
                    LevelList[a][b][rando.nextInt(roomSize - 2) + 1][rando.nextInt(roomSize - 2) + 1] = 'D';
                else if(rando.nextInt(3) == 0);
                	LevelList[a][b][rando.nextInt(roomSize - 2) + 1][rando.nextInt(roomSize - 2) + 1] = 'T';
            }

        int w = rando.nextInt(floorSize);
        int v = rando.nextInt(floorSize);

        LevelList[w][v][rando.nextInt(roomSize - 2) + 1][rando.nextInt(roomSize - 2) + 1] = 'L';
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
        for (int y = 0; y < roomSize; y++)
            for (int x = 0; x < roomSize; x++)
                if (isInt(LevelList[levelPosX][levelPosY][y][x])) {
                    System.out.println("adding a monster");
                    //orcs.add(new Monster(floor, x, y, levelPosX, levelPosY));
                    //TODO add a way to get monster type
                    Monster_list.addMonster(LevelList[levelPosX][levelPosY][y][x], x, y);
                }
    }

    private static void upDateLevel(){

        if (LevelList[levelPosX][levelPosY][Player.posY][Player.posX] != '#') {
            LevelList[levelPosX][levelPosY][Player.posY_Old][Player.posX_Old] = ' ';

            if (LevelList[levelPosX][levelPosY][Player.posY][Player.posX] == 'F') {
                Player.HP += Player.MaxHP * 0.1;
                LevelList[levelPosX][levelPosY][Player.posY][Player.posX] = ' ';
                if (Player.HP > Player.MaxHP)
                    Player.HP = Player.MaxHP;
            } else if (LevelList[levelPosX][levelPosY][Player.posY][Player.posX] == 'L') {
                floor++;
                LevelList[levelPosX][levelPosY][Player.posY][Player.posX] = ' ';
                generateFloor();
            } else if (LevelList[levelPosX][levelPosY][Player.posY][Player.posX] == 'A') {
                Player.Attack += floor;
                LevelList[levelPosX][levelPosY][Player.posY][Player.posX] = ' ';
            } else if (LevelList[levelPosX][levelPosY][Player.posY][Player.posX] == '>') {
                Player. Arrows += 3;
                LevelList[levelPosX][levelPosY][Player.posY][Player.posX] = ' ';
            } else if (LevelList[levelPosX][levelPosY][Player.posY][Player.posX] == 'D') {
                Player.Armor++;
                LevelList[levelPosX][levelPosY][Player.posY][Player.posX] = ' ';
            }else if (LevelList[levelPosX][levelPosY][Player.posY][Player.posX] == 'T') {
                LevelList[levelPosX][levelPosY][Player.posY][Player.posX] = ' ';
                do{
                    levelPosX =rando.nextInt(floorSize);
                    levelPosX =rando.nextInt(floorSize);
                    Player.posY=rando.nextInt(roomSize - 2) + 1;
                    Player.posX=rando.nextInt(roomSize - 2) + 1;
                }while(LevelList[levelPosX][levelPosY][Player.posY][Player.posX] != ' ');
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
        LevelList[levelPosX][levelPosY][ Player.posY][ Player.posX] = '@';

        Monster_list.runMonsters();

        printLevel(LevelList[levelPosX][levelPosY]);
    }

    public static void userAction(String command) {
            if (command.equals("w")) //up
                if ( Player.posY == 0) {
                    LevelList[levelPosX][levelPosY][ Player.posY_Old][Player.posX_Old] = ' ';
                    Player.posY = roomSize - 1;
                    Player.posY_Old = Player.posY;
                    levelPosY -= 1;
                    enterRoom();
                } else
                    Player.posY--;

            else if (command.equals("s"))//down
                if (Player.posY == roomSize - 1) {
                    LevelList[levelPosX][levelPosY][Player.posY_Old][ Player.posX_Old] = ' ';
                    Player. posY = 0;
                    Player. posY_Old = Player.posY;
                    levelPosY += 1;
                    enterRoom();
                } else
                    Player. posY++;

            else if (command.equals("a"))//left
                if (Player.posX == 0) {
                    LevelList[levelPosX][levelPosY][Player.posY_Old][Player.posX_Old] = ' ';
                    Player.posX = roomSize - 1;
                    Player. posX_Old = Player.posX;
                    levelPosX -= 1;
                    enterRoom();
                } else
                    Player.posX--;

            else if (command.equals("d"))//right
                if (Player.posX == roomSize - 1) {
                    LevelList[levelPosX][levelPosY][Player.posY_Old][Player.posX_Old] = ' ';
                    Player. posX = 0;
                    Player.  posX_Old = Player.posX;
                    levelPosX += 1;
                    enterRoom();
                } else
                    Player. posX++;
            else if (command.equals("shoot")) {
                if (Player.Arrows > 0) {
                    direction = scan.nextLine();
                    if (direction.equals("d") || direction.equals("a")) {
                        int ArrowPos = Player.posX;
                        do {
                            if (direction.equals("d") && ArrowPos != roomSize - 1)
                                ArrowPos++;
                            else if (ArrowPos != 0)
                                ArrowPos--;
                            if (LevelList[levelPosX][levelPosY][Player.posY][ArrowPos] == ' ')
                                LevelList[levelPosX][levelPosY][Player.posY][ArrowPos] = '-';
                            printLevel(LevelList[levelPosX][levelPosY]);

                        } while (LevelList[levelPosX][levelPosY][ Player.posY][ArrowPos] == '-' && ArrowPos != 0 && ArrowPos != roomSize - 1);
                        Monster_list.attackMonster(Player.posY, ArrowPos);
                      //  attackMonster(posY, ArrowPos);
                    } else if (direction.equals("s") || direction.equals("w")) {

                        int ArrowPos = Player.posY;
                        do {
                            if (LevelList[levelPosX][levelPosY][ArrowPos][Player.posX] != '@')
                                LevelList[levelPosX][levelPosY][ArrowPos][Player.posX] = '|';
                            if (direction.equals("s") && ArrowPos != roomSize - 1)
                                ArrowPos++;
                            else if (ArrowPos != 0)
                                ArrowPos--;
                            printLevel(LevelList[levelPosX][levelPosY]);

                        } while (LevelList[levelPosX][levelPosY][ArrowPos][Player.posX] == ' ' || LevelList[levelPosX][levelPosY][ArrowPos][Player.posX] == '@' && ArrowPos != 0 && ArrowPos != roomSize - 1);
                        Monster_list.attackMonster(ArrowPos, Player.posX);
                    }
                    Player.Arrows--;

                    for (int a = 0; a < roomSize; a++)
                        for (int b = 0; b < roomSize; b++)
                            if (LevelList[levelPosX][levelPosY][a][b] == '-' || LevelList[levelPosX][levelPosY][a][b] == '|')
                                LevelList[levelPosX][levelPosY][a][b] = ' ';
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
                command = "q";
            }
            if(!command.equals("q"))
                upDateLevel();
    }

    public static boolean isInt(char character) {
        return (int) character >= 48 && (int) character <= 57;
    }

    static public boolean isDead() {
        return  Player.HP <= 0;
    }

    public static char[][] getRoom(){
        return LevelList[levelPosX][levelPosY];
    }

    public static void setPositionChar(int Y,int X, char looks){
        LevelList[levelPosX][levelPosY][Y][X]=looks;
    }
}
