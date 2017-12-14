package ro.ubb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.dto.ProbabilityDTO;
import ro.ubb.service.SentimentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class CommentsSentimentController {
    private final SentimentService sentimentService;

    @Autowired
    public CommentsSentimentController(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }

    @GetMapping("/comment-sentiment")
    public ProbabilityDTO commentSentiment(@RequestParam Map<String, String> comments) {
        List<String> values = new ArrayList<>();
        values.addAll(comments.values());
        return sentimentService.getSentimentsFor(values).getProbability();
    }
}
