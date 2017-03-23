package client;

/**
 * Created by Даша on 16.03.2017.
 */
public class Boat {
    Coord coord = new Coord();
    private void useControl(Control c){
       switch (c.dir){
           case GO:
               coord.setY(coord.y+1);
               break;
           case LEFT:
               coord.setX(coord.x-1);
               break;
           case RIGHT:
               coord.setX(coord.x+1);
               break;
           case STOP:
               // усыпить поток лодочки
               break;
       }
    };
}
