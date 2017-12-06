package ro.ubb.iStudentBlog.service;

import org.springframework.stereotype.Service;
import ro.ubb.iStudentBlog.model.Blogpiece;
import ro.ubb.iStudentBlog.repository.BlogRepository;

import java.util.List;

/**
 * Created by Cata on 12/6/2017.
 */
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public void addBlogPiece(String content, String user){
        Blogpiece blogpiece= new Blogpiece(content,user,0);
        blogRepository.save(blogpiece);
    }

    public List<Blogpiece> findAll() {
        return  blogRepository.findAll();
    }
}
