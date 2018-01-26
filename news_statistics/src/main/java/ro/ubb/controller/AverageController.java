package ro.ubb.controller;

import ro.ubb.dummy_data.DummyDataService;
import ro.ubb.model.AverageDto;
import ro.ubb.service.AveragesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.TreeSet;

@RestController
public class AverageController {

    @Autowired
    private AveragesService averagesService;

    @Autowired
    private DummyDataService dummyDataService;

    @GetMapping("sma")
    public List<AverageDto> getSMA()
            throws Exception {

        return averagesService.getSimpleMovingAverages(dummyDataService.getMockData());

    }


    @GetMapping("ema")
    public TreeSet<AverageDto> getEma()
            throws Exception {
        return averagesService.getExponentialMovingAverages(dummyDataService.getMockData(),
                dummyDataService.getLastDayOfStats());

    }

}
