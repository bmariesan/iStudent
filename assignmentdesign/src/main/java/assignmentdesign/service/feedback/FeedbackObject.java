package assignmentdesign.service.feedback;

import assignmentdesign.domain.AssignmentEntity;
import assignmentdesign.domain.FeedbackEntity;
import assignmentdesign.dto.FeedbackDto;
import assignmentdesign.repository.AssignmentRepository;
import assignmentdesign.repository.FeedbackRepository;
import assignmentdesign.service.feedback.mapper.FeedbackMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ale_mi26 on 12/12/2017.
 */
@Component
public class FeedbackObject {
    private static final Logger log = LoggerFactory.getLogger(FeedbackObject.class);

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private FeedbackMapper feedbackMapper;

    public FeedbackDto storeFeedback(Integer assId, FeedbackDto feedbackDto) {

        FeedbackEntity feedbackEntity= feedbackMapper.map(feedbackDto, FeedbackEntity.class);
        FeedbackEntity storedFeedbackEntity = feedbackRepository.save(feedbackEntity);
        AssignmentEntity assignmentEntity=assignmentRepository.findOne(Long.valueOf(assId));

        assignmentEntity.setIdFeedback(storedFeedbackEntity.getId());
        assignmentRepository.save(assignmentEntity);

        return feedbackMapper.map(storedFeedbackEntity, FeedbackDto.class);
    }

    public List<FeedbackDto> getFeedbacks() {

        List<FeedbackEntity> feedbackEntities= feedbackRepository.findAll();
        return feedbackMapper.mapAsList(feedbackEntities, FeedbackDto.class);
    }
}
