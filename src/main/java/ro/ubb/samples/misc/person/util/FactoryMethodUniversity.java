package ro.ubb.samples.misc.person.util;

import org.thymeleaf.util.StringUtils;
import ro.ubb.samples.misc.person.model.*;
import ro.ubb.samples.misc.person.model.university.Professor;
import ro.ubb.samples.misc.person.model.university.Secretary;
import ro.ubb.samples.misc.person.model.university.Student;

public class FactoryMethodUniversity extends FactoryMethodAbstr {

    @Override
    protected Person createPerson(String personType) {
        if (StringUtils.isEmpty(personType)) {
            return new NullPerson();
        }

        /**
         * TODO Extract this creation logic.
         */
        switch (personType.toLowerCase()) {
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
