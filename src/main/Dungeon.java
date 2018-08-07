package main;

import gui.DungeonGUI;
import monsters.Monster_list;

import java.util.Arrays;
import java.util.Random;



/*TODO
 * balance the game
 * more types of monsters
 * better monster randomizer
 * different size rooms
 */

public class Dungeon {
    private final DungeonGUI GUI;
    private final Monster_list monsters;
    private final Random rando = new Random();

    private int floorNumber = 1;
    public int killCount;
    private Player player;

    private Floor[] floors;

    public Dungeon() {
        floors = new Floor[25];

        monsters = new Monster_list(this);
        player = new Player();
        GUI = new DungeonGUI(this);
        GUI.setVisible(true);

    }

    private void printFloor2(char[][] flo) {

        for (char[] f : flo)
            System.out.println(Arrays.toString(f));
    }

    public Floor getFloor() {
        return floors[floorNumber];
    }
    public void newGame(){
        floorNumber = 0;
        for (int i = 0; i < 25; i++) {
            floors[i] = new Floor();
        }

        player.reset();
        player.setPosition(getFloor().start);
        System.out.println(getFloor().start);
        getFloor().printFloor();
        //player.setX(10);
        //player.setY(10);


        getMap()[player.getY()][player.getX()] = '@';

        display();
    }

    public static void main(String[] args) {
        new Dungeon();
    }

    private void end() {
        int score;
        score = killCount * floorNumber;
        System.out.println("Game Over");
        System.out.println("monsters killed: " + killCount + ", Floor: " + floorNumber + " Score: " + score);
        System.out.println("Thanks for playing");
        //System.exit(0);
        GUI.setMenu();
    }

    /*
        public void printLevel(char[][] a) {
            StringBuilder text = new StringBuilder("<html><font size=\"5\"><p style=\"font-family:'Courier New', Courier, monospace\">");
            text.append("HP:").append(player.HP).append('/').append(player.maxHP).append(" Attack:").append(player.attack).append(" Armor: ").append(player.armor).append(" Arrows: ").append(player.arrows).append(" Room: (").append(levelPosX).append(',').append(level.getY()).append(") Floor:").append(floorNumber).append(" <br/> ");
            System.out.println("HP:" + player.HP + '/' + player.maxHP + " Attack:" + player.attack + " Armor: " + player.armor + " Arrows: " + player.arrows + " Room: (" + levelPosX + ',' + level.getY() + ") Floor:" + floorNumber);
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
        */
    private void display() {
        System.out.println();
        int lowX = player.getX() - 5;
        int lowY = player.getY() - 5;
        String text = "";

        for (int y = lowY; y <= lowY + 10; y++) {
            text += Arrays.toString(Arrays.copyOfRange(getMap()[y], player.getX() - 5, player.getX() + 6));
            text += "\n";
        }
        System.out.println(text);
    }
/*
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
                if (a != levelPosX || b != level.getY())
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
        if (floorNumber != 1) {
            player.HP = (int) (player.HP * 1.1 + floorNumber);
            player.maxHP = (int) (player.maxHP * 1.1 + floorNumber);
            player.attack = (int) (player.attack * 1.1 + floorNumber);
            player.armor = player.armor + floorNumber / 2;
        }
        enterRoom();
    }
*/
/*
    private void enterRoom() {
        //clears monsters
        monsters.clear();
        //checks every spot in room and adds integers to monster array
        for (int y = 0; y < roomSize; y++)
            for (int x = 0; x < roomSize; x++)
                if (isInt(getMap()[y][x])) {
                    System.out.println("adding a monster");
                    monsters.addMonster(getMap()[y][x], x, y);
                }

    }
    */

/*
    private void upDateLevel() {

        if (getMap()[player.getY()][player.getX()] != '#') {
            getMap()[player.posY_Old][player.posX_Old] = ' ';

            switch (getMap()[player.getY()][player.getX()]) {
                case 'F':
                    player.HP += player.maxHP * 0.1;
                    if (player.HP > player.maxHP)
                        player.HP = player.maxHP;
                    break;
                case 'L':
                    floorNumber++;
                    generateFloor();
                    break;
                case 'A':
                    player.attack += floorNumber;
                    break;
                case '>':
                    player.arrows += 3;
                    break;
                case 'D':
                    player.armor++;
                    break;

                case 'T':
                    getMap()[player.getY()][player.getX()] = ' ';
                    do {
                        levelPosX = rando.nextInt(floorSize);
                        level.getY() = rando.nextInt(floorSize);
                        player.setY( rando.nextInt(roomSize - 2) + 1);
                        player.setX(rando.nextInt(roomSize - 2) + 1);
                    } while (getMap()[player.getY()][player.getX()] != ' ');
                    enterRoom();
                    break;

            }
            if (isInt(getMap()[player.getY()][player.getX()])) {
                monsters.attackMonster(player.getY(), player.getX());
                player.revert();

            }
            player.updateOld();
        } else {
            System.out.println("You cant move into a wall");
            player.revert();
        }
        //marks position of character
        getMap()[player.getY()][player.getX()] = '@';

        monsters.runMonsters();
        display();
        printFloor2(floors[floorNumber]);
        if (isDead())
            end();
    }
    */

