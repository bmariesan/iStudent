package ro.ubb.istudent.file_utils;

import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.dto.TestDto;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVFile extends MyFile{

    public CSVFile() {
    }

    public CSVFile(List<StudentDto> students) {
        super(students);
    }


    public CSVFile(String filename, List<StudentDto> students) {
        super(filename, students);
    }

    public CSVFile(String filename, List<StudentDto> students, String statisticTitle) {
        super(filename, students, statisticTitle);
    }


    @Override
    public void persist() {
        if (super.getStudents() == null)
            //nothing to persist! -> log smth
            return;

        create();

        List<String> lines = new ArrayList<>();

        for (StudentDto s : students) {
            String tests = "";

            for (TestDto t : s.getTests()) {
                tests += t.getCourseDto().getName() + "," + String.valueOf(t.getCourseDto().getMinimumGrade()) + "," +
                        String.valueOf(t.getGrade()) + ",";
            }

            String line = s.getName() + "," + s.getGender().toString() + "," + String.valueOf(s.getAge()) + "," +
                    tests + s.getCountryDto().getCountryName() + "\n";

            lines.add(line);
        }

        Path file = Paths.get(this.filename);

        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
