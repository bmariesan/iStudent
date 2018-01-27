package ro.ubb.samples.behavioral.state.sourcemaking_02.before;// Not good: unwieldy "case" statement

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class StateDemoBefore {
 public static void main(String[] args) {
    CeilingFan fanSwitch = new CeilingFan();
    while (true) {
       System.out.print("Press ENTER");
        pullLine();
        fanSwitch.switchState();
    }
 }

 static void pullLine() {
    try {
        new BufferedReader(
                new InputStreamReader(
                        System.in))
                .readLine();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
 }
}
