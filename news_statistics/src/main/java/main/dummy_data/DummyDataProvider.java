package main.dummy_data;

import main.model.Pair;

import java.util.Date;
import java.util.List;

public interface DummyDataProvider {

    List<Pair<Date, List<Double>>> getDummyData()
            throws Exception;

}
