package client;

/**
 * Created by Даша on 16.03.2017.
 */
public enum Direction {
    STOP,  // ну тут все ясно, мы вместе это писали
    RIGHT,
    LEFT,
    BACK,
    GO;
    public int deltaX() {  // это тоже понятно, тут то что прибавляем в зависимости от направления
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
