package main;

import java.util.Arrays;
import java.util.Random;

public class Room {
    private char[][] room;

    Room(int a) {
        Random r = new Random();
        int x = r.nextInt(6) + 6;
        int y = r.nextInt(6) + 6;

        room = new char[y][x];
        for (char[] f : room)
            Arrays.fill(f, ' ');
        createWalls();
        room[y - (r.nextInt(3) + 3)][x - (r.nextInt(3) + 3)] = 's';
    }

    Room() {
        Random r = new Random();
        int x = r.nextInt(6) + 6;
        int y = r.nextInt(6) + 6;
        room = new char[y][x];
        for (char[] f : room)
            Arrays.fill(f, ' ');
        createWalls();

    }

    Room(int width, int height) {
        room = new char[height][width];
        for (char[] f : room)
            Arrays.fill(f, ' ');
        createWalls();
    }

    private void createWalls() {
        for (int x = 0; x < room[0].length; x++) {
            room[0][x] = '#';
            room[room.length - 1][x] = '#';
        }
        for (int y = 0; y < room.length; y++) {
            room[y][0] = '#';
            room[y][room[0].length - 1] = '#';
        }
    }

    public int getWidth() {
        return room[0].length;
    }

    public int getHeight() {
        return room.length;
    }

    public char[][] getRoom() {
        return room;
    }

    public Point[] getPoints() {
        Point[] p = new Point[(getWidth() + getHeight()) * 2 - 8];


        return p;
    }
}
