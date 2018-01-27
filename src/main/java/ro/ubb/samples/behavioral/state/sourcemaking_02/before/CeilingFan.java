package ro.ubb.samples.behavioral.state.sourcemaking_02.before;

class CeilingFan {
 private int currentState;

 public CeilingFan() {
    currentState = 0;
}

 public void switchState() {
    if (currentState == 0) {

        currentState++;
        System.out.println("low speed");

    } else if (currentState == 1) {

        currentState++;
        System.out.println("medium speed");

    } else if (currentState == 2) {

        currentState++;
        System.out.println("high speed");

    } else {

        System.out.println("turning off");
        currentState = 0;
    }
 }
}
