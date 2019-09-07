package assignmentdesign.service.submittedassignment.mapper;

import assignmentdesign.domain.SubmittedAssignmentEntity;
import assignmentdesign.dto.SubmittedAssignmentDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class SubmittedAssignmentMapper extends ConfigurableMapper {

    public void configure(MapperFactory factory) {

        factory.classMap(SubmittedAssignmentEntity.class, SubmittedAssignmentDto.class)
                .byDefault()
                .register();

        factory.classMap(SubmittedAssignmentDto.class, SubmittedAssignmentEntity.class)
                .byDefault()
                .register();
    }
}
