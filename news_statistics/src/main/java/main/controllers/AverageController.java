package main.controllers;

import main.model.AverageDto;
import main.model.Pair;
import main.services.AveragesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class AverageController {
    @Autowired
    private AveragesService averagesService;

    @GetMapping("sma")
    public List<AverageDto> getSMA() {
        List<Pair<Date, List<Double>>> mockData = new ArrayList<>();
        mockData.add(new Pair<>(new Date(), Arrays.asList(1.0, 2.3, 5.0, 4.3)));
        mockData.add(new Pair<>(new Date(), Arrays.asList(1.0, 2.0, 3.0, 4.0)));
        return averagesService.getSimpleMovingAverages(mockData);

    }

}
