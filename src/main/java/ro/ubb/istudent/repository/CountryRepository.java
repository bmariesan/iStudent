package ro.ubb.istudent.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ro.ubb.istudent.domain.CountryEntity;
import ro.ubb.istudent.domain.CourseEntity;

import java.util.Optional;

public interface CountryRepository extends MongoRepository<CountryEntity, String>{ //replaced by string -ObjectId> by Valer

    Optional<CountryEntity> findByCountryName(String countryName);
}
