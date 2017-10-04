package ro.ubb.istudent.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "greeting")
public class GreetingEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GreetingEntity that = (GreetingEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, message);
    }

    @Override
    public String toString() {
        return "GreetingEntity{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
