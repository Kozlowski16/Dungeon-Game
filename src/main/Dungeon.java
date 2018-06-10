package main;

import gui.DungeonGUI;
import monsters.Monster_list;

import java.util.Arrays;
import java.util.Random;

/*TODO
 * balence the game
 * more types of monsters
 * better monster randimizer
 * different size rooms
 */

public class Dungeon {
    public final int roomSize = 8;
    private final DungeonGUI GUI;
    private final Monster_list monsters;
    private final Random rando = new Random();
    private final int floorSize = 2;
    public int floor = 1;
    public int killCount;
    private Player player;
    private int levelPosX;
    private int levelPosY;
    //4d Array.... what have I done?
    private char[][][][] LevelList;

    public Dungeon() {
        LevelList = new char[floorSize][floorSize][roomSize][roomSize];
        monsters = new Monster_list(this);
        player = new Player();
        GUI = new DungeonGUI(this);
        GUI.setVisible(true);

        levelPosX = floorSize / 2;
        levelPosY = floorSize / 2;
        player.posX = roomSize / 2;
        player.posY = roomSize / 2;
        player.posX_Old = player.posX;
        player.posY_Old = player.posY;

        floor = 1;
        generateFloor();
        getRoom()[player.posY][player.posX] = '@';

        printFloor();
        printLevel(getRoom());
    }

    public static void main(String[] args) {
        new Dungeon();
        new Dungeon();
        new Dungeon();
    }

    private void reset() {
        GUI.setMenu();
        player.reset();

        levelPosX = floorSize / 2;
        levelPosY = floorSize / 2;
        player.posX = roomSize / 2;
        player.posY = roomSize / 2;
        player.posX_Old = player.posX;
        player.posY_Old = player.posY;

        floor = 1;
        generateFloor();
        getRoom()[player.posY][player.posX] = '@';

        printFloor();

        //prints room
        printLevel(getRoom());
    }

    private void end() {
        int score;
        score = killCount * floor;
        System.out.println("Game Over");
        System.out.println("monsters killed: " + killCount + ", Floor: " + floor + " Score: " + score);
        System.out.println("Thanks for playing");
        //System.exit(0);
        reset();
    }

    public void printLevel(char[][] a) {
        StringBuilder text = new StringBuilder("<html><font size=\"5\"><p style=\"font-family:'Courier New', Courier, monospace\">");
        text.append("HP:").append(player.HP).append('/').append(player.maxHP).append(" Attack:").append(player.attack).append(" Armor: ").append(player.armor).append(" Arrows: ").append(player.arrows).append(" Room: (").append(levelPosX).append(',').append(levelPosY).append(") Floor:").append(floor).append(" <br/> ");
        System.out.println("HP:" + player.HP + '/' + player.maxHP + " Attack:" + player.attack + " Armor: " + player.armor + " Arrows: " + player.arrows + " Room: (" + levelPosX + ',' + levelPosY + ") Floor:" + floor);
        for (char[] array : a) {
            System.out.println(Arrays.toString(array));
            //text+=Arrays.toString(a[i])+"<br/>";
        }
        for (int y = 0; y < roomSize; y++) {
            for (int x = 0; x < roomSize; x++) {
                if (a[y][x] == ' ')
                    text.append("&nbsp;");
                else
                    text.append(a[y][x]);
                if (x != roomSize - 1)
                    text.append(",");
                // System.out.println("Y: " + y + " X: " + x + " " + a[y][x]);
            }
            text.append(" <br/> ");
        }
        text.append("</p></font></html>");
        //System.out.println(text);

        GUI.setMapText(text.toString());
    }

