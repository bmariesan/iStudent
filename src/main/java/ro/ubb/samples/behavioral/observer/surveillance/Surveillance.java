package ro.ubb.samples.behavioral.observer.surveillance;

class CheckList {
  // Template Method design pattern
  public void byTheNumbers() {
    localize();
    isolate();
    identify();
  }

  protected void localize() { System.out.println("   establish a perimeter"); }

  protected void isolate() { System.out.println("   isolate the grid"); }

  protected void identify() { System.out.println("   identify the source"); }
}


class Surveillance extends CheckList implements AlarmListener {
  public void alarm() {
    System.out.println("Surveillance - by the numbers:");
    byTheNumbers();
  }

  protected void isolate() { System.out.println("   train the cameras"); }
}
