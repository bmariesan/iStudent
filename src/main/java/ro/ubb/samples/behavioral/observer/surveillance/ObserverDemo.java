package ro.ubb.samples.behavioral.observer.surveillance;

import java.util.Enumeration;
import java.util.Vector;

class SensorSystem {
  private Vector listeners = new Vector();

  public void register(AlarmListener alarmListener) {
    listeners.addElement(alarmListener);
  }

  public void soundTheAlarm() {
    for (Enumeration e = listeners.elements(); e.hasMoreElements();) {
      ((AlarmListener) e.nextElement()).alarm();
    }
  }
}

public class ObserverDemo {
  public static void main( String[] args ) {
    SensorSystem sensorSystem = new SensorSystem();
    sensorSystem.register(new Gates());
    sensorSystem.register(new Lighting());
    sensorSystem.register(new Surveillance());
    sensorSystem.soundTheAlarm();
  }
}
