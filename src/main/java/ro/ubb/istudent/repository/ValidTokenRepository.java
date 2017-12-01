package ro.ubb.istudent.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.istudent.domain.ValidToken;

import java.util.Date;

public interface ValidTokenRepository extends MongoRepository<ValidToken, ObjectId> {

    void deleteByToken(String token);

    void deleteByExpirationDateBefore(Date expirationDate);

    ValidToken findByTokenAndExpirationDateAfter(String token, Date expirationDate);

}
