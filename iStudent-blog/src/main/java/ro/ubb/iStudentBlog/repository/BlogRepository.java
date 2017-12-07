package ro.ubb.iStudentBlog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ro.ubb.iStudentBlog.model.Blogpiece;

import java.util.UUID;

/**
 * Created by Cata on 12/6/2017.
 */
public interface BlogRepository extends MongoRepository<Blogpiece, UUID> {
    
}
