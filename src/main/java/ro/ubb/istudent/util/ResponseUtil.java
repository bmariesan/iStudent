package ro.ubb.istudent.util;

import org.springframework.http.ResponseEntity;
import ro.ubb.istudent.dto.BaseDto;
import ro.ubb.istudent.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class ResponseUtil {

    public static final String STATISTICS_URL = "/statistics";

    public static ResponseEntity wrapOrNotFound(Optional<?> dtoOptional) {
        return dtoOptional.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find entity by id."));

    }
}
