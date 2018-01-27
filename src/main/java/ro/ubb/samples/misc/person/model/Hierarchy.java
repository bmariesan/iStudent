package ro.ubb.samples.misc.person.model;

import java.util.ArrayList;
import java.util.List;

public class Hierarchy extends Person {

    private List<Person> managedPeople = new ArrayList<>();
    private Person manager;

    public Hierarchy(Person person) {
        this.manager = person;
    }

    void add(Person person) {
        managedPeople.add(person);
    }

    void remove(Person person) {
        managedPeople.remove(person);
    }


    @Override
    public long getId() {
        return this.manager.getId();
    }

    @Override
    public void setId(long id) {
        this.manager.setId(id);
    }

    @Override
    public String getLastName() {
        return this.manager.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        this.manager.setLastName(lastName);
    }

    @Override
    public String getFirstName() {
        return this.manager.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        this.manager.setFirstName(firstName);
    }

    @Override
    public String toString() {
        return this.manager.toString();
    }
}
