package assignmentdesign.service.assignment.mapper;

import assignmentdesign.domain.AssignmentEntity;
import assignmentdesign.dto.AssignmentDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class AssignmentMapper extends ConfigurableMapper {

    public void configure(MapperFactory factory) {

        factory.classMap(AssignmentEntity.class, AssignmentDto.class)
                .byDefault()
                .register();

        factory.classMap(AssignmentDto.class, AssignmentEntity.class)
                .byDefault()
                .register();
    }
}
