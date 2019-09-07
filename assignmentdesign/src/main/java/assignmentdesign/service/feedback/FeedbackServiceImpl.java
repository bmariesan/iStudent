package assignmentdesign.service.feedback;

import assignmentdesign.dto.FeedbackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ale_mi26 on 12/12/2017.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackObject feedbackObject;

    @Override
    public FeedbackDto storeFeedback(Integer assId, FeedbackDto feedbackDto) {
        return feedbackObject.storeFeedback(assId,feedbackDto);
    }

    @Override
    public List<FeedbackDto> getFeedback() {
        return feedbackObject.getFeedbacks();
    }
}
