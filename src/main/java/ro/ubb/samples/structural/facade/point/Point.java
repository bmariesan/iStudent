package ro.ubb.samples.structural.facade.point;


// 1. Desired interface: move(), rotate()
class Point {
    // 2. Design a "wrapper" class
    private PointCartesian pointCartesian;

    public Point(double x, double y) {
        pointCartesian = new PointCartesian(x, y);
    }

    public String toString() {
        return pointCartesian.toString();
    }

    // 4. Wrapper maps
    public void move(int x, int y) {
        pointCartesian.move(x, y);
    }

    public void rotate(int angle, Point o) {
        double x = pointCartesian.getX() - o.pointCartesian.getX();
        double y = pointCartesian.getY() - o.pointCartesian.getY();
        PointPolar pointPolar = new PointPolar(Math.sqrt(x * x + y * y),Math.atan2(y, x) * 180 / Math.PI);
        // 4. Wrapper maps
        pointPolar.rotate(angle);
        System.out.println("  PointPolar is " + pointPolar);
        String str = pointPolar.toString();
        int i = str.indexOf('@');
        double r = Double.parseDouble(str.substring(1, i));
        double a = Double.parseDouble(str.substring(i + 1, str.length() - 1));
        pointCartesian = new PointCartesian(r*Math.cos(a*Math.PI/180) + o.pointCartesian.getX(),
                r*Math.sin(a * Math.PI / 180) + o.pointCartesian.getY());
    }
}
