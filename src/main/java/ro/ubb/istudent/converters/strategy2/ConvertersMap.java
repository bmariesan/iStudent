package ro.ubb.istudent.converters.strategy2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ro.ubb.istudent.converters.*;
import ro.ubb.istudent.domain.CountryEntity;
import ro.ubb.istudent.domain.CourseEntity;
import ro.ubb.istudent.domain.StudentEntity;
import ro.ubb.istudent.domain.TestEntity;
import ro.ubb.istudent.dto.CountryDto;
import ro.ubb.istudent.dto.CourseDto;
import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.dto.TestDto;

import java.util.HashMap;
import java.util.Map;

@Component
public class ConvertersMap {

    @Autowired
    private CountryConverter countryConverter;
    @Autowired
    private CourseConverter courseConverter;
    @Autowired
    private StudentConverter studentConverter;
    @Autowired
    private TestConverter testConverter;

    @Bean
    public Map<Class, GenericConverter> getConverters(){
        Map<Class, GenericConverter> map = new HashMap<Class, GenericConverter>(){{
            put(CountryDto.class, countryConverter);
            put(CourseDto.class, courseConverter);
            put(StudentDto.class, studentConverter);
            put(TestDto.class, testConverter);
            put(CountryEntity.class, countryConverter);
            put(CourseEntity.class, courseConverter);
            put(StudentEntity.class, studentConverter);
            put(TestEntity.class, testConverter);
        }};
        return map;
    }
}