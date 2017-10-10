package ro.ubb.samples.creational.prototype;

import java.util.HashMap;
import java.util.Map;

class Factory {
    private static final Map<String, Person> prototypes = new HashMap<>();
    static {
        prototypes.put("tom", new Tom());
        prototypes.put("rick", new Rick());
        prototypes.put("harry", new Harry());
    }

    public static Person getPrototype(String type) {
        try {
            return prototypes.get(type).clone();
        } catch (NullPointerException ex) {
            System.out.println("Prototype with name: " + type + ", doesn't exist");
            return null;
        }
    }
}

public class PrototypeExample {
    public static void main(String[] args) {
        // The args can be "Tom", "Rick", "Harry", or some other name.
        // If another name is specified, the created prototype will simply be empty
        if (args.length > 0) {
            for (String type : args) {
                Person prototype = Factory.getPrototype(type);
                if (prototype != null) {
                    System.out.println(prototype);
                }
            }
        } else {
            System.out.println("Run again with arguments of command string ");
        }
    }
}