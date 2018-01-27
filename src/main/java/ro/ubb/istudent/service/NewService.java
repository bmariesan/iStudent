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
        newDto.setTitle(aNew.getTitle());
        newDto.setTeacher(aNew.getTeacher());
        newDto.setCourse(aNew.getCourse());
        return newDto;
    }

    private New newDTOToEntity(NewDto dto) {
        New entity = new New();
        entity.setMessage(dto.getMessage());
        entity.setTitle(dto.getTitle());
        entity.setCourse(dto.getCourse()); //linie scrisa de Tudor
        entity.setTeacher(dto.getTeacher()); //si asta
        return entity;
    }

    public NewDto createNew(NewDto newDto) {
        return newToNewDTO(newRepository.save(newDTOToEntity(newDto)));
    }

    public List<NewDto> GetAll(){return newRepository.findAll().stream().map(this::newToNewDTO).collect(Collectors.toList());}

    public void Delete(String newsId){
        Optional<New> optionalNewEntity = newRepository.findNewById(newsId);
        if (optionalNewEntity.isPresent()) {
            newRepository.delete(optionalNewEntity.get());
        } else {
            LOG.error("News with id {} not found", newsId);
        }
    }

    public void updateNewsWithId(String newsId, NewDto request) {
        Optional<New> optionalNewsEntity = newRepository.findNewById(newsId);
        if (optionalNewsEntity.isPresent()) {
            optionalNewsEntity.get().setMessage(request.getMessage());
            optionalNewsEntity.get().setCourse(request.getCourse());
            optionalNewsEntity.get().setTitle(request.getTitle());
            newRepository.sa
        } else {
            LOG.error("News with id {} not found", newsId);
        }
    }
}
