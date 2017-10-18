package ro.ubb.samples.behavioral.iterator.set;

import java.util.Enumeration;

public class IteratorDemo {
  public static void main( String[] args ) {
    IntSet set = new IntSet();
    for (int i=2; i < 10; i += 2) set.add( i );
    for (int i=1; i < 9; i++)
      System.out.print( i + "-" + set.isMember( i ) + "  " );

    SetIterator it1 = set.createIterator();
    SetIterator it2 = set.createIterator();

    System.out.print( "\nIterator:    " );
    for (
      it1.first(), it2.first();
      !it1.isDone();
      it1.next(), it2.next() )
        System.out.print( it1.currentItem() + " " + it2.currentItem() + "  " );

    System.out.print( "\nEnumeration: " );
    for (
      Enumeration e = set.getHashTable().keys(); e.hasMoreElements(); )
        System.out.print( e.nextElement() + "  " );
  }
}
