package ro.ubb.repository;

import ro.ubb.domain.Course;
import ro.ubb.domain.News;
import ro.ubb.domain.Person;
import ro.ubb.domain.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tudorstanila on 14/12/2017.
 */
public class NewsRepository {
    private List<News> mockData;

    public NewsRepository() {
        this.mockData = new ArrayList<News>();
        mockData.add(new News(new Teacher("1","Ion","Pop",true,new ArrayList<Course>()),"First news guys"));
    }

    public News insert(Person author, String content){
        News news = new News(author,content);
        this.mockData.add(news);
        System.out.println(news);
        return news;
    }
}
