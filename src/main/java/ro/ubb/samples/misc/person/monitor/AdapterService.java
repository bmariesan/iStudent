package ro.ubb.samples.misc.person.monitor;

import ro.ubb.samples.misc.person.base.Person;

public class AdapterService {

    Person wrapPerson(Person p) {
        return new MonitoredPerson(p);
    }
}
