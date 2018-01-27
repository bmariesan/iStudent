package ro.ubb.samples.behavioral.state.sourcemaking_03_fsm;

// 1. Create a "wrapper" class that models the state machine
class FSM {
    // 2. states
    private State[] states = {new A(), new B(), new C()};
    // 4. transitions
    private int[][] transition = {{2,1,0}, {0,2,1}, {1,2,2}};
    // 3. current
    private int current = 0;

    private void next(int msg) {
        current = transition[current][msg];
    }

    // 5. All client requests are simply delegated to the current state object
    public void on() {
        states[current].on();
        next(0);
    }

    public void off() {
        states[current].off();
        next(1);
    }

    public void ack() {
        states[current].ack();
        next(2);
    }
}

// 6. Create a state base class that makes the concrete states interchangeable
// 7. The State base class specifies default behavior
abstract class State {
    public void on() {
        System.out.println("error");
    }

    public void off() {
        System.out.println("error");
    }

    public void ack() {
        System.out.println("error");
    }
}

class A extends State {
    public void on() {
        System.out.println("A + on  = C");
    }

    public void off() {
        System.out.println("A + off = B");
    }

    public void ack() {
        System.out.println("A + ack = A");
    }
}

class B extends State {
    public void on() {
        System.out.println("B + on  = A");
    }

    public void off() {
        System.out.println("B + off = C");
    }
}

class C extends State {
    // 8. The State derived classes only override the messages they need to
    public void on() {
        System.out.println("C + on  = B");
    }
}

public class StateDemo {
    public static void main(String[] args) {
        FSM fsm = new FSM();
        int[] msgs = {2, 1, 2, 1, 0, 2, 0, 0};
        for (int msg : msgs) {
            if (msg == 0) {
                fsm.on();
            } else if (msg == 1) {
                fsm.off();
            } else if (msg == 2) {
                fsm.ack();
            }
        }
    }
}
