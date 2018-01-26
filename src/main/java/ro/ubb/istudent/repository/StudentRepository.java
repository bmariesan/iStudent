package ro.ubb.istudent.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.istudent.domain.CountryEntity;
import ro.ubb.istudent.domain.StudentEntity;
import ro.ubb.istudent.domain.TestEntity;
import ro.ubb.istudent.enums.GenderEnum;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<StudentEntity,String>{ //replaced by string -ObjectId> by Valer

    Optional<StudentEntity> findByName(String name);

    Optional<List<StudentEntity>> findAllByCountry(CountryEntity countryEntity);

    Optional<List<StudentEntity>> findAllByGender(GenderEnum gender);

    Optional<List<StudentEntity>> findAllByAgeBetween(Integer minAge, Integer maxAge);

    Optional<List<StudentEntity>> findAllByAgeGreaterThanEqual(Integer age);

    /*@Query(value = "{course.name:?0}")
    Optional<List<StudentEntity>> findByCourse(String name); can't with mongo*/


}
