package ro.ubb.istudent.repository;

import org.springframework.data.repository.CrudRepository;
import ro.ubb.istudent.domain.GreetingEntity;

import java.util.Optional;

public interface GreetingRepository extends CrudRepository<GreetingEntity, Long> {

    Optional<GreetingEntity> findGreetingEntityById(Long greetingId);
}
