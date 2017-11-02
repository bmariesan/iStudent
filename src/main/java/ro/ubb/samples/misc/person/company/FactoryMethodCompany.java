package ro.ubb.samples.misc.person.company;

import org.thymeleaf.util.StringUtils;
import ro.ubb.samples.misc.person.base.NullPerson;
import ro.ubb.samples.misc.person.base.Person;
import ro.ubb.samples.misc.person.creational.FactoryMethodAbstr;

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
