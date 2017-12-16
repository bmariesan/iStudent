package assignmentdesign.service.assignment;

import assignmentdesign.domain.AssignmentEntity;
import assignmentdesign.dto.AssignmentDto;
import assignmentdesign.repository.AssignmentRepository;
import assignmentdesign.service.assignment.mapper.AssignmentMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class AssignmentObject {

    private static final Logger log = LoggerFactory.getLogger(AssignmentObject.class);

    private static final String OUTPUT_DIRECTORY = "src/main/java/assignmentdesign/file/attachment";

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AssignmentMapper assignmentMapper;

    public AssignmentDto storeAssignment(AssignmentDto assignment) {

        AssignmentEntity assignmentEntity = assignmentMapper.map(assignment, AssignmentEntity.class);
        AssignmentEntity storedAssignmentEntity = assignmentRepository.save(assignmentEntity);

        return assignmentMapper.map(storedAssignmentEntity, AssignmentDto.class);
    }

    public List<AssignmentDto> getAssignments() {

        List<AssignmentEntity> assignmentEntities = assignmentRepository.findAll();
        return assignmentMapper.mapAsList(assignmentEntities, AssignmentDto.class);
    }

    public String uploadAttachment(Long assignmentId, String attachmentFilePath) throws IOException {

        String filename = FilenameUtils.getName(attachmentFilePath);

        FileUtils.copyFile(FileUtils.getFile(attachmentFilePath), FileUtils.getFile(OUTPUT_DIRECTORY + "\\" + filename));

        AssignmentEntity assignmentEntity = assignmentRepository.findOne(assignmentId);
        if (assignmentEntity.getAttachmentPaths() == null) {
            assignmentEntity.setAttachmentPaths(Arrays.asList(filename));
        } else {
            assignmentEntity.getAttachmentPaths().add(filename);
        }

        assignmentRepository.save(assignmentEntity);

        return filename;
    }
}
