package main.model;

/**
 * Created by Vali on 10.12.2017.
 */
public class Pair<X,Y> {
    private X x;
    private Y y;

    public X getX() {
        return x;
    }

    public void setX(X x) {
        this.x = x;
    }

    public Y getY() {
        return y;
    }

    public void setY(Y y) {
        this.y = y;
    }

    public Pair(X x, Y y) {

        this.x = x;
        this.y = y;
    }
}
