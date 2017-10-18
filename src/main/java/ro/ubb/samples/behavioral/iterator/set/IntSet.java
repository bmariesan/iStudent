package ro.ubb.samples.behavioral.iterator.set;

import java.util.Hashtable;

class IntSet {
    private Hashtable ht = new Hashtable();

    public void add(int in) {
        ht.put(in, "null");
    }

    public boolean isMember(int i) {
        return ht.containsKey(i);
    }

    public Hashtable getHashTable() {
        return ht;
    }

    public SetIterator createIterator() {
        return new SetIterator(this);
    }
}