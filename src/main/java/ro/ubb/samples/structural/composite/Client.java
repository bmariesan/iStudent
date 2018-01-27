package ro.ubb.samples.structural.composite;

public class Client {
 public static void main(String[] args) {
  //Initialize four leaves
  Leaf leaf1 = new Leaf();
  Leaf leaf2 = new Leaf();
  Leaf leaf3 = new Leaf();
  Leaf leaf4 = new Leaf();

  //Initialize three composite components
  CompositeComp compositeComp0 = new CompositeComp();
  CompositeComp compositeComp1 = new CompositeComp();
  CompositeComp compositeComp2 = new CompositeComp();

  //Composes
  compositeComp1.add(leaf1);
  compositeComp1.add(leaf2);
  compositeComp1.add(leaf3);

  compositeComp2.add(leaf4);

  compositeComp0.add(compositeComp1);
  compositeComp0.add(compositeComp2);

  //Prints the complete composite0 (with leaves).
  compositeComp0.print();
 }
}