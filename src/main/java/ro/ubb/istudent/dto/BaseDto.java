package ro.ubb.istudent.dto;

import lombok.*;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
abstract class BaseDto implements Dto {
    private ObjectId id;
}
