package client;

import java.io.Serializable;

/**
 * Created by Даша on 16.03.2017.
 */
public class Coord implements Serializable {
    int x;
    int y;
    Coord(){
        this.x=0;
        this.y=0;
    }
    public Coord(int _x, int _y){
        this.x=_x;
        this.y=_y;
    }
    public void Setcoord(int _x,int _y){
        x=_x;
        y=_y;
    }
    public Coord(Coord oldcoord)
    {
        this.x=oldcoord.x;
        this.y=oldcoord.y;
    }
    public int getX(){
        return x;
    };
    public int getY(){
        return y;
    };
    public void setX(int newx){
        this.x = newx;
    };
    public void setY(int newy){
        this.y = newy;
    };
}
