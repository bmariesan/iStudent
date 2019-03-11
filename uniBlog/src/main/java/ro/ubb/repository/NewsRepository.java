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
        mockData.add(new News(1,new Teacher("1","Ion","Pop",true,new ArrayList<Course>()),"First news guys"));
        mockData.add(new News(2,new Teacher("2","Florica","Patura",true,new ArrayList<Course>()),"Second news guys"));
    }

    public List<News> getNews(){
        return mockData;
    }


    public News insert(int Id,Person author, String content){
        News news = new News(Id,author,content);
        this.mockData.add(news);
        System.out.println(news);
        return news;
    }

    public Boolean findById(int id){
        News news = mockData.get(id);
        if(news==null)
            return false;
        return true;
    }

    public void delete(int id)throws Exception{
        if(!findById(id))
            throw new Exception();
        this.mockData.remove(id);
    }

    public News update(int id,Person author,String content) throws Exception{
        if(!findById(id))
            throw new Exception();
        News auxNews=mockData.get(id);
        auxNews.setAuthor(author);
        auxNews.setText(content);
        return auxNews;
    }

}
