package ro.ubb.service;


import org.h2.server.Service;
import ro.ubb.domain.News;
import ro.ubb.domain.Person;
import ro.ubb.repository.NewsRepository;
import ro.ubb.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andreiuta on 14/12/2017.
 */
@Service
public class NewsService {

    private NewsRepository newsRepo;
    private PersonRepository personRepo;

    public NewsService(NewsRepository newsRepo,PersonRepository personRepo) {
        this.newsRepo = newsRepo;
        this.personRepo = personRepo;
    }

    public News addNews(int Id,String authorId, String content)
    {
        Person author = personRepo.findById(authorId);
        if(author.isTeacher()) {
            News inserted = newsRepo.insert(Id,author, content);
            return inserted;
        }
        else{
            return null;
        }
    }

    public void deleteNews(int id){
        try{
            newsRepo.delete(id);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public News updateNews(int id,Person author,String content){
        try {
            News n = newsRepo.update(id, author, content);
            return n;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
