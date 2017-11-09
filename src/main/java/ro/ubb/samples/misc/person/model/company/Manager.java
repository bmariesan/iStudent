package ro.ubb.samples.misc.person.model.company;

import ro.ubb.samples.misc.person.model.Person;

public class Manager extends Person {

    private String title;
    private int salary;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
