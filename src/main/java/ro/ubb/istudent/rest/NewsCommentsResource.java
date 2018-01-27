package ro.ubb.istudent.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.ubb.istudent.domain.NewsComments;
import ro.ubb.istudent.dto.NewDto;
import ro.ubb.istudent.dto.NewsCommentsDto;
import ro.ubb.istudent.service.CommentsService;

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
public class NewsCommentsResource {
    private static final String NEWS_COMMENTS_CONTROLLER_MAPPING = "/news/comments";
    private static final Logger LOG = LoggerFactory.getLogger(GreetingResource.class);
    private final CommentsService service;
    private final String baseUrl;

    public NewsCommentsResource(CommentsService service,  @Value("${application.base-url}") String baseUrl) {
        this.service = service;
        this.baseUrl = baseUrl;
    }

    @GetMapping("/news/comments/{newId}")
    public @ResponseBody
    Map<String, List<NewsCommentsDto>> getNewsComments(@PathVariable("newId") String newId)
    {
        Map<String, List<NewsCommentsDto>> result = new HashMap<>();

        try{

            result.put("comments",service.findNewCommentsById(newId).get());
            return result;
        } catch (Exception e)
        {

        }
        return result;
    }

    @PostMapping("/news/comments")
    public ResponseEntity createComment(@RequestBody NewsCommentsDto newDto) throws URISyntaxException {
        LOG.debug("Creating comments  with value: " + newDto);
        newDto.setPosterName("Pop Ion");
        NewsCommentsDto savedNew = service.createComment(newDto);
        return ResponseEntity.ok().build();
    }
}
