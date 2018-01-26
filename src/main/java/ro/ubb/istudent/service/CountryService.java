package ro.ubb.istudent.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.converters.CountryConverter;
import ro.ubb.istudent.dto.CountryDto;
import ro.ubb.istudent.repository.CountryRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CountryService {

    private CountryRepository repository;

    private CountryConverter countryConverter;

    public CountryDto save(CountryDto countryDto) {
        return countryConverter.createFromEntity(repository.save(countryConverter.createFromDto(countryDto)));
    }

    public Optional<CountryDto> findByCountryName(String countryName) {
        return repository.findByCountryName(countryName)
                .map(countryConverter::createFromEntity);
    }
}
