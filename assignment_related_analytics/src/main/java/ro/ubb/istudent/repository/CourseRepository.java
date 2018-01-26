package ro.ubb.istudent.repository;

import org.springframework.stereotype.Repository;
import ro.ubb.istudent.domain.CourseEntity;

import java.util.List;

/**
 * Created by dariusi on 1/26/18.
 */
@Repository
public class CourseRepository {
    private List<CourseEntity> courses;

    public List<CourseEntity> findAll(){
        return courses;
    }

    public void addCourse(CourseEntity c)
    {
        courses.add(c);
    }
}
