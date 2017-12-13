package ro.ubb.istudent.util;

import org.springframework.http.ResponseEntity;
import ro.ubb.istudent.dto.Dto;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class ResponseUtil {
    public static ResponseEntity wrapOrNotFound(Optional<? extends Dto> dtoOptional) {
        return dtoOptional.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find entity by id."));
    }
}
