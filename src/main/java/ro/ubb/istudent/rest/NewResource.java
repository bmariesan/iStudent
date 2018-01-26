package ro.ubb.istudent.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.istudent.domain.New;
import ro.ubb.istudent.dto.GreetingDto;
import ro.ubb.istudent.dto.NewDto;
import ro.ubb.istudent.service.GreetingService;
import ro.ubb.istudent.service.NewService;
import ro.ubb.istudent.util.ResponseUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by catablack.
 */
@RequestMapping("/api")
@RestController
public class NewResource {


    private static final String NEWS_CONTROLLER_MAPPING = "/news";
    private static final Logger LOG = LoggerFactory.getLogger(GreetingResource.class);
    private final NewService service;
    private final String baseUrl;

    public NewResource(NewService service, @Value("${application.base-url}") String baseUrl) {
        this.service = service;
        this.baseUrl = baseUrl;
    }


    @GetMapping("/news")
    public @ResponseBody
    Map<String, List<NewDto>> getHelloWorldGreeting()
    {
        Map<String, List<NewDto>> result = new HashMap<>();

        try{

            result.put("news",service.findAll().get());
            return result;
        } catch (Exception e)
        {
            //
        }
        return result;

    }

    @GetMapping("/news/{newsId}")
    public @ResponseBody
    Map<String, NewDto> getHelloWorldGreeting(@PathVariable("newsId") String newsId) {
        Map<String, NewDto> result = new HashMap<>();

        try{
            NewDto newDto = service.findNewById(newsId).get();
            result.put("new",newDto);
            return result;
        } catch (Exception e)
        {
            //
        }
        return result;
    }
    @GetMapping("/news/all")
    public @ResponseBody
    List<NewDto> GetAll(){
        try{
            return service.GetAll();
        }catch(Exception e){
            //
        }
        return null;
    }


    @PostMapping("/news")
    public ResponseEntity createGreeting(@RequestBody NewDto newDto) throws URISyntaxException {
        LOG.debug("Creating greeting with value: " + newDto);
        System.out.println("--->"+newDto.getMessage());
        NewDto savedNew = service.createNew(newDto);
        return ResponseEntity.created(new URI(baseUrl + NEWS_CONTROLLER_MAPPING + "/" + savedNew.getId())).build();
    }


}
