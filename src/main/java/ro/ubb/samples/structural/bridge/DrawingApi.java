package ro.ubb.samples.structural.bridge;

interface DrawingApi {
 void drawCircle(double x, double y, double radius);
}

class DrawingApi1 implements DrawingApi {
 public void drawCircle(double x, double y, double radius) {
  System.out.printf("API1.circle at %f:%f radius %f\n", x, y, radius);
 }
}

class DrawingApi2 implements DrawingApi {
 public void drawCircle(double x, double y, double radius) {
  System.out.printf("API2.circle at %f:%f radius %f\n", x, y, radius);
 }
}
