package ro.ubb.istudent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by catablack.
 */
@Controller
public class NewController {

//    @RequestMapping(value = "/news", method = RequestMethod.GET)
//    public String news() {
//
//        return "news";
//    }

    @RequestMapping(path = "/news", method = RequestMethod.GET)
    public @ResponseBody
    Map<String,String> sayHello() {

        Map<String,String> stringStringMap= new HashMap<>();
        stringStringMap.put("Hello","World");
        return stringStringMap;
    }

}
