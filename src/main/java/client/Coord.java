package client;

/**
 * Created by Даша on 16.03.2017.
 */
public class Coord {
    int x;
    int y;
    Coord(int _x,int _y){
        x=_x;
        y=_y;
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
