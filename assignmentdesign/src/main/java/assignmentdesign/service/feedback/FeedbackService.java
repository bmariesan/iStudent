package assignmentdesign.service.feedback;

import assignmentdesign.dto.FeedbackDto;

import java.util.List;

/**
 * Created by ale_mi26 on 12/12/2017.
 */
public interface FeedbackService {

    FeedbackDto storeFeedback(Integer assId, FeedbackDto feedbackDto);

    List<FeedbackDto> getFeedback();
}
