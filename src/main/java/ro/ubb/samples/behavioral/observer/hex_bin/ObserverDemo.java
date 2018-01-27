package ro.ubb.samples.behavioral.observer.hex_bin;

import java.util.Scanner;

public class ObserverDemo {
    public static void main( String[] args ) {
        Subject sub = new Subject();

        new ObserverToHex(sub);
        new ObserverToOctal(sub);
        new ObserverToBinary(sub);
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.print("\nEnter a number: ");
            sub.setState(scan.nextInt());
        }
    }
}
