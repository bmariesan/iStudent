package ro.ubb.samples.behavioral.chain_of_responsibility.handler_how_not_to;

import java.util.Random;

public class DemoIncorrect {
  public static void main(String[] args) {
    Handler[] nodes = { new Handler(), new Handler(), new Handler(), new Handler() };
    for (int i = 1, j; i < 6; i++) {
      System.out.println("Operation #" + i + ":");
      j = 0;
      while (!nodes[j].execute(i)) {
        j = (j + 1) % nodes.length;
      }
      System.out.println();
    }
  }
}

class Handler {
 private static final Random RANDOM = new Random();
 private static int nextID = 1;
 private int id = nextID++;

 public boolean execute(int num) {
  if (RANDOM.nextInt(4) != 0) {
   System.out.println("   " + id + "-busy  ");
   return false;
  }
  System.out.println(id + "-handled-" + num);
  return true;
 }
}
