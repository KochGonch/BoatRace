package client;

/**
 * Created by Даша on 16.03.2017.
 */
public enum Direction {
    STOP,
    RIGHT,
    LEFT,
    BACK,
    GO;
    public int deltaX() {
        switch (this) {
            case LEFT:
                return -1;
            case RIGHT:
                return 1;
            default:
                return 0;
        }
    }
    public int deltaY() {
        switch (this) {
            case GO:
                return 1;
            case BACK:
                return -1;
            default:
                return 0;
        }
    }
}
