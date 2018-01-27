package ro.ubb.istudent.file_utils;


import ro.ubb.istudent.dto.StudentDto;
import ro.ubb.istudent.enums.FileEnum;

import java.util.List;
import java.util.Random;

public class FileFactory {

    static int serial = 0;

    String fType;

    public MyFile makeFile(String type, List<StudentDto> students, String fileName, String statisticTitle){

        MyFile newFile = null;

        //String prefix = "~/Desktop/DesignPatterns/FilePersistanceTest2/";

        if (type.equals(FileEnum.CSV.toString())){
            newFile =  new CSVFile();
            fType = type;

            newFile.setStudents(students);
        }

        if (type.equals(FileEnum.JSON.toString())){
            newFile =  new JSONFile();
            fType = type;

            newFile.setStudents(students);
        }

        if (fileName == null){
            newFile.setFilename(fType + String.valueOf(serial)+"." + fType.toLowerCase());
            serial += 1;
        }
        else
            newFile.setFilename(fileName + "." + fType.toLowerCase());

        if (statisticTitle != null)
            newFile.setStatisticTitle(statisticTitle);

        if (!type.equals(FileEnum.JSON.toString()) && !type.equals(FileEnum.CSV.toString()))
            return null;

        return newFile;
    }

    public static void makeAndPersist(List<StudentDto> graduatedStudents, String statistic){
        String ftypes[] = {FileEnum.CSV.toString(), FileEnum.JSON.toString()};

        Random r = new Random();

        FileFactory fileFactory = new FileFactory();
        MyFile persistMe = fileFactory.makeFile(ftypes[r.nextInt(50) % 2],graduatedStudents,
                null,statistic);

        if (persistMe != null)
            persistMe.persist();
    }
}
