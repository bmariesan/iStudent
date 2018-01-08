package ro.ubb.istudent.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
@ToString
public abstract class BaseEntity implements Serializable {
    @Id
    private ObjectId id;
}