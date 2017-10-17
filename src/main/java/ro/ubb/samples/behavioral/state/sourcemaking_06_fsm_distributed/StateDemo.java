package ro.ubb.samples.behavioral.state.sourcemaking_06_fsm_distributed;

// 1. The "wrapper"
class FSM {
    // 2. States array
    private State[] states  = {new A(), new B(), new C()};

    // 3. Current state
    private int currentState = 0;

    // 4. Delegation and pass the this pointer
    public void on()  {
        states[currentState].on(this);
    }

    public void off() {
        states[currentState].off(this);
    }

    public void ack() {
        states[currentState].ack(this);
    }

    public void changeState(int index) {
        currentState = index;
    }
}

// 5. The State base class
abstract class State {
    // 6. Default behavior
    public void on(FSM fsm) {
        System.out.println("error");
    }

    public void off(FSM fsm) {
        System.out.println("error");
    }

    public void ack(FSM fsm) {
        System.out.println("error");
    }
}

class A extends State {
    public void on(  FSM fsm ) {
        System.out.println("A + on  = C");
        fsm.changeState(2);
    }

    public void off(FSM fsm) {
        System.out.println("A + off = B");
        fsm.changeState(1);
    }

    public void ack(FSM fsm) {
        System.out.println("A + ack = A");
        fsm.changeState(0);
    }
}

class B extends State {
    public void on(FSM fsm) {
        System.out.println("B + on  = A");
        fsm.changeState(0);
    }

    public void off(FSM fsm) {
        System.out.println("B + off = C");
        fsm.changeState(2);
    }
}

// 7. Only override some messages
class C extends State {
    // 8. "call back to" the wrapper class
    public void on(FSM fsm) {
        System.out.println("C + on  = B");
        fsm.changeState(1);
    }
}

public class StateDemo {
    public static void main(String[] args) {
        FSM fsm  = new FSM();
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
