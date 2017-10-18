package ro.ubb.samples.behavioral.observer.hex_bin;

import java.util.Scanner;

public class ObserverDemo {
    public static void main( String[] args ) {
        Subject sub = new Subject();
        // Client configures the number and type of Observers
        new HexObserver(sub);
        new OctObserver(sub);
        new BinObserver(sub);
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.print("\nEnter a number: ");
            sub.setState(scan.nextInt());
        }
    }
}
