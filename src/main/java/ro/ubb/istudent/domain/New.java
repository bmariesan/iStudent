package ro.ubb.istudent.domain;


import com.sun.corba.se.spi.ior.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by catablack.
 */

@Document(collection = "new")
public class New implements Serializable {
    @Id
    private ObjectId id;
    private String message;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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
        New aNew = (New) o;
        return Objects.equals(id, aNew.id) &&
                Objects.equals(message, aNew.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, message);
    }
}
