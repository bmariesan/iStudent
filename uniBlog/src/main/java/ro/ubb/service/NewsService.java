package ro.ubb.service;


import org.springframework.stereotype.Service;
import ro.ubb.domain.News;
import ro.ubb.domain.Person;
import ro.ubb.repository.NewsRepository;
import ro.ubb.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tudorstanila on 14/12/2017.
 */
@Service
public class NewsService {

    private NewsRepository newsRepo;
    private PersonRepository personRepo;

    public NewsService(NewsRepository newsRepo,PersonRepository personRepo) {
        this.newsRepo = newsRepo;
        this.personRepo = personRepo;
    }

    public News addNews(String authorId, String content)
    {
        Person author = personRepo.findById(authorId);
        if(author.isTeacher()) {
            News inserted = newsRepo.insert(author, content);
            return inserted;
        }
        else{
            return null;
        }
    }
}
