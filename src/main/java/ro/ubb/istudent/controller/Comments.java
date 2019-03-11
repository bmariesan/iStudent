package ro.ubb.istudent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by catablack.
 */
@Controller
public class Comments {

    @RequestMapping(value="/news/comments/{Id}",method= RequestMethod.GET)
    public String news() {return "newsPage";}
}
