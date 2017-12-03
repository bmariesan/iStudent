package ro.ubb.istudent.converters;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericConverter<E, D> {

    public abstract E createFromDto(D dto);

    public abstract D createFromEntity(E entity);

    public abstract E updateEntity(E entity, D dto);

    public List<D> createFromEntities(final Collection<E> entities) {
        return entities.stream()
                .map(this::createFromEntity)
                .collect(Collectors.toList());
    }

    public List<E> createFromDtos(final Collection<D> dtos) {
        return dtos.stream()
                .map(this::createFromDto)
                .collect(Collectors.toList());
    }
}
