package ro.ubb.repository;


import ro.ubb.domain.Course;
import ro.ubb.domain.Person;
import ro.ubb.domain.Student;
import ro.ubb.domain.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tudorstanila on 14/12/2017.
 */
public class PersonRepository  {
    private List<Person> mockData;

    public PersonRepository() {
        this.mockData = new ArrayList<Person>();
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("1","Mate"));
        mockData.add(new Teacher("1","Ion","Pop",true,courses));
        mockData.add(new Student("1","Ana","Pop",false,courses));
        mockData.add(new Student("2","Ioana","Popa",false,new ArrayList<Course>()));
    }

    public Person findById(String id){

        return mockData.stream().filter(x->x.getId().equals(id)).findFirst().get();
    }


}
