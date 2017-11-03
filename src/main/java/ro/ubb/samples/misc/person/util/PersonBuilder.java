package ro.ubb.samples.misc.person.util;

import ro.ubb.samples.misc.person.model.Person;
import ro.ubb.samples.misc.person.model.university.Professor;
import ro.ubb.samples.misc.person.model.university.Secretary;
import ro.ubb.samples.misc.person.model.university.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PersonBuilder {

    private static final List<String> FIRST_NAMES = Arrays.asList("Tom", "Rick", "Harry", "Janet", "Margaret", "Anne");
    private static final List<String> LAST_NAMES = Arrays.asList("Johnson", "Smith", "Cooper");
    private static final Random random = new Random();

    private Person person;

    public PersonBuilder newStudent() {
        this.person = new Student();
        return this;
    }

    public PersonBuilder newSecretary() {
        this.person = new Secretary();
        return this;
    }

    public PersonBuilder newProfessor() {
        this.person = new Professor();
        return this;
    }

    public PersonBuilder id(long id) {
        this.person.setId(id);
        return this;
    }

    public PersonBuilder firstName(String firstName) {
        this.person.setFirstName(firstName);
        return this;
    }

    public PersonBuilder lastName(String lastName) {
        this.person.setLastName(lastName);
        return this;
    }

    public Person build() {
        return this.person;
    }

    public String getRandomFirstName() {
        return FIRST_NAMES.get(random.nextInt(FIRST_NAMES.size()));
    }

    public String getRandomLastName() {
        return LAST_NAMES.get(random.nextInt(LAST_NAMES.size()));
    }
}
