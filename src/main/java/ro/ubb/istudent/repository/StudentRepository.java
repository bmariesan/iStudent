package ro.ubb.istudent.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ro.ubb.istudent.domain.CountryEntity;
import ro.ubb.istudent.domain.StudentEntity;
import ro.ubb.istudent.enums.Gender;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<StudentEntity, ObjectId> {
    Optional<StudentEntity> findByName(String name);

    Optional<List<StudentEntity>> findAllByCountry(CountryEntity countryEntity);

    Optional<List<StudentEntity>> findAllByGender(Gender gender);


}
