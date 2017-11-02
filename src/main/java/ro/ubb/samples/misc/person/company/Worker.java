package ro.ubb.samples.misc.person.company;

import ro.ubb.samples.misc.person.base.Person;

class Worker extends Person {

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