    private void printFloor() {
        System.out.println();
        for (int x2 = 0; x2 < floorSize; x2++) {
            for (int y1 = 0; y1 < roomSize; y1++) {
                for (int y2 = 0; y2 < floorSize; y2++) {
                    System.out.print(Arrays.toString(LevelList[x2][y2][y1]));
                    System.out.print("  ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private void generateFloor() {
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
        for (int y = 0; y < floorSize; y++)
            for (int x = 0; x < floorSize; x++)

                //fills north wall
                for (int i = 0; i < roomSize; i++) {
                    if (y == 0 || i != door1 && i != door2)
                        LevelList[y][x][0][i] = '#';

                    //fills west wall
                    if (x == 0 || i != door1 && i != door2)
                        LevelList[y][x][i][0] = '#';

                    //fill south wall
                    if (y == floorSize - 1 || i != door1 && i != door2)
                        LevelList[y][x][roomSize - 1][i] = '#';

                    //fills east wall
                    if (x == floorSize - 1 || i != door1 && i != door2)
                        LevelList[y][x][i][roomSize - 1] = '#';

                }
        //Create monster randomly
        for (int a = 0; a < floorSize; a++)
            for (int b = 0; b < floorSize; b++) {
                if (a != levelPosX || b != levelPosY)
                    for (int w = rando.nextInt(5); w > 0; w--) {
                        LevelList[a][b][rando.nextInt(roomSize - 2) + 1][rando.nextInt(roomSize - 2) + 1] = (char) (rando.nextInt(3) + 48);
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
                else if (rando.nextInt(2) == 0)
                    LevelList[a][b][rando.nextInt(roomSize - 2) + 1][rando.nextInt(roomSize - 2) + 1] = 'T';
            }
        LevelList[rando.nextInt(floorSize)][rando.nextInt(floorSize)][rando.nextInt(roomSize - 2) + 1][rando.nextInt(roomSize - 2) + 1] = 'L';

        //creating  test objects
//        LevelList[1][0][3][4] = 'L';
//        LevelList[1][0][4][4] = 'F';
//        LevelList[1][1][2][4] = '2';
//        LevelList[1][0][4][4] = '1';

        //updates player stats
        if (floor != 1) {
            player.HP = (int) (player.HP * 1.1 + floor);
            player.maxHP = (int) (player.maxHP * 1.1 + floor);
            player.attack = (int) (player.attack * 1.1 + floor);
            player.armor = player.armor + floor / 2;
        }
        enterRoom();
    }

    private void enterRoom() {
        //clears monsters
        monsters.clear();
        //checks every spot in room and adds integers to monster array
        for (int y = 0; y < roomSize; y++)
            for (int x = 0; x < roomSize; x++)
                if (isInt(getRoom()[y][x])) {
                    System.out.println("adding a monster");
                    monsters.addMonster(getRoom()[y][x], x, y);
                }
    }

    private void upDateLevel() {

        if (getRoom()[player.posY][player.posX] != '#') {
            getRoom()[player.posY_Old][player.posX_Old] = ' ';

            switch (getRoom()[player.posY][player.posX]) {
                case 'F':
                    player.HP += player.maxHP * 0.1;
                    if (player.HP > player.maxHP)
                        player.HP = player.maxHP;
                    break;
                case 'L':
                    floor++;
                    generateFloor();
                    break;
                case 'A':
                    player.attack += floor;
                    break;
                case '>':
                    player.arrows += 3;
                    break;
                case 'D':
                    player.armor++;
                    break;
                case 'T':
                    getRoom()[player.posY][player.posX] = ' ';
                    do {
                        levelPosX = rando.nextInt(floorSize);
                        levelPosY = rando.nextInt(floorSize);
                        player.posY = rando.nextInt(roomSize - 2) + 1;
                        player.posX = rando.nextInt(roomSize - 2) + 1;
                    } while (getRoom()[player.posY][player.posX] != ' ');
                    enterRoom();
                    break;
            }
            if (isInt(getRoom()[player.posY][player.posX])) {
                monsters.attackMonster(player.posY, player.posX);
                player.posY = player.posY_Old;
                player.posX = player.posX_Old;
            }
            player.posX_Old = player.posX;
            player.posY_Old = player.posY;
        } else {
            System.out.println("You cant move into a wall");
            player.posY = player.posY_Old;
            player.posX = player.posX_Old;
        }
        //marks position of character
        getRoom()[player.posY][player.posX] = '@';

        monsters.runMonsters();
        printFloor();
        printLevel(getRoom());
        if (isDead())
            end();
    }

    public void userAction(String command) {
        char direction = ' ';
        if (command.startsWith("shoot")) {
            direction = command.charAt(5);
            command = "shoot";
        }
        switch (command) {
            case "w":
//up
                if (player.posY == 0) {
                    getRoom()[player.posY_Old][player.posX_Old] = ' ';
                    player.posY = roomSize - 1;
                    player.posY_Old = player.posY;
                    levelPosY -= 1;
                    enterRoom();
                } else
                    player.posY--;
                break;
            case "s":
//down
                if (player.posY == roomSize - 1) {
                    getRoom()[player.posY_Old][player.posX_Old] = ' ';
                    player.posY = 0;
                    player.posY_Old = player.posY;
                    levelPosY += 1;
                    enterRoom();
                } else
                    player.posY++;
                break;
            case "a":
//left
                if (player.posX == 0) {
                    getRoom()[player.posY_Old][player.posX_Old] = ' ';
                    player.posX = roomSize - 1;
                    player.posX_Old = player.posX;
                    levelPosX -= 1;
                    enterRoom();
                } else
                    player.posX--;
                break;
            case "d":
//right
                if (player.posX == roomSize - 1) {
                    getRoom()[player.posY_Old][player.posX_Old] = ' ';
                    player.posX = 0;
                    player.posX_Old = player.posX;
                    levelPosX += 1;
                    enterRoom();
                } else
                    player.posX++;
                break;
            case "shoot":
                if (player.arrows > 0) {

                    if (direction == 'd' || direction == 'a') {
                        int ArrowPos = player.posX;
                        do {
                            if (direction == 'd' && ArrowPos != roomSize - 1)
                                ArrowPos++;
                            else if (ArrowPos != 0)
                                ArrowPos--;
                            if (getRoom()[player.posY][ArrowPos] == ' ')
                                getRoom()[player.posY][ArrowPos] = '-';
                            printLevel(getRoom());

                        } while (getRoom()[player.posY][ArrowPos] == '-' && ArrowPos != 0 && ArrowPos != roomSize - 1);
                        monsters.attackMonster(player.posY, ArrowPos);
                        //  attackMonster(posY, ArrowPos);
                    } else if (direction == 's' || direction == 'w') {

                        int ArrowPos = player.posY;
                        do {
                            if (getRoom()[ArrowPos][player.posX] != '@')
                                getRoom()[ArrowPos][player.posX] = '|';
                            if (direction == 's' && ArrowPos != roomSize - 1)
                                ArrowPos++;
                            else if (ArrowPos != 0)
                                ArrowPos--;
                            printLevel(getRoom());

                        }
                        while (getRoom()[ArrowPos][player.posX] == ' ' || getRoom()[ArrowPos][player.posX] == '@' && ArrowPos != 0 && ArrowPos != roomSize - 1);
                        monsters.attackMonster(ArrowPos, player.posX);
                    }
                    player.arrows--;

                    for (int a = 0; a < roomSize; a++)
                        for (int b = 0; b < roomSize; b++)
                            if (getRoom()[a][b] == '-' || getRoom()[a][b] == '|')
                                getRoom()[a][b] = ' ';
                } else {
                    System.out.println("You need arrows to shoot.");
                }
                break;
            //cheater dev command
            case "n":
                break;
            default:
                System.out.println("please input a valid command");
                command = "q";
                break;
        }
        if (!command.equals("q"))
            upDateLevel();
    }

    private boolean isInt(char character) {
        return (int) character >= 48 && (int) character <= 57;
    }

    private boolean isDead() {
        return player.HP <= 0;
    }

    public char[][] getRoom() {
        return LevelList[levelPosY][levelPosX];
    }

    public void setPositionChar(int Y, int X, char looks) {
        getRoom()[Y][X] = looks;
    }

    public Player getPlayer() {
        return player;
    }
}
