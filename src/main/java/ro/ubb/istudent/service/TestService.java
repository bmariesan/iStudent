package ro.ubb.istudent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.converters.TestConverter;
import ro.ubb.istudent.converters.strategy2.ConverterStrategy;
import ro.ubb.istudent.domain.TestEntity;
import ro.ubb.istudent.dto.TestDto;
import ro.ubb.istudent.repository.TestRepository;

@Service
@AllArgsConstructor
@Slf4j
public class TestService {

    private TestRepository repository;
    private ConverterStrategy converterStrategy;

    public TestDto save(TestDto studentDto) {
        return (TestDto) converterStrategy
                .createFromEntity(repository.save((TestEntity) converterStrategy.createFromDto(studentDto)));
    }
}
