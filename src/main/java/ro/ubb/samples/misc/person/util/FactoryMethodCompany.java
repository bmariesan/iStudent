package ro.ubb.samples.misc.person.util;

import org.thymeleaf.util.StringUtils;
import ro.ubb.samples.misc.person.model.NullPerson;
import ro.ubb.samples.misc.person.model.Person;
import ro.ubb.samples.misc.person.model.company.Manager;
import ro.ubb.samples.misc.person.model.company.Worker;

public class FactoryMethodCompany extends FactoryMethodAbstr {

    @Override
    protected Person createPerson(String personType) {
        if (StringUtils.isEmpty(personType)) {
            return new NullPerson();
        }

        switch (personType.toLowerCase()) {
            case "w":
            case "work":
            case "worker":
                return new Worker();

            case "m":
            case "manager":
                return new Manager();

            default:
                return new NullPerson();
        }
    }
}
