package ro.ubb.samples.misc.person.service;

import ro.ubb.samples.misc.person.model.NullPerson;
import ro.ubb.samples.misc.person.model.Person;
import ro.ubb.samples.misc.person.util.FactoryMethodCompany;
import ro.ubb.samples.misc.person.util.FactoryMethodAbstr;
import ro.ubb.samples.misc.person.util.FactorySimple;
import ro.ubb.samples.misc.person.util.FactoryMethodUniversity;

public class ServiceImpl implements Service {

    @Override
    public Person getPerson(String personType) {
        Person person = new NullPerson();

        person = createViaSimpleFactory(personType);
//                person = createViaFactoryMethod(s);
//                person = createViaAbstractFactoryMethod(s);

        System.out.println("Created: " + person.toString());

        /**
         * TODO: Client request: monitor me the person that comes here! But do it with no changes in the current implementation!
         */
        return person;
    }


    private static Person createViaSimpleFactory(String s) {
        FactorySimple factorySimple = new FactorySimple();
        return factorySimple.getPerson(s);
    }

    private static Person createViaFactoryMethod(String personType) {
        FactoryMethodAbstr factoryMethod = new FactoryMethodUniversity();
        return factoryMethod.getPerson(personType);
    }

    private static Person createViaAbstractFactoryMethod(String personType) {
        FactoryMethodAbstr factoryMethodAbstr = getAbstractFactoryMethod(personType);
        return factoryMethodAbstr.getPerson(personType);
    }

    /**
     * Realistically, this could happen when the app is given an argument for the switch cases,
     * such as university or company.
     */
    private static FactoryMethodAbstr getAbstractFactoryMethod(String arg) {
        switch (arg.toLowerCase()) {
            case "m":
            case "manager":
            case "w":
            case "worker":
                return new FactoryMethodCompany();
            case "s":
            case "student":
            case "secretary":
            case "t":
            case "teacher":
            case "professor":
            default:
                return new FactoryMethodUniversity();
        }
    }
}
