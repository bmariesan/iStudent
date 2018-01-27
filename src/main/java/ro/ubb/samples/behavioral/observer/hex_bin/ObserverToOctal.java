package ro.ubb.samples.behavioral.observer.hex_bin;

class ObserverToOctal extends Observer {
    public ObserverToOctal(Subject subject) {
        this.subject = subject;
        this.subject.add( this );
    }

    public void update() {
        System.out.print(" " + Integer.toOctalString(subject.getState()));
    }
}
