package ro.ubb.istudent.dto;

import lombok.*;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDto implements Serializable {
    private ObjectId id;
}
