package ro.ubb.samples.misc.person.model;

import org.springframework.core.style.ToStringCreator;

public abstract class Person {

    // TODO use these later
    public static String KEY_STUDENT = "student";
    public static String KEY_SECRETARE = "secretary";
    public static String KEY_PROFESSOR = "professor";
    public static String KEY_WORKER = "worker";
    public static String KEY_MANAGER = "manager";

    private long id;
    private String lastName;
    private String firstName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("type", this.getClass().getSimpleName())
                .append("id", this.id)
                .append("lastName", this.lastName)
                .append("firstName", this.firstName)
                .toString();
    }
}
