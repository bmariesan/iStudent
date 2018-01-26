package ro.ubb.dummy_data;

import ro.ubb.model.Pair;
import ro.ubb.utils.DateFormatterUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class DummyDataReader implements DummyDataProvider {

    @Autowired
    private DateFormatterUtil formatterUtil;

    private List<Pair<Date, List<Double>>> readFromFile(String fileName) {
        List<Pair<Date, List<Double>>> dummyData = new ArrayList<>();
        Path path = Paths.get(fileName);

        try {
            return Files.lines(path).map((line) -> {
                String[] split = line.split(";");
                try {
                    Date date = formatterUtil.stringToDate(split[0]);
                    List<Double> ratings = new ArrayList<>();
                    for (String rating : split[1].split(" ")) {
                        ratings.add(Double.parseDouble(rating));
                    }
                    return new Pair<Date, List<Double>>(date, ratings);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return dummyData;

    }

    @Override
    public List<Pair<Date, List<Double>>> getDummyData()
            throws Exception {
        List<Pair<Date, List<Double>>> pairs = readFromFile(
                "C:\\Faculta\\Design Patterns\\iStudent\\news_statistics\\src\\main\\resources" + "\\DummyData.txt");
        if (pairs != null) {
            return pairs;
        } else {
            throw new Exception("No dummy data available!");
        }
    }
}
