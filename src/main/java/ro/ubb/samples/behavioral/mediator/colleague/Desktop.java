package ro.ubb.samples.behavioral.mediator.colleague;

public class Desktop extends Device {
  public Desktop(Mediator m) {
    super(m);
  }

  public void receive(String message) {
    System.out.println("Colleague Received: " + message);
  }
}
