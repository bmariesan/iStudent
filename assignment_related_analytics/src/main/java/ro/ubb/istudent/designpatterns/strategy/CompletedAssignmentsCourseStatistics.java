package ro.ubb.istudent.designpatterns.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.istudent.domain.AssignmentEntity;
import ro.ubb.istudent.domain.CourseEntity;
import ro.ubb.istudent.dto.CourseDTO;
import ro.ubb.istudent.repository.AssignmentRepository;
import ro.ubb.istudent.repository.CourseRepository;
import ro.ubb.istudent.util.MockBuilder;

import java.util.ArrayList;
import java.util.List;

@Component("completedAssignmentsCourseStatistics")
@Transactional
public class CompletedAssignmentsCourseStatistics{

    @Autowired
    private CourseRepository courserRepo;

    public List<CourseDTO> computeStatistics() {
        courserRepo.load(MockBuilder.sharedInstace().courses);
        List<CourseDTO> result = new ArrayList<>();
        for(CourseEntity c: courserRepo.findAll())
        {
            int cnt = 0;
            for(AssignmentEntity a : c.getAssignments())
            {
                if(a.isCompleted())
                    cnt++;
            }
            result.add(new CourseDTO(c.getName(),cnt, c.getAssignments().size() - cnt));
        }

        return result;
    }
}