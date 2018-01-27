package ro.ubb.istudent.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ro.ubb.istudent.domain.New;
import ro.ubb.istudent.domain.NewsComments;

import java.util.List;
import java.util.Optional;

/**
 * Created by catablack.
 */
public interface CommentsRepository  extends MongoRepository<NewsComments, ObjectId> {

    @Query("{ 'newsId' : ?0 }")
    Optional<List<NewsComments>> findNewBynewsId(String newId);
}
