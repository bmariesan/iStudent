package assignmentdesign.repository;

import assignmentdesign.domain.FeedbackEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by ale_mi26 on 12/12/2017.
 */
public interface FeedbackRepository extends MongoRepository<FeedbackEntity, Long> {

    <S extends FeedbackEntity> S save(S entity);

    List<FeedbackEntity> findAll();
}
