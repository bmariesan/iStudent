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

public class JSONFile extends MyFile{

    public JSONFile() {
    }

    public JSONFile(List<StudentDto> students) {
        super(students);
    }

    public JSONFile(String filename, List<StudentDto> students) {
        super(filename, students);
    }

    public JSONFile(String filename, List<StudentDto> students, String statisticTitle) {
        super(filename, students, statisticTitle);
    }

    @Override
    public void persist() {
        if (super.getStudents() == null)
            //nothing to persist! -> log smth
            return;

        //create file with that name and required expression
        super.create();

        List<String> lines = new ArrayList<>();

        String json = "{\n\t\"statistic\" : \"" + this.getStatisticTitle() + "\",\n\t\"students\" : ["; //2\t

        for (StudentDto s : students) {

            json += "\n\t\t{\"name\" : \"" + s.getName() + "\",\n\t\t\"gender\" : \"" + s.getGender().toString() +
                    "\",\n\t\t\"age\" : " + String.valueOf(s.getAge()) + ",\n\t\t\"tests\" : [\n"; //tests => 3\t

            for (TestDto t : s.getTests()) {
                json += "\n\t\t\t{\n\t\t\t\t\"course\" : { \n\t\t\t\t\t\"name\" : \"" +t.getCourseDto().getName()
                        + "\",\n\t\t\t\t\t\"minGrade\" : " + String.valueOf(t.getCourseDto().getMinimumGrade()) +
                        "\n\t\t\t\t},\n\t\t\t\t\"grade\" : " + t.getGrade().toString() + "\n\t\t},";
            }

            json = json.substring(0, json.length() - 1);

            json += "\n\t\t],";

            json += "\n\t\t\"country\" : { \n\t\t\t\t\"name\" : \"" +
                    s.getCountryDto().getCountryName() + "\"\n\t\t\t\t}\n\t\t},";

        }

        json = json.substring(0, json.length() - 1);

        json += "\n\t]\n}";

        lines.add(json);

        Path file = Paths.get(this.filename);

        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
