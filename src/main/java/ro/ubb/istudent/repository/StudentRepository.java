package ro.ubb.istudent.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.istudent.domain.CountryEntity;
import ro.ubb.istudent.domain.StudentEntity;
import ro.ubb.istudent.enums.GenderEnum;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<StudentEntity, ObjectId> {
    Optional<StudentEntity> findByName(String name);

    Optional<List<StudentEntity>> findAllByCountry(CountryEntity countryEntity);

    Optional<List<StudentEntity>> findAllByGender(GenderEnum gender);

    Optional<List<StudentEntity>> findAllByAgeBetween(Integer minAge, Integer maxAge);

    Optional<List<StudentEntity>> findAllByAgeGreaterThanEqual(Integer age);
}
