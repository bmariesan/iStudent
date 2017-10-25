package ro.ubb.samples.behavioral.observer.hex_bin;

class ObserverToHex extends Observer {
    public ObserverToHex(Subject subject) {
        this.subject = subject;
        this.subject.add(this);
    }

    public void update() {
        System.out.print(" " + Integer.toHexString(subject.getState()));
    }
}
