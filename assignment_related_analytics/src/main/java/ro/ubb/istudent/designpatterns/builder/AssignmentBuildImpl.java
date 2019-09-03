package ro.ubb.istudent.designpatterns.builder;

import ro.ubb.istudent.domain.AssignmentEntity;
import ro.ubb.istudent.domain.FeedbackEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by Cristina on 1/25/2018.
 */

public class AssignmentBuildImpl implements AssignmentBuilder {
    private AssignmentEntity assignment;

    public AssignmentBuildImpl() {
        assignment = new AssignmentEntity();
    }

    @Override
    public AssignmentEntity build() {
        return assignment;
    }

    @Override
    public AssignmentBuilder id(long id) {
        assignment.setId(id);
        return this;
    }

    @Override
    public AssignmentBuilder feedback(FeedbackEntity feedbackEntity) {
        assignment.setFeedback(feedbackEntity);
        return this;
    }

    @Override
    public AssignmentBuilder date(Date date) {
        assignment.setDate(date);
        return this;
    }

    @Override
    public AssignmentBuilder deadline(Date deadline) {
       assignment.setDeadline(deadline);
       return this;
    }

    @Override
    public AssignmentBuilder description(String description) {
        assignment.setDescription(description);
        return this;
    }

    @Override
    public AssignmentBuilder isCompleted(Boolean isCompleted) {
        assignment.setIsCompleted(isCompleted);
        return this;
    }

    @Override
    public AssignmentBuilder attachments(List<Long> attachments) {
        assignment.setAttachments(attachments);
        return this;
    }
}