    public void userAction2(String command) {
        System.out.println("did");
        int oldX = player.getX();
        int oldY = player.getY();
        char direction = ' ';
        if (command.startsWith("shoot")) {
            direction = command.charAt(5);
            command = "shoot";
        }
        switch (command) {
            case "w":
                player.setY(player.getY() - 1);
                break;
            case "s":
//down
                player.setY(player.getY() + 1);
                break;
            case "a":
//left
                player.setX(player.getX() - 1);
                break;
            case "d":
//right
                player.setX(player.getX() + 1);
                break;

            //cheater dev command
            case "n":
                break;
            default:
                System.out.println("please input a valid command");
                command = "q";
                break;
        }
        if (!command.equals("q")) {
            if (isInt(getMap()[player.getY()][player.getX()])) {
                monsters.attackMonster(player.getY(), player.getX());
                player.setY(oldY);
                player.setX(oldX);

            } else if (getMap()[player.getY()][player.getX()] == '#') {
                player.setY(oldY);
                player.setX(oldX);
            } else
                switch (getMap()[player.getY()][player.getX()]) {
                    case 'F':
                        player.HP += player.maxHP * 0.1;
                        if (player.HP > player.maxHP)
                            player.HP = player.maxHP;
                        break;
                    case 's':
                        increaseFloor();
                        oldX = player.getX();
                        oldY = player.getY();
                        break;
                    case 'l':
                        decreaseFloor();
                        oldX = player.getX();
                        oldY = player.getY();
                    case 'A':
                        player.attack += floorNumber;
                        break;
                    case '>':
                        player.arrows += 3;
                        break;
                    case 'D':
                        player.armor++;
                        break;
                     /*
                case 'T':
                    getMap()[player.getY()][player.getX()] = ' ';
                    do {
                        levelPosX = rando.nextInt(floorSize);
                        level.getY() = rando.nextInt(floorSize);
                        player.setY( rando.nextInt(roomSize - 2) + 1);
                        player.setX(rando.nextInt(roomSize - 2) + 1);
                    } while (getMap()[player.getY()][player.getX()] != ' ');
                    enterRoom();
                    break;
                    */
                }
            getMap()[oldY][oldX] = ' ';

            getMap()[player.getY()][player.getX()] = '@';

            monsters.runMonsters();
            display();


        }
    }

    private void increaseFloor() {
        floorNumber++;
        player.setPosition(getFloor().start);
    }

