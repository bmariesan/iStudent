package ro.ubb.istudent.converters.strategy2;

import org.springframework.stereotype.Component;
import ro.ubb.istudent.converters.GenericConverter;
import ro.ubb.istudent.domain.BaseEntity;
import ro.ubb.istudent.dto.BaseDto;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ConverterStrategy {

    private Map<Class, GenericConverter> map;

    private ConverterStrategy(Map<Class, GenericConverter> map){
        this.map = map;
    }

    public BaseEntity createFromDto(BaseDto dto) {
        try {
            return (BaseEntity) map.get(dto.getClass()).createFromDto(dto);
        } catch (Throwable t){
            return null;
        }
    }

    public BaseDto createFromEntity(BaseEntity entity) {
        try {
            return (BaseDto) map.get(entity.getClass()).createFromEntity(entity);
        } catch (Throwable t){
            return null;
        }
    }

    public BaseEntity updateEntity(BaseEntity entity, BaseDto dto) {
        try {
            return (BaseEntity) map.get(entity.getClass()).updateEntity(entity, dto);
        } catch (Throwable t){
            return null;
        }
    }

    public List<? extends BaseDto> createFromEntities(final Collection<? extends BaseEntity> entities) {
        return entities.stream()
                .map(this::createFromEntity)
                .collect(Collectors.toList());
    }

    public List<? extends BaseEntity> createFromDtos(final Collection<? extends BaseDto> dtos) {
        return dtos.stream()
                .map(this::createFromDto)
                .collect(Collectors.toList());
    }
}
