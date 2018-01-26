package ro.ubb.istudent.file_utils;


import ro.ubb.istudent.dto.StudentDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class MyFile {
    String filename;
    List<StudentDto> students;
    String statisticTitle;

    public MyFile() {
    }

    public MyFile(List<StudentDto> students) {
        this.students = students;
    }

    public MyFile(String filename, List<StudentDto> students) {
        this.filename = filename;
        this.students = students;
    }

    public MyFile(String filename, List<StudentDto> students, String statisticTitle) {
        this.filename = filename;
        this.students = students;
        this.statisticTitle = statisticTitle;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDto> students) {
        this.students = students;
    }

    public String getStatisticTitle() {
        return statisticTitle;
    }

    public void setStatisticTitle(String statisticTitle) {
        this.statisticTitle = statisticTitle;
    }

    /**
     * Creates the file in memory with the corresponding extension.
     */
    public void create(){

        File f = new File(System.getProperty("user.dir") + "/" + this.getFilename());
        System.out.println(this.getFilename());
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Writes in the file the given student list.
     */
    public abstract void persist();
}
