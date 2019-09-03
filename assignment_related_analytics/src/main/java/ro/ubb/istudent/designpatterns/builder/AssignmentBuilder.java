package ro.ubb.istudent.designpatterns.builder;

import ro.ubb.istudent.domain.AssignmentEntity;
import ro.ubb.istudent.domain.FeedbackEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by Cristina on 1/25/2018.
 */

public interface AssignmentBuilder {

    AssignmentEntity build();

    AssignmentBuilder id(final long id);

    AssignmentBuilder feedback(final FeedbackEntity feedbackEntity);

    AssignmentBuilder date(final Date date);

    AssignmentBuilder deadline(final Date deadline);

    AssignmentBuilder description(final String description);

    AssignmentBuilder isCompleted(final Boolean isCompleted);

    AssignmentBuilder attachments(final List<Long> attachments);

}
