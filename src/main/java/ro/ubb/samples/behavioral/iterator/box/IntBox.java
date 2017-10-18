package ro.ubb.samples.behavioral.iterator.box;

import java.util.ArrayList;
import java.util.List;

class IntBox {
    private List<Integer> list = new ArrayList<>();

    public void add(int in) {
        list.add(in);
    }

    public BoxIterator getIterator() {
        return new BoxIterator(this);
    }

    public List<Integer> getList() {
        return list;
    }
}
