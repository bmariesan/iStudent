package ro.ubb.samples.misc.person;

import ro.ubb.samples.misc.person.model.Person;
import ro.ubb.samples.misc.person.service.Service;
import ro.ubb.samples.misc.person.service.ServiceManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DemoRunner {

    private static Service service = ServiceManager.getService();

    public static void main(String[] args) {
        readFromKeyboard();
    }

    private static void readFromKeyboard() {
        String s = "input";
        // Press Ctrl+C to end.
        try {
            System.out.println("Enter the type of person you want to create (Student, Secretary or Professor) ( 'q' to quit) ");

            while (!s.toLowerCase().equals("q")) {
                s = new BufferedReader(new InputStreamReader(System.in)).readLine();
                Person person = service.getPerson(s);
            }

        } catch (Exception exc) {
            System.exit(1);
        }
    }
}
