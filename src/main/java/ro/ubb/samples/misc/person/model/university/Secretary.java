package ro.ubb.samples.misc.person.model.university;

import ro.ubb.samples.misc.person.model.Person;

public class Secretary extends Person {

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
