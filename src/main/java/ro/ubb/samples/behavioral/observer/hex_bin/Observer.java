package ro.ubb.samples.behavioral.observer.hex_bin;

abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
