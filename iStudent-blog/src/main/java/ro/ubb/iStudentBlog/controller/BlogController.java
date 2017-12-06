package ro.ubb.iStudentBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.iStudentBlog.model.Blogpiece;
import ro.ubb.iStudentBlog.service.BlogService;

import java.util.List;

/**
 * Created by Cata on 12/6/2017.
 */
@RestController
@RequestMapping("/blogpiece")
public class BlogController {

    @Autowired
    BlogService blogService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Blogpiece> getAll(){
        return blogService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createPiece(String content, String user){

    }
}
