package ro.ubb.istudent.service;

import org.springframework.stereotype.Service;

import ro.ubb.istudent.domain.New;
import ro.ubb.istudent.dto.NewDto;
import ro.ubb.istudent.repository.NewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by catablack.
 */
@Service
public class NewService {

    private static final Logger LOG = LoggerFactory.getLogger(NewService.class);
    private NewRepository newRepository;

    public NewService(NewRepository newRepository) {
        this.newRepository = newRepository;
    }

    public Optional<NewDto> findNewById(String newId) {
        return newRepository.findNewById(newId)
                .map(this::newToNewDTO);
    }

    public Optional<List<NewDto>> findAll() {
        return Optional.of(newRepository.findAll().stream().map(this::newToNewDTO).collect(Collectors.toList()));
    }
    private NewDto newToNewDTO(New aNew) {
        NewDto newDto = new NewDto();
        newDto.setId(aNew.getId().toHexString());
        newDto.setMessage(aNew.getMessage());
        return newDto;
    }

    private New newDTOToEntity(NewDto dto) {
        New entity = new New();
        entity.setMessage(dto.getMessage());
        return entity;
    }

    public NewDto createNew(NewDto newDto) {
        return newToNewDTO(newRepository.save(newDTOToEntity(newDto)));
    }

}
