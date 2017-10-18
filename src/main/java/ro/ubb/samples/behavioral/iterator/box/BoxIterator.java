package ro.ubb.samples.behavioral.iterator.box;

import java.util.NoSuchElementException;

public class BoxIterator {
    private IntBox box;
    private java.util.Iterator iterator;
    private int value;

    public BoxIterator(IntBox intBox) {
        box = intBox;
    }
    public void first() {
        iterator = box.getList().iterator();
        next();
    }
    public void next() {
        try {
            value = (Integer) iterator.next();
        } catch (NoSuchElementException ex) {
            value = -1;
        }
    }
    public boolean isDone() {
        return value == -1;
    }
    public int currentValue() {
        return value;
    }
}
