import assignmentdesign.AssignmentDesignApplication;
import assignmentdesign.dto.AssignmentDto;
import assignmentdesign.dto.FeedbackDto;
import assignmentdesign.service.feedback.FeedbackObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AssignmentDesignApplication.class)
@Transactional
public class FeedbackTest {

    @Autowired
    FeedbackObject feedbackObject;

    @Test
    public void  testAddFeedbackToAssignment(){
        FeedbackDto feedbackDto=new FeedbackDto();
        feedbackDto.setId(25);

        AssignmentDto assignmentEntity=new AssignmentDto();
        assignmentEntity.setId(1);
        assignmentEntity.setIsCompulsory(true);
        assignmentEntity.setPoints(25);

        assignmentEntity.setIdFeedback(feedbackDto.getId());

        FeedbackDto res=feedbackObject.storeFeedback(assignmentEntity.getId(),feedbackDto);
        Assert.assertEquals(feedbackDto.getId(),res.getId());

    }

    @Test
    public void testGetAll(){
      FeedbackDto feedbackDto=new FeedbackDto();
      feedbackDto.setId(10);
      feedbackDto.setDescription("the best essay written");
      feedbackDto.setWrittenById(2);
      feedbackDto.setWrittenByType("teacher");

        AssignmentDto assignmentEntity=new AssignmentDto();
        assignmentEntity.setId(1);


      FeedbackDto res=feedbackObject.storeFeedback(assignmentEntity.getId(),feedbackDto);

      Assert.assertEquals(2,feedbackObject.getFeedbacks().size());
    }
}
