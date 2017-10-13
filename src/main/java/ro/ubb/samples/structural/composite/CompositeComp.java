package ro.ubb.samples.structural.composite;

import java.util.ArrayList;
import java.util.List;

class CompositeComp implements Component {
 //Collection of child components.
 private List<Component> childComponents = new ArrayList<>();

 //Prints all components.
 public void print() {
    for (Component component : childComponents) component.print();
 }

 //Add child component
 void add(Component component) {
    childComponents.add(component);
}

 //Remove child component
 void remove(Component component) {
        childComponents.remove(component);
    }
}
