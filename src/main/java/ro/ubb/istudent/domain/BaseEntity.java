package ro.ubb.istudent.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
public abstract class BaseEntity implements Serializable {
    @Id
    private String id; //Added by Valer
    //private ObjectId id; <-- comment4ed by Valer

}
