package ro.ubb.samples.behavioral.state.sourcemaking_02.after;

class StateHigh implements State {
    public void pull(CeilingFan wrapper) {
        wrapper.setState(new StateOff());
        System.out.println("turning off");
    }
}
