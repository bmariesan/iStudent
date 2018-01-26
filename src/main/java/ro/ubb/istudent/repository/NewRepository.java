package ro.ubb.istudent.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.istudent.domain.New;

import java.util.Optional;

/**
 * Created by catablack.
 */
public interface NewRepository  extends MongoRepository<New, ObjectId> {

    Optional<New> findNewById(String newId);

}
