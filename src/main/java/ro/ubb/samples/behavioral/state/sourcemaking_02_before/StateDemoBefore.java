package ro.ubb.samples.behavioral.state.sourcemaking_02_before;// Not good: unwieldy "case" statement

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class CeilingFanPullChainBefore {
    private int currentState;

    public CeilingFanPullChainBefore() {
        currentState = 0;
    }

    public void pull() {
        if (currentState == 0) {
            currentState = 1;
            System.out.println("low speed");
        } else if (currentState == 1) {
            currentState = 2;
            System.out.println("medium speed");
        } else if (currentState == 2) {
            currentState = 3;
            System.out.println("high speed");
        } else {
            currentState = 0;
            System.out.println("turning off");
        }
    }
}

public class StateDemoBefore {
    public static void main(String[] args) {
        CeilingFanPullChainBefore chain = new CeilingFanPullChainBefore();
        while (true) {
            System.out.print("Press ENTER");
            getLine();
            chain.pull();
        }
    }

    static String getLine() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line = in.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return line;
    }
}
