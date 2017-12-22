package ro.ubb.istudent.grading.criteria;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.istudent.grading.course.Course;
import ro.ubb.istudent.grading.course.CourseRepository;

/**
 * @author Alexandru Stoica
 */

@Service
@Immutable
public class GradingCriteriaService implements
        GradingCriteriaServiceReadProtocol,
        GradingCriteriaServiceWriteProtocol {

    @Autowired
    private final CourseRepository courseRepository;

    public GradingCriteriaService(final CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course insert(final GradingCriteria criteria, final ObjectId courseId) {
        return save(criteria, courseId);
    }

    @Override
    public Course insertWithComponentsRedistribution(
            final GradingCriteria criteria, final ObjectId courseId) {
        return new ModifyGradingCriteriaCommand(courseRepository, courseId,
                new RedistributedGradingCriteria(criteria)).execute();
    }

    @Override
    public Course delete(final ObjectId courseId) {
        return new ModifyGradingCriteriaCommand
                (courseRepository, courseId, null).execute();
    }

    @Override
    public Course update(final GradingCriteria criteria, final ObjectId courseId) {
        return save(criteria, courseId);
    }

    @Override
    public GradingCriteria getByCourseId(final ObjectId courseId) {
        return new FindGradingCriteriaCommand
                (courseRepository, courseId).execute();
    }

    private Course save(final GradingCriteria criteria, final ObjectId courseId) {
        return new ModifyGradingCriteriaCommand(courseRepository, courseId,
                new GradingCriteriaWithValidatedPercentage(criteria)).execute();
    }
}
