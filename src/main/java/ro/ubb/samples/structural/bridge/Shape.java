package ro.ubb.samples.structural.bridge;

abstract class Shape {
 protected DrawingApi drawingApi;

 protected Shape(DrawingApi drawingApi) {
  this.drawingApi = drawingApi;
 }
 abstract void draw(); // low-level
 abstract void resizeBy(double percentage); // high-level
}

class CircleShape extends Shape {
    private double x, y, radius;
    public CircleShape(double x, double y, double radius,
                       DrawingApi drawingApi) {
        super(drawingApi);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    // low-level i.e. Implementation specific
    public void draw() {
        drawingApi.drawCircle(x, y, radius);
    }
    // high-level i.e. Abstraction specific
    public void resizeBy(double percentage) {
        radius *= (1.0 + percentage / 100.0);
    }
}
