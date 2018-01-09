package main.dummy_data;

import main.model.Pair;
import main.utils.DateFormatterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class DummyDataService {

    @Autowired
    private DateFormatterUtil dateFormatterUtil;

    @Autowired
    private DummyDataProvider dummyDataProvider;

    public List<Pair<Date, List<Double>>> getMockData()
            throws Exception {

        return dummyDataProvider.getDummyData();
    }

    public Date getLastDayOfStats()
            throws ParseException {
        return dateFormatterUtil.stringToDate("29/12/2017");
    }

}
