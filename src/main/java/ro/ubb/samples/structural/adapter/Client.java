package ro.ubb.samples.structural.adapter;

/**
 * This client wants to access the Adaptee class,
 * but it cannot currently, since it must access an ITarget.
 * This should stay the same.
 */
public class Client {
    private ITarget target;

    public Client(ITarget target) {
        this.target = target;
    }

    public void makeRequest() {
        target.methodA();
    }
}
