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

class Dungeon {
    public static int roomSize = 8;
    static int floor = 1;
    static int killCount;
    private static Random rando = new Random();
    private static int levelPosX;
    private static int levelPosY;
    private static String direction;
    private static Scanner scan = new Scanner(System.in);
    private static int floorSize = 2;
    private static boolean qq;
    //4d Array.... what have I done?
    private static char[][][][] LevelList = new char[floorSize][floorSize][roomSize][roomSize];

    public static void main(String[] args) {
        DungeonGUI hello = new DungeonGUI();
        hello.setVisible(true);

        levelPosX = floorSize / 2;
        levelPosY = floorSize / 2;
        Player.posX = roomSize / 2;
        Player.posY = roomSize / 2;
        Player.posX_Old = Player.posX;
        Player.posY_Old = Player.posY;

        generateFloor();

        //prints room and player
        getRoom()[Player.posY][Player.posX] = '@';
        printLevel(getRoom());

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

    private static void printLevel(char[][] a) {
        String text = "<html><font size=\"5\"><p style=\"font-family:'Courier New', Courier, monospace\">";
        text += "HP:" + Player.HP + '/' + Player.MaxHP + " Attack:" + Player.Attack + " Armor: " + Player.Armor + " Arrows: " + Player.Arrows + " Room: (" + levelPosX + ',' + levelPosY + ") Floor:" + floor + " <br/> ";
        System.out.println("HP:" + Player.HP + '/' + Player.MaxHP + " Attack:" + Player.Attack + " Armor: " + Player.Armor + " Arrows: " + Player.Arrows + " Room: (" + levelPosX + ',' + levelPosY + ") Floor:" + floor);
        for (char[] anA : a) {
            System.out.println(Arrays.toString(anA));
            //text+=Arrays.toString(a[i])+"<br/>";
        }
        for (int y = 0; y < roomSize; y++) {
            for (int x = 0; x < roomSize; x++) {
                if (a[y][x] == ' ')
                    text += "&nbsp;";
                else
                    text += a[y][x];
                if (x != roomSize - 1)
                    text += ",";
                // System.out.println("Y: " + y + " X: " + x + " " + a[y][x]);
            }
            text += " <br/> ";
        }

        text += "</p></font></html>";
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
                if (a != levelPosX || b != levelPosY)
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
                else if (rando.nextInt(3) == 0) ;
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
                if (isInt(getRoom()[y][x])) {
                    System.out.println("adding a monster");
                    //orcs.add(new Monster(floor, x, y, levelPosX, levelPosY));
                    //TODO add a way to get monster type
                    Monster_list.addMonster(getRoom()[y][x], x, y);
                }
    }

    private static void upDateLevel() {

        if (getRoom()[Player.posY][Player.posX] != '#') {
            getRoom()[Player.posY_Old][Player.posX_Old] = ' ';

            switch (getRoom()[Player.posY][Player.posX]) {
                case 'F':
                    Player.HP += Player.MaxHP * 0.1;
                    getRoom()[Player.posY][Player.posX] = ' ';
                    if (Player.HP > Player.MaxHP)
                        Player.HP = Player.MaxHP;
                    break;
                case 'L':
                    floor++;
                    getRoom()[Player.posY][Player.posX] = ' ';
                    generateFloor();
                    break;
                case 'A':
                    Player.Attack += floor;
                    getRoom()[Player.posY][Player.posX] = ' ';
                    break;
                case '>':
                    Player.Arrows += 3;
                    getRoom()[Player.posY][Player.posX] = ' ';
                    break;
                case 'D':
                    Player.Armor++;
                    getRoom()[Player.posY][Player.posX] = ' ';
                    break;
                case 'T':
                    getRoom()[Player.posY][Player.posX] = ' ';
                    do {
                        levelPosX = rando.nextInt(floorSize);
                        levelPosX = rando.nextInt(floorSize);
                        Player.posY = rando.nextInt(roomSize - 2) + 1;
                        Player.posX = rando.nextInt(roomSize - 2) + 1;
                    } while (getRoom()[Player.posY][Player.posX] != ' ');
                    enterRoom();
                    break;
            }

            Monster_list.attackMonster(Player.posY, Player.posX);


            Player.posX_Old = Player.posX;
            Player.posY_Old = Player.posY;
        } else {
            System.out.println("You cant move into a wall");
            Player.posY = Player.posY_Old;
            Player.posX = Player.posX_Old;
        }

        //marks position of character
        getRoom()[Player.posY][Player.posX] = '@';

        Monster_list.runMonsters();

        printLevel(getRoom());
    }

    public static void userAction(String command) {
        switch (command) {
            case "w":
//up
                if (Player.posY == 0) {
                    getRoom()[Player.posY_Old][Player.posX_Old] = ' ';
                    Player.posY = roomSize - 1;
                    Player.posY_Old = Player.posY;
                    levelPosY -= 1;
                    enterRoom();
                } else
                    Player.posY--;
                break;
            case "s":
//down
                if (Player.posY == roomSize - 1) {
                    getRoom()[Player.posY_Old][Player.posX_Old] = ' ';
                    Player.posY = 0;
                    Player.posY_Old = Player.posY;
                    levelPosY += 1;
                    enterRoom();
                } else
                    Player.posY++;
                break;
            case "a":
//left
                if (Player.posX == 0) {
                    getRoom()[Player.posY_Old][Player.posX_Old] = ' ';
                    Player.posX = roomSize - 1;
                    Player.posX_Old = Player.posX;
                    levelPosX -= 1;
                    enterRoom();
                } else
                    Player.posX--;
                break;
            case "d":
//right
                if (Player.posX == roomSize - 1) {
                    getRoom()[Player.posY_Old][Player.posX_Old] = ' ';
                    Player.posX = 0;
                    Player.posX_Old = Player.posX;
                    levelPosX += 1;
                    enterRoom();
                } else
                    Player.posX++;
                break;
            case "shoot":
                if (Player.Arrows > 0) {
                    direction = scan.nextLine();
                    if (direction.equals("d") || direction.equals("a")) {
                        int ArrowPos = Player.posX;
                        do {
                            if (direction.equals("d") && ArrowPos != roomSize - 1)
                                ArrowPos++;
                            else if (ArrowPos != 0)
                                ArrowPos--;
                            if (getRoom()[Player.posY][ArrowPos] == ' ')
                                getRoom()[Player.posY][ArrowPos] = '-';
                            printLevel(getRoom());

                        } while (getRoom()[Player.posY][ArrowPos] == '-' && ArrowPos != 0 && ArrowPos != roomSize - 1);
                        Monster_list.attackMonster(Player.posY, ArrowPos);
                        //  attackMonster(posY, ArrowPos);
                    } else if (direction.equals("s") || direction.equals("w")) {

                        int ArrowPos = Player.posY;
                        do {
                            if (getRoom()[ArrowPos][Player.posX] != '@')
                                getRoom()[ArrowPos][Player.posX] = '|';
                            if (direction.equals("s") && ArrowPos != roomSize - 1)
                                ArrowPos++;
                            else if (ArrowPos != 0)
                                ArrowPos--;
                            printLevel(getRoom());

                        }
                        while (getRoom()[ArrowPos][Player.posX] == ' ' || getRoom()[ArrowPos][Player.posX] == '@' && ArrowPos != 0 && ArrowPos != roomSize - 1);
                        Monster_list.attackMonster(ArrowPos, Player.posX);
                    }
                    Player.Arrows--;

                    for (int a = 0; a < roomSize; a++)
                        for (int b = 0; b < roomSize; b++)
                            if (getRoom()[a][b] == '-' || getRoom()[a][b] == '|')
                                getRoom()[a][b] = ' ';
                } else {
                    System.out.println("You need arrows to shoot.");
                    direction = "q";
                }
                break;
            //cheater dev command
            case "n":
                break;
            case "stop":
                qq = true;
                break;
            default:
                System.out.println("please input a valid command");
                command = "q";
                break;
        }
        if (!command.equals("q"))
            upDateLevel();
    }

    public static boolean isInt(char character) {
        return (int) character >= 48 && (int) character <= 57;
    }

    private static boolean isDead() {
        return Player.HP <= 0;
    }

    public static char[][] getRoom() {
        return LevelList[levelPosX][levelPosY];
    }

    public static void setPositionChar(int Y, int X, char looks) {
        getRoom()[Y][X] = looks;
    }
}
