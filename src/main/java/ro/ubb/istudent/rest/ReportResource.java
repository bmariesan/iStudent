package ro.ubb.istudent.rest;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfWriter;
import com.mongodb.util.JSON;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import sun.font.FontFamily;
import sun.misc.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@RequestMapping("/api")
@RestController
public class ReportResource {

    IReportService service =  new ReportService();
    @GetMapping("/report/html")
    public String getHelloWorldGreeting() {
        Report report = new Report();
        report.setTitle("Studenti promovati");

        Statistics statistics = new Statistics();
        statistics.setYear(2017);
        Statistics statistics2 = new Statistics();
        statistics.setYear(2018);

        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Design Patterns",150);
        hashMap.put("LFTC",21);
        statistics.setResult(hashMap);
        report.getStatistics().add(statistics);

        hashMap = new HashMap<>();
        hashMap.put("Design Patterns",88);
        hashMap.put("LFTC",45);
        statistics2.setResult(hashMap);
        report.getStatistics().add(statistics2);

        return report.ToHTML();
    }


    @GetMapping("/report/pdf")
    public ResponseEntity<byte[]> createPDF() {
        Report report = new Report();
        Statistics statistics = new Statistics();
        statistics.setYear(2017);
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Design", 150);
        hashMap.put("LFTC", 21);
        statistics.setResult(hashMap);
        report.getStatistics().add(statistics);
        report.generatePDF();

        Path path = Paths.get("C:/Bogdan/raport.pdf");
        byte[] contents = new byte[0];
        try {
            contents = Files.readAllBytes(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "raport.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);

        return response;
    }




}
