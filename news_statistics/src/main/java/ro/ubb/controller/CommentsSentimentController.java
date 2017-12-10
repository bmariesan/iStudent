package ro.ubb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.service.SentimentService;


@RestController
public class CommentsSentimentController {
    private final SentimentService sentimentService;

    @Autowired
    public CommentsSentimentController(SentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }

    @GetMapping("/comment-sentiment")
    public String fuckYou(@RequestParam String text) {
        return sentimentService.getSentimentsFor(text).getProbability().toString();
    }
}
