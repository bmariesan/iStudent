package ro.ubb.dummy_data;

import ro.ubb.model.Pair;

import java.util.Date;
import java.util.List;

public interface DummyDataProvider {

    List<Pair<Date, List<Double>>> getDummyData()
            throws Exception;

}
