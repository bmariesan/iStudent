package ro.ubb.istudent.rest;

import com.mongodb.util.JSON;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.istudent.domain.Report;
import ro.ubb.istudent.domain.Statistics;
import ro.ubb.istudent.service.IReportService;
import ro.ubb.istudent.service.ReportService;
import ro.ubb.istudent.util.ResponseUtil;

import java.util.HashMap;

@RequestMapping("/api")
@RestController
public class ReportResource {

    IReportService service =  new ReportService();
    @GetMapping("/report")
    public String getHelloWorldGreeting() {
        Report report = new Report();
        Statistics statistics = new Statistics();
        statistics.setYear(2017);
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Design",150);
        hashMap.put("LFTC",21);
        statistics.setResult(hashMap);
        report.getStatistics().add(statistics);
        return report.ToHTML();
    }

    @RequestMapping("/json")
    public Report getStatisticsJson() {
        Report report = new Report();

        Statistics statistics = new Statistics();
        statistics.setYear(2010);
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Geometrie",30);
        hashMap.put("Algebra",21);
        hashMap.put("LFTC",90);
        hashMap.put("ASC",21);
        statistics.setResult(hashMap);
        report.getStatistics().add(statistics);

        Statistics stats2011 = new Statistics();
        stats2011.setYear(2011);
        HashMap<String, Integer> hash = new HashMap<>();
        hash.put("Geometrie",30);
        hash.put("Algebra",11);
        hash.put("LFTC",22);
        hash.put("ASC",23);
        stats2011.setResult(hash);
        report.getStatistics().add(stats2011);

        Statistics stats2012 = new Statistics();
        stats2012.setYear(2012);
        HashMap<String, Integer> hash2012 = new HashMap<>();
        hash2012.put("Design patterns",80);
        hash2012.put("MPP",50);
        hash2012.put("MAP",88);
        hash2012.put("Web",60);
        stats2012.setResult(hash2012);
        report.getStatistics().add(stats2012);


        Statistics stats2013 = new Statistics();
        stats2013.setYear(2013);
        HashMap<String, Integer> hash2013 = new HashMap<>();
        hash2013.put("Criptografie",90);
        hash2013.put("MPP",25);
        hash2013.put("Geometrie",74);
        hash2013.put("Structuri de date",36);
        stats2013.setResult(hash2013);
        report.getStatistics().add(stats2013);

        Statistics stats2014 = new Statistics();
        stats2014.setYear(2014);
        HashMap<String, Integer> hash2014 = new HashMap<>();
        hash2014.put("Criptografie",90);
        hash2014.put("Mobile",23);
        hash2014.put("LFTC",65);
        hash2014.put("PPD",90);
        stats2014.setResult(hash2014);
        report.getStatistics().add(stats2014);

        Statistics stats2015 = new Statistics();
        stats2015.setYear(2015);
        HashMap<String, Integer> hash2015 = new HashMap<>();
        hash2015.put("Sport",80);
        hash2015.put("PLF",60);
        hash2015.put("Calcul",29);
        hash2015.put("PDAV",80);
        stats2015.setResult(hash2015);
        report.getStatistics().add(stats2015);

        Statistics stats2016 = new Statistics();
        stats2016.setYear(2016);
        HashMap<String, Integer> hash2016 = new HashMap<>();
        hash2016.put("Matematica",90);
        hash2016.put("Romana",88);
        hash2016.put("Geografie",40);
        hash2016.put("Istorie",24);
        stats2016.setResult(hash2016);
        report.getStatistics().add(stats2016);

        Statistics stats2017 = new Statistics();
        stats2017.setYear(2017);
        HashMap<String, Integer> hash2017 = new HashMap<>();
        hash2017.put("Informatica",90);
        hash2017.put("PPD",80);
        hash2017.put("Analiza",60);
        hash2017.put("Matematica",90);
        stats2017.setResult(hash2017);
        report.getStatistics().add(stats2017);

        return report;
    }
}
