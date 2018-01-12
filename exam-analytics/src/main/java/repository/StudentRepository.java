package repository;

import domain.IStatistic;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<IStatistic,ObjectId>{

}
