package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Floor {
    private final int width = 50;
    private final int height = 50;

    private char[][] map;

    public char[][] getMap() {
        return map;
    }

    private ArrayList<Point> points = new ArrayList<>();
    private ArrayList<Point> points2 = new ArrayList<>();

    private static Random r = new Random();
    private int IDCount = 0;
    private int roomCount = 0;
    public Point start;
    public Point exit;

    public Floor() {
        map = new char[height][width];
        int roomNumber = 10;
        start = createFirstRoom();
        for (int x = 0; x < roomNumber - 1; x++)
            createRoom();
        extraHallways();
        //trim();
        start = findStart();
        exit = generateEnd();

        fill();
    }

    public void enterfloor() {

    }

    public Point findStart() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] == 's') {
                    return new Point(x, y);
                }
            }
        }
        throw new java.lang.Error("Failed to find start");
    }

    public Point generateEnd(int count) {
        int count2 = r.nextInt(count) + 1;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] == ' ') {
                    if (count2 == 0) {
                        if (map[y][x + 1] == ' ') {
                            map[y][x + 1] = 'E';
                            return new Point(x + 1, y);
                        } else if (map[y][x - 1] == ' ') {
                            map[y][x - 1] = 'E';
                            return new Point(x - 1, y);
                        } else if (map[y + 1][x] == ' ') {
                            map[y + 1][x] = 'E';
                            return new Point(x, y + 1);
                        } else if (map[y - 1][x] == ' ') {
                            map[y - 1][x] = 'E';
                            return new Point(x, y - 1);
                        } else return generateEnd(count);
                    }
                    count2--;
                }

            }
        }
        throw new java.lang.Error("Failed to find end");
        //return null;
    }

    public Point generateEnd() {
        int count = 0;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] == ' ') {
                    count++;
                }
            }
        }
        int count2 = r.nextInt(count) + 1;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] == ' ') {
                    if (count2 == 0) {
                        if (map[y][x + 1] == ' ') {
                            map[y][x + 1] = 'E';
                            return new Point(x + 1, y);
                        } else if (map[y][x - 1] == ' ') {
                            map[y][x - 1] = 'E';
                            return new Point(x - 1, y);
                        } else if (map[y + 1][x] == ' ') {
                            map[y + 1][x] = 'E';
                            return new Point(x, y + 1);
                        } else if (map[y - 1][x] == ' ') {
                            map[y - 1][x] = 'E';
                            return new Point(x, y - 1);
                        } else return generateEnd(count);
                    }
                    count2--;
                }

            }
        }
        throw new java.lang.Error("Failed to find end");
        //return null;
    }

    public static void main(String[] args) {
        /*
        long time = System.currentTimeMillis();

        int roomNumber = 10;
        for (int x = 0; x < roomNumber; x++)
            createRoom();

        extraHallways();
        //test();
        trim();
        fill();

        printFloor();
        System.out.println(roomCount);
        System.out.println("time: " + (System.currentTimeMillis() - time));
        */
    }

    public char[][] generateFloor() {
        int roomNumber = 10;
        createFirstRoom();
        for (int x = 0; x < roomNumber - 1; x++)
            createRoom();
        extraHallways();
        trim();
        fill();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] == 's') {
                    map[0][0] = (char) (x + 1);
                    map[0][1] = (char) y;
                }
            }
        }

        return map;
    }

    private Point createFirstRoom() {
        Room room = new Room(0);
        int x1 = width / 2;
        int y1 = height / 2;
        drawRoom(room, x1, y1);
        Point[] p = new Point[(room.getWidth() + room.getHeight()) * 2 - 8];
        int position = 0;
        for (int x = 1; x < room.getWidth() - 1; x++) {
            p[position] = new Point(x + x1, y1, IDCount);
            position++;
            p[position] = new Point(x + x1, y1 + room.getHeight() - 1, IDCount);
            position++;
        }
        for (int y = 1; y < room.getHeight() - 1; y++) {
            p[position] = new Point(x1, y1 + y, IDCount);
            position++;
            p[position] = new Point(x1 + room.getWidth() - 1, y1 + y, IDCount);
            position++;
        }
        for (Point ps : p)
            points.add(ps);

        for (int y = height / 2 - room.getHeight() / 2; y < height / 2 + room.getHeight(); y++) {
            for (int x = width / 2 - room.getWidth() / 2; x < width / 2 + room.getWidth(); x++) {
                if (map[y][x] == 's') {
                    return new Point(x, y);
                }
            }
        }
        throw new java.lang.Error("Failed to find start");
    }

    private void createRoom() {
        Room room = new Room();
        int x1;
        int y1;
        boolean error = false;
        int count = 0;
        int failCount = 0;
        while (failCount <= 10) {
            do {
                error = false;
                //System.out.println("changed x and y");
                x1 = r.nextInt(width - room.getWidth() + 1);
                y1 = r.nextInt(height - room.getHeight() + 1);

                for (int y = -3; y < room.getHeight() + 3; y++) {
                    for (int x = -3; x < room.getWidth() + 3; x++) {
                        if (y1 + y < 0 || y1 + y >= height || x1 + x < 0 || x1 + x >= width) {
                            //System.out.println("out of bounds");
                            error = true;
                            break;
                        }

                        if (map[y1 + y][x1 + x] != '\u0000') {
                            error = true;
                            count++;
                            //System.out.println("fail: " + count + " x: " + x1 + " y: " + y1);
                            break;
                        }
                    }
                    if (error)
                        break;
                }

            } while (error && count <= 100);
            //System.out.println(points.size());
            Point[] p = new Point[(room.getWidth() + room.getHeight()) * 2 - 8];
            int position = 0;
            for (int x = 1; x < room.getWidth() - 1; x++) {
                p[position] = new Point(x + x1, y1, IDCount);
                position++;
                p[position] = new Point(x + x1, y1 + room.getHeight() - 1, IDCount);
                position++;
            }
            for (int y = 1; y < room.getHeight() - 1; y++) {
                p[position] = new Point(x1, y1 + y, IDCount);
                position++;
                p[position] = new Point(x1 + room.getWidth() - 1, y1 + y, IDCount);
                position++;
            }
            if (!error && points.size() == 0) {

                drawRoom(room, x1, y1);
                IDCount++;
                for (Point ps : p)
                    points.add(ps);
                roomCount++;
                break;
            } else if (!error) {

                drawRoom(room, x1, y1);
                int trys = 0;
                String mode = "error";
                Point endPoint;
                Point startPoint;
                do {
                    endPoint = p[r.nextInt(p.length)];
                    startPoint = points.get(r.nextInt(points.size()));
                    if (Math.abs(endPoint.getX() - startPoint.getX()) < 15 && Math.abs(endPoint.getY() - startPoint.getY()) < 15)
                        mode = validateHallway(startPoint, endPoint);

                    trys++;
                } while (mode.equals("error") && trys < 500);
                if (mode.equals("error")) {
                    for (int y = 0; y < room.getHeight(); y++) {
                        for (int x = 0; x < room.getWidth(); x++) {
                            map[y1 + y][x1 + x] = '\u0000';
                        }
                    }
                    //System.out.println("error failed to find hallwayPath");

                } else {
                    createHallway(startPoint, endPoint, mode);
                    IDCount++;
                    //drawRoom(room,x1,y1);
                    for (Point ps : p)
                        points.add(ps);
                    roomCount++;
                    break;
                }

            }
            failCount++;
        }
    }


    private void drawRoom(Room room, int x1, int y1) {
        for (int y = 0; y < room.getHeight(); y++) {
            for (int x = 0; x < room.getWidth(); x++) {
                map[y1 + y][x1 + x] = room.getRoom()[y][x];
            }
        }
    }

    private String validateHallway(Point start, Point end) {
        return validateHallway(start.getX(), start.getY(), end.getX(), end.getY());
    }

    private String validateHallway(int x1, int y1, int x2, int y2) {
        final int x11 = x1;
        final int y11 = y1;
        String mode = "";

        if (Math.abs(x1 - x2) < 2 && Math.abs(y1 - y2) < 2) {
            return "error";
        }
        if (x1 != x2 && y1 == y2) {
            x1 = change(x1, x2);
            while (x1 != x2) {
                if (map[y1][x1] != '\u0000' || map[y1 + 1][x1] != '\u0000' || map[y1 - 1][x1] != '\u0000') {
                    //print("X mode Failed returning error");
                    return "error";
                }
                x1 = change(x1, x2);
            }
            return "x";
        } else if (x1 == x2 && y1 != y2) {
            y1 = change(y1, y2);
            while (y1 != y2) {
                if (map[y1][x1] != '\u0000' || map[y1][x1 + 1] != '\u0000' || map[y1][x1 - 1] != '\u0000') {
                    //print("Y mode Failed returning error");
                    return "error";
                }
                y1 = change(y1, y2);
            }

            return "y";
        }
        ArrayList<String> modes = new ArrayList<>();
        modes.add("xy");
        modes.add("yx");
        while (modes.size() > 0) {
            int index = r.nextInt(modes.size());
            mode = modes.get(index);
            switch (mode) {
                case "xy":
                    boolean e = false;
                    //floorNumber[y1 + 1][x1] = '#';
                    //floorNumber[y1 - 1][x1] = '#';
                    int change = change(x1, x2) - x1;
                    do {
                        x1 = change(x1, x2);
                        if (map[y1][x1] != '\u0000' || map[y1 + 1][x1] != '\u0000' || map[y1 - 1][x1] != '\u0000') {
                            //System.out.println("failed at xy part X");
                            //System.out.println(floorNumber[y1][x1]);
                            // System.out.println(floorNumber[y1 + 1][x1]);
                            // System.out.println(floorNumber[y1 - 1][x1]);
                            e = true;
                            break;
                        }
                    } while (x1 != x2);

                    if (e)
                        break;
                    if (map[y1][x1 + change] != '\u0000' || map[y1 + 1][x1 + change] != '\u0000' || map[y1 - 1][x1 + change] != '\u0000') {
                        break;
                    }
                    y1 = change(y1, y2);
                    while (y1 != y2) {
                        if (map[y1][x1] != '\u0000' || map[y1][x1 + 1] != '\u0000' || map[y1][x1 - 1] != '\u0000') {
                            //System.out.println("failed at xy part y");
                            //System.out.println(floorNumber[y1][x1]);
                            //System.out.println(floorNumber[y1][x1+1]);
                            //System.out.println(floorNumber[y1][x1-1]);
                            e = true;
                            break;
                        }
                        y1 = change(y1, y2);
                    }
                    if (e)
                        break;

                    return mode;
                case "yx":
                    //floorNumber[y1][x1 - 1] = '#';
                    //floorNumber[y1][x1 + 1] = '#';
                    e = false;
                    change = change(y1, y2) - y1;

                    do {
                        y1 = change(y1, y2);
                        if (map[y1][x1] != '\u0000' || map[y1][x1 + 1] != '\u0000' || map[y1][x1 - 1] != '\u0000') {
                            // System.out.println("failed at YX part Y");
                            //System.out.println(floorNumber[y1][x1]);
                            // System.out.println(floorNumber[y1][x1+1]);
                            //System.out.println(floorNumber[y1][x1-1]);
                            e = true;
                            break;
                        }
                    } while (y1 != y2);

                    if (e)
                        break;
                    if (map[y1 + change][x1] != '\u0000' || map[y1 + change][x1 + 1] != '\u0000' || map[y1 + change][x1 - 1] != '\u0000') {
                        break;
                    }
                    x1 = change(x1, x2);
                    while (x1 != x2) {
                        if (map[y1][x1] != '\u0000' || map[y1 + 1][x1] != '\u0000' || map[y1 - 1][x1] != '\u0000') {
                            //System.out.println("failed at YX part X");
                            //System.out.println(floorNumber[y1][x1]);
                            // System.out.println(floorNumber[y1 + 1][x1]);
                            // System.out.println(floorNumber[y1 - 1][x1]);
                            e = true;
                            break;
                        }
                        x1 = change(x1, x2);
                    }
                    if (e)
                        break;

                    return mode;
            }
            x1 = x11;
            y1 = y11;
            modes.remove(index);
        }

        //print("no good Mode Found returning error");
        return "error";
    }

    private void createHallway(Point start, Point end, String mode) {
        createHallway(start.getX(), start.getY(), end.getX(), end.getY(), mode);
    }

    private void createHallway(int x1, int y1, int x2, int y2) {
        String mode = validateHallway(x1, y1, x2, y2);
        createHallway(x1, y1, x2, y2, mode);
    }

    private void createHallway(int x1, int y1, int x2, int y2, String mode) {

        map[y1][x1] = ' ';
        if (!mode.equals("error")) {

            switch (mode) {
                case "x":
                    map[y1 + 1][x1] = '#';
                    map[y1 - 1][x1] = '#';
                    while (x1 != x2) {
                        x1 = change(x1, x2);
                        map[y1][x1] = ' ';
                        map[y1 + 1][x1] = '#';
                        map[y1 - 1][x1] = '#';
                    }
                    break;
                case "y":
                    map[y1][x1 + 1] = '#';
                    map[y1][x1 - 1] = '#';
                    while (y1 != y2) {
                        y1 = change(y1, y2);
                        map[y1][x1] = ' ';
                        map[y1][x1 + 1] = '#';
                        map[y1][x1 - 1] = '#';
                    }
                    break;
                case "xy": {
                    int change = change(x1, x2) - x1;
                    while (x1 != x2) {
                        x1 = change(x1, x2);
                        map[y1][x1] = ' ';
                        map[y1 + 1][x1] = '#';
                        map[y1 - 1][x1] = '#';
                    }

                    points2.add(new Point(x1 + change, y1));
                    points2.add(new Point(x1, 2 * y1 - change(y1, y2)));
                    map[y1][x1 + change] = '#';
                    map[2 * y1 - change(y1, y2)][x1 + change] = '#';
                    while (y1 != y2) {
                        y1 = change(y1, y2);
                        map[y1][x1] = ' ';
                        map[y1][x1 + 1] = '#';
                        map[y1][x1 - 1] = '#';
                    }
                    break;
                }
                case "yx": {
                    int change = change(y1, y2) - y1;
                    while (y1 != y2) {
                        y1 = change(y1, y2);
                        map[y1][x1] = ' ';
                        map[y1][x1 + 1] = '#';
                        map[y1][x1 - 1] = '#';
                    }
                    points2.add(new Point(x1, y1 + change));
                    points2.add(new Point(2 * x1 - change(x1, x2), y1));
                    map[y1 + change][x1] = '#';
                    map[y1 + change][2 * x1 - change(x1, x2)] = '#';
                    while (x1 != x2) {
                        x1 = change(x1, x2);
                        map[y1][x1] = ' ';
                        map[y1 + 1][x1] = '#';
                        map[y1 - 1][x1] = '#';
                    }
                    break;
                }
            }

        }
        map[y2][x2] = ' ';
    }

    private int change(int start, int target) {
        if (start != target)
            start = start < target ? start + 1 : start - 1;
        return start;
    }

    private void extraHallways() {
        String mode;
        Point endPoint;
        Point startPoint;
        int num = 0;
        do {
            int count = 0;
            do {
                endPoint = points.get(r.nextInt(points.size()));
                startPoint = points2.get(r.nextInt(points2.size()));
                mode = validateHallway(startPoint, endPoint);

                count++;
            } while (mode.equals("error") && count < 100);
            //System.out.println(num);
            if (!mode.equals("error")) {
                createHallway(startPoint, endPoint, mode);
                //System.out.println("did Somethng 2");
            } else
                //System.out.println("failed");
                num++;
        } while (num <= 3);
        num = 0;
        do {
            int count = 0;
            do {
                endPoint = points.get(r.nextInt(points.size()));
                startPoint = points.get(r.nextInt(points.size()));
                mode = validateHallway(startPoint, endPoint);

                count++;
            } while (mode.equals("error") && count < 100);
            //System.out.println(num);
            if (!mode.equals("error")) {
                createHallway(startPoint, endPoint, mode);
                //System.out.println("did Somethng");
            } else
                //System.out.println("failed");
                num++;
        } while (num <= 10);
        //System.out.println(num);
    }

    private void fill() {
        for (int y = 0; y < map.length; y++)
            for (int x = 0; x < map[0].length; x++)
                if (map[y][x] == '\u0000')
                    map[y][x] = '#';
    }

    private void trim() {
        int max_x = 0;
        int min_x = 99999;

        int max_y = 0;
        int min_y = 99999;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++)
                if (map[y][x] != '\u0000') {
                    if (x < min_x)
                        min_x = x;
                    if (x > max_x)
                        max_x = x;
                    if (y < min_y)
                        min_y = y;
                    if (y > max_y)
                        max_y = y;
                }

        }
        ///*
        // System.out.println("min x: " + min_x);
        // System.out.println("max x: " + max_x);
        //System.out.println("min y: " + min_y);
        //System.out.println("max y: " + max_y);
        //*/
        for (int y = 0; y < height; y++)
            map[y] = Arrays.copyOfRange(map[y], min_x, max_x + 1);

        char[][] temp = new char[max_y - min_y + 1][];
        for (int i = min_y, w = 0; i <= max_y; i++, w++)
            temp[w] = map[i].clone();
        map = temp.clone();
    }

    public void printFloor() {
        for (char[] f : map)
            System.out.println(Arrays.toString(f));
    }

    private void addRoom(int width, int height, int x, int y) {
        Room room = new Room(width, height);
        for (int y1 = 0; y1 < room.getHeight(); y1++)
            for (int x1 = 0; x1 < room.getWidth(); x1++) {
                map[y1 + y][x1 + x] = room.getRoom()[y1][x1];
            }
    }

    private void drawPoint(Point p) {
        map[p.getY()][p.getX()] = '$';
    }

    private void createHallway() {
        int y1 = r.nextInt(45) + 2;
        int x1 = r.nextInt(45) + 2;

        int y2 = r.nextInt(45) + 2;
        int x2 = r.nextInt(45) + 2;
        x1 = 10;
        y1 = 10;
        x2 = x1;
        y2 = y1;
        String mode = validateHallway(x1, y1, x2, y2);
        createHallway(x1, y1, x2, y2, mode);
    }

    public void reset() {
        points.clear();
        points2.clear();
        map = new char[height][width];
        roomCount = 0;
        IDCount = 0;
    }


    public void test() {
        addRoom(5, 5, 0, 0);

        addRoom(5, 5, 0, 10);
        addRoom(5, 5, 10, 0);

        addRoom(5, 5, 20, 4);
        addRoom(5, 5, 4, 17);

        createHallway(4, 2, 10, 2);
        createHallway(2, 4, 2, 10);

        //createHallway2(12,4,20,6);
        createHallway(20, 6, 12, 4);

        //createHallway2(4,13,6,17);
        createHallway(6, 17, 4, 13);
    }
}