package ro.ubb.samples.structural.bridge;

import java.util.ArrayList;
import java.util.List;

class Client {
 public static void main(final String[] args) {
  List<Shape> shapes = new ArrayList<>();
  shapes.add(new CircleShape(1, 2, 3,
          new DrawingApi1()));
  shapes.add(new CircleShape(5, 7, 11,
          new DrawingApi2()));
  for (Shape shape : shapes) {
   shape.resizeBy(2.5);
   shape.draw();
  }
 }
}
