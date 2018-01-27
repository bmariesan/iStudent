package ro.ubb.istudent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.converters.CountryConverter;
import ro.ubb.istudent.converters.strategy2.ConverterStrategy;
import ro.ubb.istudent.domain.BaseEntity;
import ro.ubb.istudent.domain.CountryEntity;
import ro.ubb.istudent.dto.CountryDto;
import ro.ubb.istudent.repository.CountryRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CountryService {

    private CountryRepository repository;
    private ConverterStrategy converterStrategy;

    public CountryDto save(CountryDto countryDto) {
        return (CountryDto)converterStrategy
                .createFromEntity(repository.save((CountryEntity) converterStrategy.createFromDto(countryDto)));
    }

    public Optional<CountryDto> findByCountryName(String countryName) {
        return repository.findByCountryName(countryName)
                .map(entity -> (CountryDto) converterStrategy.createFromEntity(entity));
    }
}
