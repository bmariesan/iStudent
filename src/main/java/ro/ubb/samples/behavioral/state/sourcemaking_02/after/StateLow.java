package ro.ubb.samples.behavioral.state.sourcemaking_02.after;

class StateLow implements State {
    public void pull(CeilingFan wrapper) {
        wrapper.setState(new StateMedium());
        System.out.println("medium speed");
    }
}
