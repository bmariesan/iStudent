package ro.ubb.istudent.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.istudent.domain.CountryEntity;
import ro.ubb.istudent.dto.CountryDto;

import java.util.Collections;

@Component
public class CountryConverter extends GenericConverter<CountryEntity, CountryDto> {

    @Autowired
    private StudentConverter studentConverter;

    @Override
    public CountryEntity createFromDto(CountryDto dto) {
        CountryEntity countryEntity = CountryEntity.builder()
                .countryName(dto.getCountryName())
                .build();

        countryEntity.setId(dto.getId());

        return countryEntity;
    }

    @Override
    public CountryDto createFromEntity(CountryEntity entity) {
        CountryDto countryDto = CountryDto.builder()
                .countryName(entity.getCountryName())
                .build();

        countryDto.setId(entity.getId());

        return countryDto;
    }

    @Override
    public CountryEntity updateEntity(CountryEntity entity, CountryDto dto) {
        entity.setCountryName(dto.getCountryName());

        return entity;
    }
}
