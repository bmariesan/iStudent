package assignmentdesign.service.feedback.mapper;

import assignmentdesign.domain.FeedbackEntity;
import assignmentdesign.dto.FeedbackDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

/**
 * Created by ale_mi26 on 12/12/2017.
 */
@Component
public class FeedbackMapper extends ConfigurableMapper {

    public void configure(MapperFactory factory) {

        factory.classMap(FeedbackEntity.class, FeedbackDto.class)
                .byDefault()
                .register();

        factory.classMap(FeedbackDto.class, FeedbackEntity.class)
                .byDefault()
                .register();
    }
}
