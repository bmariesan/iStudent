package ro.ubb.istudent.dto;

import lombok.*;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDto implements Serializable {
    //TODO is there any reason to send the id?
    private ObjectId id;
}
