package ro.ubb.samples.structural.facade.point;


class Line {
    private Point o, e;
    public Line(Point ori, Point end) {
        o = ori;
        e = end;
    }

    public void  move(int x, int y) {
        o.move(x, y);
        e.move(x, y);
    }

    public void  rotate(int angle) {
        e.rotate(angle, o);
    }

    public String toString() {
        return "origin is " + o + ", end is " + e;
    }
}