    private void decreaseFloor() {
        floorNumber--;
        player.setPosition(getFloor().exit);
    }
/*
    public void userAction(String command) {
        char direction = ' ';
        if (command.startsWith("shoot")) {
            direction = command.charAt(5);
            command = "shoot";
        }
        switch (command) {
            case "w":
//up
                if (player..getY() == 0) {
                    getMap()[player..getY()_Old][player.posX_Old] = ' ';
                    player..getY() = roomSize - 1;
                    player..getY()_Old = player..getY();
                    level.getY() -= 1;
                    enterRoom();
                } else
                    player..getY()--;
                break;
            case "s":
//down
                if (player..getY() == roomSize - 1) {
                    getMap()[player..getY()_Old][player.posX_Old] = ' ';
                    player..getY() = 0;
                    player..getY()_Old = player..getY();
                    level.getY() += 1;
                    enterRoom();
                } else
                    player..getY()++;
                break;
            case "a":
//left
                if (player.getX() == 0) {
                    getMap()[player..getY()_Old][player.posX_Old] = ' ';
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
                    getMap()[player..getY()_Old][player.posX_Old] = ' ';
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
                        int ArrowPos = player.getX();
                        do {
                            if (direction == 'd' && ArrowPos != roomSize - 1)
                                ArrowPos++;
                            else if (ArrowPos != 0)
                                ArrowPos--;
                            if (getMap()[player..getY()][ArrowPos] == ' ')
                                getMap()[player..getY()][ArrowPos] = '-';
                            printLevel(getMap());

                        } while (getMap()[player..getY()][ArrowPos] == '-' && ArrowPos != 0 && ArrowPos != roomSize - 1);
                        monsters.attackMonster(player..getY(), ArrowPos);
                        //  attackMonster(.getY(), ArrowPos);
                    } else if (direction == 's' || direction == 'w') {

                        int ArrowPos = player..getY();
                        do {
                            if (getMap()[ArrowPos][player.getX()] != '@')
                                getMap()[ArrowPos][player.getX()] = '|';
                            if (direction == 's' && ArrowPos != roomSize - 1)
                                ArrowPos++;
                            else if (ArrowPos != 0)
                                ArrowPos--;
                            printLevel(getMap());

                        }
                        while (getMap()[ArrowPos][player.getX()] == ' ' || getMap()[ArrowPos][player.getX()] == '@' && ArrowPos != 0 && ArrowPos != roomSize - 1);
                        monsters.attackMonster(ArrowPos, player.getX());
                    }
                    player.arrows--;

                    for (int a = 0; a < roomSize; a++)
                        for (int b = 0; b < roomSize; b++)
                            if (getMap()[a][b] == '-' || getMap()[a][b] == '|')
                                getMap()[a][b] = ' ';
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
    */

    private boolean isInt(char character) {
        return (int) character >= 48 && (int) character <= 57;
    }

    private boolean isDead() {
        return player.HP <= 0;
    }

    public char[][] getMap() {
        return floors[floorNumber].getMap();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setPositionChar(int Y, int X, char looks) {
        getMap()[Y][X] = looks;
    }

    public Player getPlayer() {
        return player;
    }
}




                /*
            case "shoot":
                if (player.arrows > 0) {

                    if (direction == 'd' || direction == 'a') {
                        int ArrowPos = player.posX;
                        do {
                            if (direction == 'd' && ArrowPos != roomSize - 1)
                                ArrowPos++;
                            else if (ArrowPos != 0)
                                ArrowPos--;
                            if (getMap()[player..getY()][ArrowPos] == ' ')
                                getMap()[player..getY()][ArrowPos] = '-';
                            printLevel(getMap());

                        } while (getMap()[player..getY()][ArrowPos] == '-' && ArrowPos != 0 && ArrowPos != roomSize - 1);
                        monsters.attackMonster(player..getY(), ArrowPos);
                        //  attackMonster(.getY(), ArrowPos);
                    } else if (direction == 's' || direction == 'w') {

                        int ArrowPos = player..getY();
                        do {
                            if (getMap()[ArrowPos][player.posX] != '@')
                                getMap()[ArrowPos][player.posX] = '|';
                            if (direction == 's' && ArrowPos != roomSize - 1)
                                ArrowPos++;
                            else if (ArrowPos != 0)
                                ArrowPos--;
                            printLevel(getMap());

                        }
                        while (getMap()[ArrowPos][player.posX] == ' ' || getMap()[ArrowPos][player.posX] == '@' && ArrowPos != 0 && ArrowPos != roomSize - 1);
                        monsters.attackMonster(ArrowPos, player.posX);
                    }
                    player.arrows--;

                    for (int a = 0; a < roomSize; a++)
                        for (int b = 0; b < roomSize; b++)
                            if (getMap()[a][b] == '-' || getMap()[a][b] == '|')
                                getMap()[a][b] = ' ';
                } else {
                    System.out.println("You need arrows to shoot.");
                }
                break;
                */