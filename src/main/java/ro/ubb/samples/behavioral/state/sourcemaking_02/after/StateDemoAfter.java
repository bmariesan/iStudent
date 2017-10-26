package ro.ubb.samples.behavioral.state.sourcemaking_02.after;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StateDemoAfter {
 public static void main(String[] args) {
    CeilingFan fan = new CeilingFan();
    while (true) {
        System.out.print("Press ENTER");
        pullLine();
        fan.switchState();
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
