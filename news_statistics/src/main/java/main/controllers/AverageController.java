package main.controllers;

import main.model.AverageDto;
import main.services.AveragesService;
import main.services.DummyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
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
            throws ParseException {

        return averagesService.getSimpleMovingAverages(dummyDataService.getMockData());

    }

    @GetMapping("ema")
    public TreeSet<AverageDto> getEma()
            throws ParseException {
        return averagesService.getExponentialMovingAverages(dummyDataService.getMockData(),
                dummyDataService.getLastDayOfStats());

    }

}
