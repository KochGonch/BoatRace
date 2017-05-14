package client;

import server.RBServer;

import java.io.Serializable;

/**
 * Created by Даша on 16.03.2017.
 */
public class Coord implements Serializable { // последнее непонятное слово надо, без него не соединяются клиент и сервер
    int x;
    int y;
    Direction dir;
    private int targetX=20;  // границы битового поля
    private int targetY=20;
    RBServer server;  // хз надо ли, взяла у Бори
    int num;
    public int number=0;
    boolean move = true;
    public boolean stop = false;

    public boolean isEnd() {
        return ((x==targetX)&&(y==targetY));
    } // ну тут вроде все очевидно

    Coord(){
        this.x=0;
        this.y=0;
        this.dir = Direction.GO;
    }
    public Coord(int _x, int _y, Direction dir){
        this.x=_x;
        this.y=_y;
        this.dir = dir;
    }  // куча всяких конструкторов
    public Coord(Coord oldcoord)
    {
        this.x=oldcoord.x;
        this.y=oldcoord.y;
        this.dir = oldcoord.dir;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Direction getDIR(){
        return dir;
    }
    public void setTX(int x) {
        this.targetX = x;
    }
    public void setTY(int y) {
        this.targetY = y;
    }
    public int getTX() {
        return targetX;
    }
    public int getTY() {
        return targetY;
    }
    public void Setcoord(int _x,int _y){
        x=_x;
        y=_y;
    }
    public void Setdir(Direction dir){
        dir=dir;
    }

    public void setX(int newx){
        this.x = newx;
    };
    public void setY(int newy){
        this.y = newy;
    };
    public void print()
    {
        System.out.println(x);
        System.out.println(y);
        System.out.println(dir);
    }


    private void move() {
        if (move) {
            setX(getX() + dir.deltaX());
            setY(getY() + dir.deltaY());
        }
    }
}
