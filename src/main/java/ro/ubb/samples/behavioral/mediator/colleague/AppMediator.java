package ro.ubb.samples.behavioral.mediator.colleague;

import java.util.ArrayList;

public class AppMediator implements Mediator {
  private ArrayList<Device> devices;
  public AppMediator() { devices = new ArrayList<Device>(); }

  public void addDevice(Device device) { devices.add(device); }

  public void send(String message, Device originator) {
    for (Device device : devices) {
      if (device != originator)  device.receive(message);
    }
  }
}
