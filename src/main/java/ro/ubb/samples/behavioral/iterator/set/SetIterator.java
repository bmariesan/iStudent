package ro.ubb.samples.behavioral.iterator.set;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class SetIterator {
    private IntSet set;
    private Enumeration e;
    private Integer current;

    public SetIterator(IntSet in) {
        set = in;
    }
    public void first() {
        e = set.getHashTable().keys();
        next();
    }
    public boolean isDone() {
        return current == null;
    }
    public int currentItem() {
        return current;
    }
    public void next() {
        try {
            current = (Integer) e.nextElement();
        } catch (NoSuchElementException e) {
            current = null;
        }
    }
}
