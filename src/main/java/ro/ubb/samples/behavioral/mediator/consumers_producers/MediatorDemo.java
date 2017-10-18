package ro.ubb.samples.behavioral.mediator.consumers_producers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MediatorDemo {
    public static void main( String[] args ) {
        List<Thread> producerList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press ENTER for exit");
        Mediator mediator = new Mediator();
        producerList.add(new Thread(new Producer(mediator)));
        producerList.add(new Thread(new Producer(mediator)));
        producerList.add(new Thread(new Consumer(mediator)));
        producerList.add(new Thread(new Consumer(mediator)));
        producerList.add(new Thread(new Consumer(mediator)));
        producerList.add(new Thread(new Consumer(mediator)));
        for (Thread p : producerList) {
            p.start();
        }
        boolean stop = false;
        String exit = scanner.nextLine();
        while (!stop) {
            if (exit.equals("")) {
                stop = true;
                for (Thread p : producerList) {
                    //noinspection deprecation
                    p.stop();
                }
            }
        }
    }
}
