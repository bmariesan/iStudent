package ro.ubb.samples.misc.person.creational;

import org.thymeleaf.util.StringUtils;
import ro.ubb.samples.misc.person.base.NullPerson;
import ro.ubb.samples.misc.person.base.Person;
import ro.ubb.samples.misc.person.university.Professor;
import ro.ubb.samples.misc.person.university.Secretary;
import ro.ubb.samples.misc.person.university.Student;

public class FactorySimple {

    public Person getPerson(String s) {

        if (StringUtils.isEmpty(s)) {
            return new NullPerson();
        }

        switch (s.toLowerCase()) {
            case "st":
            case "student":
                return new Student();

            case "sec":
            case "secr":
            case "secretary":
                return new Secretary();

            case "t":
            case "teacher":
            case "p":
            case "prof":
            case "professor":
                return new Professor();

            default:
                return new NullPerson();
        }
    }
}
