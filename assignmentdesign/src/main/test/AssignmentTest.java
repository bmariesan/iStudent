import assignmentdesign.AssignmentDesignApplication;
import assignmentdesign.dto.AssignmentDto;
import assignmentdesign.service.assignment.AssignmentObject;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;



import static org.hamcrest.CoreMatchers.is;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Matchers.any;


/**
 * Created by ale_mi26 on 1/26/2018.
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AssignmentDesignApplication.class)
@Transactional
public class AssignmentTest {

    @Mock
    private AssignmentObject getAssignmentObject;

    @InjectMocks
    private AssignmentDto assignmentDto;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }


   @Autowired
   AssignmentObject assignmentObject;

    @Test
    public void testStoreAssignment(){
        AssignmentDto assignmentEntity=new AssignmentDto();
        assignmentEntity.setId(1);
        assignmentEntity.setIsCompulsory(true);
        assignmentEntity.setPoints(25);

        List<String> attachmentPaths=new ArrayList<>();
        attachmentPaths.add("src/main/java/assignmentdesign/file/attachment");
        assignmentEntity.setAttachmentPaths(attachmentPaths);
        List<String> tips=new ArrayList<>();
        tips.add("do more tests");
        assignmentEntity.setTips(tips);
        assignmentEntity.setIdFeedback(2);

        List<AssignmentDto> results=new ArrayList<>();
        results.add(this.assignmentObject.storeAssignment(assignmentEntity));

        Assert.assertEquals(1,results.size());

    }

    @Test
    public void testAddAssignment_returnsNewAssignment() {

        Mockito.when(getAssignmentObject.storeAssignment(any(AssignmentDto.class))).thenReturn(new AssignmentDto());

        AssignmentDto assignment = new AssignmentDto();

        MatcherAssert.assertThat(getAssignmentObject.storeAssignment(assignment),is(notNullValue()));

    }

    @Test
    public void testGetAll() {
        AssignmentDto assignmentEntity=new AssignmentDto();
        assignmentEntity.setId(1);
        assignmentEntity.setIsCompulsory(true);
        assignmentEntity.setPoints(25);

        List<String> attachmentPaths=new ArrayList<>();
        attachmentPaths.add("src/main/java/assignmentdesign/file/attachment");
        assignmentEntity.setAttachmentPaths(attachmentPaths);
        List<String> tips=new ArrayList<>();
        tips.add("do more tests");
        assignmentEntity.setTips(tips);
        assignmentEntity.setIdFeedback(2);

        List<AssignmentDto> results=new ArrayList<>();
        results.add(this.assignmentObject.storeAssignment(assignmentEntity));

        Assert.assertEquals(1,this.assignmentObject.getAssignments().size());
    }

}
