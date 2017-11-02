package ro.ubb.samples.misc.person.university;

import ro.ubb.samples.misc.person.base.Person;

import java.time.Year;

public class Student extends Person {

    private Year yearOfAdmittance;

    public Year getYearOfAdmittance() {
        return yearOfAdmittance;
    }

    public void setYearOfAdmittance(Year yearOfAdmittance) {
        this.yearOfAdmittance = yearOfAdmittance;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
