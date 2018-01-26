package ro.ubb.istudent.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.domain.GreetingEntity;
import ro.ubb.istudent.dto.GreetingDto;
import ro.ubb.istudent.repository.GreetingRepository;

import java.util.Optional;

@Service
public class GreetingService {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingService.class);
    private final GreetingRepository repository;

    public GreetingService(GreetingRepository repository) {
        this.repository = repository;
    }

    public Optional<GreetingDto> findGreetingById(String greetingId) {
        return repository.findGreetingEntityById(greetingId)
                .map(this::greetingToGreetingDTO);
    }

    public void updateGreetingWithId(String greetingId, GreetingDto request) {
        Optional<GreetingEntity> optionalGreetingEntity = repository.findGreetingEntityById(greetingId);
        if (optionalGreetingEntity.isPresent()) {
            GreetingEntity greetingEntity = optionalGreetingEntity.get();
            greetingEntity.setMessage(request.getMessage());
            repository.save(greetingEntity);
        } else {
            LOG.error("Greeting with id {} not found", greetingId);
        }
    }

    public void deleteGreetingById(String greetingId) {
        Optional<GreetingEntity> optionalGreetingEntity = repository.findGreetingEntityById(greetingId);
        if (optionalGreetingEntity.isPresent()) {
            repository.delete(optionalGreetingEntity.get());
        } else {
            LOG.error("Greeting with id {} not found", greetingId);
        }
    }

    public GreetingDto createGreeting(GreetingDto greeting) {
        return greetingToGreetingDTO(repository.save(greetingDTOToEntity(greeting)));
    }

    private GreetingDto greetingToGreetingDTO(GreetingEntity entity) {
        GreetingDto dto = new GreetingDto();
        dto.setId(entity.getId().toHexString());
        dto.setMessage(entity.getMessage());
        return dto;
    }

    private GreetingEntity greetingDTOToEntity(GreetingDto dto) {
        GreetingEntity entity = new GreetingEntity();
        entity.setMessage(dto.getMessage());
        return entity;
    }
}
