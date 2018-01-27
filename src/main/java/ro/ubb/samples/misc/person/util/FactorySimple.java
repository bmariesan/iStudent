package ro.ubb.samples.misc.person.util;

import org.thymeleaf.util.StringUtils;
import ro.ubb.samples.misc.person.model.NullPerson;
import ro.ubb.samples.misc.person.model.Person;
import ro.ubb.samples.misc.person.model.university.Professor;
import ro.ubb.samples.misc.person.model.university.Secretary;
import ro.ubb.samples.misc.person.model.university.Student;

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
