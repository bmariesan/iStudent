package ro.ubb.samples.misc.person.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.ubb.samples.misc.person.model.Person;

public class AdapterService {

    Person wrapPerson(Person p) {
        return new MonitoredPerson(p);
    }
}

/**
 * TODO Q: Why does it have to also extend Person?
 * TODO Q: Any familiar design patterns here?
 */
class MonitoredPerson extends Person {

    private static final Logger LOGGER = LoggerFactory.getLogger(Person.class);

    private Person wrapped;

    /**
     * TODO: Q: notice the visibility level of the constructor
     *
     * @param person
     */
    MonitoredPerson(Person person) {
        this.wrapped = person;
    }

    @Override
    public long getId() {
        LOGGER.info("Exposed id field of " + this.wrapped.toString());
        return this.wrapped.getId();
    }

    @Override
    public void setId(long id) {
        LOGGER.info("Modified id field of " + this.wrapped.toString());
        this.wrapped.setId(id);
    }

    @Override
    public String getLastName() {
        LOGGER.info("Exposed last name field of " + this.wrapped.toString());
        return this.wrapped.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        LOGGER.info("Modified last name field of " + this.wrapped.toString());
        this.wrapped.setLastName(lastName);
    }

    @Override
    public String getFirstName() {
        LOGGER.info("Exposed first name field of " + this.wrapped.toString());
        return this.wrapped.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        LOGGER.info("Modified first name field of " + this.wrapped.toString());
        this.wrapped.setFirstName(firstName);
    }

    @Override
    public String toString() {
        LOGGER.info("Exposed toString representation of " + this.wrapped.toString());
        return this.wrapped.toString();
    }
}
