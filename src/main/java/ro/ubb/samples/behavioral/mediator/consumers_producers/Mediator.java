package ro.ubb.samples.behavioral.mediator.consumers_producers;

// 1. The "intermediary"
class Mediator {
    // 4. The Mediator arbitrates
    private boolean slotFull = false;
    private int number;

    public synchronized void storeMessage(int num) {
        // no room for another message
        while (slotFull == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        slotFull = true;
        number = num;
        notifyAll();
    }

    public synchronized int retrieveMessage() {
        // no message to retrieve
        while (slotFull == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        slotFull = false;
        notifyAll();
        return number;
    }
}