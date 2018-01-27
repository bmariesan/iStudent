package ro.ubb.samples.misc.person.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.ubb.samples.misc.person.model.NullPerson;
import ro.ubb.samples.misc.person.model.Person;

public abstract class FactoryMethodAbstr {
    private static final Logger LOGGER = LoggerFactory.getLogger(FactoryMethodAbstr.class);

    public Person getPerson(String personType) {
        Person created = createPerson(personType);
        if (created instanceof NullPerson) {
            LOGGER.warn("Another null Person was created! Make your code better for the user!");
            return null;
        }
        return created;
    }

    protected abstract Person createPerson(String personType);
}
