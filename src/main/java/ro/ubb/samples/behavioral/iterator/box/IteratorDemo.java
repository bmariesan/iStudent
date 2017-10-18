package ro.ubb.samples.behavioral.iterator.box;

class IteratorDemo {

    public static void main(String[] args) {

        IntBox intBox = new IntBox();
        for (int i = 9; i > 0; --i) {
            intBox.add(i);
        }

        BoxIterator firstItr = intBox.getIterator();
        BoxIterator secondItr = intBox.getIterator();

        for (firstItr.first(); !firstItr.isDone(); firstItr.next()) {
            System.out.print(firstItr.currentValue() + "  ");
        }


        for (
          firstItr.first(), secondItr.first();
          !firstItr.isDone();
          firstItr.next(), secondItr.next()) {

            System.out.print(firstItr.currentValue() + " " + secondItr.currentValue() + "  ");

        }
    }
}
