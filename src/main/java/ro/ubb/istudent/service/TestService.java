package ro.ubb.istudent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.converters.TestConverter;
import ro.ubb.istudent.dto.TestDto;
import ro.ubb.istudent.repository.TestRepository;

@Service
@AllArgsConstructor
@Slf4j
public class TestService {

    private TestRepository repository;

    private TestConverter testConverter;

    public TestDto save(TestDto studentDto) {
        return testConverter.createFromEntity(repository.save(testConverter.createFromDto(studentDto)));
    }
}
