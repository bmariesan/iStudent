package ro.ubb.samples.behavioral.mediator.colleague;


public class Client {
  public static void main(String[] args) {
    AppMediator mediator = new AppMediator();
    Desktop desktop = new Desktop(mediator);
    Mobile mobile = new Mobile(mediator);
    mediator.addDevice(desktop);
    mediator.addDevice(mobile);
    desktop.send("Hello World");
    mobile.send("Hello");
  }
}
