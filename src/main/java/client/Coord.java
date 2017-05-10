package client;

import java.io.Serializable;

/**
 * Created by Даша on 16.03.2017.
 */
public class Coord implements Serializable {
    int x;
    int y;
    Direction dir;
    Coord(){
        this.x=0;
        this.y=0;
        this.dir = Direction.GO;
    }
    public Coord(int _x, int _y, Direction dir){
        this.x=_x;
        this.y=_y;
        this.dir = dir;
    }
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
    public void print(Coord coord)
    {
        System.out.println(coord.getX());
        System.out.println(coord.getY());
        System.out.println(coord.getDIR());
    }
}
