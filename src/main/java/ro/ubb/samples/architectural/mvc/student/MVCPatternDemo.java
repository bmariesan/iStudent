package ro.ubb.samples.architectural.mvc.student;

import ro.ubb.samples.architectural.mvc.student.controller.StudentController;
import ro.ubb.samples.architectural.mvc.student.model.Student;
import ro.ubb.samples.architectural.mvc.student.view.StudentView;

public class MVCPatternDemo {
    public static void main(String[] args) {

        //fetch fromStudent record based on his roll no from the database
        Student model  = retriveStudentFromDatabase();

        //Create a view : to write fromStudent details on console
        StudentView view = new StudentView();

        StudentController controller = new StudentController(model, view);

        controller.updateView();

        //update model data
        controller.setStudentName("John");

        controller.updateView();
    }

    private static Student retriveStudentFromDatabase(){
        Student student = new Student();
        student.setName("Robert");
        student.setRollNo("10");
        return student;
    }
}
