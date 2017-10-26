package ro.ubb.samples.behavioral.state.sourcemaking_02.after;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

interface State {
    void pull(CeilingFanPullChainAfter wrapper);
}

class CeilingFanPullChainAfter {
    private State currentState;

    public CeilingFanPullChainAfter() {
        currentState = new Off();
    }

    public void set_state(State s) {
        currentState = s;
    }

    public void pull() {
        currentState.pull(this);
    }
}

class Off implements State {
    public void pull(CeilingFanPullChainAfter wrapper) {
        wrapper.set_state(new Low());
        System.out.println("low speed");
    }
}

class Low implements State {
    public void pull(CeilingFanPullChainAfter wrapper) {
        wrapper.set_state(new Medium());
        System.out.println("medium speed");
    }
}

class Medium implements State {
    public void pull(CeilingFanPullChainAfter wrapper) {
        wrapper.set_state(new High());
        System.out.println("high speed");
    }
}

class High implements State {
    public void pull(CeilingFanPullChainAfter wrapper) {
        wrapper.set_state(new Off());
        System.out.println("turning off");
    }
}

public class StateDemoAfter {
    public static void main(String[] args) {
        CeilingFanPullChainAfter chain = new CeilingFanPullChainAfter();
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
