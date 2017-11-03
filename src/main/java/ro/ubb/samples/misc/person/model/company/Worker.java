package ro.ubb.samples.misc.person.model.company;

import ro.ubb.samples.misc.person.model.Person;

public class Worker extends Person {

    private int salary;

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
