package main;

public class Point {
    private int x;
    private int y;
    private int ID1;
    private int ID2;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y, int id) {
        this.x = x;
        this.y = y;
        ID1 = id;
    }

    public Point(int x, int y, int id, int id2) {
        this.x = x;
        this.y = y;
        ID1 = id;
        ID2 = id2;
    }

    public int getID() {
        return ID1;
    }

    public int getID2() {
        return ID2;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return ("(" + x + ", " + y + ")");
    }
}
