package main.services;

import main.model.Pair;
import main.utils.DateFormatterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class DummyDataService {

    @Autowired
    DateFormatterUtil dateFormatterUtil;

    public List<Pair<Date, List<Double>>> getMockData()
            throws ParseException {
        List<Pair<Date, List<Double>>> mockData = new ArrayList<>();
        mockData.add(new Pair<>(dateFormatterUtil.stringToDate("09/09/2017"), Arrays.asList(0.5, 1.5, 2.5, 4.0)));
        mockData.add(new Pair<>(dateFormatterUtil.stringToDate("08/08/2017"), Arrays.asList(1.0, 2.3, 5.0, 4.3)));
        mockData.add(new Pair<>(dateFormatterUtil.stringToDate("09/08/2017"), Arrays.asList(1.0, 2.0, 3.0, 4.0)));

        return mockData;
    }

    public Date getLastDayOfStats()
            throws ParseException {
        return dateFormatterUtil.stringToDate("29/12/2017");
    }

}
