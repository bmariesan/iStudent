package ro.ubb.samples.structural.adapter;

class Adapter implements ITarget {
    Adaptee adaptee = new Adaptee();

    public void methodA() {
        adaptee.methodB();
    }
}
